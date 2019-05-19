package FileStream.ObjectStream;
import java.io.*;
public class ObjectInputStreamHelper {
    private FileInputStream fis;
    private ObjectInputStream is;

    private Object current;


    public ObjectInputStreamHelper(String path) throws IOException, ClassNotFoundException {
        fis = new FileInputStream(path);
        is = new ObjectInputStream(fis);
        try{
            this.current = this.is.readObject();
        } catch (EOFException e){
            this.current = null;
        };
    }

    public Object read() throws IOException, ClassNotFoundException {
        Object next = this.current;
        try{
            this.current = this.is.readObject();
        } catch (EOFException e){
            this.current = null;
        };
        return next;
    }

    public boolean hasNext(){
        return this.current != null;
    }

    public void close() throws IOException {
        is.close();
        fis.close();
    }
}
