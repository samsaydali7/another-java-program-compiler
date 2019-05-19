package FileStream.ReduceResult;

import MapReduce.Assemple.Assembler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class RRListener extends ReduceResultBaseListener {
    private LinkedHashSet currentKeys;
    private Object currentObjectKey;
    private Object currentObjectValue;
    private LinkedHashMap<Object,Object> currentObject;
    private ArrayList<ArrayList<Object>> currentArray;
    private ArrayList<Object> currentArrayElement;
    private Object currentSimple;

    private Assembler assembler;
    @Override
    public void enterKeys(ReduceResultParser.KeysContext ctx) {
        this.currentKeys = new LinkedHashSet();
    }

    @Override
    public void exitKey(ReduceResultParser.KeyContext ctx) {
        this.currentKeys.add(ctx.getText());
    }

    @Override
    public void exitSimple(ReduceResultParser.SimpleContext ctx) {
        this.currentSimple = ctx.getText();
        this.assembler.addSimpleReduceResult(currentKeys,currentSimple);
    }

    @Override
    public void enterObject(ReduceResultParser.ObjectContext ctx) {
        this.currentObject = new LinkedHashMap<>();
    }

    @Override
    public void exitObjectKey(ReduceResultParser.ObjectKeyContext ctx) {
        this.currentObjectKey = ctx.getText();
    }

    @Override
    public void exitObjectValue(ReduceResultParser.ObjectValueContext ctx) {
        this.currentObjectValue = ctx.getText();
    }

    @Override
    public void exitObjectEntry(ReduceResultParser.ObjectEntryContext ctx) {
        this.currentObject.put(currentObjectKey,currentObjectValue);
    }

    @Override
    public void exitObject(ReduceResultParser.ObjectContext ctx) {
        this.assembler.addComplexReduceResult(this.currentKeys,this.currentObject);
    }

    @Override
    public void enterArray(ReduceResultParser.ArrayContext ctx) {
        this.currentArray = new ArrayList<>();
    }

    @Override
    public void exitArray(ReduceResultParser.ArrayContext ctx) {
        this.assembler.addSelectList(this.currentKeys,this.currentArray);
    }

    @Override
    public void enterArrayElemnt(ReduceResultParser.ArrayElemntContext ctx) {
        this.currentArrayElement = new ArrayList<>();
    }

    @Override
    public void exitArrayElemnt(ReduceResultParser.ArrayElemntContext ctx) {
        this.currentArray.add(this.currentArrayElement);
    }

    @Override
    public void exitElement(ReduceResultParser.ElementContext ctx) {
        this.currentArrayElement.add(ctx.getText());
    }

    public void setAssembler(Assembler assembler) {
        this.assembler = assembler;
    }
}
