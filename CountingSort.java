public class CountingSort
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

        if (A.length == 0) return 0;

        int max = A[0];
        for (int i = 1; i < A.length; i++)
        {
            comparisons++;
            if (A[i] > max)
                max = A[i];
        }
        int[] count = new int[max + 1];
        for (int value : A)
        {
            count[value]++;
            swaps++;
        }
        int index = 0;
        for(int i = 0; i < count.length; i++)
        {
            while (count[i]-- > 0)
            {
                A[index++] = i;
                swaps++;
            }
        }
        return System.nanoTime() - start;
    }
}
