package AJPC;

import java.io.File;
import java.io.IOException;

public class AnotherJavaProjectCompiler {
    public void compile() throws IOException, InterruptedException {
        // cd to src folder of input java files
        File src = Config.getSrcPath();

        // put all java files names in sources.txt
        String command1 = "cmd /c dir /s /B *.java > sources.txt";
        CommandExecutor executor1 = new CommandExecutor(command1,src);


        // Compile all java files in sources.txt to classes
        String command2 = "javac -cp \"../lib/antlr.jar\" -d ../classes @sources.txt";
        CommandExecutor executor2 = new CommandExecutor(command2,src);

        // cd to dist folder for compiled files
        File dist = Config.getDistPath();

        // Make excitable jar
        String command3 = "jar -cvmf manifest.txt Project1.jar *";
        ProcessBuilder processBuilder = new ProcessBuilder("jar","-cvmf","manifest.txt","Project1.jar","*");
        processBuilder.directory(dist);
        processBuilder.inheritIO().start();

    }
}
