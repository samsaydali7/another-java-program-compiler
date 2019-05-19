package AJPC;

import java.io.File;

public class Config {
    private static String prodRoot = "compiled";
    private static String src = "\\src";
    private static String dist = "\\classes";

    public static File getSrcPath(){
        return new File(prodRoot + src);
    }

    public static File getDistPath(){
        return new File(prodRoot + dist);
    }
}
