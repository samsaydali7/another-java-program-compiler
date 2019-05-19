package MapReduce.Reduce;

import FileStream.ResultWriter;
import FileStream.ShuffleResult.SRLoader;
import MapReduce.FileConfig;
import MapReduce.Reduce.Aggrigation.Aggregator;
import MapReduce.Reduce.Aggrigation.IAggregatable;
import MapReduce.Util.Compare.NumericComparator;
import MapReduce.Util.Compare.SortComparator;
import MapReduce.Util.Explain;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Reducer implements IAggregatable {
    Aggregator aggregator;
    private File[] shuffleResults;
    private String reduceOutput;
    private ResultWriter resultWriter;
    ArrayList<String> headers;
    private Map<LinkedHashSet<Object>,Object> result;
    private NumericComparator lineComparator;
    private SortComparator selectListComparator;
    public boolean distict;

    Window window;

    public Reducer(String pipeline,Aggregator aggregator) throws IOException {
        this.aggregator = aggregator;
        this.shuffleResults = FileConfig.listShuffleResultsFiles(pipeline);
        this.reduceOutput = FileConfig.getNextReduceResult(pipeline);
        this.result = new LinkedHashMap<>();
        this.resultWriter = new ResultWriter(this.reduceOutput);
        this.window = null;
        this.distict = false;
        Explain.addReducer();
    }

    public void receiveHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }
    public void receiveLine(Pair<LinkedHashSet,ArrayList<Object>> line){
        if (this.window != null) {receiveLineWindowed(line); return;}
        LinkedHashSet key = line.getKey();
        ArrayList<Object> values = line.getValue();
        if (this.lineComparator != null){
            values.sort(lineComparator);
        }
        if (this.selectListComparator != null) {
            values = breakList(values);
        }
        if (distict) values = distict(values);
        for (Object value:values) {
            this.aggregator.aggregate(value);
        }
        this.result.put(key,this.aggregator.getResult());
        this.aggregator.clean();
    }
    private void receiveLineWindowed(Pair<LinkedHashSet,ArrayList<Object>> line){
        LinkedHashSet key = line.getKey();
        ArrayList<Object> values = line.getValue();
        if (this.lineComparator != null){
            values.sort(lineComparator);
        }
        if (this.selectListComparator != null) {
            values = breakList(values);
        }
        ArrayList<ArrayList<Object>> partitions = this.window.partition(values);
        int lineNumber = 0;
        for (ArrayList partition : partitions){
            for (Object value:partition) {
                this.aggregator.aggregate(value);
            }
            LinkedHashSet newKey = new LinkedHashSet();
            newKey.addAll(key);
            newKey.add(lineNumber);
            this.result.put(newKey,this.aggregator.getResult());
            this.aggregator.clean();
            lineNumber++;
        }
    }

    public void reduce() throws IOException, ClassNotFoundException {
        for (File file : this.shuffleResults){
            Explain.logReducingFile(file.getAbsolutePath());
            SRLoader loader = new SRLoader(file.getAbsolutePath());
            loader.setReducer(this);
            loader.load();
        }
        this.resultWriter.setHeaders(headers);
        for (Map.Entry<LinkedHashSet<Object>,Object> entry : this.result.entrySet()){
            Pair line = new Pair<LinkedHashSet<Object>,Object>(entry.getKey(),entry.getValue());
            resultWriter.write(line);
        }
    }

    public void finish() throws IOException {
        this.resultWriter.close();
    }

    @Override
    public Reducer acceptAggregator(Aggregator aggrigator) {
        this.aggregator = aggrigator;
        return this;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setLineComparator(NumericComparator lineComparator) {
        this.lineComparator = lineComparator;
    }

    public void setSelectListComparator(SortComparator selectListComparator) {
        this.selectListComparator = selectListComparator;
    }

    public ArrayList<Object> breakList(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> broken = new ArrayList<>();
        for (Object o : values){
            ArrayList<Object> list = new ArrayList<>();
            String listStr = o.toString();
            listStr = listStr.substring(1,listStr.length()-1);
            String[] slitted =  listStr.split(Pattern.quote(", "));
            Collections.addAll(list, slitted);
            broken.add(list);
        }
        broken.sort(selectListComparator);
        ArrayList returned = new ArrayList();
        returned.addAll(broken);
        return returned;
    }

    private ArrayList<Object> distict(ArrayList<Object> list){
        list.sort(new NumericComparator());
        ArrayList<Object> distictList = new ArrayList<>();
        int i = 0 ,j = 0;
        while (i < list.size()){
            Object o = list.get(i);
            distictList.add(o);
            while (j < list.size()){
                if (list.get(i).equals(list.get(j)))
                    j++;
                else break;
            }
            i = j;
        }
        return distictList;
    }
}
