package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.*;

public class _9_8_ReviewingCollectionTypes {
    public static void main(String[] args) {
        unmodifiables();

        Map<String, Integer> map = new TreeMap<>();
        map.put("blue", 41);
        map.put("red", 90);

        List<String> list = Arrays.asList("green", "yellow");
        Set<String> set = new HashSet<>(list);

        Map<String, Integer> mapView = Collections.unmodifiableMap(map);
        Collection<String> collView  = Collections.unmodifiableCollection(list);
        List<String> listView        = Collections.unmodifiableList(list);
        Set<String> setView          = Collections.unmodifiableSet(set);

//        collView.add("pink");     // UnsupportedOperationException
//        setView.remove("green");  // UnsupportedOperationException
//        mapView.put("blue", 42);  // UnsupportedOperationException

        System.out.println(mapView); // {blue=41, red=90}
        System.out.println(collView); // [green, yellow]
        System.out.println(listView); // [green, yellow]
        System.out.println(setView); // [green, yellow]

        map.put("blue", 105);      // altera a TreeMap
        list.set(1, "purple");      // altera o array de base

        System.out.println(mapView);   // {blue=105, red=90}
        System.out.println(collView);  // [green, purple]
        System.out.println(listView);  // [green, purple]
        System.out.println(setView);   // [green, yellow]

        set.add("orange");              // OK — add in setView
        System.out.println(setView);   // [green, yellow, orange]

        list.add("orange");            // UnsupportedOperationException

    }

    private static void unmodifiables(){
        System.out.println("unmodifiable collections");

        Collection<String> coll = Collections.unmodifiableCollection(List.of("brown"));
        List<String> list = Collections.unmodifiableList(List.of("orange"));
        Set<String> set = Collections.unmodifiableSet(Set.of("green"));
        Map<String,Integer> map = Collections.unmodifiableMap(Map.of("red", 1));

    }
}
