package main.java;

import java.util.*;

public class SortUtil {
    /*
     * A universal helper method for swapping list elements.
     */
    public static <E extends Comparable<E>> void swap(List<E> list, int first, int second) {
        E temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    /*
     * A universal helper method for verifying whether or not a list is sorted.
     */
    public static <E extends Comparable<E>> boolean isSorted(List<E> list) {
        if (list.size() <= 1) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i)).compareTo(list.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * A universal helper method for verifying whether or not a list is sorted given
     * certain criteria.
     */
    public static <E extends Comparable<E>> boolean isSorted(List<E> list, String param) throws Exception {
        if (list.size() <= 1) {
            return true;
        }
        switch (param) {
            // Ascending order
            case "order:asc":
                for (int i = 1; i < list.size(); i++) {
                    if ((list.get(i)).compareTo(list.get(i - 1)) < 0) {
                        return false;
                    }
                }
                // Descending order
            case "order:desc":
                for (int i = 1; i < list.size(); i++) {
                    if ((list.get(i)).compareTo(list.get(i - 1)) > 0) {
                        return false;
                    }
                }
            default:
                throw new Exception("Invalid String argument specified for isSorted()");
        }
    }
}
