package FileStream.MapResult;

import MapReduce.Shuffle.Shuffler;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class MRLoader {
    private final String PATH;
    private Shuffler shuffler;

    public MRLoader(String path) {
        this.PATH = path;
    }

    public void load() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.PATH);
        MapResultLexer lexer = new MapResultLexer(new ANTLRInputStream(fileInputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MapResultParser parser = new MapResultParser(tokens);

        ParseTree tree = parser.file();
        ParseTreeWalker walker = new ParseTreeWalker();
        MRListener listener = new MRListener();
        listener.setShuffler(shuffler);
        walker.walk(listener,tree);
    }

    public void setShuffler(Shuffler shuffler) {
        this.shuffler = shuffler;
    }
}
