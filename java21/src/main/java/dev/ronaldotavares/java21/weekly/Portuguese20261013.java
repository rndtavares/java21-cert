package dev.ronaldotavares.java21.weekly;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/** Code snippets for the Portuguese weekly meeting on October 13, 2026. */
public class Portuguese20261013 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: a varargs parameter is a String array; indexing it produces a String.
    private static void question1() {
        System.out.println("\nquestion1");
        new Portuguese20261013().showVarargsTypes("Java", "21");
    }

    private void showVarargsTypes(String... params) {
        final var a = params;
        final var b = params[0];
        final String[] array = a; // These assignments reveal the inferred types.
        final String first = b;
        System.out.println("a=" + Arrays.toString(array) + ", b=" + first);
    }

    // Main 2: compare uses the first difference; mismatch returns its index.
    private static void question2() {
        System.out.println("\nquestion2");
        final int[] first = {0, 1, 4, 5};
        final int[] second = {0, 1, 1, 5, 6};
        final int comparison = Arrays.compare(first, second);
        final int mismatch = Arrays.mismatch(first, second);
        System.out.println(comparison + " " + mismatch);
        System.out.println("reversed comparison=" + Arrays.compare(second, first));
    }

    // Main 3: ignoring the result of plus leaves the original Instant unchanged.
    private static void question3() {
        System.out.println("\nquestion3");
        final Instant instant = Instant.parse("2024-06-25T06:43:30.00z");
        instant.plus(10, ChronoUnit.HOURS);
        System.out.println(instant.plus(-10, ChronoUnit.HOURS));
        System.out.println("original=" + instant);
        // Assign the first plus result to a new variable to compare the outputs.
    }

    // Main 4: overriding toString changes printing without changing the enum name.
    private static void question4() {
        System.out.println("\nquestion4");
        System.out.println(Coffee.ESPRESSO);
        System.out.println("name=" + Coffee.ESPRESSO.name());
    }

    private enum Coffee {
        ESPRESSO("Very Strong"), MOCHA("Bold"), LATTE("Mild");

        private final String strength;

        Coffee(String strength) {
            this.strength = strength;
        }

        @Override
        public String toString() {
            return String.valueOf(strength);
        }
        // Returning String.valueOf(Coffee.ESPRESSO) here would recurse forever.
    }

    // Main 5: the superclass constructor calls the override before B initializes i.
    private static void question5() {
        System.out.println("\nquestion5");
        final A value = new B();
        value.print();
        System.out.println();
    }

    private static class A {
        private A() {
            print();
        }

        void print() {
            System.out.print("A ");
        }
    }

    private static final class B extends A {
        private int i = 4;

        @Override
        void print() {
            System.out.print(i + " ");
        }
    }

    // Intermediate reserve: rectangular and explicitly sized jagged arrays both work.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final int[][] rectangular = new int[2][4]; // Option B.
        fillArray(rectangular);
        final int[][] jagged = new int[2][]; // Option D.
        jagged[0] = new int[2];
        jagged[1] = new int[4];
        fillArray(jagged);
        System.out.println("rectangular=" + Arrays.deepToString(rectangular));
        System.out.println("jagged=" + Arrays.deepToString(jagged));
        // new int[2][] alone leaves both rows null (option A).
    }

    private static void fillArray(int[][] array) {
        array[0][0] = 1;
        array[0][1] = 2;
        array[1][0] = 3;
        array[1][1] = 4;
        array[1][2] = 5;
        array[1][3] = 6;
    }

    // Difficult reserve: options B and E are exhaustive switch expressions.
    private static void reserve2() {
        System.out.println("\nreserve2");
        final Point first = new Point(4, 2);
        final Point second = new Point(2, 4);
        System.out.println(testPointWithDefault(first) + " " + testPointWithDefault(second));
        System.out.println(testPointWithRecordPattern(first) + " " + testPointWithRecordPattern(second));
        // Try passing null: neither switch has case null, so it throws NPE.
    }

    private record Point(int x, int y) { }

    private static boolean testPointWithDefault(Point point) {
        return switch (point) {
            case Point(var x, var y) when x > y -> true;
            default -> false;
        };
    }

    private static boolean testPointWithRecordPattern(Point point) {
        return switch (point) {
            case Point first when first.x() > first.y() -> true;
            case Point(var x, var y) -> false;
        };
    }
}
