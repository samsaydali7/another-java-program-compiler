package MapReduce.Reduce.RowFunctions.dateparser;

public class SimpleDateParserType1 extends Date{
    public SimpleDateParserType1(String date){
        /*Date must be somthing like this "1/3/2019" */
         String dateElements[] = date.split("/");
         setDay(dateElements[0]);
         setMonth(dateElements[1]);
         setYear(dateElements[2]);
         printDateElement();
    }
    public void printDateElement(){
        System.out.println(getDay()+"/"+getMonth()+"/"+getYear());
    }
}
