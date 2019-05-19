package MapReduce.Reduce.Aggrigation.Complex;

import MapReduce.Reduce.Aggrigation.Aggregator;

import java.util.ArrayList;
import java.util.Collections;


public class Summarize extends Aggregator {
    private double count;
    private double sum;
    private double min;
    private double max;
    private double mean;
    private double StandardDeviation;
    private double median;
    private ArrayList<Double> set;
    private double Q1;
    private double Q3;

    public Summarize() {
        this.set           = new ArrayList<>();
        this.max           = Integer.MIN_VALUE;
        this.min           = Integer.MAX_VALUE;
        this.mean          = this.sum = this.count = this.StandardDeviation = this.median = Q1 = Q3 = 0;
    }
    public ArrayList<Double> readSet(Object value) {

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
    
    public double getMedianOrQ1OrQ2(ArrayList<Double> set,boolean deeper){
        if (set.size() == 1){
            if (deeper){
                Q1 = set.get(0);
                Q3 = set.get(0);
            }
            return set.get(0);
        }
        if (set.size() == 2){
            if (deeper){
                Q1 = set.get(0);
                Q3 = set.get(1);
            }
            return (set.get(0) + set.get(1))/2;
        }
        if (set.size() == 3){
            if (deeper){
                Q1 = set.get(0);
                Q3 = set.get(2);
            }
            return set.get(1);
        }
        if(set.size() %2 == 0){
            if (deeper){
                ArrayList<Double> first = new ArrayList<>();
                ArrayList<Double> second = new ArrayList<>();
                for (int i = 0; i < (set.size()/2); i++)
                    first.add(set.get(i));

                for (int i = (set.size()/2) ; i < set.size() ; i++)
                    second.add(set.get(i));

                Q1 = getMedianOrQ1OrQ2(first,false);
                Q3 = getMedianOrQ1OrQ2(second,false);
            }
           double val1 =  set.get(((int)(set.size()/2))-1);
           double val2 =  set.get((int)(set.size()/2));
           double myMedian = (val1+val2)/2;
           return myMedian;
        }else{
            if (deeper){
                ArrayList<Double> first = new ArrayList<>();
                ArrayList<Double> second = new ArrayList<>();
                for (int i = 0; i < (set.size()/2) -1 ; i++)
                    first.add(set.get(i));
                for (int i = (set.size()/2) + 1; i < set.size() ; i++)
                    second.add(set.get(i));
                Q1 = getMedianOrQ1OrQ2(first,false);
                Q3 = getMedianOrQ1OrQ2(second,false);
            }
            double myMedian = set.get(((int) (set.size()/2)));
            return myMedian;
        }
    }
    public double getStandardDeviation(ArrayList<Double> set){
        Summarize s                        = new Summarize();
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


    public Object aggregate(Object value) {
        double item = Double.parseDouble(value.toString());
        this.set.add(item);
        return this;
    }


    public Object getResult() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(set);
        double Median = getMedianOrQ1OrQ2(set,true);
        String median = Double.toString(Median);
        double Count  = set.size();
        String count  = Integer.toString((int) Count);
        double Min    = getMin(set);
        String min    = Double.toString(Min);
        double Max    = getMax(set);
        String max    = Double.toString(Max);
        double Mean   = calculateMean(set);
        String mean   = Double.toString(Mean);
        double Std    = getStandardDeviation(set);
        String std    = Double.toString(Std);
        String q1     = Double.toString(Q1);
        String q3     = Double.toString(Q3);

        sb.append("{");
        sb.append("count:" + count);
        sb.append(',');
        sb.append("mean:" + mean);
        sb.append(',');
        sb.append("std:" + std);
        sb.append(',');
        sb.append("min:" + min);
        sb.append(',');
        sb.append("q1:" + q1);
        sb.append(',');
        sb.append("median:" + median);
        sb.append(',');
        sb.append("q3:" + q3);
        sb.append(',');
        sb.append("max:" + max);
        sb.append("}");
        return sb.toString();
    }

    public void clean(){
        this.set = new ArrayList<>();
        this.min    = Integer.MAX_VALUE;
        this.max    = Integer.MIN_VALUE;
        this.mean = this.sum = this.count = this.StandardDeviation = this.median = Q1 = Q3 = 0;
        
    }
}