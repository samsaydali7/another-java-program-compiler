package AJPC;

import java.io.*;

class CommandExecutor {
    private String command;
    private File dir;

    CommandExecutor(String command,File dir) throws IOException, InterruptedException {
        this.command = command;
        this.dir = dir;
        this.run();
    }

    private void run() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(command,new String[]{},dir);

        listenErrorStream(proc);
        listenInputStream(proc);
        int exitVal = proc.waitFor();
        System.out.println("Process exitValue: " + exitVal);
    }

    private void listenErrorStream(Process proc) throws IOException {
        InputStream stderr = proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ( (line = br.readLine()) != null)
            System.out.println(line);
    }

    private void listenInputStream(Process process) throws IOException {
        InputStream stderr = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ( (line = br.readLine()) != null)
            System.out.println(line);
    }
}
