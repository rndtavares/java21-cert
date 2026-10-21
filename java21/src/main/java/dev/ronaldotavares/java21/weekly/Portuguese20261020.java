package dev.ronaldotavares.java21.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/** Code snippets for the Portuguese weekly meeting on October 20, 2026. */
public class Portuguese20261020 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: a class extends another class but implements an interface.
    private static void question1() {
        System.out.println("\nquestion1");
        final var saw = new SteamPowerSaw();
        saw.operate();
        saw.operate(10);
        // Change "extends PowerTool" to "implements PowerTool": compilation fails.
        // Add @Override to operate(int): this overload does not override a method.
    }

    private interface Tool {
        void operate();
    }

    private abstract static class PowerTool implements Tool { }

    private static class PowerSaw extends PowerTool {
        @Override
        public void operate() {
            System.out.println("PowerSaw.operate()");
        }
    }

    private static final class SteamPowerSaw extends PowerSaw {
        public void operate(int pressure) {
            System.out.println("SteamPowerSaw.operate(" + pressure + ")");
        }
    }

    // Main 2: Predicate<List<String>> fixes the lambda parameter type to List<String>.
    private static void question2() {
        System.out.println("\nquestion2");
        System.out.println(checkList(new ArrayList<>(), list -> list.isEmpty()));
        System.out.println(checkList(new ArrayList<>(), list -> list.add("hello")));
        // A typed (ArrayList<String> list) parameter is not valid here: the target is List<String>.
        // A block body with "return" would require braces and a semicolon.
    }

    private static boolean checkList(List<String> list, Predicate<? super List<String>> predicate) {
        return predicate.test(list);
    }

    // Main 3: copying Arrays.asList separates the ArrayList from its backing array.
    private static void question3() {
        System.out.println("\nquestion3");
        final String[] names = {"charlie", "bob", "andy", "dave"};
        final var copy = new ArrayList<>(Arrays.asList(names));
        copy.sort((var left, var right) -> left.compareTo(right));
        System.out.println(names[0] + " " + copy.get(0));

        final var backedByArray = Arrays.asList(names);
        backedByArray.sort(String::compareTo);
        System.out.println("after sorting the backed list: " + names[0]);
    }

    // Main 4: filter is intermediate; count is the terminal operation.
    private static void question4() {
        System.out.println("\nquestion4");
        final List<Integer> values = Arrays.asList(100, 200, 230, 291, 43);
        System.out.println(values.stream().filter(value -> value < 200).count());
        // Replace count() with toList() to inspect which values survived the filter.
    }

    // Main 5: each direct permitted subtype must be final, sealed, or non-sealed.
    private static void question5() {
        System.out.println("\nquestion5");
        final Cacheable first = new IntResult();
        final CacheableAlternative second = new OtherIntResult();
        first.clear();
        second.clear();
        // Remove non-sealed from Value or Result to reproduce option A's error.
        // Remove "implements Cacheable" from Result to reproduce option D's error.
    }

    private sealed interface Cacheable permits Value, Result {
        default void clear() {
            System.out.println("clearing cache...");
        }
    }

    private non-sealed interface Value extends Cacheable { }

    private abstract static non-sealed class Result implements Cacheable { }

    private static final class IntResult extends Result { }

    private sealed interface CacheableAlternative permits ValueAlternative, ResultAlternative {
        default void clear() {
            System.out.println("clearing alternative cache...");
        }
    }

    private abstract non-sealed interface ValueAlternative extends CacheableAlternative { }

    private abstract static sealed class ResultAlternative implements CacheableAlternative
            permits OtherIntResult { }

    private static final class OtherIntResult extends ResultAlternative { }

    // Intermediate reserve: each filter accepts one Predicate and returns another Stream.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final List<Movie> movies = List.of(
                new Movie("On the Waterfront", Genre.DRAMA),
                new Movie("Psycho", Genre.THRILLER),
                new Movie("Oldboy", Genre.THRILLER),
                new Movie("Shining", Genre.HORROR));
        final Predicate<Movie> thriller = movie -> movie.genre() == Genre.THRILLER;
        final Predicate<Movie> name = movie -> movie.name().startsWith("O");
        movies.stream().filter(thriller).filter(name).forEach(movie -> System.out.println(movie.name()));
        // Compare with filter(thriller.and(name)).
    }

    private enum Genre { DRAMA, THRILLER, HORROR, ACTION }

    private record Movie(String name, Genre genre) { }

    // Difficult reserve: filter needs a Predicate, supplied by a lambda or method reference.
    private static void reserve2() {
        System.out.println("\nreserve2");
        final List<Book> books = List.of(
                new Book("30 Days", "fiction"),
                new Book("Fast Food Nation", "non-fiction"),
                new Book("Wired", "fiction"));
        books.stream().filter(BookFilter::isFiction).forEach(book -> System.out.print(book.title() + ", "));
        System.out.println();
        books.stream().filter(book -> BookFilter.isFiction(book))
                .forEach(book -> System.out.print(book.title() + ", "));
        System.out.println();
    }

    private record Book(String title, String genre) { }

    private static final class BookFilter {
        private static boolean isFiction(Book book) {
            return book.genre().equals("fiction");
        }
    }
}
