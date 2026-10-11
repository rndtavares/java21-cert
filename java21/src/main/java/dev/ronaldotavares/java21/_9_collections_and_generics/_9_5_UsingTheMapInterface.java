package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class _9_5_UsingTheMapInterface {
    public static void main(String[] args) {
        creating();
        comparing();
        iterating();
        getting();
        replacing();
        puttingIfAbsent();
        merging();
    }

    private static void creating(){
        Map.of("key1" , "value1" ,"key2" , "value2");
        Map.ofEntries(
                Map.entry("key1" ,"value1"),
                Map.entry("key2" , "value2"));
    }

    private static void comparing(){
        System.out.println("comparing maps");
        addElementsAndPrint(new HashMap<>());        // koala,giraffe,lion,
        addElementsAndPrint(new LinkedHashMap<>());  // koala,lion,giraffe,
        addElementsAndPrint(new TreeMap<>());        // giraffe,koala,lion,
    }

    private static void addElementsAndPrint(Map<String, String> map) {
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        String food = map.get("koala"); // bamboo
        for (String key: map.keySet())
            System.out.print(key + ",");
        System.out.println();

        System.out.println(map.containsKey("lion")); // true
        System.out.println(map.containsValue("lion")); // false
        System.out.println(map.size()); // 3
        map.clear();
        System.out.println(map.size()); // 0
        System.out.println(map.isEmpty()); // true

//        System.out.println(map.contains("lion")); // DOES NOT COMPILE
    }

    private static void iterating(){
        System.out.println("iterating maps");

        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'a');
        map.put(2, 'b');
        map.put(3, 'c');
        map.forEach((k, v) -> System.out.println(v));

        map.values().forEach(System.out::println);

        map.entrySet().forEach(e ->
                System.out.println(e.getKey() + " " + e.getValue()));
    }

    private static void getting(){
        System.out.println("getting maps");

        Map<Character, String> map = new HashMap<>();
        map.put('x', "spot");

        System.out.println("X marks the " + map.get('x'));             // spot
        System.out.println("X marks the " + map.getOrDefault('x', "")); // spot
        System.out.println("Y marks the " + map.get('y'));             // null
        System.out.println("Y marks the " + map.getOrDefault('y', ""));// ""
    }

    private static void replacing(){
        System.out.println("replacing maps");

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 4);
        Integer original = map.replace(2, 10); // retorna 4
        System.out.println(map);              // {1=2, 2=10}
        map.replaceAll((k, v) -> k + v);
        System.out.println(map);              // {1=3, 2=12}
    }

    public static void puttingIfAbsent(){
        System.out.println("puttingIfAbsent maps");

        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", null);
        favorites.putIfAbsent("Jenny", "Tram"); // Ignored
        favorites.putIfAbsent("Sam", "Tram");   // Added
        favorites.putIfAbsent("Tom", "Tram");   // Updated
        System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
    }

    private static void merging(){
        System.out.println("merging maps");

        BiFunction<String, String, String> mapper =
                (v1, v2) -> v1.length() > v2.length() ? v1 : v2;

        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");

        String jenny = favorites.merge("Jenny", "Skyride", mapper); // keeps "Bus Tour"
        String tom = favorites.merge("Tom", "Skyride", mapper);     // update to "Skyride"

        System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
        System.out.println(jenny);     // Bus Tour
        System.out.println(tom);       // Skyride

        System.out.println("merging with null values or missing keys");
        BiFunction<String, String, String> mapper1 =
                (v1, v2) -> v1.length()> v2.length() ? v1 : v2;

        Map<String, String> favorites1 = new HashMap<>();
        favorites1.put("Sam", null);

        favorites1.merge("Tom","Skyride", mapper1);
        favorites1.merge("Sam","Skyride", mapper1);

        System.out.println(favorites1); // {Tom=Skyride, Sam=Skyride}

        System.out.println("merging with null function return");
        BiFunction<String, String, String> mapper2 = (v1, v2) -> null;

        Map<String, String> favorites2 = new HashMap<>();
        favorites2.put("Jenny", "Bus Tour");
        favorites2.put("Tom", "Bus Tour");

        favorites2.merge("Jenny","Skyride", mapper2);
        favorites2.merge("Sam","Skyride", mapper2);
        System.out.println(favorites2); // {Tom=Bus Tour, Sam=Skyride}
    }


}
