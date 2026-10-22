package dev.ronaldotavares.java21.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/** Code snippets for the English weekly meeting on October 22, 2026. */
public class English20261022 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: size is the element count, not the last valid index.
    private static void question1() {
        System.out.println("\nquestion1");
        final var values = new ArrayList<String>();
        values.add("111");
        values.add("222");
        System.out.println("size=" + values.size() + ", last=" + values.get(values.size() - 1));
        try {
            System.out.println(values.get(values.size()));
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("get(size): " + exception.getClass().getSimpleName());
        }
    }

    // Main 2: only an abstract interface method must be implemented.
    private static void question2() {
        System.out.println("\nquestion2");
        final ColdDrink drink = new CrazyDrink();
        System.out.println(drink.getName() + ": " + drink.getAlcoholPercent());
        System.out.println("static method=" + Drink.computeAlcoholPercent());
        // Remove getName() from CrazyDrink to see the missing implementation error.
        // Drink's static method is not inherited by ColdDrink or CrazyDrink.
    }

    private interface Drink {
        default double getAlcoholPercent() {
            return 0.0;
        }

        static double computeAlcoholPercent() {
            return 0.0;
        }
    }

    private interface ColdDrink extends Drink {
        String getName();
    }

    private static final class CrazyDrink implements ColdDrink {
        @Override
        public String getName() {
            return "CrazyDrink";
        }
    }

    // Main 3: an assignment uses declared types; a cast checks the runtime object.
    private static void question3() {
        System.out.println("\nquestion3");
        final C3 object = new C3();
        final I1 first = object;
        final I2 second = (I2) first;
        final I1 inherited = new C4();
        System.out.println("I2 after cast=" + (second instanceof C3));
        System.out.println("I1 inherited by C4=" + (inherited instanceof C4));
        // C3 narrower = new C1();  // A superclass reference cannot be assigned to C3.
        // C3 unrelated = new C2(); // C2 and C3 are unrelated classes.
    }

    private interface I1 { }

    private interface I2 { }

    private static class C1 implements I1 { }

    private static class C2 implements I2 { }

    private static class C3 extends C1 implements I2 { }

    private static final class C4 extends C3 implements I1, I2 { }

    // Main 4: List<String>.forEach requires a Consumer<? super String>.
    private static void question4() {
        System.out.println("\nquestion4");
        final List<String> values = Arrays.asList("a", "aa", "aaa");
        final Function<String, Integer> length = String::length;
        final Consumer<Integer> printLength = value -> System.out.print("Len:" + value + " ");
        final Predicate<String> empty = String::isEmpty;
        // None of length, printLength, or empty can be passed directly to forEach.
        values.forEach(value -> printLength.accept(length.apply(value)));
        System.out.println("\nany empty=" + values.stream().anyMatch(empty));
    }

    // Main 5: sorting Items by name and mapping before sorting both produce the target order.
    private static void question5() {
        System.out.println("\nquestion5");
        final List<Item> items = Arrays.asList(
                new Item(1, "Screw"), new Item(2, "Nail"), new Item(3, "Bolt"));
        System.out.print("sort Items, then map: ");
        items.stream().sorted(Comparator.comparing(Item::getName))
                .map(Item::getName).forEach(System.out::print);
        System.out.print("\nmap, then sort names: ");
        items.stream().map(Item::getName).sorted().forEach(System.out::print);
        System.out.print("\nsort by id instead: ");
        items.stream().sorted(Comparator.comparing(Item::getId)).forEach(System.out::print);
        System.out.println();
    }

    private static final class Item {
        private final Integer id;
        private final String name;

        private Item(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private Integer getId() {
            return id;
        }

        private String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Intermediate reserve: non-sealed requires a sealed parent; final and non-sealed conflict.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final Readable device = new Device();
        final Readable book = new Book();
        final Readable journal = new Journal();
        System.out.println(device.getClass().getSimpleName() + ", "
                + book.getClass().getSimpleName() + ", "
                + journal.getClass().getSimpleName());
        // non-sealed class Device implements Readable { } // Readable is not sealed.
        // final non-sealed class Book extends DocType { } // Modifiers conflict.
    }

    private interface Readable { }

    private static final class Device implements Readable { }

    private abstract static sealed class DocType implements Readable permits Book, Journal { }

    private static final class Book extends DocType { }

    private static final class Journal extends DocType { }

    // Difficult reserve: the original if/else handles null, so the switch needs case null.
    private static void reserve2() {
        System.out.println("\nreserve2");
        final List<Building> buildings = Arrays.asList(new Hotel(10), new Hospital(20), null);
        for (final Building building : buildings) {
            System.out.println(printDetailsWithIf(building));
            System.out.println(printDetailsWithSwitch(building));
        }
        // A default branch without case null would throw NullPointerException for null.
    }

    private sealed interface Building permits Hospital, Hotel { }

    private record Hospital(int beds) implements Building { }

    private record Hotel(int rooms) implements Building { }

    private static String printDetailsWithIf(Building building) {
        if (building instanceof Hotel hotel) {
            return "Hotel has " + hotel.rooms() + " rooms";
        } else if (building instanceof Hospital hospital) {
            return "Hospital has " + hospital.beds() + " beds";
        }
        return "Building not recognized.";
    }

    private static String printDetailsWithSwitch(Building building) {
        return switch (building) {
            case Hotel(var rooms) -> "Hotel has " + rooms + " rooms";
            case Hospital(var beds) -> "Hospital has " + beds + " beds";
            case null -> "Building not recognized.";
        };
    }
}
