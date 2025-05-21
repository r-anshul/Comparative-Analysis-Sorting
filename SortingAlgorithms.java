public class SortingAlgorithms {

    public static SortResult bubbleSort(int[] arr) {
        int[] copy = arr.clone();
        int comparisons = 0, swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - i - 1; j++) {
                comparisons++;
                if (copy[j] > copy[j + 1]) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                    swaps++;
                }
            }
        }

        long endTime = System.nanoTime();
        return new SortResult("Bubble Sort", endTime - startTime, comparisons, swaps,
                "O(n^2)", "O(1)", true, true);
    }

    public static SortResult selectionSort(int[] arr) {
        int[] copy = arr.clone();
        int comparisons = 0, swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < copy.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < copy.length; j++) {
                comparisons++;
                if (copy[j] < copy[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = copy[i];
            copy[i] = copy[minIdx];
            copy[minIdx] = temp;
            swaps++;
        }

        long endTime = System.nanoTime();
        return new SortResult("Selection Sort", endTime - startTime, comparisons, swaps,
                "O(n^2)", "O(1)", false, true);
    }

    public static SortResult insertionSort(int[] arr) {
        int[] copy = arr.clone();
        int comparisons = 0, swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 1; i < copy.length; i++) {
            int key = copy[i];
            int j = i - 1;
            while (j >= 0 && copy[j] > key) {
                comparisons++;
                copy[j + 1] = copy[j];
                swaps++;
                j--;
            }
            comparisons++;
            copy[j + 1] = key;
        }

        long endTime = System.nanoTime();
        return new SortResult("Insertion Sort", endTime - startTime, comparisons, swaps,
                "O(n^2)", "O(1)", true, true);
    }

    public static SortResult mergeSort(int[] arr) {
        int[] copy = arr.clone();
        int[] comparisons = {0}, swaps = {0};
        long startTime = System.nanoTime();
        mergeSortRecursive(copy, 0, copy.length - 1, comparisons, swaps);
        long endTime = System.nanoTime();

        return new SortResult("Merge Sort", endTime - startTime, comparisons[0], swaps[0],
                "O(n log n)", "O(n)", true, false);
    }

    private static void mergeSortRecursive(int[] arr, int left, int right, int[] comparisons, int[] swaps) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(arr, left, mid, comparisons, swaps);
            mergeSortRecursive(arr, mid + 1, right, comparisons, swaps);
            merge(arr, left, mid, right, comparisons, swaps);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] comparisons, int[] swaps) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            comparisons[0]++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
                swaps[0]++;
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static SortResult quickSort(int[] arr) {
        int[] copy = arr.clone();
        int[] comparisons = {0}, swaps = {0};
        long startTime = System.nanoTime();
        quickSortRecursive(copy, 0, copy.length - 1, comparisons, swaps);
        long endTime = System.nanoTime();

        return new SortResult("Quick Sort", endTime - startTime, comparisons[0], swaps[0],
                "O(n log n)", "O(log n)", false, true);
    }

    private static void quickSortRecursive(int[] arr, int low, int high, int[] comparisons, int[] swaps) {
        if (low < high) {
            int pi = partition(arr, low, high, comparisons, swaps);
            quickSortRecursive(arr, low, pi - 1, comparisons, swaps);
            quickSortRecursive(arr, pi + 1, high, comparisons, swaps);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] comparisons, int[] swaps) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons[0]++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swaps[0]++;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swaps[0]++;
        return i + 1;
    }

    public static SortResult heapSort(int[] arr) {
        int[] copy = arr.clone();
        int comparisons = 0, swaps = 0;
        long startTime = System.nanoTime();

        int n = copy.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            comparisons += heapify(copy, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = copy[0];
            copy[0] = copy[i];
            copy[i] = temp;
            swaps++;
            comparisons += heapify(copy, i, 0);
        }

        long endTime = System.nanoTime();
        return new SortResult("Heap Sort", endTime - startTime, comparisons, swaps,
                "O(n log n)", "O(1)", false, true);
    }

    private static int heapify(int[] arr, int n, int i) {
        int comparisons = 0;
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            comparisons++;
            if (arr[left] > arr[largest]) largest = left;
        }

        if (right < n) {
            comparisons++;
            if (arr[right] > arr[largest]) largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            comparisons += heapify(arr, n, largest);
        }

        return comparisons;
    }

    public static SortResult countingSort(int[] arr) {
        int[] copy = arr.clone();
        long startTime = System.nanoTime();
        int comparisons = 0, swaps = 0;

        int max = Integer.MIN_VALUE;
        for (int value : copy) if (value > max) max = value;

        int[] count = new int[max + 1];
        for (int value : copy) count[value]++;

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                copy[index++] = i;
                swaps++;
            }
        }

        long endTime = System.nanoTime();
        return new SortResult("Counting Sort", endTime - startTime, comparisons, swaps,
                "O(n + k)", "O(k)", true, false);
    }

    public static SortResult radixSort(int[] arr) {
        int[] copy = arr.clone();
        long startTime = System.nanoTime();
        int comparisons = 0, swaps = 0;

        int max = Integer.MIN_VALUE;
        for (int value : copy) if (value > max) max = value;

        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortByDigit(copy, exp);

        long endTime = System.nanoTime();
        return new SortResult("Radix Sort", endTime - startTime, comparisons, swaps,
                "O(nk)", "O(n + k)", true, false);
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int value : arr) count[(value / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }
}
