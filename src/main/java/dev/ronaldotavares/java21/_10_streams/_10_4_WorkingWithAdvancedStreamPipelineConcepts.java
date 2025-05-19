package dev.ronaldotavares.java21._10_streams;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _10_4_WorkingWithAdvancedStreamPipelineConcepts {
    public static void main(String[] args) {
        System.out.println("Working with Advanced Stream Pipeline Concepts");
        linkingStreamsToTheUnderlyingData();
        chainingOptionals();
        checkedExceptionsAndFunctionalInterfaces();
        usingBasicCollectors();
        collectingIntoMaps();
        groupingPartitioningAndMapping();
        teeingCollectors();
        usingASpliterator();
    }

    private static void linkingStreamsToTheUnderlyingData(){
        System.out.println("Linking Streams to the Underlying Data");

        var cats = new ArrayList<String>();
        cats.add("Annie");
        cats.add("Ripley");
        var stream = cats.stream();
        cats.add("KC");
        System.out.println(stream.count());
    }

    private static void chainingOptionals(){
        System.out.println("Chaining Optionals");

        Optional<Integer> optional1 = Optional.empty();
        oldThreeDigit(optional1);
        threeDigit(optional1);

        Optional<Integer> optional2 = Optional.of(4);
        oldThreeDigit(optional2);
        threeDigit(optional2);

        Optional<Integer> optional3 = Optional.of(123);
        oldThreeDigit(optional3);
        threeDigit(optional3);

        Optional<String> optional = Optional.of("some text");
        Optional<Integer> result = optional.map(String::length);
        System.out.println(result);

//        Optional<Integer> result = optional
//                .map(ChainingOptionals::calculator); // DOES NOT COMPILE

        Optional<Integer> result1 = optional
                .flatMap(_10_4_WorkingWithAdvancedStreamPipelineConcepts::calculator);
        System.out.println(result1);
    }

    private static void oldThreeDigit(Optional<Integer> optional) {
        if (optional.isPresent()) { // outer if
            var num = optional.get();
            var string =
                    "" + num;
            if (string.length() == 3) // inner if
                System.out.println(string);
        }
    }

    private static void threeDigit(Optional<Integer> optional) {
        optional.map(n -> "" + n) // part 1
                .filter(s -> s.length() == 3) // part 2
                .ifPresent(System.out::println); // part 3
    }

    private static Optional<Integer> calculator(String text) {
        // Calculation logic here
        return Optional.of(text.length());
    }

    private static void checkedExceptionsAndFunctionalInterfaces(){
        System.out.println("Checked Exceptions and Functional Interfaces");

        try {
            good();
        } catch (IOException e) {
            System.out.println(e);
        }

        //ugly way
        Supplier<List<String>> s = () -> {
            try {
                return ExceptionCaseStudy.create();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        //cleaner way
        Supplier<List<String>> s2 = _10_4_WorkingWithAdvancedStreamPipelineConcepts::createSafe;
    }

    private static void good() throws IOException {
        ExceptionCaseStudy.create().stream().count();
    }

//    private void bad() throws IOException {
//        Supplier<List<String>> s = ExceptionCaseStudy::create; // DOES NOT COMPILE (unhandled exception type IOException)
//    }

    private static List<String> createSafe() {
        try {
            return ExceptionCaseStudy.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void usingBasicCollectors(){
        System.out.println("Using Basic Collectors");

        joining();
        average();
        toCollection();
        toList();
    }

    private static void joining(){
        System.out.println("Joining");

        var ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result); // lions, tigers, bears
    }

    private static void average(){
        System.out.println("Average");

        var ohMy = Stream.of("lions", "tigers", "bears");
        Double result = ohMy.collect(Collectors.averagingInt(String::length));
        System.out.println(result); // 5.333...
    }

    private static void toCollection(){
        System.out.println("To Collection");

        var ohMy = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result = ohMy
                .filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result);
    }

    private static void toList(){
        System.out.println("To List");

        Stream<String> ohMy1 = Stream.of("lions","tigers","bears");
        List<String> mutableList = ohMy1.collect(Collectors.toList());
        System.out.println(mutableList);

        Stream<String> ohMy2 = Stream.of("lions","tigers","bears");
        List<String> immutableList = ohMy2.toList();
        System.out.println(immutableList);

        mutableList.add("zebras"); // No issues
//        immutableList.add("zebras"); // UnsupportedOperationException
    }

    private static void collectingIntoMaps(){
        System.out.println("Collecting into Maps");

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = ohMy.collect(
                Collectors.toMap(s -> s, String::length));
        System.out.println(map); // {lions=5, bears=5, tigers=6}

        //s -> s is the same as Function.identity()
        var ohMy1 = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map1 = ohMy1.collect(
                Collectors.toMap(Function.identity(), String::length));
        System.out.println(map1); // {lions=5, bears=5, tigers=6}


        var ohMy2 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map2 = ohMy2.collect(Collectors.toMap(
                String::length,
//                k -> k)); // BAD
                k -> k,
                (s1, s2) -> s1 + ", " + s2));
        System.out.println(map2); // {5=lions,bears, 6=tigers}
        System.out.println(map2.getClass()); // class java.util.HashMap

        var ohMy3 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, String> map3 = ohMy3.collect(Collectors.toMap(
                String::length,
                k -> k,
                (s1, s2) -> s1 + "," + s2,
                TreeMap::new));
        System.out.println(map3); // {5=lions,bears, 6=tigers}
        System.out.println(map3.getClass()); // class java.util.TreeMap
    }

    private static void groupingPartitioningAndMapping(){
        System.out.println("Grouping, Partitioning and Mapping");

        grouping();
        partitioning();
        counting();
    }

    private static void grouping(){
        System.out.println("Grouping");

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map = ohMy.collect(
                Collectors.groupingBy(String::length));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}

        var ohMy1 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map1 = ohMy1.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.toSet()));
        System.out.println(map1); // {5=[lions, bears], 6=[tigers]}
        System.out.println(map1.getClass()); // {5=[lions, bears], 6=[tigers]}

        var ohMy2 = Stream.of("lions","tigers","bears");
        TreeMap<Integer, Set<String>> map2 = ohMy2.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toSet()));
        System.out.println(map2); // {5=[lions, bears], 6=[tigers]}
        System.out.println(map2.getClass()); // {5=[lions, bears], 6=[tigers]}

        var ohMy3 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, List<String>> map3 = ohMy3.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toList()));
        System.out.println(map3);
        System.out.println(map3.getClass());

        var ohMy4 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map4 = ohMy4.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                s -> s.charAt(0),
                                Collectors.minBy((a, b) -> a - b))));
        System.out.println(map4); // {5=Optional[b], 6=Optional[t]}

//        same as before code above
//        var ohMy5 = Stream.of("lions", "tigers", "bears");
//        var map5 = ohMy.collect(groupingBy(String::length,
//                mapping(s -> s.charAt(0), minBy((a, b) -> a - b))));
//        System.out.println(map); // {5=Optional[b], 6=Optional[t]}
    }

    private static void partitioning(){
        System.out.println("Partitioning");

        var ohMy = Stream.of("lions","tigers","bears");
        Map<Boolean, List<String>> map = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(map); // {false=[tigers], true=[lions, bears]}

        var ohMy1 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map1 = ohMy1.collect(
                Collectors.partitioningBy(s -> s.length() <= 7));
        System.out.println(map1); // {false=[], true=[lions, tigers, bears]}

        var ohMy2 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, Set<String>> map2 = ohMy2.collect(
                Collectors.partitioningBy(
                        s -> s.length() <= 7,
                        Collectors.toSet()));
        System.out.println(map2); // {false=[], true=[lions, tigers, bears]}
    }

    private static void counting(){
        System.out.println("Counting");

        var ohMy = Stream.of("lions","tigers","bears");
        Map<Integer, Long> map = ohMy.collect(
                Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(map);
    }

    private static void teeingCollectors(){
        System.out.println("Teeing Collectors");

        record Separations(String spaceSeparated, String commaSeparated) {}

        var list = List.of("x", "y", "z");
        Separations result = list.stream()
                .collect(Collectors.teeing(
                        Collectors.joining(" "),
                        Collectors.joining(", "),
        (s, c) -> new Separations(s, c)));
        System.out.println(result);
    }

    private static void usingASpliterator(){
        System.out.println("Using a Spliterator");

        var stream = List.of("bird-", "bunny-", "cat-", "dog-", "fish-", "lamb-", "mouse-");
        Spliterator<String> originalBagOfFood = stream.spliterator();
        Spliterator<String> emmasBag = originalBagOfFood.trySplit();
        emmasBag.forEachRemaining(System.out::print);  // bird-bunny-cat-
        System.out.println();

        Spliterator<String> jillsBag = originalBagOfFood.trySplit();
        jillsBag.tryAdvance(System.out::print);        // dog-
        System.out.println();
        jillsBag.forEachRemaining(System.out::print);  // fish-
        System.out.println();

        originalBagOfFood.forEachRemaining(System.out::print); // lamb-mouse-
        System.out.println();

        var originalBag = Stream.iterate(1, n -> ++n)
                .spliterator();
        Spliterator<Integer> newBag = originalBag.trySplit();
        newBag.tryAdvance(System.out::println); // 1
        newBag.tryAdvance(System.out::println); // 2
        newBag.tryAdvance(System.out::println); // 3
    }
}

class ExceptionCaseStudy {
    public static List<String> create() throws IOException {
        throw new IOException();
    }
}
