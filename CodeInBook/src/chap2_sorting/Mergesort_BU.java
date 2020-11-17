package chap2_sorting;


public class Mergesort_BU extends SortingStem
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
        int N = a.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));

    }

    public static void main(String[] args)
    {
        // String[] a = StdIn.readAllStrings();
        Comparable[] a = {9, 8, 78, 6, 5, 4, 3, 2, 1};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

