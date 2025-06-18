public class SelectionSort
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
        for (int i = 0; i < n - 1; i++)
        {
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
            {
                comparisons++;
                if (A[j] < A[minIdx])
                    minIdx = j;
            }
            if (minIdx != i)
            {
                swaps++;
                int temp = A[i];
                A[i] = A[minIdx];
                A[minIdx] = temp;
            }
        }
        return System.nanoTime() - start;
    }
}