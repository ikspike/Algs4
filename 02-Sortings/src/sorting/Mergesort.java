package sorting;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Mergesort extends General_methods
{
    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
                a[k] = aux[j++];    // left half exhausted (take from the right)
            else if (j > hi)
                a[k] = aux[i++];    // right half exhausted (take from the left)
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];    // current key on right less than current key on left (take from the right)
            else
                a[k] = aux[i++];    // current key on right greater than or equal to current key on left (take from the right)
        }
    }

    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args)
    {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

