package dev.ronaldotavares.java21.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Predicate;

/** Code snippets for the English weekly meeting on November 5, 2026. */
public class English20261105 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: both HashSet constructors and add() retain only unique elements.
    private static void question1() {
        System.out.println("\nquestion1");
        final var names = new ArrayList<String>();
        names.addAll(List.of("Ally", "Billy", "Cally", "Billy", "Ally"));
        final var addedOneByOne = new HashSet<String>();
        for (var name : names) addedOneByOne.add(name);
        final var constructedFromList = new HashSet<String>(names);
        System.out.println(names.size() + " " + addedOneByOne.size() + " "
                + constructedFromList.size());
        // HashSet does not guarantee iteration order; compare with LinkedHashSet.
    }

    // Main 2: computeIfAbsent receives a Function<K, V>, then returns the list.
    private static void question2() {
        System.out.println("\nquestion2");
        final Map<String, List<Double>> groupedValues = new HashMap<>();
        groupedValues.computeIfAbsent("A", key -> new ArrayList<Double>()).add(1.0);
        groupedValues.computeIfAbsent("A", key -> new ArrayList<Double>()).add(2.0);
        System.out.println(groupedValues);
        // compute() and computeIfPresent() receive a BiFunction<K, V, V> instead.
        // computeIfPresent() cannot create a list for a missing key.
    }

    // Main 3: add(E) uses Double, whereas indexOf/contains accept Object.
    private static void question3() {
        System.out.println("\nquestion3");
        final var values = new ArrayList<Double>();
        System.out.println(values.indexOf(1.0));
        System.out.println(values.contains("string"));
        values.add(111.0);
        System.out.println(values);
        // values.add(111) does not compile: an int boxes to Integer, not Double.
        // values.length does not exist; values.get(values.size()) compiles but fails.
    }

    // Main 4: Java 21 record patterns deconstruct a generic record with inferred types.
    private static void question4() {
        System.out.println("\nquestion4");
        final var student = new Student(123, "Bob");
        final var course = new Course("CS102", "Data Structures");
        final Relation<Student, Course> enrollment = new Relation<>(student, course);
        if (enrollment instanceof Relation(var a, var b) && a.id() == 123) {
            System.out.println(a.name() + " enrolled in " + b.code());
        }
        // Relation<>(...) is not a record pattern; <> belongs to construction.
        // Record components have private fields; use name() and code().
        // Replacing && with || would leave a and b out of scope on the right.
    }

    private record Course(String code, String name) { }
    private record Student(int id, String name) { }
    private record Relation<X, Y>(X x, Y y) { }

    // Main 5: flatMap removes a List layer; mapToDouble returns a DoubleStream.
    private static void question5() {
        System.out.println("\nquestion5");
        final List<List<Book>> books = Arrays.asList(
                Arrays.asList(new Book("Windmills of the Gods", 7.0),
                        new Book("Tell me your dreams", 9.0)),
                Arrays.asList(new Book("There is a hippy on the highway", 5.0),
                        new Book("Easy come easy go", 5.0)));
        final double total = books.stream()
                .flatMap(group -> group.stream())
                .mapToDouble(book -> book.price())
                .sum();
        System.out.println(total);
        // map(Book::price) stays a Stream<Double>, which has no sum() method.
    }

    private record Book(String title, Double price) { }

    // Intermediate reserve: filter accepts one Predicate; chain filters or use and().
    private static void reserve1() {
        System.out.println("\nreserve1");
        final var movies = List.of(
                new Movie("On the Waterfront", Genre.DRAMA),
                new Movie("Psycho", Genre.THRILLER),
                new Movie("Oldboy", Genre.THRILLER),
                new Movie("Shining", Genre.HORROR));
        final Predicate<Movie> thriller = movie -> movie.genre() == Genre.THRILLER;
        final Predicate<Movie> startsWithO = movie -> movie.name().startsWith("O");
        movies.stream().filter(thriller).filter(startsWithO)
                .forEach(movie -> System.out.println(movie.name()));
        // Equivalent: .filter(thriller.and(startsWithO)).
    }

    private enum Genre { DRAMA, THRILLER, HORROR }
    private record Movie(String name, Genre genre) { }

    // Difficult reserve: natural ordering is required even for the first TreeSet.add().
    private static void reserve2() {
        System.out.println("\nreserve2");
        final var people = new TreeSet<Person>();
        try {
            people.add(new Person("MATH"));
            System.out.println("A");
        } catch (ClassCastException exception) {
            System.out.println(exception.getClass().getSimpleName());
        }
        final var sortedByInterest = new TreeSet<>(Comparator.comparing(Person::interest));
        sortedByInterest.add(new Person("MATH"));
        System.out.println(sortedByInterest);
        // Person does not implement Comparable; supplying a Comparator fixes that.
    }

    private record Person(String interest) { }
}
