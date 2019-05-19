package FileStream.DataSet;

import javafx.util.Pair;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public abstract class DataSetLoader {

    protected ArrayList<Pair<String,String>> relations;
    protected HashMap<String,String> attrNames;
    protected HashMap<String,ArrayDeque<String>> relationNames;

    public DataSetLoader(Pair<String,String>... relations){
        this.relations = new ArrayList<>();
        Collections.addAll(this.relations,relations);
    }
    public DataSetLoader(HashMap<String,ArrayDeque<String>> relationNames,HashMap<String,String> attrNames, Pair<String,String>... relations){
        this.relations = new ArrayList<>();
        Collections.addAll(this.relations,relations);
        this.attrNames     = attrNames;
        this.relationNames = relationNames;
    }
    public abstract void load() throws IOException;
    public abstract void addRow(ArrayList<String> row) throws IOException;
    public abstract void addHeaders(ArrayList<String> headers) throws IOException;

    private boolean hasAttributeName(String attribute){
        return this.attrNames != null && this.attrNames.containsKey(attribute);
    }
    private boolean hasRelationName(String relation){
        return this.relationNames != null && this.relationNames.containsKey(relation) && this.relationNames.get(relation).size() > 0;
    }
    protected String getRelationName(String relationPath){
        String [] parts = relationPath.split(Pattern.quote("\\"));
        String relation =  parts[parts.length - 1].toLowerCase();
        if (this.hasRelationName(relation) && (this instanceof JoiningDataSetLoader))
            return this.relationNames.get(relation).poll();
        if (this.hasRelationName(relation) && (this instanceof SimpleDataSetLoader))
            return this.relationNames.get(relation).peek();
        return relation;
    }
    protected String getAttributeName(String attribute,String relation){
        if (this.hasAttributeName(attribute))
            return this.attrNames.get(attribute);
        if (this.hasAttributeName(relation + "_" + attribute))
            return this.attrNames.get(relation + "_" + attribute);
        return relation + "_" + attribute;
    }
    public abstract void finish() throws IOException;
}
