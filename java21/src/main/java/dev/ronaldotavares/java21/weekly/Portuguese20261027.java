package dev.ronaldotavares.java21.weekly;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleSupplier;
import java.util.stream.Stream;

/** Code snippets for the Portuguese weekly meeting on October 27, 2026. */
public class Portuguese20261027 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: pattern variables use the object's runtime type, not the reference type.
    private static void question1() {
        System.out.println("\nquestion1");
        B b = new C();
        A a = b;
        if (a instanceof B b1) b1.b();
        if (a instanceof C c1) c1.c();
        if (a instanceof D d1) d1.d();
        // Change new C() to new D() and compare the third condition.
    }

    private static class A { }
    private static class B extends A {
        void b() { System.out.println("b"); }
    }
    private static class C extends B {
        void c() { System.out.println("c"); }
    }
    private static class D extends C {
        void d() { System.out.println("d"); }
    }

    // Main 2: the inner class hides data and overrides getData().
    private static void question2() {
        System.out.println("\nquestion2");
        System.out.println(new Onion().getData());
        // In Layer.getData(), compare data, super.data, and Onion.this.data.
    }

    private static class Onion {
        private String data = "skin";

        private class Layer extends Onion {
            String data = "thegoodpart";

            @Override
            public String getData() {
                return data;
            }
        }

        public String getData() {
            return new Layer().getData();
        }
    }

    // Main 3: DoubleSupplier unboxes the null Double returned by the method reference.
    private static void question3() {
        System.out.println("\nquestion3");
        BookWithNullablePrice book = new BookWithNullablePrice("Java in 24 hrs", null);
        DoubleSupplier supplier = book::getPrice;
        try {
            System.out.println(book.getTitle() + " " + supplier.getAsDouble());
        } catch (NullPointerException exception) {
            System.out.println(exception.getClass().getSimpleName());
        }
        // Change the price to 0.0; then compare Supplier<Double> with DoubleSupplier.
    }

    private record BookWithNullablePrice(String title, Double price) {
        String getTitle() { return title; }
        Double getPrice() { return price; }
    }

    // Main 4: LinkedHashSet preserves insertion order when add() sees a duplicate.
    private static void question4() {
        System.out.println("\nquestion4");
        SequencedCollection<String> subjects = new LinkedHashSet<>();
        subjects.add("Math");
        subjects.add("Chemistry");
        subjects.add("Physics");
        subjects.add("English");
        subjects.add("Math");
        subjects.forEach(System.out::print);
        System.out.println();
        subjects.addFirst("English");
        System.out.println("after addFirst: " + subjects);
        // A HashSet is not a SequencedCollection; TreeSet.addFirst throws.
    }

    // Main 5: takeWhile and dropWhile operate on the current encounter order.
    private static void question5() {
        System.out.println("\nquestion5");
        Stream.of(new Person("Bob", 'M'), new Person("Rita", 'F'),
                        new Person("Mukesh", 'M'), new Person("Amita", 'T'),
                        new Person("Sonya", 'F'))
                .takeWhile(person -> person.gender() == 'M' || person.gender() == 'F')
                .sorted((left, right) -> right.name().compareTo(left.name()))
                .dropWhile(person -> person.name().length() < 5)
                .forEach(person -> System.out.print(person.name()));
        System.out.println();
        // Move Amita to the end or swap sorted() and dropWhile() to inspect the change.
    }

    private record Person(String name, char gender) { }

    // Intermediate reserve: an extends wildcard lets us read a Number, not add one.
    private static void reserve1() {
        System.out.println("\nreserve1");
        List<Integer> integers = new ArrayList<>(List.of(10, 20));
        System.out.println(firstNumber(integers));
        List<? super Number> consumer = new ArrayList<Number>();
        consumer.add(30);
        Object value = consumer.get(0);
        System.out.println(value);
        // In firstNumber(), list.add(30) would not compile.
        // Number n = consumer.get(0) would not compile either.
    }

    private static Number firstNumber(List<? extends Number> list) {
        return list.get(0);
    }

    // Difficult reserve: Stream<Double> and DoubleStream use different operators.
    private static void reserve2() {
        System.out.println("\nreserve2");
        List<PricedBook> books = List.of(
                new PricedBook("The Outsider", 2.99),
                new PricedBook("Where the Crawdads Sing", 4.99),
                new PricedBook("Elevation", 2.99),
                new PricedBook("Coffin from Hong Kong", 1.99));
        double boxedTotal = books.stream().map(PricedBook::price).reduce(0.0, Double::sum);
        DoubleBinaryOperator sum = Double::sum;
        double primitiveTotal = books.stream().mapToDouble(PricedBook::price).reduce(0.0, sum);
        System.out.println("Stream<Double>: " + boxedTotal);
        System.out.println("DoubleStream: " + primitiveTotal);
        // Without an identity, DoubleStream.reduce(sum) returns OptionalDouble.
    }

    private record PricedBook(String title, Double price) { }
}
