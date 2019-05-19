package MapReduce.Map;

import java.util.ArrayList;

public abstract class Filter {
    private Mapper mapper;

    public abstract boolean valid(Object object);
    public String getValue(ArrayList<String> row, String key){
        return this.mapper.getValue(row,key);
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
