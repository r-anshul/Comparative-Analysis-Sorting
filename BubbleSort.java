public class BubbleSort
{
    public static int comparisons = 0;
    public static int swaps = 0;

    public static void resetCounters()
    {
        comparisons = 0;
        swaps = 0;
    }

    public static long sort(int[] A)
    {
        long start = System.nanoTime();
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++)
            {
                comparisons++;
                if (A[j] > A[j + 1]) {
                    swaps++;
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        return System.nanoTime() - start;
    }
}
