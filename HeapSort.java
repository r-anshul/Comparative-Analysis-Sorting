public class HeapSort
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
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(A, n, i);
        for (int i = n - 1; i > 0; i--)
        {
            swaps++;
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heapify(A, i, 0);
        }
        return System.nanoTime() - start;
    }
    private static void heapify(int[] A, int n, int i)
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n)
        {
            comparisons++;
            if (A[left] > A[largest])
                largest = left;
        }
        if (right < n)
        {
            comparisons++;
            if (A[right] > A[largest])
                largest = right;
        }
        if (largest != i)
        {
            swaps++;
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            heapify(A, n, largest);
        }
    }
}
