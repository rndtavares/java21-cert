package dev.ronaldotavares.java21.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Code snippets for the English weekly meeting on October 28, 2026. */
public class English20261028 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: a record has a canonical constructor, accessor, equals, and toString.
    private static void question1() {
        System.out.println("\nquestion1");
        final var first = new Student(42);
        final var second = new Student(42);
        System.out.println(first);
        System.out.println("id()=" + first.id() + ", equals=" + first.equals(second));
        // Student has no getId(), clone(), or zero-argument constructor.
    }

    private record Student(int id) { }

    // Main 2: the inner class can access an enclosing instance through Outer.this.
    private static void question2() {
        System.out.println("\nquestion2");
        new Outer().new Inner().methodA();
        // Inside Inner, this.i does not compile because Inner has no field i.
    }

    private static class Outer {
        private int i = 10;

        private class Inner {
            private void methodA() {
                System.out.println(i);
                System.out.println(Outer.this.i);
            }
        }
    }

    // Main 3: DoubleSupplier unboxes the nullable Double returned by getPrice().
    private static void question3() {
        System.out.println("\nquestion3");
        final var book = new BookWithNullablePrice("Java in 24 hrs", null);
        final DoubleSupplier supplier = book::getPrice;
        try {
            System.out.println(book.getTitle() + " " + supplier.getAsDouble());
        } catch (NullPointerException exception) {
            System.out.println(exception.getClass().getSimpleName());
        }
        // Change the price to 0.0, or use Supplier<Double> to compare results.
    }

    private record BookWithNullablePrice(String title, Double price) {
        private String getTitle() { return title; }
        private Double getPrice() { return price; }
    }

    // Main 4: unmodifiableList is a view that reflects changes to its backing list.
    private static void question4() {
        System.out.println("\nquestion4");
        final var original = new ArrayList<String>();
        original.add("A");
        final var view = Collections.unmodifiableList(original);
        original.add(null);
        System.out.println(original + " " + view);
        try {
            view.add(null);
        } catch (UnsupportedOperationException exception) {
            System.out.println(exception.getClass().getSimpleName());
        }
        // Compare with List.copyOf(original): it rejects the null element.
    }

    // Main 5: the Map value type must match List<String>, not ArrayList<String>.
    private static void question5() {
        System.out.println("\nquestion5");
        final List<String> cities = new ArrayList<>();
        cities.add("New York");
        cities.add("Albany");
        final Map<String, List<String>> inferred = new HashMap<>();
        final Map<String, List<String>> explicit = new HashMap<String, List<String>>();
        inferred.put("NY", cities);
        explicit.put("NY", cities);
        System.out.println(inferred);
        System.out.println(explicit);
        // Map<String, ArrayList<String>> would reject cities declared as List<String>.
        // HashMap<String, ArrayList<String>> cannot be assigned to Map<String, List<String>>.
    }

    // Intermediate reserve: flatMap removes one List layer; mapToDouble enables sum().
    private static void reserve1() {
        System.out.println("\nreserve1");
        final List<List<PricedBook>> books = Arrays.asList(
                Arrays.asList(new PricedBook("Windmills of the Gods", 7.0),
                        new PricedBook("Tell me your dreams", 9.0)),
                Arrays.asList(new PricedBook("There is a hippy on the highway", 5.0),
                        new PricedBook("Easy come easy go", 5.0)));
        final double total = books.stream()
                .flatMap(group -> group.stream())
                .mapToDouble(book -> book.price())
                .sum();
        System.out.println(total);
        // Remove flatMap() to see why List<PricedBook> has no price() accessor.
    }

    private record PricedBook(String title, Double price) { }

    // Difficult reserve: sorted establishes order, preserved by forEachOrdered.
    private static void reserve2() {
        System.out.println("\nreserve2");
        final Stream<String> words = Stream.of("Pizza", "Bread", "Tofu", "Pizza",
                "Tofu", "Cheese", "Pizza");
        final Map<String, Long> counts = words.collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(counts);
        counts.keySet().stream().sorted().parallel()
                .forEachOrdered(key -> System.out.print(counts.get(key)));
        System.out.println();
        // Replacing forEachOrdered() with forEach() removes the output-order guarantee.
    }
}
