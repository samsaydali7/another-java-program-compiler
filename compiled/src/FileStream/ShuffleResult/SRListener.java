package FileStream.ShuffleResult;

import MapReduce.Reduce.Reducer;
import javafx.util.Pair;

import java.util.*;

public class SRListener extends ShuffleResultBaseListener{
    private ArrayList<String> headers;
    private Reducer reducer;

    private LinkedHashSet<String> currentKeys;
    private ArrayList<Object> currentValues;
    private Pair<LinkedHashSet,ArrayList<Object>> line;


    public void setReducer(Reducer reducer) {
        this.reducer = reducer;
    }

    @Override
    public void enterHeader(ShuffleResultParser.HeaderContext ctx) {
        this.headers = new ArrayList<>();
    }

    @Override
    public void exitHeader(ShuffleResultParser.HeaderContext ctx) {
        this.reducer.receiveHeaders(this.headers);
    }

    @Override
    public void exitEntry(ShuffleResultParser.EntryContext ctx) {
        this.headers.add(ctx.TEXT().getText());
    }

    @Override
    public void enterLine(ShuffleResultParser.LineContext ctx) {
        this.currentKeys = new LinkedHashSet<>();
        this.currentValues = new ArrayList<>();
    }

    @Override
    public void exitLine(ShuffleResultParser.LineContext ctx) {
        this.line = new Pair(this.currentKeys,this.currentValues);
        this.reducer.receiveLine(line);
    }

    @Override
    public void exitKey(ShuffleResultParser.KeyContext ctx) {
        this.currentKeys.add(ctx.TEXT().getText());
    }

    @Override
    public void exitValue(ShuffleResultParser.ValueContext ctx) {
        this.currentValues.add(ctx.getText());
    }
}
