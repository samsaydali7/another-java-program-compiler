package MapReduce;

import FileStream.FileHelper;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class FileConfig {
    public static final String pipelinesTempRoot = ".\\temp\\pipelines";
    public static final String mapResultDir = "\\map";
    public static final String shuffleResultDir = "\\shuffle";
    public static final String reduceResultDir = "\\reduce";

    public static final String resultFile = "\\result";
    public static final String resultExt = ".txt";

    // Join
    public static final String joinTempRoot = ".\\temp";
    public static final String joinResultDir = "\\join";
    public static final String joinRelationDir = "\\joinRelation";
    public static final String joinResultExt = ".csv";

    // Assemble
    public static final String assembleTempRoot = ".\\temp";
    public static final String assembleResultDir = "\\assemble";
    public static final String assembleRelationDir = "\\assembleRelation";
    public static final String assembleResultExt = ".csv";
    public static int ignoreCount = 0;

    // Resut GUI
    public static final String GUIRoot = ".\\resultGUI";


    public static String getNextPipeline(){
        int number = FileHelper.getNextNumber(pipelinesTempRoot);
        return pipelinesTempRoot + "\\pipeline" + number;
    }
    public static File[] getPiplines(){
        File file = new File(pipelinesTempRoot);
        return file.listFiles();
    }
    // Mapper
    public static File getNextMapResultFile(String pipelinePath){
        int number = FileHelper.getNextNumber(pipelinePath + mapResultDir);
        String filepath =  pipelinePath + mapResultDir + resultFile + number + resultExt;
        return new File(filepath);
    }
    public static String getNextMapResult(String pipelinePath){
        int number = FileHelper.getNextNumber(pipelinePath + mapResultDir);
        return pipelinePath + mapResultDir + resultFile + number + resultExt;
    }
    public static File[] listMapResultsFiles(String pipelinePath){
        File file = new File(pipelinePath + mapResultDir);
        return file.listFiles();
    }
    public static String[] listMapResults(String pipelinePath){
        File file = new File(pipelinePath + mapResultDir);
        return file.list();
    }


    // Shuffler
    public static File getNextShuffleResultFile(String pipelinePath){
        int number = FileHelper.getNextNumber(pipelinePath + shuffleResultDir);
        String filepath =  pipelinePath + shuffleResultDir + resultFile + number + resultExt;
        return new File(filepath);
    }
    public static String getNextShuffleResult(String pipelinePath){
        int number = FileHelper.getNextNumber(pipelinePath + shuffleResultDir);
        return pipelinePath + shuffleResultDir + resultFile + number + resultExt;
    }
    public static File[] listShuffleResultsFiles(String pipelinePath){
        File file = new File(pipelinePath + shuffleResultDir);
        return file.listFiles();
    }
    public static String[] listShuffleResults(String pipelinePath){
        File file = new File(pipelinePath + shuffleResultDir);
        return file.list();
    }

    // Reducer
    public static File getNextReduceResultFile(String pipelinePath){
        int number = FileHelper.getNextNumber(pipelinePath + reduceResultDir);
        String filepath =  pipelinePath + reduceResultDir + resultFile + number + resultExt;
        return new File(filepath);
    }
    public static String getNextReduceResult(String pipelinePath){
        int number = FileHelper.getNextNumber(pipelinePath + reduceResultDir);
        return pipelinePath + reduceResultDir + resultFile + number + resultExt;
    }
    public static File[] listReduceResultsFiles(String pipelinePath){
        File file = new File(pipelinePath + reduceResultDir);
        return file.listFiles();
    }
    public static String[] listReduceResults(String pipelinePath){
        File file = new File(pipelinePath + reduceResultDir);
        return file.list();
    }

    // Join
    public static String getNextJoinRelation(){
        String path = joinTempRoot + joinResultDir;
        int number = FileHelper.getNextNumber(path);
        return path + joinRelationDir + number;
    }
    public static String lastJoinRelation(){
        String path = joinTempRoot + joinResultDir;
        int count = FileHelper.getCount(path);
        return path + joinRelationDir + count;
    }
    public static String getNextJoinRelationFile(String relationPath){
        int number = FileHelper.getNextNumber(relationPath);
        return relationPath + resultFile +  number + joinResultExt;
    }
    public static File[] listJoinRelationFiles(String relationPath){
        File file = new File(relationPath);
        return file.listFiles();
    }

    // Assembler
    public static String[] getAllReduceResults(){
        File[] pipelines  = getPiplines();
        ArrayList<String> list = new ArrayList<>();

        for (File pipeline:pipelines){
            File[] reduces = listReduceResultsFiles(pipeline.getAbsolutePath());
            for (File result : reduces){
                list.add(result.getAbsolutePath());
            }
        }
        String[] myList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            myList[i] = list.get(i);
        }

        return myList;
    }

    public static String getNextAssembleRelation(){
        String path = assembleTempRoot + assembleResultDir;
        int number = FileHelper.getNextNumber(path);
        return path + assembleRelationDir + number;
    }
    public static String lastAssembelRelation(){
        String path = assembleTempRoot + assembleResultDir;
        int count = FileHelper.getCount(path);
        return path + assembleRelationDir + count;
    }
    public static String getNextAssembeRelationFile(String relationPath){
        int number = FileHelper.getNextNumber(relationPath);
        return relationPath + resultFile +  number + assembleResultExt;
    }
    public static File[] listAssembeRelationFiles(String relationPath){
        File file = new File(relationPath);
        return file.listFiles();
    }

    public static void clearSubQuery(){
        File file = new File(pipelinesTempRoot);
        file.renameTo(new File("temp\\Ignore" + ++ignoreCount));
        file = new File(joinTempRoot + joinResultDir);
        file.renameTo(new File("temp\\Ignore" + ++ignoreCount));
        //FileHelper.delete(file);
    }
    // Result GUI
    public static String getResultGUIIndex(){
        return GUIRoot + "\\index.html";
    }
    public static URI getResultGUIIndexURI(){
        File file = new File(GUIRoot.substring(2,GUIRoot.length()) + "\\index.html");
        return file.toURI();
    }
    public static String getResultGUIExplainOutput(){
        return GUIRoot + "\\explain.js";
    }
    public static String getResultGUIResultOutput(){
        return GUIRoot + "\\result.js";
    }
}
