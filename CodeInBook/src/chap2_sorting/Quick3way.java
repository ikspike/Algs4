package chap2_sorting;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Quick3way extends SortingStem {

    public static void sort(Comparable[] array) {
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = array[lo];
        while (i <= gt) {
            int cmp = array[i].compareTo(v);
            if (cmp < 0) exch(array, lt++, i++);
            else if (cmp > 0) exch(array, i, gt--);
        }   // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        sort(array, lo, lt - 1);
        sort(array, gt + 1, hi);
    }

    public static void main(String[] args) {
        Comparable[] array = {7, 3, 9, 14, 5};
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
