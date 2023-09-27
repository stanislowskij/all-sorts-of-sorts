package sorts;

import java.util.List;

import main.SortUtil;

public class HeapSort {
    // For a simplified explanation of Heap Sort, see this video by udiprod:
    // https://www.youtube.com/watch?v=H5kAcmGOn4Q

    public static <E extends Comparable<E>> void sort(List<E> list) {
        int n = list.size();
        /*
         * Begin by constructing a max heap ("heapify"). We do this by iterating through the heap starting
         * from the parent of the last leaf (index retrieved by n/2-1). For each pass, we sift
         * the node's children and its descendants down the heap if necessary. See siftDown() method below.
         */
        for(int i = n / 2 - 1; i >= 0; i--) {
            // We consider the heap size to be n for every pass here just to make sure it gets fully heapified.
            siftDown(list, i, n);
        }

        /*
         * Once we have a max heap, we can begin the sorting process.
         * Since a max heap always has its largest element at the top of the heap, we can move this to
         * the last position in the unsorted array and consider it now sorted. As long as we maintain the
         * max heap, this will eventually produce a fully-sorted array. We can stop once we have one element
         * remaining.
         */
        int heapSize = n;
        while(heapSize > 1) {
            // Swap the first and last positions of the unsorted array.
            SortUtil.swap(list, 0, heapSize-1);
            // We consider this now sorted, so heapify from the root node without considering sorted elements
            // (i.e. elements no longer within our range of heapSize.
            heapSize--;
            siftDown(list, 0, heapSize);
        }
        // Verify that this produces a sorted array by printing the output:
        System.out.println(list.toString());
    }
    private static <E extends Comparable<E>> void siftDown(List<E> list, int index, int heapSize) {
        // This method drives the process of heapSort.
        /*
         * Given a heap, we consider it to be a max heap if any give node's children's values are smaller than
         * its own value.
         * 
         * We can make this true for a given heap by comparing the root node with its children, swapping places
         * with the larger child (if any). We then work down the tree from the child's old position in the heap
         * until we either reach the bottom (the node we check has no children according to our heapSize constraint) 
         * or no more swaps need to be made.
         */
        // The index of the left child of a given node is calculated as 2i+1.
        int l = 2 * index + 1;
        // The index of the right child of a given node is calculated as 2i+2.
        int r = 2 * index + 2;
        // Start by assuming the parent node has the largest value. This can change later.
        int indexOfLargest = index;

        E largest = list.get(index);

        // Case 0: Neither left nor right children exist as far as our heapSize is concerned. 
        // We can stop before doing any unnecessary checks.
        if(l >= heapSize && r >= heapSize) {
            return;
        }
        // Case 1: Existing left child has a larger value than its parent -> set for swap.
        if(l < heapSize && list.get(l).compareTo(list.get(index)) > 0) {
            indexOfLargest = l;
            largest = list.get(l);
        }
        // Case 2: Existing right child has a larger value than both its parent and left sibling -> set for swap.
        if(r < heapSize && list.get(r).compareTo(largest) > 0) {
            indexOfLargest = r;
            largest = list.get(r);
        }
        // Case 3: Neither child ended up being larger, so no changes need to be made. We can stop here.
        if(largest.compareTo(list.get(index)) == 0) {
            return;
        }
        // Now we swap the parent node with whatever child is largest, and recursively sift it down from the 
        // child's old index. This will eventually produce a proper max heap from our original root node.
        SortUtil.swap(list, index, indexOfLargest);
        siftDown(list, indexOfLargest, heapSize);
    } 
    public String toString() {
        return new StringBuilder()
            .append("Runtime [best case]: O(nlogn)\n")
            .append("Runtime [worst case]: O(nlogn)\n")
            .append("Runtime [average case]: O(nlogn)")
            .append("Memory usage: sorts in place\n")
            .append("Stable: false")
            // Unlike some other sorting algorithms, HeapSort cannot easily be implemented stably 
            // due to the nature by which it sorts objects in the list. When it compares equal elements,
            // it will inevitably swap their places.
            .toString();
    }
}
