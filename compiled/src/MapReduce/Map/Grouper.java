package MapReduce.Map;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Grouper {
    private Mapper mapper;

    public abstract Object grouberKey(ArrayList<String> row);
    public abstract Object grouberValue(ArrayList<String> row);
    public String getValue(ArrayList<String> row,String key){
        return this.mapper.getValue(row,key);
    }
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public static Grouper make(String[] keys,String[] values){
        return new Grouper() {
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                for (String key: keys) {
                    keysSet.add(getValue(row,key));
                }
                return keysSet;
            }

            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                for (String value : values){
                    list.add(this.getValue(row,value));
                }
                return list;
            }
        };
    }

    public static Grouper make(String[] keys,String value){
        return new Grouper() {
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                for (String key: keys) {
                    keysSet.add(getValue(row,key));
                }
                return keysSet;
            }

            public Object grouberValue(ArrayList<String> row) {
                return this.getValue(row,value);
            }
        };
    }
}
