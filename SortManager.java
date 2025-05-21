import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortManager {

    // Generate random array of given size with values 0-9999
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    // Run all sorts on a fresh copy of the array and collect results
    public static List<SortResult> runAllSorts(int[] originalArray) {
        List<SortResult> results = new ArrayList<>();

        results.add(runBubbleSort(originalArray));
        results.add(runSelectionSort(originalArray));
        results.add(runInsertionSort(originalArray));
        results.add(runMergeSort(originalArray));
        results.add(runQuickSort(originalArray));
        results.add(runHeapSort(originalArray));
        results.add(runCountingSort(originalArray));
        results.add(runBucketSort(originalArray));

        return results;
    }

    private static SortResult runBubbleSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        BubbleSort.resetCounters();
        long time = BubbleSort.sort(arrayCopy);

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

    private static SortResult runSelectionSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        SelectionSort.resetCounters();
        long time = SelectionSort.sort(arrayCopy);

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

    private static SortResult runInsertionSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        InsertionSort.resetCounters();
        long time = InsertionSort.sort(arrayCopy);

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

    private static SortResult runMergeSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        MergeSort.resetCounters();
        long time = MergeSort.sort(arrayCopy);

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

    private static SortResult runQuickSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        QuickSort.resetCounters();
        long time = QuickSort.sort(arrayCopy);

        return new SortResult(
                "Quick Sort",
                time,
                QuickSort.comparisons,
                QuickSort.swaps,
                "O(n log n) average",
                "O(log n) average",
                false,
                true
        );
    }

    private static SortResult runHeapSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        HeapSort.resetCounters();
        long time = HeapSort.sort(arrayCopy);

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

    private static SortResult runCountingSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        CountingSort.resetCounters();
        long time = CountingSort.sort(arrayCopy);

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

    private static SortResult runBucketSort(int[] arr) {
        int[] arrayCopy = arr.clone();
        RadixSort.resetCounters();
        long time = RadixSort.sort(arrayCopy);

        return new SortResult(
                "Bucket Sort",
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
