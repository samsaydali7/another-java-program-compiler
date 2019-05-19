package FileStream.DataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFileReader {
    private int numberOfColumns = 0 ;
    private int[] tempBuffer;
    private char delimiter;
    private BufferedReader reader;
    private ArrayList<String> headers;
    private DataSetLoader loader;

    public CSVFileReader(String fileName, char delimiter) throws IOException {
        this.reader = new BufferedReader(new FileReader(fileName));
        this.delimiter = delimiter;
    }

    public void load() throws IOException {
        String line = reader.readLine();
        this.headers = this.extractHeader(line);
        this.loader.addHeaders(headers);

        while((line = reader.readLine()) != null){
            this.loader.addRow(this.extractRow(line));
        }
    }

    private ArrayList<String> extractRow(String line) throws IOException {

        int[] commas = findCommasInLine(line, tempBuffer);
        ArrayList<String> row = new ArrayList<>();

        row.add(stripQuotes(line.substring(0,commas[0])));
        for(int i = 0; i < numberOfColumns - 1 ; i++){
            row.add(stripQuotes(line.substring(commas[i]+1,commas[i+1])));
        }
        row.add(stripQuotes(line.substring(commas[commas.length-1]+1)));
        return row;
    }

    private ArrayList<String> extractHeader(String line){
        this.countNumberOfColumns(line);
        tempBuffer = new int[numberOfColumns];

        int[] commas = findCommasInLine(line, tempBuffer);
        ArrayList<String> headers = new ArrayList<>();

        headers.add(stripQuotes(line.substring(0,commas[0])));
        for(int i = 0; i < numberOfColumns - 1 ; i++){
            headers.add(stripQuotes(line.substring(commas[i]+1,commas[i+1])));
        }
        headers.add(stripQuotes(line.substring(commas[commas.length-1]+1)));
        return headers;
    }

    private int[] findCommasInLine(String line, int[] nums){
        int counter = 0;
        boolean stopCounting = false;
        for (int index = 0; index < line.length(); index++)
        {
            if (line.charAt(index) == '\'' || line.charAt(index) == '\"')
            {
                stopCounting = !stopCounting;
            }
            if (line.charAt(index) == delimiter && !stopCounting)
            {
                nums[counter++] = index;
            }
        }
        return nums;
    }

    private void countNumberOfColumns(String line){
        boolean stopCounting = false;
        for (int index = 0; index < line.length()-1; index++)
        {
            if (line.charAt(index) == '\'' || line.charAt(index) == '\"')
            {
                stopCounting = !stopCounting;
            }
            if (line.charAt(index) == delimiter  && !stopCounting)
            {
                this.numberOfColumns++;
            }
        }

    }

    public void finish() throws IOException {
        this.reader.close();
        this.loader.finish();
    }

    public void setLoader(DataSetLoader loader){
        this.loader = loader;
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

    private String stripQuotes (String string){
        if (string.charAt(0) == '\"' && string.charAt(string.length() - 1) == '\"'){
            string = string.substring(1,string.length() - 1);
        }
        if (string.charAt(0) == '\'' && string.charAt(string.length() - 1) == '\''){
            string = string.substring(1,string.length() - 1);
        }
        return string.trim();
    }
}
