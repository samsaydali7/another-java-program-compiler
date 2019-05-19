package MapReduce.Shuffle;

import FileStream.MapResult.MRLoader;
import FileStream.ResultWriter;
import MapReduce.FileConfig;

import java.io.File;
import java.io.IOException;
import java.util.*;

import MapReduce.Util.Explain;
import javafx.util.Pair;

public class Shuffler {

    private File[] mapResults;
    private String shuffleOutput;
    private ResultWriter resultWriter;
    private ArrayList<String> headers;
    private Map<LinkedHashSet,ArrayList<Object>> result;

    // Constructor
    public Shuffler(String pipeline) throws IOException {
        this.mapResults = FileConfig.listMapResultsFiles(pipeline);
        this.shuffleOutput = FileConfig.getNextShuffleResult(pipeline);
        this.result = new HashMap<>();
        Explain.addShuffler();
    }

    public void receiveHeaders(ArrayList<String> headers){
        this.headers = headers;
    }
    public void receiveLine(Pair<LinkedHashSet,Object> line){
        LinkedHashSet key = line.getKey();
        Object value = line.getValue();
        if(result.containsKey(key))
            this.result.get(key).add(value);
        else{
            ArrayList<Object> valAra = new ArrayList<>();
            valAra.add(value);
            this.result.put(key,valAra);
        }
    }

    public void finish() throws IOException {
        this.resultWriter.close();
    }

    public void shuffle() throws IOException, ClassNotFoundException {
        for (int i = 0; i < this.mapResults.length; i++) {
            String mapResult = this.mapResults[i].getAbsolutePath();
            Explain.logShufflingFile(mapResult);
            MRLoader loader = new MRLoader(mapResult);
            loader.setShuffler(this);
            loader.load();
        }

        this.resultWriter = new ResultWriter(this.shuffleOutput);
        this.resultWriter.setHeaders(this.headers);
        for (Map.Entry entry : this.result.entrySet()) {
            Pair<LinkedHashSet,Object> line = new Pair(entry.getKey(),entry.getValue());
            resultWriter.write(line);
        }

        //this.finish();
    }
}
