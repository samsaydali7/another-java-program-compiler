package MapReduce.Reduce.RowFunctions.dateparser;
public abstract class Date {
    private int day,month,year;
    public void setDay(String day){
        int dd = Integer.parseInt(day);
        this.day = dd;
    }
    public void setMonth(String month){
        int mm = Integer.parseInt(month);
        this.month = mm;
    }
    public void setYear(String year){
        int yyyy = Integer.parseInt(year);
        this.year = yyyy;
    }
    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }
    public abstract void printDateElement();
}
