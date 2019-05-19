package MapReduce.Reduce.Aggrigation;

public class Max extends Aggregator {
    private double max;
    private boolean notSet = true;
    AggregateType type;

    public Max() {
        this.type = AggregateType.DOUBLE;
    }
    public Max(AggregateType type) {
        this.type = type;
    }

    @Override
    public Object aggregate(Object value) {
        double parsed = Double.parseDouble((String) value);
        if(notSet){
            max = parsed;
            notSet = false;
        }
        else if( parsed > max){
            max = parsed;
        }
        return max;
    }

    @Override
    public Object getResult() {
        switch (type){
            case INTEGER:
                return (int)this.max;
            case DOUBLE:
                return this.max;
            default:
                return this.max;
        }
    }

    @Override
    public void clean() {
        notSet = true;
    }
}
