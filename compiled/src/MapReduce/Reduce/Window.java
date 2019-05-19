package MapReduce.Reduce;

import java.util.ArrayList;

public class Window {
    public enum Clause { ROWS,RANGE }
    public enum Type { PRECEDING,FOLLOWING,BOTH,BOUND_PRECEDING_UNBOUND_FOLLOWING,
        UNBOUND_PRECEDING_BOUND_FOLLOWING,SELF }
    public Clause clause;
    public Type type;
    public boolean UNBOUNDED;
    public int precedingFactor;
    public int followingFactor;

    public Window(Clause clause, Type type, boolean UNBOUNDED) {
        this.clause = clause;
        this.type = type;
        this.UNBOUNDED = UNBOUNDED;
    }

    public void setPrecedingFactor(int precedingFactor) {
        this.precedingFactor = precedingFactor;
    }

    public void setFollowingFactor(int followingFactor) {
        this.followingFactor = followingFactor;
    }

    public ArrayList<ArrayList<Object>> partition(ArrayList<Object> values){

        if (clause == Clause.ROWS && type == Type.SELF)
            return partitionRowsSelf(values);
        if (clause == Clause.ROWS && type == Type.PRECEDING && UNBOUNDED)
            return partitionRowsPrecedingUnbound(values);
        if (clause == Clause.ROWS && type == Type.PRECEDING)
            return partitionRowsPreceding(values);
        else if (clause == Clause.ROWS && type == Type.FOLLOWING && UNBOUNDED)
            return partitionRowsFollowingUnbound(values);
        else if (clause == Clause.ROWS && type == Type.FOLLOWING)
            return partitionRowsFollowing(values);
        else if (clause == Clause.ROWS && type == Type.BOTH && UNBOUNDED)
            return partitionRowsBothUnbound(values);
        else if (clause == Clause.ROWS && type == Type.BOTH)
            return partitionRowsBoth(values);
        else if (clause == Clause.ROWS && type == Type.BOUND_PRECEDING_UNBOUND_FOLLOWING)
            return partitionRowsFollowingUnboundPrecedingBound(values);
        else if (clause == Clause.ROWS && type == Type.UNBOUND_PRECEDING_BOUND_FOLLOWING)
            return partitionRowsPrecedingUnboundFollowingBound(values);
        return partitionRowsPreceding(values);
    }

    private ArrayList<ArrayList<Object>> partitionRowsSelf(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
            partition.add(values.get(i));
            partitions.add(partition);
        }
        return partitions;
    }

    private ArrayList<ArrayList<Object>> partitionRowsPreceding(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
            if (i < precedingFactor){
                for (int j = 0; j <= i ; j++){
                    partition.add(values.get(j));
                }
            }else{
                for (int j = i- precedingFactor; j <= i ; j++){
                    partition.add(values.get(j));
                }
            }
            partitions.add(partition);
        }
        return partitions;
    }
    private ArrayList<ArrayList<Object>> partitionRowsPrecedingUnbound(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();

                for (int j = 0; j <= i ; j++){
                    partition.add(values.get(j));
                }

            partitions.add(partition);
        }
        return partitions;
    }
    private ArrayList<ArrayList<Object>> partitionRowsFollowing(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
            if (i + followingFactor < values.size()){
                for (int j = i; j <= i + followingFactor ; j++){
                    partition.add(values.get(j));
                }
            }else{
                for (int j = i ; j < values.size() ; j++){
                    partition.add(values.get(j));
                }
            }
            partitions.add(partition);
        }
        return partitions;
    }
    private ArrayList<ArrayList<Object>> partitionRowsFollowingUnbound(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
                for (int j = i ; j < values.size() ; j++){
                    partition.add(values.get(j));
                }
            partitions.add(partition);
        }
        return partitions;
    }

    private ArrayList<ArrayList<Object>> partitionRowsBoth(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
            if (i < precedingFactor){
                for (int j = 0; j < i ; j++){
                    partition.add(values.get(j));
                }
            }else{
                for (int j = i- precedingFactor; j < i ; j++){
                    partition.add(values.get(j));
                }
            }
            partition.add(values.get(i));
            if (i + followingFactor < values.size()){
                for (int j = i + 1; j <= i + followingFactor ; j++){
                    partition.add(values.get(j));
                }
            }else{
                for (int j = i + 1 ; j < values.size() ; j++){
                    partition.add(values.get(j));
                }
            }
            partitions.add(partition);
        }
        return partitions;
    }
    private ArrayList<ArrayList<Object>> partitionRowsBothUnbound(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
            partition.addAll(values);
            partitions.add(partition);
        }
        return partitions;
    }
    private ArrayList<ArrayList<Object>> partitionRowsFollowingUnboundPrecedingBound(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();
            if (i < precedingFactor){
                for (int j = 0; j < i ; j++){
                    partition.add(values.get(j));
                }
            }else{
                for (int j = i- precedingFactor; j < i ; j++){
                    partition.add(values.get(j));
                }
            }
            for (int j = i ; j < values.size() ; j++){
                partition.add(values.get(j));
            }
            partitions.add(partition);
        }
        return partitions;
    }
    private ArrayList<ArrayList<Object>> partitionRowsPrecedingUnboundFollowingBound(ArrayList<Object> values){
        ArrayList<ArrayList<Object>> partitions = new ArrayList<>();
        for (int i = 0; i < values.size() ; i++) {
            ArrayList<Object> partition = new ArrayList<>();

            for (int j = 0; j <= i ; j++){
                partition.add(values.get(j));
            }
            if (i + followingFactor < values.size()){
                for (int j = i + 1; j <= i + followingFactor ; j++){
                    partition.add(values.get(j));
                }
            }else{
                for (int j = i + 1 ; j < values.size() ; j++){
                    partition.add(values.get(j));
                }
            }

            partitions.add(partition);
        }
        return partitions;
    }
}
