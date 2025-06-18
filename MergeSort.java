public class MergeSort
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
        mergeSort(A, 0, A.length - 1);
        return System.nanoTime() - start;
    }
    private static void mergeSort(int[] A, int left, int right)
    {
        if (left < right)
        {
            int mid = (left + right) / 2;
            mergeSort(A, left, mid);
            mergeSort(A, mid + 1, right);
            merge(A, left, mid, right);
        }
    }
    private static void merge(int[] A, int left, int mid, int right)
    {
        int[] leftA = new int[mid - left + 1];
        int[] rightA = new int[right - mid];
        System.arraycopy(A, left, leftA, 0, leftA.length);
        System.arraycopy(A, mid + 1, rightA, 0, rightA.length);
        int i = 0, j = 0, k = left;
        while (i < leftA.length && j < rightA.length)
        {
            comparisons++;
            if (leftA[i] <= rightA[j])
                A[k++] = leftA[i++];
            else
            {
                swaps++;
                A[k++] = rightA[j++];
            }
        }
        while (i < leftA.length)
            A[k++] = leftA[i++];
        while (j < rightA.length)
            A[k++] = rightA[j++];
    }
}
