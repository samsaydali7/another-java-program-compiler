package MapReduce.Util;

import FileStream.ExplainPrintStream;
import MapReduce.FileConfig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class Explain {

    private static PrintStream stream;
    private static boolean use;
    private static int mappersCount,shufflersCount,reducersCount,assemblersCount;
    static {
        try {
            stream = new ExplainPrintStream(new FileOutputStream(FileConfig.getResultGUIExplainOutput()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        use = false;
    }
    public static void use(boolean isUse){
        use = isUse;
        if (!use){
            log("Explain was not activated");
        }
    }
    public static void log(String line){
            stream.println(line);
    }
    public static void addLine(){
        if (use)
            stream.println();
    }
    public static void logRelationJoin(String relation1,String relation2){
        if (use){
            addLine();
            log("Joining " + fixPath(relation1) + " with " + fixPath(relation2));
        }
    }
    public static void logFilesJoin(String file1,String file2){
        if (use)
            log("\tCrossing " + fixPath(file1) + " with " + fixPath(file2));
    }
    public static void logLoadingRelaton(String relation){
        if (use){
            addLine();
            log("Loading relation : " + fixPath(relation));
        }

    }
    public static void logMappingFile(String file){
        if (use){
            mappersCount++;
            log("\tMapping " + fixPath(file));
        }

    }
    public static void addShuffler(){
        if (use) shufflersCount++;
    }
    public static void logShufflingFile(String file){
        if (use)
            log("\tShuffling " + fixPath(file));
    }
    public static void addReducer(){
        if (use)
            reducersCount++;
    }
    public static void logReducingFile(String file){
        if (use)
            log("\tReducing " + fixPath(file));
    }
    public static void logAssemblingBegan(){
        if (use){
            addLine();
            assemblersCount++;
            log("Assembler began");
        }

    }
    public static void logAssemblingFile(String file){
        if (use)
            log("\tAssembling " + fixPath(file));
    }
    public static void logSortingInAssembler(){
        if (use)
            log("\tAssembler sort");
    }
    public static void logDistinguishInAssembler(){
        if (use)
            log("\tAssembler selecting distinct rows");
    }

    public static void getStatistics(){
        if (use){
            addLine();
            log(mappersCount + " mappers, " + shufflersCount + " shufflers, " + reducersCount + " reducers, " + assemblersCount + " assemblers");
        }else {
            log("Explain was not activated");
        }
        stream.close();
    }
    private static String fixPath(String path){
        path = path.replaceAll(Pattern.quote("\\"),"/");
        return path;
    }
}
