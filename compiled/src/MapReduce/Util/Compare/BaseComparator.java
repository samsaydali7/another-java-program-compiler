package MapReduce.Util.Compare;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class BaseComparator implements Comparator<ArrayList> {

    protected int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
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
