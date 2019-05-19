package MapReduce.Map;

import FileStream.ResultWriter;
import MapReduce.FileConfig;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class Mapper implements IFilterable,IGroupable,IProjectable{
    public Map<String,Integer> headers;
    public ResultWriter resultWriter;
    private Filter filter;
    private Grouper grouper;

    // Constructors
    public Mapper(List<String> headers,String pipeline) throws IOException {
        this.headers = new HashMap<>();
        int col = 0;
        for (String header:headers) {
            this.headers.put(header,col);
            col++;
        }
        this.resultWriter = new ResultWriter(FileConfig.getNextMapResult(pipeline),(ArrayList<String>) headers);
    }

    private int getColIndex(String key){
        return this.headers.get(key);
    }

    public String getValue(List<String> row, String key){
        return row.get(this.getColIndex(key));
    }

    public void addRow(ArrayList<String> row) throws IOException {

        if(this.filter.valid(row)){
            Pair<LinkedHashSet,Object> keyValue;
            Object key =  this.grouper.grouberKey(row);
            Object value = this.grouper.grouberValue(row);
            keyValue = new Pair(key,value);
            resultWriter.write(keyValue);
        }
    }

    public void finish() throws IOException {
        this.resultWriter.close();
    }
    @Override
    public Mapper acceptGrouper(Grouper grouper) {
        this.grouper = grouper;
        this.grouper.setMapper(this);
        return this;
    }

    @Override
    public IFilterable acceptFilter(Filter filter) {
        this.filter = filter;
        this.filter.setMapper(this);
        return this;
    }

    @Override
    public IProjectable acceptProjector(Projector projector) {
        Map<String,Integer> headers = new HashMap<>();
        for (String key:this.headers.keySet()) {
            if(projector.valid(key)){
                headers.put(key,this.headers.get(key));
            }
        }
        this.headers = headers;
        return this;
    }
}
