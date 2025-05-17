package dev.ronaldotavares.java21._10_streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _10_2_UsingStreams {
    public static void main(String[] args) {
        System.out.println("Using Streams");
        creatingFiniteStreams();
        creatingParalelStreams();
        creatingInfiniteStreams();
        terminalOperations();
        intermediateOperations();
        badPractices();
        puttingTogetherThePipeline();
    }

    private static void creatingInfiniteStreams(){
        System.out.println("Creating infinite streams");
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        System.out.println(randoms); // java.util.stream.ReferencePipeline$3@4517d9a3

        Stream<Integer> oddUnder100 = Stream.iterate(
                1,             // seed
                n -> n < 100,  // Predicate to specify when done
                n -> n + 2);   // UnaryOperator to get next value
    }

    private static void creatingParalelStreams(){
        System.out.println("Creating parallel streams");
        var list = List.of("a", "b", "c");
        Stream<String> fromListParallel = list.parallelStream();
    }

    private static void creatingFiniteStreams(){
        System.out.println("Creating finite streams");
        Stream<String> empty = Stream.empty(); // count = 0
        Stream<Integer> singleElement = Stream.of(1); // count = 1
        Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
        var list = List.of("a", "b", "c");
        Stream<String> fromList = list.stream();
    }

    private static void terminalOperations(){
        System.out.println("Terminal operations");
        count();
        minMax();
        findAnyFindFirst();
        anyMatchAllMatchNoneMatch();
        forEach();
        reduce();
        collect();
        utilCollectors();
    }

    private static void count(){
        System.out.println("Count");

        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(s.count()); // 3
    }

    private static void minMax(){
        System.out.println("Min/Max");

        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println); // ape

        Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
        System.out.println(minEmpty.isPresent()); // false
    }

    private static void findAnyFindFirst(){
        System.out.println("Find any / find first");

        Stream<String> s = Stream.of("monkey","gorilla","bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");

        s.findAny().ifPresent(System.out::println); // monkey (usually)
        infinite.findAny().ifPresent(System.out::println); // chimp
    }

    private static void anyMatchAllMatchNoneMatch(){
        System.out.println("Any match / all match / none match");
        var list = List.of("monkey","2","chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true

//        System.out.println(infinite.allMatch(pred)); // Never terminates
    }

    private static void forEach(){
        System.out.println("For each");

        Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
        s.forEach(System.out::println); // MonkeyGorillaBonobo

//        Stream<Integer> s1 = Stream.of(1);
//        for (Integer i : s1) {} //DOES NOT COMPILE
    }

    private static void reduce(){
        System.out.println("Reduce");

        var array = new String[] { "w","o","l","f"};
        var result = "";
        for (var s: array) result = result + s;
        System.out.println(result); // wolf

        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        String word1 = stream1.reduce("", (s, c) -> s + c);
        System.out.println(word1); // wolf

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", String::concat);
        System.out.println(word); // wolf

        Stream<Integer> stream2 = Stream.of(3, 5, 6);
        System.out.println(stream2.reduce(1, (a, b) -> a*b)); // 90

        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty.reduce(op).ifPresent(System.out::println); // no output
        oneElement.reduce(op).ifPresent(System.out::println); // 3
        threeElements.reduce(op).ifPresent(System.out::println); // 90

        Stream<String> stream3 = Stream.of("w", "o", "l", "f!");
        int length = stream3.reduce(0, (i, s) -> i+s.length(), (a, b) -> a+b);
        System.out.println(length); // 5
    }

    private static void collect(){
        System.out.println("Collect");

        Stream<String> stream = Stream.of("w", "o", "l", "f");

        StringBuilder word = stream.collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append);
        System.out.println(word); // wolf

        stream = Stream.of("w", "o", "l", "f");

    }

    private static void utilCollectors(){
        System.out.println("Util collectors");

        Stream<String> stream = Stream.of("w", "o", "l", "f");

        TreeSet<String> set = stream.collect(
                TreeSet::new,
                TreeSet::add,
                TreeSet::addAll);
        System.out.println(set); // [f, l, o, w]

        stream = Stream.of("w", "o", "l", "f");

        TreeSet<String> set1 =
                stream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set1); // [f, l, o, w]

        stream = Stream.of("w", "o", "l", "f");

        Set<String> set2 = stream.collect(Collectors.toSet());
        System.out.println(set2); // [f, w, l, o]
    }

    private static void intermediateOperations(){
        System.out.println("Intermediate operations");
        filter();
        distinct();
        limitSkip();
        map();
        flatMap();
        concat();
        sorted();
        peek();
    }

    private static void filter(){
        System.out.println("Filter");

        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");

        s.filter(x -> x.startsWith("m"))
                .forEach(System.out::println); // monkey
    }

    private static void distinct(){
        System.out.println("Distinct");

        Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
        s.distinct()
                .forEach(System.out::println); // duckgoose
    }

    private static void limitSkip(){
        System.out.println("Limit/Skip");

        Stream<Integer> s = Stream.iterate(1, n -> n + 1);
        s.skip(5)
            .limit(2)
            .forEach(System.out::println); // 67
    }

    private static void map(){
        System.out.println("Map");

        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.map(String::length)
                .forEach(System.out::println); // 676
    }

    private static void flatMap(){
        System.out.println("Flat map");

        List<String> zero = List.of();
        var one = List.of("Bonobo");
        var two = List.of("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);

        animals.flatMap(m -> m.stream())
                .forEach(System.out::println);
    }

    private static void concat(){
        System.out.println("Concat");

        var one = Stream.of("Bonobo");
        var two = Stream.of("Mama Gorilla", "Baby Gorilla");

        Stream.concat(one, two).forEach(System.out::println);
    }

    private static void sorted(){
        System.out.println("Sorted");

        Stream<String> s = Stream.of("brown-", "bear-");
        s.sorted()
                .forEach(System.out::println); // bear-brown-

        Stream<String> s1 = Stream.of("brown bear-","grizzly-");
        s1.sorted(Comparator.reverseOrder())
                .forEach(System.out::println); // grizzly-brown bear-

        Stream<String> s2 = Stream.of("brown bear- ", "grizzly- ");
//        s2.sorted(Comparator::reverseOrder); // DOES NOT COMPILE
    }

    private static void peek(){
        System.out.println("Peek");

        var stream = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream.filter(s -> s.startsWith("g"))
                .peek(System.out::println).count(); // grizzly
    }

    private static int count = 20;
    private static void badPractices(){
        System.out.println("Bad practices");
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(p -> count++);
        System.out.println(count);

        var numbers = new ArrayList<>();
        var letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');
        Stream<List<?>> stream = Stream.of(numbers, letters);
        stream.map(List::size).forEach(System.out::print); // 11

        System.out.println();

        Stream<List<?>> bad = Stream.of(numbers, letters);
        bad.peek(x -> x.remove(0))
                .map(List::size)
                .forEach(System.out::print); // 00
    }

    private static void puttingTogetherThePipeline(){
        System.out.println("Putting together the pipeline");

        var list = List.of("Toby", "Anna", "Leroy", "Alex");
        List<String> filtered = new ArrayList<>();

        for (String name: list)
            if (name.length() == 4) filtered.add(name);

        Collections.sort(filtered);
        var iter = filtered.iterator();
        if (iter.hasNext()) System.out.println(iter.next());
        if (iter.hasNext()) System.out.println(iter.next());

//        list.stream().filter(n -> n.length() == 4).sorted()
//                .limit(2).forEach(System.out::println);

        list.stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println); // Alex Anna

//        Stream.generate(() -> "Elsa")
//                .filter(n -> n.length() == 4)
//                .sorted() //Out of memory -> nunca executa pois precisa que o stream seja finito
//                .limit(2)
//                .forEach(System.out::println);

        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println); // Elsa Elsa

//        Stream.generate(() -> "Olaf Lazisson")
//                .filter(n -> n.length() == 4)
//                .limit(2) // Nunca termina pois não recebe dois elementos
//                .sorted()
//                .forEach(System.out::println);

        long count = Stream.of("goldfish","finch")
                .filter(s -> s.length()> 5)
                .collect(Collectors.toList())
                .stream()
                .count();
        System.out.println(count); // 1

        List<String> helper = Stream.of("goldfish","finch")
                .filter(s -> s.length()> 5)
                .collect(Collectors.toList());
        long count1 = helper.stream()
                .count();
        System.out.println(count1);
    }
}
