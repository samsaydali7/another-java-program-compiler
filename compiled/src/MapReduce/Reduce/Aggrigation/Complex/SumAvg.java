package MapReduce.Reduce.Aggrigation.Complex;

import MapReduce.Reduce.Aggrigation.Aggregator;

public class SumAvg extends Aggregator {

    double sum;
    int count;
    double avg;

    public SumAvg() {
        this.sum = 0;
        this.count = 0;
        this.avg = 0;
    }

    @Override
    public Object aggregate(Object value) {
       this.sum += Double.valueOf((String) value);
       this.count++;
       return this;
    }

    @Override
    public Object getResult() {
        this.avg = sum/count;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("sum:" + this.sum);
        sb.append(',');
        sb.append("avg:" + this.avg);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void clean() {
        this.sum = 0;
        this.count = 0;
        this.avg = 0;
    }
}
