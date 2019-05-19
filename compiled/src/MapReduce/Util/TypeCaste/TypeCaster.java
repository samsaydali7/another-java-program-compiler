package MapReduce.Util.TypeCaste;


import MapReduce.Util.DateParse.DateParser;
import MapReduce.Util.DateParse.OutFormat;

public class TypeCaster {
    public Object caste(Object item, CastTo ct){
        /* types supported in date parser */
            //date,datetime,datetime2,smalldatetime,time
        /* types needs to be cast in our type cast operation */
            //date,datetime,datetime2,smalldatetime,time,CHAR,SIGNED,UNSIGNED,BINARY

            /*
            Test d = new Test();

            System.out.println(d.TRUNCATE(5.55555, 3));
            the output is 5.555
            *** SELECT ROUND(5.56749, 3);*** round for three numbers start from (.) so we look to 4th number if (bigger than 5)or (equal & less than 5)  
            System.out.println(d.ROUND(5.56749, 3));
            the output is 5.567
            *** SELECT ROUND(5.56749, 3);***
            System.out.println(d.ROUND(5.56769, 3));
            the output is 5.568

            TypeCaster tc = new TypeCaster();
            *** SELECT CAST(5-10 AS UNSIGNED); ***
            System.out.println(tc.caste(5-10, CastTo.UNSIGNED));
            the output is 4294967291
            *** SELECT CAST(5-10 AS SIGNED); ***
            System.out.println(tc.caste(5-10, CastTo.SIGNED));
            the output is -5
            *** SELECT CAST("Friday, July 20 2018" AS DATE); ***
            System.out.println(tc.caste("Friday, July 20 2018", CastTo.date));
            the output is 2018-07-20
            *** SELECT CAST("2:35:50pm, Friday, 20 July 2018" AS DATE); ***
            System.out.println(tc.caste("2:35:50pm, Friday, 20 July 2018", CastTo.datetime));
            the output is 05/01/18 02:35
            *** SELECT CAST(5555 AS CHAR); ***
            System.out.println(tc.caste(5555, CastTo.CHAR));
            the output is 5555
            *** SELECT CAST(534 AS CHAR); ***
            System.out.println(tc.caste(534, CastTo.BINARY));
            the output is 1000010110
            */
            Object result = new Object();
            switch(ct){
                case date:result = DateParser.parse((String)item, OutFormat.date);
                break;
                case datetime:result = DateParser.parse((String)item, OutFormat.datetime);
                break;
                case datetime2:result = DateParser.parse((String)item, OutFormat.datetime2);
                break;
                case smalldatetime:result = DateParser.parse((String)item, OutFormat.smalldatetime);
                break;
                case time:result = DateParser.parse((String)item, OutFormat.time);
                break;
                case CHAR:result = CharCatster(item);
                //return result.toString();
                break;
                case UNSIGNED:result = UnSigndCatster(item);
                break;
                case SIGNED:result = SigndCatster(item);
                break;
                case BINARY:result = BinaryCaster(item);
                break;
            }
        return result;
    }
    public String CharCatster(Object item){
        String Sitem = item.toString();
        //char characterSet[] = Sitem.toCharArray();
        //return characterSet;
        return Sitem;
    }
    public long UnSigndCatster(Object item){
        //byte b = (byte)item;
        //int x = Byte.toUnsignedInt(b);
        //return x;
        long x = Integer.toUnsignedLong((int)item);
        //String s = item.toString();
        //long x = Integer.parseUnsignedInt(s);
        return x;
    }
    public int SigndCatster(Object item){
        String s = item.toString();
        int x = Integer.parseInt(s);
        return x;
    }
    public /*byte[]*/String BinaryCaster(Object item){
        //int binary = 0;
        String Sitem = Integer.toBinaryString((int)item);
        //byte[] b = new byte[Sitem.length()];
        //for(int i=0;i<Sitem.length();i++){
        //   b[i] =  (byte)Sitem.charAt(i);
        //}
        //return b;
        return Sitem;
    }
}
