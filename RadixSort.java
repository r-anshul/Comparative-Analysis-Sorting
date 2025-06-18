public class RadixSort
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
        int max = getMax(A);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(A, exp);
        long end = System.nanoTime();
        return end - start;
    }
    private static int getMax(int[] A)
    {
        int max = A[0];
        for (int i = 1; i < A.length; i++)
        {
            comparisons++;
            if (A[i] > max)
                max = A[i];
        }
        return max;
    }
    private static void countSort(int[] A, int exp)
    {
        int n = A.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++)
            count[(A[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--)
        {
            output[count[(A[i] / exp) % 10] - 1] = A[i];
            count[(A[i] / exp) % 10]--;
            swaps++;
        }
        System.arraycopy(output, 0, A, 0, n);
    }
}