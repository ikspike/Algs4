package chap2.section4;

import static util.ArrayUtil.less;

@SuppressWarnings("unchecked")
public class Exercise3_PriorityQueueOrderedArray {
    private static class PriorityQueueOrderedArray<Key extends Comparable<Key>> {
        private Key[] a;
        private int N = 0;

        private void resize(int capacity) {
            Key[] temp = (Key[]) new Comparable[capacity];
            for (int i = 0; i < N; i++) {
                temp[i] = a[i];
            }
            a = temp;
        }

        public PriorityQueueOrderedArray(int size) {
            a = (Key[]) new Comparable[size];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void insert(Key v) {
            if (N == a.length) {
                resize(2 * a.length);
            }

            int i = N - 1;
            while (i >= 0 && less(v, a[i])) {
                a[i + 1] = a[i];
                i--;
            }

            a[i + 1] = v;
            N++;
        }

        public Key max() {
            return a[N - 1];
        }

        public Key delMax() {
            if (isEmpty()) {
                throw new RuntimeException("Priority queue underflow");
            }

            N--;
            Key maxValue = a[N];
            a[N] = null;
            return maxValue;
        }
    }

    public static void main(String[] args) {
        testPriorityQueueOrderedArray();
    }

    public static void testPriorityQueueOrderedArray() {
        PriorityQueueOrderedArray<Integer> PQ =
                new PriorityQueueOrderedArray<>(10);
        PQ.insert(7);
        PQ.insert(3);
        PQ.insert(12);
        PQ.insert(9);
        System.out.println("Deleted max value: " + PQ.delMax() + "\tExpected: 12");
        System.out.println("Deleted max value: " + PQ.delMax() + "\tExpected: 9");
        System.out.println("Deleted max value: " + PQ.delMax() + "\tExpected: 7");
        System.out.println("Deleted max value: " + PQ.delMax() + "\tExpected: 3");
    }
}
