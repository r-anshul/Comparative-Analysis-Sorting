public class RadixSort {
    public static int comparisons = 0;
    public static int swaps = 0;

    public static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }

    public static long sort(int[] arr) {
        long start = System.nanoTime();

        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }

        long end = System.nanoTime();
        return end - start;
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            swaps++; // using swap to represent element moves
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}
