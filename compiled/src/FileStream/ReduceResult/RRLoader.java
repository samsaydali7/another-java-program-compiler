package FileStream.ReduceResult;

import MapReduce.Assemple.Assembler;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class RRLoader {

    private final String PATH;
    private Assembler assembler;

    public RRLoader(String path) {
        PATH = path;
    }


    public void load() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.PATH);
        ReduceResultLexer lexer = new ReduceResultLexer(new ANTLRInputStream(fileInputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReduceResultParser parser = new ReduceResultParser(tokens);
        // Remove error message
        parser.removeErrorListeners();

        ParseTree tree = parser.file();
        ParseTreeWalker walker = new ParseTreeWalker();
        RRListener listener = new RRListener();
        listener.setAssembler(this.assembler);
        walker.walk(listener,tree);
    }

    public void setAssembler(Assembler assembler) {
        this.assembler = assembler;
    }
}
