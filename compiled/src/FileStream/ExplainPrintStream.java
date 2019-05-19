package FileStream;

import java.io.OutputStream;
import java.io.PrintStream;

public class ExplainPrintStream extends PrintStream{

    public ExplainPrintStream(OutputStream out) {
        super(out,false);
        print("explain_output = `");
    }

    @Override
    public void close() {
        print("`;");
        //super.close();
    }
}
