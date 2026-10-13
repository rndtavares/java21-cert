package dev.ronaldotavares.java21.weekly;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SequencedCollection;

/** Code snippets for the English weekly meeting on October 15, 2026. */
public class English20261015 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: substring returns "per"; delete removes positions 3 and 4.
    private static void question1() {
        System.out.println("\nquestion1");
        final String text = "blooper";
        final StringBuilder builder = new StringBuilder(text);
        builder.append(text.substring(4)).delete(3, 5);
        System.out.println(builder);
        System.out.println("original String=" + text);
    }

    // Main 2: the form assumes the date is September 2, 2022.
    private static void question2() {
        System.out.println("\nquestion2");
        final LocalDate today = LocalDate.of(2022, Month.SEPTEMBER, 2);
        final LocalDate yesterday = LocalDate.of(2022, Month.SEPTEMBER, 1);
        final Period period = Period.between(today, yesterday);
        System.out.println(period);
        try {
            final Duration duration = Duration.between(today, yesterday);
            System.out.println(duration);
        } catch (DateTimeException exception) {
            System.out.println("Duration.between: " + exception.getClass().getSimpleName());
        }
        // Duration needs a time component; try LocalDateTime values instead.
    }

    // Main 3: removeFirst returns the element; remove(Object) returns boolean.
    private static void question3() {
        System.out.println("\nquestion3");
        final SequencedCollection<String> values = new ArrayList<>();
        values.addFirst("a");
        values.addFirst("b");
        values.addLast("c");
        values.add("a");
        System.out.println(values.removeFirst() + " " + values.remove("a") + " " + values.remove("x"));
        System.out.println("remaining=" + values);
    }

    // Main 4: a is a proper prefix of b, and the first mismatch is at index 3.
    private static void question4() {
        System.out.println("\nquestion4");
        final int[] first = {'h', 'e', 'l'};
        final int[] second = {'h', 'e', 'l', 'l', 'o'};
        final int comparison = Arrays.compare(first, second);
        final int mismatch = Arrays.mismatch(first, second);
        System.out.println(comparison + " " + mismatch);
        // Character literals are valid int array elements.
    }

    // Main 5: fields use the reference type; overridden methods use object type.
    private static void question5() {
        System.out.println("\nquestion5");
        final Baap base = new Beta();
        System.out.println(base.h + " " + base.getH());
        final Beta derived = (Beta) base;
        System.out.println(derived.h + " " + derived.getH());
    }

    private static class Baap {
        public int h = 4;

        public int getH() {
            System.out.println("Baap " + h);
            return h;
        }
    }

    private static final class Beta extends Baap {
        public int h = 44;

        @Override
        public int getH() {
            System.out.println("Beta " + h);
            return h;
        }
    }

    // Intermediate reserve: these two constructor chains implement options A and B.
    private static void reserve1() {
        System.out.println("\nreserve1");
        System.out.println(new BarbieUsingThis().name);
        System.out.println(new BarbieUsingSuper().name);
        System.out.println(new BarbieUsingThis("mydoll").name);
    }

    private static class Doll {
        final String name;

        private Doll(String name) {
            this.name = name;
        }
    }

    private static final class BarbieUsingThis extends Doll {
        private BarbieUsingThis() {
            this("unknown");
        }

        private BarbieUsingThis(String name) {
            super(name);
        }
    }

    private static final class BarbieUsingSuper extends Doll {
        private BarbieUsingSuper() {
            super("unknown");
        }

        private BarbieUsingSuper(String name) {
            super(name);
        }
    }

    // Difficult reserve: valueOf needs the enum name, not overridden toString.
    private static void reserve2() {
        System.out.println("\nreserve2");
        for (final AccountType type : AccountType.values()) {
            System.out.print(AccountType.getAccountType(type.toString()));
        }
        System.out.println();
        System.out.println("name=" + AccountType.CHECKING.name()
                + ", toString=" + AccountType.CHECKING);
    }

    private enum AccountType {
        CHECKING, SAVINGS, FD;

        @Override
        public String toString() {
            return name().toLowerCase();
        }

        private static String getAccountType(String type) {
            try {
                final AccountType accountType = AccountType.valueOf(type);
                return accountType.ordinal() % 2 == 0
                        ? accountType.name().toLowerCase()
                        : accountType.name().toUpperCase();
            } catch (IllegalArgumentException exception) {
                return "NOT_SUPPORTED";
            }
        }
    }
}
