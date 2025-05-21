public class CountingSort {
    public static int comparisons = 0; // Not many real comparisons in Counting Sort
    public static int swaps = 0; // We’ll count write operations here as swaps

    public static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }

    public static long sort(int[] arr) {
        long start = System.nanoTime();

        if (arr.length == 0) return 0;

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] count = new int[max + 1];

        for (int value : arr) {
            count[value]++;
            swaps++; // counting as a write
        }

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                arr[index++] = i;
                swaps++; // counting as a write
            }
        }

        return System.nanoTime() - start;
    }
}
