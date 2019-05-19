package FileStream.MapResult;

import MapReduce.Shuffle.Shuffler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MRListener extends MapResultBaseListener {
    private ArrayList<String> headers;

    private LinkedHashSet<String> currentKeys;
    private Object currentValue;
    private Pair<LinkedHashSet,Object> currrentLine;
    private Shuffler shuffler;

    public void setShuffler(Shuffler shuffler) {
        this.shuffler = shuffler;
    }

    @Override
    public void enterHeader(MapResultParser.HeaderContext ctx) {
        this.headers = new ArrayList<>();
    }

    @Override
    public void exitHeader(MapResultParser.HeaderContext ctx) {
        this.shuffler.receiveHeaders(headers);
    }

    @Override
    public void exitEntry(MapResultParser.EntryContext ctx) {
        this.headers.add(ctx.TEXT().getText());
    }

    @Override
    public void enterLine(MapResultParser.LineContext ctx) {
        this.currentKeys = new LinkedHashSet<>();
    }

    @Override
    public void exitLine(MapResultParser.LineContext ctx) {
        this.currrentLine = new Pair<>(this.currentKeys,this.currentValue);
        this.shuffler.receiveLine(currrentLine);
    }

    @Override
    public void enterKey(MapResultParser.KeyContext ctx) {
        this.currentKeys.add(ctx.TEXT().getText());
    }

    @Override
    public void exitValue(MapResultParser.ValueContext ctx) {
        this.currentValue = ctx.getText();
    }
}
