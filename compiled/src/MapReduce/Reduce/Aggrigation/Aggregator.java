package MapReduce.Reduce.Aggrigation;

abstract public class Aggregator {
    public abstract Object aggregate(Object value);
    public abstract Object getResult();
    public abstract void clean();
}
