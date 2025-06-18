import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortManager {

    public static int[] generateRandomArray(int size)
    {
        int[] A = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt(10000);
        }
        return A;
    }

    public static List<SortResult> runAllSorts(int[] originalAay)
    {
        List<SortResult> results = new ArrayList<>();

        results.add(runBubbleSort(originalAay));
        results.add(runSelectionSort(originalAay));
        results.add(runInsertionSort(originalAay));
        results.add(runMergeSort(originalAay));
        results.add(runQuickSort(originalAay));
        results.add(runHeapSort(originalAay));
        results.add(runCountingSort(originalAay));
        results.add(runRadixSort(originalAay));

        return results;
    }

    private static SortResult runBubbleSort(int[] A) {
        int[] AayCopy = A.clone();
        BubbleSort.resetCounters();
        long time = BubbleSort.sort(AayCopy);

        return new SortResult(
                "Bubble Sort",
                time,
                BubbleSort.comparisons,
                BubbleSort.swaps,
                "O(n²)",
                "O(1)",
                true,
                true
        );
    }

    private static SortResult runSelectionSort(int[] A) {
        int[] AayCopy = A.clone();
        SelectionSort.resetCounters();
        long time = SelectionSort.sort(AayCopy);

        return new SortResult(
                "Selection Sort",
                time,
                SelectionSort.comparisons,
                SelectionSort.swaps,
                "O(n²)",
                "O(1)",
                false,
                true
        );
    }

    private static SortResult runInsertionSort(int[] A) {
        int[] AayCopy = A.clone();
        InsertionSort.resetCounters();
        long time = InsertionSort.sort(AayCopy);

        return new SortResult(
                "Insertion Sort",
                time,
                InsertionSort.comparisons,
                InsertionSort.swaps,
                "O(n²)",
                "O(1)",
                true,
                true
        );
    }

    private static SortResult runMergeSort(int[] A) {
        int[] AayCopy = A.clone();
        MergeSort.resetCounters();
        long time = MergeSort.sort(AayCopy);

        return new SortResult(
                "Merge Sort",
                time,
                MergeSort.comparisons,
                MergeSort.swaps,
                "O(n log n)",
                "O(n)",
                true,
                false
        );
    }

    private static SortResult runQuickSort(int[] A) {
        int[] AayCopy = A.clone();
        QuickSort.resetCounters();
        long time = QuickSort.sort(AayCopy);

        return new SortResult(
                "Quick Sort",
                time,
                QuickSort.comparisons,
                QuickSort.swaps,
                "O(n log n)",
                "O(log n)",
                false,
                true
        );
    }

    private static SortResult runHeapSort(int[] A) {
        int[] AayCopy = A.clone();
        HeapSort.resetCounters();
        long time = HeapSort.sort(AayCopy);

        return new SortResult(
                "Heap Sort",
                time,
                HeapSort.comparisons,
                HeapSort.swaps,
                "O(n log n)",
                "O(1)",
                false,
                true
        );
    }

    private static SortResult runCountingSort(int[] A) {
        int[] AayCopy = A.clone();
        CountingSort.resetCounters();
        long time = CountingSort.sort(AayCopy);

        return new SortResult(
                "Counting Sort",
                time,
                CountingSort.comparisons,
                CountingSort.swaps,
                "O(n + k)",
                "O(k)",
                true,
                false
        );
    }

    private static SortResult runRadixSort(int[] A) {
        int[] AayCopy = A.clone();
        RadixSort.resetCounters();
        long time = RadixSort.sort(AayCopy);

        return new SortResult(
                "Radix Sort",
                time,
                RadixSort.comparisons,
                RadixSort.swaps,
                "O(n + k)",
                "O(n)",
                true,
                false
        );
    }
}
