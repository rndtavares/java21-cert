package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.*;

public class _9_3_WorkingWithSetMethods {
    public static void main(String[] args) {
        setMethods();
    }

    private static void setMethods() {
        System.out.println("Set methods");

        Set<Character> letters = Set.of('c', 'a', 't');
        Set<Character> copy = Set.copyOf(letters);

        Set<Integer> set = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        boolean b1 = set.add(66); // true
        linkedHashSet.add(66); // true
        treeSet.add(66); // true
        boolean b2 = set.add(10); // true
        linkedHashSet.add(10); // true
        treeSet.add(10); // true
        boolean b3 = set.add(66); // false
        linkedHashSet.add(66); // false
        treeSet.add(66); // false
        boolean b4 = set.add(8); // true
        linkedHashSet.add(8); // true
        treeSet.add(8); // true

        for (Integer value: set)
            System.out.print(value + " , "); // 66,8,10,

        System.out.println();

        for (Integer value: linkedHashSet)
            System.out.print(value + " , "); // 66,10,8,

        System.out.println();

        for (Integer value: treeSet)
            System.out.print(value + " , "); // 8,10,66,

        System.out.println();
    }
}
