package MapReduce.Reduce.Aggrigation;

import java.util.ArrayList;

public class DoNothing extends Aggregator{
    public ArrayList<Object> list;

    public DoNothing() {
        this.list = new ArrayList<>();
    }

    @Override
    public Object aggregate(Object value) {
        this.list.add(value);
        return this;
    }

    @Override
    public Object getResult() {
        return this.list.toString();
    }

    @Override
    public void clean() {
        this.list.clear();
    }
}
