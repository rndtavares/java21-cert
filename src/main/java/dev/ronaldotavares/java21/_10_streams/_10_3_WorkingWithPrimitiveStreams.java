package dev.ronaldotavares.java21._10_streams;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class _10_3_WorkingWithPrimitiveStreams {
    public static void main(String[] args) {
        System.out.println("Working with Primitive Streams");
        introducing();
        creatingPrimitiveStreams();
        mappingStreams();
        usingOptionalWithPrimitiveStreams();
        summarizingStatistics();
    }

    private static void introducing(){
        System.out.println("Introducing Primitive Streams");
        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.reduce(0, (s, n) -> s + n)); // 6

        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        System.out.println(stream1.mapToInt(x -> x).sum()); // 6

        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble avg = intStream.average();
        System.out.println(avg.getAsDouble()); // 2.0
    }

    private static void creatingPrimitiveStreams(){
        System.out.println("Creating Primitive Streams");

        DoubleStream empty = DoubleStream.empty();

        DoubleStream oneValue = DoubleStream.of(3.14);
        oneValue.forEach(System.out::println);
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        varargs.forEach(System.out::println);

        var random = DoubleStream.generate(Math::random);
        var fractions = DoubleStream.iterate(.5, d -> d / 2);
        random.limit(3).forEach(System.out::println);
        fractions.limit(3).forEach(System.out::println);

        IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
        count.forEach(System.out::print); // 12345

        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::print); // 12345

        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(System.out::print); // 12345
        System.out.println();
    }

    private static void mappingStreams(){
        System.out.println("Mapping Streams");

        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream = objStream.mapToInt(s -> s.length());
        intStream.forEach(System.out::println);

        var integerList = new ArrayList<Integer>();
        IntStream stream = IntStream.rangeClosed(1, 10);
        integerList = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        IntStream ints = integerList.stream()
                .flatMapToInt(x -> IntStream.of(x));
        ints.forEach(System.out::println);

        DoubleStream doubles = integerList.stream()
                .flatMapToDouble(x -> DoubleStream.of(x));
        doubles.forEach(System.out::println);

        LongStream longs = integerList.stream()
                .flatMapToLong(x -> LongStream.of(x));
        longs.forEach(System.out::println);

        LongStream stream1 = LongStream.range(1, 6);
        Stream<Long> longStream = stream1.mapToObj(x -> x);
        longStream.forEach(System.out::println);

        IntStream stream2 = IntStream.range(1, 6);
        Stream<Integer> integerStream1 = stream2.boxed();
        integerStream1.forEach(System.out::println);
    }

    private static void usingOptionalWithPrimitiveStreams(){
        System.out.println("Using Optional with Primitive Streams");

        var stream = IntStream.rangeClosed(1,10);
        OptionalDouble optional = stream.average();

        optional.ifPresent(System.out::println);                  // 5.5
        System.out.println(optional.getAsDouble());               // 5.5
        System.out.println(optional.orElseGet(() -> Double.NaN)); // 5.5

        LongStream longs = LongStream.of(5, 10);
        long sum = longs.sum();
        System.out.println(sum); // 15

        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
//        OptionalDouble min = doubles.min();    // runs infinitely
    }

    private static void summarizingStatistics(){
        System.out.println("Summarizing Statistics");

        var ints = IntStream.rangeClosed(1,3);
        OptionalInt optional = ints.max();
        int i = optional.orElseThrow(RuntimeException::new);
        System.out.println(i);

        ints = IntStream.rangeClosed(1,3);
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) throw new RuntimeException();
        int j = stats.getMax() - stats.getMin();
        System.out.println(j);

        IntSummaryStatistics intSummaryStatistics = IntStream.empty().summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
    }
}
