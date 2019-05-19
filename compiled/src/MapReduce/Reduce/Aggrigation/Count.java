package MapReduce.Reduce.Aggrigation;

public class Count extends Aggregator {
    private int count;
    @Override
    public Integer aggregate(Object value) {
        count += 1 ;
        return count;
    }

    @Override
    public Integer getResult() {
        return this.count;
    }

    @Override
    public void clean() {
        this.count = 0;
    }
}
