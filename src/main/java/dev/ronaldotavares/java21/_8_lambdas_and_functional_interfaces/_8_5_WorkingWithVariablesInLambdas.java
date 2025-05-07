package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class _8_5_WorkingWithVariablesInLambdas {
    public static void main(String[] args) {
        listingParameters();
        usingLocalVariablesInsideALambdaBody();
        referencingVariablesFromTheLambdaBody();
    }

    private static void listingParameters(){
        Predicate<String> p = x -> true;
        Predicate<String> p1 = (var x) -> true;
        Predicate<String> p2 = (String x) -> true;

        consume((var x) -> System.out.println(x), 123);

        //mixed types does not compile
//        (var x, y) -> "Hello" // DOES NOT COMPILE
//        (var x, Integer y) -> true // DOES NOT COMPILE
//        (String x, var y, Integer z) -> true // DOES NOT COMPILE
//        (Integer x, y) -> "goodbye" // DOES NOT COMPILE
    }

    private static void consume(Consumer<Integer> c, int num) {
        c.accept(num);
    }

    private static void counts(List<Integer> list) {
        list.sort((var x, var y) -> x.compareTo(y));
    }

    private static void countsModifiers(List<Integer> list) {
        list.sort((final var x, @Deprecated var y) -> x.compareTo(y));
    }

    private static void usingLocalVariablesInsideALambdaBody(){
        Comparator<Integer> tComparator = (a, b) -> {
            int c = 0;
            return 5;
        };
        int compared = tComparator.compare(1, 2);
        System.out.println(compared);

        Comparator<Integer> tComparator1 = (a, b) -> {
//            int a = 0; DOES NOT COMPILE
            return 5;
        };

        //KEEP YOUR LAMBDAS SHORT - use methods
//        Predicate<Integer> p1 = a -> returnSame(a);
//        Predicate<Integer> p1 = this::returnSame;
    }

    private static void variables(int a) {
//            int b = 1;
//            Predicate<Integer> p1 = a -> { //redeclaration of a
//                int b = 0; // redeclaration of b
//                int c = 0;
//                return b == c;
//            } //missing ;
    }

    private static void referencingVariablesFromTheLambdaBody(){
        var crow = new Crow();
        crow.caw("test");
    }
}

class Crow {
    private String color;
    public void caw(String name) {
        String volume = "loudly";
        Consumer<String> consumer = s ->
                System.out.println(name + " says "
                        + volume + " that she is " + color);
        consumer.accept(name);
    }
}

class Crow1 {
    private String color;
    public void caw(String name) {
        String volume = "loudly";
        name = "Caty";
        color = "black";

//        Consumer<String> consumer = s ->
//            System.out.println(name + " says " // DOES NOT COMPILE
//            + volume + " that she is " + color); // DOES NOT COMPILE
        volume = "softly";
    }
 }