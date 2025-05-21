public class SelectionSort {
    public static int comparisons = 0;
    public static int swaps = 0;

    public static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }

    public static long sort(int[] arr) {
        long start = System.nanoTime();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swaps++;
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
        return System.nanoTime() - start;
    }
}
