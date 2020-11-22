package chap2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import static util.ArrayUtil.*;

public class Exercise25_InsertionSortWithoutExchange {
    private enum InsertionSortType {
        DEFAULT, IMPROVED;
    }

    private static void insertionSort(Comparable[] array) {
        int size = array.length;
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0 && less(array[j], array[j - 1]); j--)
                exchange(array, j, j - 1);
        }
    }

    private static void insertionSortWithoutExchanges(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Comparable aux = array[i];

            int j;

            for (j = i; j > 0 && aux.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = aux;
        }
    }

    private static void sortCompare() {
        int size = 10000;
        int times = 100;

        Comparable[] array = new Comparable[size];
        for (int i = 0; i < size; i++) {
            array[i] = StdRandom.uniform();
        }
        insertionSort(array);
        StdOut.println("Default sorted: " + isSorted(array));

        for (int i = 0; i < size; i++) {
            array[i] = StdRandom.uniform();
        }
        insertionSortWithoutExchanges(array);
        StdOut.println("Improved sorted: " + isSorted(array));


        double t1 = timeRandomInput(InsertionSortType.DEFAULT, size, times);
        double t2 = timeRandomInput(InsertionSortType.IMPROVED, size, times);
        StdOut.printf("For %d random Doubles:\n\tInsertion Sort (without exchanges)  is is", size);
        StdOut.printf(" %.1f times faster than Insertion Sort (default)", t1 / t2);
    }

    private static double time(InsertionSortType type, Comparable[] array) {
        Stopwatch timer = new Stopwatch();
        if (type == InsertionSortType.DEFAULT) {
            insertionSort(array);
        } else if (type == InsertionSortType.IMPROVED) {
            insertionSortWithoutExchanges(array);
        }
        return timer.elapsedTime();
    }

    private static double timeRandomInput(InsertionSortType type, int length, int numberOfExperiments) {
        double total = 0;
        Comparable[] array = new Comparable[length];

        for (int experiment = 0; experiment < numberOfExperiments; experiment++) {
            for (int i = 0; i < length; i++) {
                array[i] = StdRandom.uniform();
            }

            total += time(type, array);
        }

        return total;
    }

    public static void main(String[] args) {
        sortCompare();
    }

}
