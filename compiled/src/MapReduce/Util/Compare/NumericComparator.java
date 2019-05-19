package MapReduce.Util.Compare;

import java.util.Comparator;

public class NumericComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return numericCompare(o1,o2);
    }

    public int numericCompare(Object o1,Object o2){
        double n1 = Double.valueOf(o1.toString());
        double n2 = Double.valueOf(o2.toString());
        if (n1 - n2 > 0.00000001 )
            return 1;
        else if (n1 == n2)
            return 0;
        else
            return -1;
    }
}
