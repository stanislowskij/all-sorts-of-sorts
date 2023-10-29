package sorts;

import java.util.ArrayList;
import java.util.List;

// Rough early implementation for now
public class MergeSort {
    public static <E extends Comparable<E>> void sort(List<E> list) {
        sort(list, 0, list.size() - 1);
    }

    private static <E extends Comparable<E>> void sort(List<E> list,
            int start, int end) {
        // We don't need to split this subarray if it contains less than 2 elements
        // (base case)
        if (end - start + 1 < 2) {
            return;
        }
        // Split array down the middle
        int middle = (end + start) / 2;
        // Call merge sort recursively on the two halves
        sort(list, start, middle);
        sort(list, middle + 1, end);
        // Merge the two halves we end up with, once we get back from these recursive
        // calls.
        merge(list, start, middle, end);
    }

    private static <E extends Comparable<E>> void merge(List<E> list,
            int start, int middle, int end) {
        // Begin merging two halves of the array given by start, end, and the middle
        // partition point, using a sentinel for each half.
        // We should merge the two sorted halves into the portion of the original list
        // that corresponds to the two halves we're merging.
        int i = start;
        int j = middle + 1; // beginning of second array
        int s = 0; // stepper for where to copy into list

        // Allocate a copy array to temporarily store merged subarray, then copy that
        // into list
        List<E> sortedPortion = new ArrayList<E>(end - start);

        while (i <= middle && j <= end) {
            // Compare values at i and j, place smaller one first.
            if (list.get(i).compareTo(list.get(j)) <= 0) {
                sortedPortion.add(s, list.get(i));
                i++;
            } else {
                sortedPortion.add(s, list.get(j));
                j++;
            }
            s++;
        }
        // Take any stragglers from both sorted halves; we're gonna have at least one if
        // the partitions are unequal in length
        while (i <= middle) {
            sortedPortion.add(s, list.get(i));
            i++;
            s++;
        }
        while (j <= end) {
            sortedPortion.add(s, list.get(j));
            j++;
            s++;
        }

        // Finally, copy the completed sorted portion into the original list
        for (int r = 0; r < sortedPortion.size(); r++) {
            list.set(r + start, sortedPortion.get(r));
        }
    }
}
