package FileStream.DataSet;

import FileStream.FileHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter {
    private String path;
    private BufferedWriter bufferedWriter;

    public CSVWriter(String path) throws IOException {
        this.path = path;
        if (!FileHelper.exists(path))
            FileHelper.makeFileParent(path);
        this.bufferedWriter = new BufferedWriter(new FileWriter(path,true));
    }

    public void writeHeaders(ArrayList<String> headers) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            stringBuilder.append("\"" + headers.get(i)  + "\"");
            if (i != headers.size() - 1)
                stringBuilder.append(',');
        }
        bufferedWriter.write(stringBuilder.toString());
    }

    public void writeRow(ArrayList<String> row) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < row.size(); i++) {
            stringBuilder.append("\"" + row.get(i) + "\"");
            if (i != row.size() - 1)
                stringBuilder.append(',');
        }
        bufferedWriter.newLine();
        bufferedWriter.write(stringBuilder.toString());
    }

    public void finish() throws IOException {
        this.bufferedWriter.close();
    }
}
