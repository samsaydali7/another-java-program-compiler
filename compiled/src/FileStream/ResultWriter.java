package FileStream;

import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ResultWriter {
    private final String PATH;
    private BufferedWriter bw;
    private ArrayList<String> headers;

    public ResultWriter(String path, ArrayList<String> headers) throws IOException {
        PATH = path;
        this.headers = headers;
        if (!FileHelper.exists(PATH))
            FileHelper.makeFileParent(PATH);
        this.bw = new BufferedWriter(new FileWriter(PATH,true));
        bw.write(headers.toString());
        bw.newLine();
    }
    public ResultWriter(String path) throws IOException {
        PATH = path;
        if (!FileHelper.exists(PATH))
            FileHelper.makeFileParent(PATH);
        this.bw = new BufferedWriter(new FileWriter(PATH,true));
    }

    public void write(Pair<LinkedHashSet,Object> line) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(line.getKey());
        sb.append(":");
        sb.append(line.getValue());
        sb.append(">");
        this.bw.write(sb.toString());
        bw.newLine();
    }

    public void close() throws IOException {
        this.bw.close();
    }

    public void setHeaders(ArrayList<String> headers) throws IOException {
        this.headers = headers;
        bw.write(headers.toString());
        bw.newLine();
    }
}
