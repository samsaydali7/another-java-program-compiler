package FileStream.ShuffleResult;

import MapReduce.Reduce.Reducer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class SRLoader {

    private final String PATH;
    private Reducer reducer;

    public SRLoader(String path) {
        this.PATH = path;
    }

    public void load() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(this.PATH);
        ShuffleResultLexer lexer = new ShuffleResultLexer(new ANTLRInputStream(fileInputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShuffleResultParser parser = new ShuffleResultParser(tokens);

        ParseTree tree = parser.file();
        ParseTreeWalker walker = new ParseTreeWalker();
        SRListener listener = new SRListener();
        listener.setReducer(reducer);
        walker.walk(listener,tree);

    }

    public void setReducer(Reducer reducer) {
        this.reducer = reducer;
    }
}
