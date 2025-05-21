public class SortResult {
    public String algorithmName;
    public long timeTakenNs;
    public int comparisons;
    public int swaps;
    public String timeComplexity;   // theoretical time complexity
    public String spaceComplexity;  // theoretical space complexity
    public boolean stable;
    public boolean inPlace;

    // Algorithm name constants
    public static final String BUBBLE_SORT = "Bubble Sort";
    public static final String SELECTION_SORT = "Selection Sort";
    public static final String INSERTION_SORT = "Insertion Sort";
    public static final String MERGE_SORT = "Merge Sort";
    public static final String QUICK_SORT = "Quick Sort";
    public static final String COUNTING_SORT = "Counting Sort";  // replaced shell sort
    public static final String HEAP_SORT = "Heap Sort";
    public static final String RADIX_SORT = "Radix Sort";

    public SortResult(String algorithmName, long timeTakenNs, int comparisons, int swaps,
                      String timeComplexity, String spaceComplexity, boolean stable, boolean inPlace) {
        this.algorithmName = algorithmName;
        this.timeTakenNs = timeTakenNs;
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
        this.stable = stable;
        this.inPlace = inPlace;
    }
}
