package MapReduce.Util.Compare;

import java.util.ArrayList;

public class DistinctComparator extends BaseComparator{

    @Override
    public int compare(ArrayList o1, ArrayList o2) {
        return stringCompare(o1.toString(),o2.toString());
    }

}
