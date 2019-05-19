package MapReduce.Assemple;

import FileStream.DataSet.CSVWriter;
import FileStream.ReduceResult.RRLoader;
import FileStream.ResultGUIWriter;
import MapReduce.Util.Compare.BaseComparator;
import MapReduce.Util.Compare.DistinctComparator;
import MapReduce.Util.Compare.DumpComparator;
import MapReduce.Util.Compare.SortComparator;
import MapReduce.FileConfig;
import MapReduce.Util.Explain;

import java.io.IOException;
import java.util.*;

public class Assembler {
    private LinkedHashMap<LinkedHashSet<Object>,ArrayList<ArrayList<Object>>> keysRows;
    private ArrayList<String> headers;
    public boolean groupingFlag;
    private ArrayList<ArrayList<Object>> resultList;
    private BaseComparator sortComparator;
    private CSVWriter writer;
    private ResultGUIWriter guiWriter;

    public Assembler() throws IOException {
        this.keysRows = new LinkedHashMap<>();
        this.headers = new ArrayList<String>();
        this.sortComparator = new DumpComparator();
        String relation = FileConfig.getNextAssembleRelation();
        this.writer = new CSVWriter(FileConfig.getNextAssembeRelationFile(relation));
        guiWriter = new ResultGUIWriter();
    }
    public Assembler(ArrayList<String> headers) throws IOException {
        this.keysRows = new LinkedHashMap<>();
        this.headers = headers;
        this.sortComparator = new DumpComparator();
        String relation = FileConfig.getNextAssembleRelation();
        this.writer = new CSVWriter(FileConfig.getNextAssembeRelationFile(relation));
        Explain.logAssemblingBegan();
        guiWriter = new ResultGUIWriter();
    }

    public abstract class Filter {

        public abstract boolean valid(ArrayList<Object> row);

        protected double getDouble(ArrayList<Object> row, String key){
            return Double.parseDouble(this.getValue(row,key).toString());
        }

        protected Object getValue(ArrayList<Object> row, String key){
            return row.get(getKeyIndex(key));
        }

        private int getKeyIndex(String key){
            return headers.indexOf(key);
        }
    }

    public void assemble() throws IOException {
        String[] list = FileConfig.getAllReduceResults();
        for (String path:list){
            RRLoader loader = new RRLoader(path);
            Explain.logAssemblingFile(path);
            loader.setAssembler(this);
            loader.load();
        }
        if (groupingFlag)
            this.groupByExtract();
        else
            this.extract();
    }


    public void addNormal(LinkedHashSet<Object> keys,ArrayList<Object> values){
        if (this.keysRows.containsKey(keys)){
            ArrayList<ArrayList<Object>> rows = this.keysRows.get(keys);
            for (int i = 0; i < rows.size() ; i++) {
                rows.get(i).add(values.get(i));
            }
        }else
        {
            ArrayList<ArrayList<Object>> rows = new ArrayList<>();
            for (Object value : values) {
                ArrayList<Object> list = new ArrayList<>();
                list.add(value);
                rows.add(list);
            }
            this.keysRows.put(keys,rows);
        }

    }
    public void addSelectList(LinkedHashSet<Object> keys,ArrayList<ArrayList<Object>> values){
        if (this.keysRows.containsKey(keys)){
            ArrayList<ArrayList<Object>> rows = this.keysRows.get(keys);
            for(ArrayList row : rows){
                for (ArrayList value : values)
                    row.addAll(value);
            }
        }else
        {
            this.keysRows.put(keys,values);
        }

    }
    public void addSimpleReduceResult(LinkedHashSet<Object> keys,Object value){
        if (this.keysRows.containsKey(keys)){
            ArrayList<ArrayList<Object>> rows = this.keysRows.get(keys);
            for (ArrayList row : rows){
                row.add(value);
            }
        }else{
            ArrayList<ArrayList<Object>> rows = new ArrayList<>();
            ArrayList<Object> list = new ArrayList<>();
            list.add(value);
            rows.add(list);
            this.keysRows.put(keys,rows);
        }
    }

    public void addComplexReduceResult(LinkedHashSet<Object> keys,Map<Object,Object> value){
        if (this.keysRows.containsKey(keys)){
            ArrayList<ArrayList<Object>> rows = this.keysRows.get(keys);
            for (ArrayList row : rows){
                for (Map.Entry entry : value.entrySet()){
                    row.add(entry.getValue());
                }
            }
        }else{
            ArrayList<ArrayList<Object>> rows = new ArrayList<>();
            ArrayList<Object> list = new ArrayList<>();
            for (Map.Entry entry : value.entrySet()){
                list.add(entry.getValue());
            }
            rows.add(list);
            this.keysRows.put(keys,rows);
        }
    }

    private void tailHeaders(List<Object> newHeaders){

    }

    public void print(){
        //System.out.println(keysRows);
        /*for (Object key : keysRows.keySet()){
            ArrayList<ArrayList<Object>> superlist = this.keysRows.get(key);
            for (ArrayList sublist : superlist){
                System.out.println(sublist);
            }
        }*/
        System.out.println(resultList);
    }

    public void write() throws IOException {
        this.writer.writeHeaders(this.headers);
        this.guiWriter.addHeaders(this.headers);
        for (ArrayList row : this.resultList){
            this.writer.writeRow(row);
            this.guiWriter.addRow(row);
        }
        this.writer.finish();
        this.guiWriter.close();
    }

    private void groupByExtract(){
        ArrayList<ArrayList<Object>> myList = new ArrayList<>();
        for (Object key : keysRows.keySet()){
            ArrayList<ArrayList<Object>> superlist = this.keysRows.get(key);
            myList.add(superlist.get(0));
        }
        this.resultList = myList;
    }

    private void extract(){
        ArrayList<ArrayList<Object>> myList = new ArrayList<>();
        for (Object key : keysRows.keySet()){
            ArrayList<ArrayList<Object>> superlist = this.keysRows.get(key);
            myList.addAll(superlist);
        }
        this.resultList = myList;
    }

    public void distinct(){
        Explain.logDistinguishInAssembler();
        this.resultList.sort(new DistinctComparator());
        ArrayList<ArrayList<Object>> newResultList = new ArrayList<>();
        ArrayList<Object> last;
        int i = 0,j = 0;
        while (i < resultList.size()){
            last = resultList.get(i);
            newResultList.add(last);
            while (j < resultList.size()){
                if (new DistinctComparator().compare(resultList.get(j),last) == 0)
                    j++;
                else
                    break;
            }
            i = j;
        }
        this.resultList = newResultList;
    }

    public void orderBy(String column, SortComparator.SortType type,boolean numeric){
        int index = this.headers.indexOf(column);
        if (this.sortComparator instanceof DumpComparator)
            this.sortComparator = new SortComparator(type,index,numeric);
        else
            ((SortComparator) this.sortComparator).thenUse(new SortComparator(type,index,numeric));
    }
    public void orderBy(SortComparator sortComparator){
        if (this.sortComparator instanceof DumpComparator)
            this.sortComparator = sortComparator;
        else
            ((SortComparator) this.sortComparator).thenUse(sortComparator);
    }

    public void sort(){
        Explain.logSortingInAssembler();
        this.resultList.sort(this.sortComparator);
    }

    public void filter(Filter filter){
        ArrayList<ArrayList<Object>> newList = new ArrayList<>();
        for (ArrayList<Object> row : this.resultList){
            if (filter.valid(row))
                newList.add(row);
        }
        this.resultList = newList;
    }
}
