package chap2_sorting;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quicksort extends SortingStem
{
    // Partition into a[lo..i-1], a[i], a[i+1..hi].
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo;     // left pointer i
        int j = hi + 1;   // right pointer j
        Comparable v = a[lo];   // partitioning item

        while (true)
        {   // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;  //
            exch(a, i, j);
        }

        exch(a, lo, j); // a[j] now points to the rightmost less element
        return j;
    }

    public static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)   // ending condition of recursion
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args)
    {
        //String[] a = StdIn.readAllStrings();
        Comparable[] a = {9, 8, 78, 6, 5, 4, 3, 2, 1};
        StdOut.print("Input: ");
        show(a);
        sort(a);
        assert isSorted(a);
        StdOut.print("Sorted: ");
        show(a);
    }

}
