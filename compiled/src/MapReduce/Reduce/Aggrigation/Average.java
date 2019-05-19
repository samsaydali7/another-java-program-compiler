package MapReduce.Reduce.Aggrigation;

public class Average extends Aggregator {
    private double sum;
    private int count;

    @Override
    public Object aggregate(Object value) {
        this.sum += Double.parseDouble((String) value);
        count++;
        return sum/count;
    }

    @Override
    public Object getResult() {
        return this.sum/count;
    }

    @Override
    public void clean() {
        this.sum = 0;
        this.count = 0;
    }
}
