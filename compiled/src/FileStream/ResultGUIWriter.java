package FileStream;

import MapReduce.FileConfig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ResultGUIWriter {
    private PrintStream stream;

    public ResultGUIWriter() throws FileNotFoundException {
        this.stream = new PrintStream(new FileOutputStream(FileConfig.getResultGUIResultOutput(),false));
    }

    public void addHeaders(ArrayList<String> headers){
        stream.println("headers = [");
        stream.print("\t");
        for (String header:headers)
            stream.print("\"" + header + "\",");
        stream.println();
        stream.println("]");
        stream.println("rows = [");
    }

    public void addRow(ArrayList<String> row){
        stream.println("\t[");
        stream.print("\t\t");
        for (String col:row)
            stream.print("\"" + col + "\",");
        stream.println();
        stream.println("\t],");

    }

    public void close(){
        stream.println("]");
        stream.close();
    }
}
