package MapReduce.Util.Compare;

import java.util.ArrayList;

public class SortComparator extends BaseComparator{

    BaseComparator baseComparator;
    public enum SortType {
        ASC,DESC
    }

    public SortComparator(SortType type, int sortIndex, boolean numeric) {
        this.type = type;
        this.sortIndex = sortIndex;
        this.numeric = numeric;
        this.baseComparator = new DumpComparator();
    }

    private SortType type;
    private int sortIndex;
    private boolean numeric;


    @Override
    public int compare(ArrayList o1, ArrayList o2) {
        int result;
        if (type == SortType.ASC)
            result = ASCCompare(o1,o2);
        else if (type == SortType.DESC)
            result = DESCCompare(o1,o2);
        else
            result =  ASCCompare(o1,o2);
        if (result != 0)
            return result;
        else
            return this.baseComparator.compare(o1,o2);
    }

    private int ASCCompare(ArrayList o1, ArrayList o2){
        return objComp(o1.get(sortIndex),o2.get(sortIndex));
    }
    private int DESCCompare(ArrayList o1, ArrayList o2){
        return -1 * objComp(o1.get(sortIndex),o2.get(sortIndex));
    }

    private int objComp(Object o1,Object o2){
        if (numeric) return numericCompare(o1,o2);
        return stringCompare(o1.toString(),o2.toString());
    }

    public BaseComparator thenUse(BaseComparator comparator){
        this.baseComparator = comparator;
        return this;
    }
}
