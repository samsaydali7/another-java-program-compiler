package MapReduce.Reduce.Analytical;

import MapReduce.Reduce.Aggrigation.Aggregator;

public class LastValue extends Aggregator {
    Object lastValue;

    public LastValue() {
        this.lastValue = null;
    }

    @Override
    public Object aggregate(Object value) {
        this.lastValue = value.toString().trim();
        return this;
    }

    @Override
    public Object getResult() {
        return this.lastValue;
    }

    @Override
    public void clean() {
        this.lastValue = null;
    }
}
