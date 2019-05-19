package FileStream.DataSet;

import FileStream.FileHelper;
import MapReduce.Map.Filter;
import MapReduce.Map.Grouper;
import MapReduce.Map.Mapper;
import MapReduce.Util.Explain;
import javafx.util.Pair;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SimpleDataSetLoader extends DataSetLoader {
    private Filter filter;
    private Grouper grouper;
    private String pipeline;
    private Mapper currentMapper;
    private String relationPath;
    private String relationName;
    private String delimiter;
    public boolean concatRelationName = true;

    /**
     * @param filter
     * @param grouper
     * @param relations pair of relation and it's delimiter
     */
    public SimpleDataSetLoader(Filter filter, Grouper grouper, String pipeline, Pair<String, String>... relations) {
        super(relations);
        this.relationPath = this.relations.get(0).getKey();
        this.delimiter    = this.relations.get(0).getValue();
        this.filter       = filter;
        this.grouper      = grouper;
        this.pipeline     = pipeline;
    }
    public SimpleDataSetLoader(Filter filter, Grouper grouper, String pipeline, HashMap<String,ArrayDeque<String>> relationName, HashMap<String,String> attrNames, Pair<String, String>... relations) {
        super(relationName,attrNames,relations);
        this.relationPath = this.relations.get(0).getKey();
        this.delimiter    = this.relations.get(0).getValue();
        this.filter       = filter;
        this.grouper      = grouper;
        this.pipeline     = pipeline;
    }

    @Override
    public void load() throws IOException {
        File[] childs = FileHelper.getChildesFiles(this.relationPath);
        Explain.logLoadingRelaton(relationPath);
        relationName = getRelationName(this.relationPath);
        for (File child : childs){
            Explain.logMappingFile(child.getAbsolutePath());
            CSVFileReader reader = new CSVFileReader(child.getAbsolutePath(),delimiter.charAt(0));
            reader.setLoader(this);
            reader.load();
            reader.finish();
            this.currentMapper.finish();
        }

    }

    @Override
    public void addRow(ArrayList<String> row) throws IOException {
        this.currentMapper.addRow(row);
    }

    @Override
    public void addHeaders(ArrayList<String> headers) throws IOException {
        /*ArrayList<String> newHeaders = new ArrayList<>();
        for (String header: headers) {
            String newHeader = this.getAttributeName(header,getRelationName(this.relationPath));
            newHeaders.add(newHeader);
        }*/
        if (concatRelationName){
            ArrayList<String> newHeaders = new ArrayList<>();
            for (String header: headers) {
                String newHeader = this.getAttributeName(header,relationName);
                newHeaders.add(newHeader);
            }
            this.currentMapper = new Mapper(newHeaders,pipeline);
        }
        else
            this.currentMapper = new Mapper(headers,pipeline);

        this.currentMapper.acceptFilter(filter);
        this.currentMapper.acceptGrouper(grouper);
    }

    @Override
    public void finish() throws IOException {
        this.currentMapper.finish();
    }
}
