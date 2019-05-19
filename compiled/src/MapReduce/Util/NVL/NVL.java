package MapReduce.Util.NVL;
public class NVL{
    String string1, string2;
    public NVL(String string1,String string2){
        this.string1 = string1;
        this.string2 = string2;
    }
    public String NVLAnswer(String string1,String string2){
        if(string1 == "NULL")
        return string2;
        else 
        return string1;
    }
}