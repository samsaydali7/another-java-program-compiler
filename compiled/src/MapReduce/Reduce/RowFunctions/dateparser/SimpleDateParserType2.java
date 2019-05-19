package MapReduce.Reduce.RowFunctions.dateparser;

public class SimpleDateParserType2 extends Date {
    public SimpleDateParserType2(String date){
        /*date must be something like this "1-3-2019"*/
         String dateElements[] = date.split("-");
         setDay(dateElements[0]);
         setMonth(dateElements[1]);
         setYear(dateElements[2]);
         printDateElement();
    }
    public void printDateElement(){
        System.out.println(getDay()+"-"+getMonth()+"-"+getYear());
    }
}
