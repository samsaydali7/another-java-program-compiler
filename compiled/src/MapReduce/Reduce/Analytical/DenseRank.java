package MapReduce.Reduce.Analytical;

import MapReduce.Reduce.Aggrigation.Aggregator;

public class DenseRank extends Aggregator {
    private int rank;
    private Object last;
    private Object current;

    public DenseRank() {
        rank = 0;
    }

    @Override
    public Object aggregate(Object value) {
        value = value.toString().trim();
        current = value;
        if (!current.equals(last))
            rank++;
        last = current;
        return this;
    }

    @Override
    public Object getResult() {
        return rank;
    }

    @Override
    public void clean() {
        this.last = null;
        this.current = null;
        this.rank = 0;
    }
}
