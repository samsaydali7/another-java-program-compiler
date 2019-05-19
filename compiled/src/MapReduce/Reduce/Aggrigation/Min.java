package MapReduce.Reduce.Aggrigation;

public class Min extends Aggregator {
    private double min;
    private boolean notSet = true;
    AggregateType type;

    public Min() {
        this.type = AggregateType.DOUBLE;
    }

    public Min(AggregateType type) {
        this.type = type;
    }

    @Override
    public Object aggregate(Object value) {
        double parsed = Double.parseDouble((String) value);
        if(notSet){
            min = parsed;
            notSet = false;
        }
        else if( parsed < min){
            min = parsed;
        }
        return min;
    }

    @Override
    public Object getResult() {
        switch (type){
            case INTEGER:
                return (int)this.min;
            case DOUBLE:
                return this.min;
            default:
                return this.min;
        }
    }

    @Override
    public void clean() {
        notSet = true;
    }
}
