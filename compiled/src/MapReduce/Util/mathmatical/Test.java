package MapReduce.Util.mathmatical;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

public class Test {
    public double TRUNCATE(double number,int places){
       String Snumber = Double.toString(number);
       String Sresult = null;
       for(int i=0;i<Snumber.length();i++){
           if(Snumber.charAt(i)=='.')
               Sresult = Snumber.substring(0, i+places+1);
       }
       return Double.parseDouble(Sresult);
    }
    public double ROUND(double number, int places){
        String Snumber = Double.toString(number);
        char[] ASnumber = new char[Snumber.length()];
        String Sresult = null;
        for(int i=0;i<Snumber.length();i++){
           if(Snumber.charAt(i)=='.'){
               if((Character.getNumericValue(Snumber.charAt(i+places+1))>5)){
                   System.out.println("places+1:"+Snumber.charAt(i+places+1));
                   System.out.println("places:"+Snumber.charAt(i+places));
                   int k = Snumber.charAt(i+places);
                   k++;k = k-48;
                   char c = (char)(k + '0');
                   System.out.println("k:   "+k);
                    Sresult = Snumber.substring(0, i+places);
                    Sresult +=c;
                    return Double.parseDouble(Sresult);
               }
               else{
                   Sresult = Snumber.substring(0, i+places+1);
                   return Double.parseDouble(Sresult);
                   
                    
               }
               
           }
               
        }
        return number;
    }
}
