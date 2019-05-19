package AJPC;

import java.io.File;
import java.io.IOException;

public class Main{

    public static void main(String[] args) {
        AnotherJavaProjectCompiler ajpc = new AnotherJavaProjectCompiler();
        try {
            ajpc.compile();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void tests(){
        try {
            CommandExecutor executor1 = new CommandExecutor("java -version",new File("C:\\"));
            CommandExecutor executor2 = new CommandExecutor("javac -version",new File("C:\\"));
            CommandExecutor executor3 = new CommandExecutor("cmd /c dir",new File("C:\\Program Files"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
