package dev.ronaldotavares.java21._13_concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _13_6_WorkingWithParallelStreams {
    public static void main(String[] args) {
        System.out.println("Working with Parallel Streams");

        var workingWithParallelStreams = new _13_6_WorkingWithParallelStreams();

        workingWithParallelStreams.generatingRandomNumbers();
        workingWithParallelStreams.creatingParallelStreams();
        workingWithParallelStreams.performingAParallelDecomposition();
        workingWithParallelStreams.processingParallelReductions();
    }

    void generatingRandomNumbers(){
        System.out.println("Generating Random Numbers");

        ThreadLocalRandom.current()
                .ints()
                .limit(5)
                .forEach(System.out::println); // Prints 5 random ints
    }

    void creatingParallelStreams(){
        System.out.println("Creating Parallel Streams");

        Collection<Integer> collection = List.of(1, 2);
        Stream<Integer> p1 = collection.stream().parallel();
        Stream<Integer> p2 = collection.parallelStream();
    }

    void performingAParallelDecomposition(){
        System.out.println("Performing a Parallel Decomposition");

        // Sequential Stream
        long start = System.currentTimeMillis();
        List.of(1, 2, 3, 4, 5)
                .stream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        var timeTaken = (System.currentTimeMillis()-start)/1000;
        System.out.println("Sequential Stream Time: " + timeTaken + " seconds");

        // Parallel Stream
        start = System.currentTimeMillis();
        List.of(1, 2, 3, 4, 5)
                .parallelStream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        timeTaken = (System.currentTimeMillis()-start)/1000;
        System.out.println("Paralel Stream Time: " + timeTaken + " seconds");

        start = System.currentTimeMillis();
        List.of(1, 2, 3, 4, 5)
                .parallelStream()
                .map(w -> doWork(w))
                .forEachOrdered(s -> System.out.print(s + " "));

        System.out.println();
        timeTaken = (System.currentTimeMillis()-start)/1000;
        System.out.println("Ordered Paralel Stream Time: " + timeTaken + " seconds");
    }

    private static int doWork(int input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        return input;
    }

    void processingParallelReductions(){
        System.out.println("Processing Parallel Reductions");

        performingOrderBasedTasks();
        combiningResultsWithReduce();
        combiningResultsWithCollect();
        requirementsForParallelReductionWithCollect();
    }

    void performingOrderBasedTasks(){
        System.out.println("Performing Order-Based Tasks");

        System.out.println("findAny (1-6) in parallel:");
        System.out.println(List.of(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .findAny()
                .get());

        //sorting parallel streams
        System.out.println("1-100 in parallel, sorted:");
        IntStream.range(1,100).parallel().sorted().forEach(System.out::println);
        System.out.println("1-100 in parallel, sorted and with forEachOrdered:");
        IntStream.range(1,100).parallel().sorted().forEachOrdered(System.out::println);

        //unordered streams
        System.out.println("1-6 in serial, unordered:");
        List.of(1, 2, 3, 4, 5, 6).stream().unordered().forEach(System.out::println);
        System.out.println("1-6 in parallel, unordered:");
        List.of(1, 2, 3, 4, 5, 6).stream().unordered().parallel().forEach(System.out::println);
    }

    void combiningResultsWithReduce(){
        System.out.println("Combining Results with reduce");

        System.out.println(List.of('w', 'o', 'l', 'f')
                .parallelStream()
                .reduce("",
                        (s1, c) -> s1 + c,
                        (s2, s3) -> s2 + s3)); // "wolf"

        System.out.println("problematic acumulator");
        System.out.println(List.of(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .reduce(0, (a, b) -> (a - b)));

        System.out.println("problematic identity");
        System.out.println(List.of("w", "o", "l", "f")
                .parallelStream()
                .reduce("X", String::concat)); // "XwXoXlXf"

    }

    void combiningResultsWithCollect(){
        System.out.println("Combining Results with collect");

        Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,
                Set::add,
                Set::addAll);
        System.out.println(set);  // [f, l, o, w]
    }

    void requirementsForParallelReductionWithCollect(){
        System.out.println("Requirements for Parallel Reduction with collect");
//        parallelStream.collect(Collectors.toSet()); // Not a parallel reduction

        parallelReductionWithToConcurrentMap();
        parallelReductionWithGroupingByConcurrent();
        avoidingStatefulStreams();
    }

    void parallelReductionWithToConcurrentMap(){
        System.out.println("Parallel Reduction with toConcurrentMap");

        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, String> map = ohMy
                .collect(Collectors.toConcurrentMap(String::length,
                        k -> k,
                        (s1, s2) -> s1 + "," + s2));
        System.out.println(map); // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap
    }

    void parallelReductionWithGroupingByConcurrent(){
        System.out.println("Parallel Reduction with groupingByConcurrent");

        var ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> map = ohMy.collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}
    }

    void avoidingStatefulStreams(){
        System.out.println("Avoiding Stateful Streams");

        System.out.println("with serial stream and stateful operation");
        var sertialList = addValues(IntStream.range(1, 11));
        System.out.print(sertialList); // [2, 4, 6, 8, 10]

        System.out.println();

        System.out.println("with parallel stream and stateful operation");
        var parallelList = addValues(IntStream.range(1, 11).parallel());
        System.out.print(parallelList); // [6, 8, 10, 2, 4]

        System.out.println();

        System.out.println("with serial stream and not stateful operation");
        var sertialListBetter = addValuesBetter(IntStream.range(1, 11));
        System.out.print(sertialListBetter); // [2, 4, 6, 8, 10]

        System.out.println();

        System.out.println("with parallel stream and not stateful operation");
        var parallelBetterList = addValuesBetter(IntStream.range(1, 11).parallel());
        System.out.print(parallelBetterList); // [2, 4, 6, 8, 10]

    }

    List<Integer> addValues(IntStream source) {
        var data = Collections.synchronizedList(new ArrayList<Integer>());
        source.filter(s -> s % 2 == 0)
                .forEach(i -> { data.add(i); }); // STATEFUL: DON'T DO THIS!
        return data;
    }

    public List<Integer> addValuesBetter(IntStream source) {
        return source.filter(s -> s % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
    }
}