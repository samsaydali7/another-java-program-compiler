package MapReduce.Reduce.Analytical;

import MapReduce.Reduce.Aggrigation.Aggregator;

public class Rank extends Aggregator {
    private int rank;
    private int lastRank;
    private boolean lastRecorded;
    private Object last;
    private Object current;

    public Rank() {
        rank = 0;
        lastRank = 0;
        lastRecorded = false;
    }

    @Override
    public Object aggregate(Object value) {
        value = value.toString().trim();
        last = current;
        current = value;
        if (current.equals(last) && !lastRecorded)
        {
            lastRecorded = true;
            lastRank = rank;
            rank++;
        }else if (current.equals(last)){
            rank++;
        }
        else {
            lastRecorded = false;
            rank++;
        }
        return this;
    }

    @Override
    public Object getResult() {
        if (current.equals(last))
            return lastRank;
        else return rank;
    }

    @Override
    public void clean() {
        this.last = null;
        this.current = null;
        this.rank = 0;
        this.lastRank = 0;
        this.lastRecorded = false;
    }
}
