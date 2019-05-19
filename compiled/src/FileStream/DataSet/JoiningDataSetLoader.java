package FileStream.DataSet;

import FileStream.FileHelper;
import MapReduce.FileConfig;
import MapReduce.Util.Explain;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JoiningDataSetLoader extends DataSetLoader{

    public abstract class JoinFilter{
        public abstract boolean valid(ArrayList<String> row1,ArrayList<String> row2);
        public ArrayList<String> concat(ArrayList<String> row1,ArrayList<String> row2){
            ArrayList<String> row = new ArrayList<>();
            row.addAll(row1);
            row.addAll(row2);
            return row;
        }
        public String getValue1(ArrayList<String> row,String key){
            return row.get(headers.indexOf(key));
        }
        public String getValue2(ArrayList<String> row,String key){
            int gab = headers.size() - row.size();
            return row.get(headers.indexOf(key) - gab);
        }
        public boolean equals(ArrayList<String> row1,String key1,ArrayList<String> row2,String key2){
            return getValue1(row1,key1).equals(getValue2(row2,key2));
        }
    }
    public enum JoinType {INNER,LEFT,RIGHT,FULL_OUTER,SELF}


    private boolean firstCross;
    private boolean addRelationNameToHeaders;
    private boolean crossing;
    private ArrayList<String> headers;
    private ArrayList<String> firstRelationHeaders;
    private ArrayList<String> secondRelationHeaders;

    private ArrayList<ArrayList<String>> rows;
    private ArrayList<ArrayList<String>> resultRows;
    private String currentOutputRelation;
    private ArrayDeque<JoinFilter> filters;
    JoinFilter currentFilter;
    private ArrayDeque<JoinType> joinTypes;
    JoinType currentType;

    private boolean concatSecondRelationHeaders;
    private String currentInputRelation1;
    private String currentInputRelation2;
    private String currentInputRelation2File;
    private char currentInputRelation2Del;


    // For left join
    private HashMap<ArrayList<String>,Boolean> lacksRight;
    // For right join
    private HashMap<ArrayList<String>,Boolean> lacksLeft;
    // Both for full outer



    /**
     * @param relations pair of relation and it's delimiter
     */
    public JoiningDataSetLoader(Pair<String, String>... relations) {
        super(relations);
        this.firstCross = true;
        this.headers    = new ArrayList<>();
        this.rows       = new ArrayList<>();
        this.resultRows = new ArrayList<>();
    }

    public JoiningDataSetLoader(HashMap<String,ArrayDeque<String>> relationNames,HashMap<String,String> attrNames, Pair<String, String>... relations) {
        super(relationNames,attrNames,relations);
        this.firstCross = true;
        this.headers    = new ArrayList<>();
        this.rows       = new ArrayList<>();
        this.resultRows = new ArrayList<>();
    }

    public void addFilters(JoinFilter... filters){
        this.filters = new ArrayDeque<>();
        Collections.addAll(this.filters,filters);
    }
    public void addTypes(JoinType... types){
        this.joinTypes = new ArrayDeque<>();
        Collections.addAll(this.joinTypes,types);
    }

    @Override
    public void load() throws IOException {
        String relation1  = this.relations.get(0).getKey();
        String delimiter1 = this.relations.get(0).getValue();
        String relation2  = this.relations.get(1).getKey();
        String delimiter2 = this.relations.get(1).getValue();
        currentFilter = filters.poll();
        currentType = joinTypes.poll();
        this.initMatchingRecords();
        this.cross(relation1,delimiter1,relation2,delimiter2);
        this.firstCross = false;
        this.flushMatchingRecords();

        for (int i = 2; i < this.relations.size(); i++) {
            currentFilter = filters.poll();
            currentType = joinTypes.poll();
            relation1 = FileConfig.lastJoinRelation();
            delimiter1 = ",";
            relation2  = this.relations.get(i).getKey();
            delimiter2 = this.relations.get(i).getValue();
            this.cross(relation1,delimiter1,relation2,delimiter2);
            this.flushMatchingRecords();
        }
    }

    @Override
    public void addRow(ArrayList<String> row) throws IOException {
        if (!crossing){
            this.rows.add(row);
            this.lacksRight(row);
            loadSecondRelationFile();
            return;
        }
        ArrayList lastRow = rows.get(rows.size()-1);
        if (currentFilter.valid(lastRow,row)){
            this.hasMatchingLeft(row);
            this.hasMatchingRight(lastRow);
            ArrayList<String> line = new ArrayList<>();
            line.addAll(lastRow);
            line.addAll(row);
            resultRows.add(line);
        } else {
            this.lacksLeft(row);
        }
    }

    @Override
    public void addHeaders(ArrayList<String> headers) throws IOException {
        if (addRelationNameToHeaders)
                if (!crossing){
                    for (String header : headers)
                        this.headers.add(getAttributeName(header, currentInputRelation1));
                    firstRelationHeaders.clear();
                    firstRelationHeaders.addAll(this.headers);
                }

                else{
                    if (concatSecondRelationHeaders){
                        secondRelationHeaders.clear();
                        for (String header : headers){
                            this.headers.add(getAttributeName(header, currentInputRelation2));
                            secondRelationHeaders.add(getAttributeName(header, currentInputRelation2));
                        }
                        concatSecondRelationHeaders = false;
                    }
                }
        else
            if (!crossing){
                this.headers.addAll(headers);
                firstRelationHeaders.clear();
                this.firstRelationHeaders.addAll(headers);
            }
            else if (concatSecondRelationHeaders){
                this.headers.addAll(headers);
                secondRelationHeaders.clear();
                this.secondRelationHeaders.addAll(headers);
                concatSecondRelationHeaders = false;
            }
    }

    @Override
    public void finish() throws IOException {

    }

    private void cross(String relation1,String delimiter1,String relation2,String delimiter2) throws IOException {
        this.currentOutputRelation = FileConfig.getNextJoinRelation();
        Explain.logRelationJoin(relation1,relation2);
        this.currentInputRelation1 = getRelationName(relation1);
        this.currentInputRelation2 = getRelationName(relation2);
        File[] childs1 = FileHelper.getChildesFiles(relation1);
        File[] childs2 = FileHelper.getChildesFiles(relation2);

        for (File file1 : childs1)
            for (File file2 : childs2){
            Explain.logFilesJoin(file1.getAbsolutePath(),file2.getAbsolutePath());
                this.concatSecondRelationHeaders = true;
                crossFiles(relation1,file1.getAbsolutePath(),delimiter1,relation2,file2.getAbsolutePath(),delimiter2);
                this.writeResults();
            }

    }
    private void crossFiles(String relation1,String file1,String delimiter1,String relation2,String file2,String delimiter2) throws IOException {
        CSVFileReader reader;

        this.currentInputRelation2Del = delimiter2.charAt(0);
        this.currentInputRelation2File = file2;

        this.addRelationNameToHeaders = this.firstCross;
        this.crossing                 = false;
        reader                        = new CSVFileReader(file1,delimiter1.charAt(0));
        reader.setLoader(this);
        reader.load();
        reader.finish();
    }

    private void loadSecondRelationFile() throws IOException {
        this.addRelationNameToHeaders = true;
        this.crossing = true;
        CSVFileReader reader = new CSVFileReader(currentInputRelation2File,currentInputRelation2Del);
        reader.setLoader(this);
        reader.load();
        reader.finish();
        crossing = false;
    }

    private void writeResults() throws IOException {
        String currentOutputRelationFile = FileConfig.getNextJoinRelationFile(currentOutputRelation);
        CSVWriter writer = new CSVWriter(currentOutputRelationFile);
        writer.writeHeaders(this.headers);
        for (ArrayList row : this.resultRows) {
            writer.writeRow(row);
        }
        writer.finish();
        this.flush();
    }

    private void flush(){
        this.headers.clear();
        this.resultRows.clear();
        this.rows.clear();
    }

    private void writeMatchingsRecord() throws IOException {
        String currentOutputRelationFile = FileConfig.getNextJoinRelationFile(currentOutputRelation);
        CSVWriter writer = new CSVWriter(currentOutputRelationFile);
        ArrayList<String> headers = new ArrayList<>();
        headers.addAll(firstRelationHeaders);
        headers.addAll(secondRelationHeaders);
        writer.writeHeaders(headers);
        if (currentType == JoinType.LEFT || currentType == JoinType.FULL_OUTER){
            for (ArrayList<String> rightLaked:lacksRight.keySet()){
                if (lacksRight.get(rightLaked)){
                    for (int i = 0; i <secondRelationHeaders.size() ; i++) {
                        rightLaked.add("NULL");
                    }
                    writer.writeRow(rightLaked);
                }
            }
        }
        if (currentType == JoinType.RIGHT || currentType == JoinType.FULL_OUTER){
            for (ArrayList<String> leftLacked:lacksLeft.keySet()){
                if (lacksLeft.get(leftLacked)){
                    ArrayList<String> nulls = new ArrayList<>();
                    for (int i = 0; i < firstRelationHeaders.size() ; i++) {
                        nulls.add("NULL");
                    }
                    nulls.addAll(leftLacked);
                    writer.writeRow(nulls);
                }
            }
        }
        writer.finish();
    }
    private void initMatchingRecords(){
        this.lacksLeft = new HashMap<>();
        this.lacksRight = new HashMap<>();
        this.firstRelationHeaders = new ArrayList<>();
        this.secondRelationHeaders = new ArrayList<>();
    }
    private void flushMatchingRecords() throws IOException {
        this.writeMatchingsRecord();
        this.firstRelationHeaders.clear();
        this.secondRelationHeaders.clear();
        this.lacksLeft.clear();
        this.lacksRight.clear();
    }
    private void hasMatchingRight(ArrayList<String> row){
        lacksRight.put(row,false);
    }
    private void hasMatchingLeft(ArrayList<String> row){
        lacksLeft.put(row,false);
    }
    private void lacksRight(ArrayList<String> row){
        if (!this.lacksRight.containsKey(row))
            this.lacksRight.put(row,true);
    }
    private void lacksLeft(ArrayList<String> row){
        if (!this.lacksLeft.containsKey(row))
            this.lacksLeft.put(row,true);
    }
}
