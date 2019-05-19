package MapReduce.Reduce.RowFunctions.dateparser;

public class DateTime extends Date{
    private int second,minuts,hours;
    public DateTime(String date){
        /* date time should be like this "1:3:2019:30:40:50" */
         String dateElements[] = date.split(":");
         setDay(dateElements[0]);
         setMonth(dateElements[1]);
         setYear(dateElements[2]);
         setSeconds(dateElements[3]);
         setMinuts(dateElements[4]);
         setHours(dateElements[5]);
         printDateElement();
    }
    public void setSeconds(String seconds){
        int ss = Integer.parseInt(seconds);
        this.second = ss;
    }
    public void setMinuts(String minuts){
        int mm = Integer.parseInt(minuts);
        this.minuts = mm;
    }
    public void setHours(String hours){
        int hh = Integer.parseInt(hours);
        this.hours = hh;
    }
    public int getSceconds(){
        return this.second;
    }
    public int getMinuts(){
        return this.minuts;
    }
    public int getHours(){
        return this.hours;
    }
    public void printDateElement(){
        System.out.println(getDay()+":"+getMonth()+":"+getYear()+"  "+getSceconds()+":"+getMinuts()+":"+getHours());
    }
}
