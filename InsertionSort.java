public class InsertionSort
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
        long startTime = System.nanoTime();
        int n = A.length;
        for (int i = 1; i < n; i++)
        {
            int key = A[i];
            int j = i - 1;
            while (j >= 0)
            {
                comparisons++;
                if (A[j] > key)
                {
                    A[j + 1] = A[j];
                    swaps++;
                    j--;
                } else
                    break;
            }
            A[j + 1] = key;
        }
        return System.nanoTime() - startTime;
    }
}
