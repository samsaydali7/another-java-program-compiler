package MapReduce.Reduce.Analytical;

import MapReduce.Reduce.Aggrigation.Aggregator;

public class FirstValue extends Aggregator{
    Object firstValue;

    public FirstValue() {
        this.firstValue = null;
    }

    @Override
    public Object aggregate(Object value) {
        if (firstValue == null)
            this.firstValue = value.toString().trim();
        return this;
    }

    @Override
    public Object getResult() {
        return this.firstValue;
    }

    @Override
    public void clean() {
        this.firstValue = null;
    }
}
