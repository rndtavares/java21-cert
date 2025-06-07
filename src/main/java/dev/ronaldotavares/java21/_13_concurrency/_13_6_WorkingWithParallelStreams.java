package dev.ronaldotavares.java21._13_concurrency;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
}
