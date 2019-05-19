package FileStream;

import java.io.File;

public class FileHelper {
    public static String[] getChildes(String path){
        File file = new File(path);
        return file.list();
    }
    public static File[] getChildesFiles(String path){
        File file = new File(path);
        return file.listFiles();
    }
    public static int getNextNumber(String path){
        return getCount(path) + 1;
    }
    public static int getCount(String path){
        File file = new File(path);
        if(file.exists() || file.isDirectory())
            return file.list().length;
        else
            return 0;
    }
    public static void makeDir(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    public static void makeFileParent(String path){
        File file = new File(path);
        if(!file.exists()){
            String parent = file.getParent();
            makeDir(parent);
        }
    }
    public static boolean exists(String path){
        File file = new File(path);
        return file.exists();
    }
    public static void delete(String path){
        File file = new File(path);
        delete(file);
    }
    public static void delete(File file){
        if (!file.exists()){
            return;
        }
        if(file.isDirectory()){
            for (int i = 0; i < file.listFiles().length; i++) {
                delete(file.listFiles()[i]);
            }
            file.delete();
        }else{

            file.delete();
        }
    }
    public static void deleteTemp(){
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");

        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
        FileHelper.delete(".\\temp");
    }
}
