package MapReduce.Reduce.Aggrigation;

public class Sum extends Aggregator {

    private double sum;
    private AggregateType type;

    public Sum() {
        this.type = AggregateType.DOUBLE;
    }
    public Sum(AggregateType type) {
        this.type = type;
    }

    @Override
    public Object aggregate(Object value) {
        this.sum += Double.parseDouble((String) value);
        return sum;
    }

    @Override
    public Object getResult() {
        switch (type){
            case INTEGER:
                return (int)this.sum;
            case DOUBLE:
                return this.sum;
            default:
                return this.sum;
        }
    }

    @Override
    public void clean() {
        this.sum = 0;
    }
}