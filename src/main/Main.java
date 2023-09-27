package main;

import java.util.*;
import sorts.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(-4, 900, 3, -18, 2, 4, 4, 4, 1));
        
        HeapSort.sort(a);
    }
}
