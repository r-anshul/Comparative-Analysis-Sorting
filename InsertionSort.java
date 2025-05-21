public class InsertionSort {
    public static int comparisons = 0;
    public static int swaps = 0;

    public static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }

    public static long sort(int[] arr) {
        long startTime = System.nanoTime();
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    swaps++;
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
        }

        return System.nanoTime() - startTime;
    }
}
