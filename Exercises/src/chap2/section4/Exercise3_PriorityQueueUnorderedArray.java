package chap2.section4;

@SuppressWarnings("unchecked")
public class Exercise3_PriorityQueueUnorderedArray {
    private class PriorityQueueUnorderedArray<Key extends Comparable<Key>> {
        private Key[] a;
        private int N = 0;

        private void resize(int capacity) {
            Key[] temp = (Key[]) new Comparable[capacity];
            for (int i = 0; i < N; i++) {
                temp[i] = a[i];
            }
            a = temp;
        }

        private void exch(Key[] a, int m, int n) {
            Key temp = a[m];
            a[m] = a[n];
            a[n] = temp;
        }

        public PriorityQueueUnorderedArray(int size) {
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
            a[N] = v;
            N++;
        }

        public Key max() {
            Key max = a[0];
            for (int i = 1; i < N; i++) {
                if (max.compareTo(a[i]) < 0) {
                    max = a[i];
                }
            }
            return max;
        }

        public Key delMax() {
            if (isEmpty()) {
                throw new RuntimeException("Priority queue underflow");
            }

            int maxIndex = 0;
            for (int i = 1; i < N; i++) {
                if (a[maxIndex].compareTo(a[i]) < 0) {
                    maxIndex = i;
                }
            }
            Key maxValue = a[maxIndex];
            exch(a, maxIndex, N - 1);
            a[N - 1] = null;
            N--;
            return maxValue;
        }
    }

    public static void main(String[] args) {
        testPriorityQueueUnorderedArray();
    }

    public static void testPriorityQueueUnorderedArray() {
        PriorityQueueUnorderedArray<Integer> PQ =
                new Exercise3_PriorityQueueUnorderedArray().new PriorityQueueUnorderedArray<>(10);
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
