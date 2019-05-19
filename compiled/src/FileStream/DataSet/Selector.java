package FileStream.DataSet;

import FileStream.ResultWriter;
import MapReduce.Map.Filter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Selector {
    private Map<String,Integer> headers;
    private ArrayList<String> selectList;
    private ResultWriter writer;
    private Filter filter;

    public Selector(ArrayList<String>  headers, ArrayList<String> selectList, Filter filter) {
        this.headers    = new LinkedHashMap<>();
        this.selectList = selectList;
        this.filter     = filter;

        // Filter Headers
        for (int i = 0; i < headers.size() ; i++)
            if (colSelected(headers.get(i)))
                this.headers.put(getColName(headers.get(i)),i);

    }
    public void addRow(ArrayList<String> row){
        ArrayList<String> selected = new ArrayList<>();
        for (int i = 0; i < row.size(); i++) {

        }
    }
    private boolean colSelected(String col){
        return this.selectList.contains(col);
    }
    private boolean validIndex(int index){
        return this.headers.containsValue(index);
    }
    private String getColName(String col){
        return col;
    }
}
