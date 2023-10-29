package main.java;

import java.util.*;
import sorts.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 1, 3, 4));

        MergeSort.sort(a);

        System.out.println(a);
    }
}
