package MapReduce.Reduce.Aggrigation.Complex;

import MapReduce.Reduce.Aggrigation.Aggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Summarize2 /*extends Aggregator*/ {
    private double count;
    private double sum;
    private double min;
    private double max;
    private double mean;
    private double StandardDeviation;
    private double median;
    private static ArrayList<Double> set;
    private static ArrayList <Double> firstHalfSet;
    private static ArrayList<Double> secondHalfSet;
    private double Q1;
    private double Q2;

    public void SummarizeTester() {
        /* Example 1 odd number of elements */
        /*Summarize2 calculations = new Summarize2();
        set.add(2.0);
        set.add(5.0);
        set.add(20.0);
        set.add(7.0);
        set.add(10.0);
        set.add(14.0);
        set.add(9.0);
        set.add(3.0);
        set.add(6.0);
        set.add(12.0);
        set.add(15.0);
        System.out.println(calculations.print(set));
        */
        /* Example 2 even number of elements */
        /*Summarize2 calculations = new Summarize2();
        set.add(2.0);
        set.add(5.0);
        set.add(20.0);
        set.add(7.0);
        set.add(10.0);
        set.add(14.0);
        set.add(9.0);
        set.add(3.0);
        set.add(6.0);
        set.add(12.0);
        System.out.println(calculations.print(set)); 
        */
        /*Example 3 to read values as ArrayList of string*/
        Summarize2 calculations  = new Summarize2();
        ArrayList<String> values = new ArrayList<String>();
        values.add("2.0");
        values.add("5.0");
        values.add("20.0");
        values.add("7.0");
        values.add("10.0");
        values.add("14.0");
        values.add("9.0");
        values.add("3.0");
        values.add("6.0");
        values.add("12.0");
        for(String i:values){
            calculations.readSet(i);
        }
        System.out.println(calculations.print(set));
    }
    
    public Summarize2() {
        this.set           = new ArrayList<>();
        this.firstHalfSet  = new ArrayList<>();
        this.secondHalfSet = new ArrayList<>();
        this.max           = Integer.MIN_VALUE;
        this.min           = Integer.MAX_VALUE;
        this.mean          = this.sum = this.count = this.StandardDeviation = this.median = Q1 = Q2 = 0;
    }
    public ArrayList<Double> readSet(Object value) {
        double item = Double.parseDouble((String) value);
        this.set.add(item);
        return this.set;
    }
    public static double calculateMean(ArrayList<Double> set){
        double sum = 0 ,count = 0;
        for(double i:set){
            sum+=i;
        }
        count = set.size();
        return sum/count;
    }
    public double getMax(ArrayList<Double> set){
        if (set == null || set.size() == 0) { 
            return Integer.MIN_VALUE; 
        }
        max = Collections.max(set);
        return max;
    }
    public double getMin(ArrayList<Double> set){
        if (set == null || set.size() == 0) { 
            return Integer.MAX_VALUE; 
        }
        min = Collections.min(set); 
        return min;       
    }
    public ArrayList<Double> sortSet(ArrayList<Double> set){
        Collections.sort(set);         
        return set;  
    }
    
    public double getMedianOrQ1OrQ2(ArrayList<Double> set){
        if(set.size() %2 == 0){
           double val1 =  set.get((int)(set.size()/2)-1);
           double val2 =  set.get((int)(set.size()/2));
           median      = (val1+val2)/2;
           set.add(median);
           //Collections.sort(set);
           return median;
        }else{
          //median     = (set.size()/2)+1;
            median     = set.get((set.size()/2));
            return median;
        }
    }
    public double getQ1(ArrayList<Double> set){
        for(double i:set){
            if(i < median){
                firstHalfSet.add(i);
            }
        }
        if(firstHalfSet.size() %2 == 0){
           double val1 =  firstHalfSet.get((int)(firstHalfSet.size()/2)-1);
           double val2 =  firstHalfSet.get((int)(firstHalfSet.size()/2));
           Q1      = (val1+val2)/2;
           return Q1;
        }else{
            Q1     = firstHalfSet.get((firstHalfSet.size()/2));
            return Q1;
        }   
    }
    public double getQ2(ArrayList<Double> set){
        for(double i:set){
            if(i > median){
                secondHalfSet.add(i);
            }
        }
        if(secondHalfSet.size() %2 == 0){
           double val1 =  secondHalfSet.get((int)(secondHalfSet.size()/2)-1);
           double val2 =  secondHalfSet.get((int)(secondHalfSet.size()/2));
           Q2 = (val1+val2)/2;
           return Q2;
        }else{
            Q2 = secondHalfSet.get((secondHalfSet.size()/2));
            return Q2;
        }   
    }
    public double getStandardDeviation(ArrayList<Double> set){
        Summarize2 s                        = new Summarize2();
        ArrayList<Double> varianceSet       = new ArrayList<>();
        ArrayList<Double> variancePower2Set = new ArrayList<>();
        double avg                          = s.calculateMean(set);
        double sum                          = 0;
        for(double i:set){
            varianceSet.add(i-avg);
        }
        for(double i:varianceSet){
            variancePower2Set.add(i*i);
        }
        for(double i:variancePower2Set){
            sum+=i;
        }
        return Math.sqrt(sum/set.size());
    }
    public String print(ArrayList<Double> set){
        //Summarize2 calculations = new Summarize2();
        //calculations.sortSet(set);
        Collections.sort(set);
        double Median = getMedianOrQ1OrQ2(set);
        Collections.sort(set);
        String median = Double.toString(Median);
        Collections.sort(set);
        double Count  = set.size();
        String count  = Double.toString(Count);
        double Min    = getMin(set); 
        String min    = Double.toString(Min);
        double Max    = getMax(set); 
        String max    = Double.toString(Max);
        double Mean   = calculateMean(set); 
        String mean   = Double.toString(Mean);
        double Std    = getStandardDeviation(set); 
        String std    = Double.toString(Std);
        double Q1     = getQ1(set);
        String q1     = Double.toString(Q1);
        double Q3     = getQ2(set);
        String q3     = Double.toString(Q3);
        
        String s = "count:"+count+"    mean:"+mean+ "    std:"+std+ "     min:"+min+"    Q1:"+q1+"      median:"+median +"     Q3:"+q3+"     Max:"+max;
        return s;
    }


    public Object aggregate(Object value) {
        return null;
    }


    public Object getResult() {
        return null;
    }

    public static void clean(){
        Summarize2.set           = new ArrayList<>();
        Summarize2.firstHalfSet  = new ArrayList<>();
        Summarize2.secondHalfSet = new ArrayList<>();
        double Count  = 0;
        double Min    = 0; 
        double Max    = 0; 
        double Mean   = 0; 
        double Std    = 0; 
        double Q1     = 0;
        double Q3     = 0;
        
    }
}