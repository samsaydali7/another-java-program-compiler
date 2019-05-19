package FileStream.ObjectStream;
import java.io.*;

public class ObjectOutputStreamHelper {
    private FileOutputStream fos;
    private ObjectOutputStream os;

    public ObjectOutputStreamHelper(String path) throws IOException {
        if((new File(path)).exists()){
            fos = new FileOutputStream(path,true);
            os = new AppendingObjectOutputStream(fos);
        } else {
            File outFile = new File(path);
            outFile.getParentFile().mkdirs();
            outFile.createNewFile();
            fos = new FileOutputStream(path);
            os = new ObjectOutputStream(fos);
        }

    }

    public void write(Object object) throws IOException {
        os.writeObject(object);
    }

    public void close() throws IOException {
        os.close();
        fos.close();
    }
}
