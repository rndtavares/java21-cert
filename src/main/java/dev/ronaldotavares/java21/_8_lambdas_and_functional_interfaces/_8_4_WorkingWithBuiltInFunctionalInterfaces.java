package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.*;

public class _8_4_WorkingWithBuiltInFunctionalInterfaces {
    public static void main(String[] args) {
        supplier();
        consumer();
        biConsumer();
        predicate();
        biPredicate();
        function();
        biFunction();
        unaryOperator();
        binaryOperator();
        usingConvenienceMethodsOnFunctionalInterfaces();
        learningTheFunctionalInterfacesForPrimitives();
    }

    private static void supplier(){
        System.out.println("Supplier");
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();

        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();

        System.out.println(d1); // 2025-02-20
        System.out.println(d2); // 2025-02-20

        Supplier<StringBuilder> sb1 = StringBuilder::new;
        Supplier<StringBuilder> sb2 = () -> new StringBuilder();

        System.out.println(sb1.get()); // Empty string
        System.out.println(sb2.get()); // Empty string

        Supplier<ArrayList<String>> s3 = ArrayList::new;
        ArrayList<String> a1 = s3.get();
        System.out.println(a1); // []

        System.out.println(s3);
    }

    private static void consumer(){
        System.out.println("Consumer");

        Consumer<String> c1 = x -> System.out.println(x);
        Consumer<String> c2 = System.out::println;

        c1.accept("Annie");
        c2.accept("Annie");

    }

    private static void biConsumer(){
        System.out.println("BiConsumer");

        var map = new HashMap<String, Integer>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        b1.accept("chicken",7);
        b2.accept("chick", 1);

        System.out.println(map); // {chicken=7, chick=1}

        var map1 = new HashMap<String, String>();
        BiConsumer<String, String> b3 = map1::put;
        BiConsumer<String, String> b4 = (k, v) -> map1.put(k, v);
        b3.accept("chicken", "Cluck");
        b4.accept("chick", "Tweep");

        System.out.println(map1); // {chicken=Cluck, chick=Tweep}
    }

    private static void predicate(){
        System.out.println("Predicate");


        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();

        System.out.println(p1.test("")); // true
        System.out.println(p2.test("")); // true
    }

    private static void biPredicate(){
        System.out.println("BiPredicate");

        BiPredicate<String, String> b1 = String::startsWith;
        BiPredicate<String, String> b2 =
                (string, prefix) -> string.startsWith(prefix);

        System.out.println(b1.test("chicken","chick")); // true
        System.out.println(b2.test("chicken","chick")); // true
    }

    private static void function(){
        System.out.println("Function");

        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = s -> s.length();

        System.out.println(f1.apply("cluck")); // 5
        System.out.println(f2.apply("cluck")); // 5
    }

    private static void biFunction(){
        System.out.println("BiFunction");

        BiFunction<String, String, String> b1 = String::concat;
        BiFunction<String, String, String> b2 = (s1, s2) -> s1.concat(s2);

        System.out.println(b1.apply("baby ", "chick")); // baby chick
        System.out.println(b2.apply("baby ", "chick")); // baby chick
    }

    private static void unaryOperator(){
        System.out.println("UnaryOperator");

        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = s -> s.toUpperCase();

        System.out.println(u1.apply("chirp")); // CHIRP
        System.out.println(u2.apply("chirp")); // CHIRP
    }

    private static void binaryOperator(){
        System.out.println("BinaryOperator");

        BinaryOperator<String> b1 = String::concat;
        BinaryOperator<String> b2 = (s1, s2) -> s1.concat(s2);

        System.out.println(b1.apply("baby ", "chick")); // baby chick
        System.out.println(b2.apply("baby ", "chick")); // baby chick
    }

    private static void usingConvenienceMethodsOnFunctionalInterfaces(){
        System.out.println("Using convenience methods on functional interfaces");
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");

        Predicate<String> brownEggs1 = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs1 = s -> s.contains("egg") && !s.contains("brown");

        Predicate<String> brownEggs = egg.and(brown);
        Predicate<String> otherEggs = egg.and(brown.negate());

        Consumer<String> c1 = x -> System.out.print("1: " + x);
        Consumer<String> c2 = x -> System.out.println(",2: " + x);
        Consumer<String> combined = c1.andThen(c2);
        combined.accept("Annie"); // 1: Annie,2: Annie

        Function<Integer, Integer> before = x -> x + 1;
        Function<Integer, Integer> after = x -> x * 2;
        Function<Integer, Integer> combined1 = after.compose(before);
        System.out.println(combined1.apply(3)); // 8
    }

    private static void learningTheFunctionalInterfacesForPrimitives(){
        System.out.println("Learning the functional interfaces for primitives");

        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > 0.5;
        System.out.println(b1.getAsBoolean());
        System.out.println(b2.getAsBoolean());
    }
}
