package dev.ronaldotavares.java21.weekly;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/** Code snippets for the Portuguese weekly meeting on November 17, 2026. */
public class Portuguese20261117 {
    public static void main(String[] args) throws Exception {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: module-info.java exports packages, not individual classes.
    private static void question1() {
        System.out.println("\nquestion1");
        // This is the correct module-info.java from the question. A module declaration
        // must live in its own file, so it is displayed rather than compiled here.
        System.out.println("""
                module abc.payroll{
                  exports com.abc.payroll;
                }
                """);
        // These alternatives are invalid: exports com.abc.payroll.*;
        // exports com.abc.payroll to all; and exports individual class names.
    }

    // Main 2: preserve the question's calls; only the date assumed in its
    // enunciation affects the printed Period, not Duration.between's exception.
    private static void question2() {
        System.out.println("\nquestion2");
        Period p = Period.between(LocalDate.now(),
                LocalDate.of(2022, Month.SEPTEMBER, 1));
        System.out.println(p);
        try {
            Duration d = Duration.between(LocalDate.now(),
                    LocalDate.of(2022, Month.SEPTEMBER, 1));
            System.out.println(d);
        } catch (UnsupportedTemporalTypeException exception) {
            System.out.println("Duration.between(LocalDate, LocalDate): "
                    + exception.getClass().getSimpleName());
        }
        // To reproduce the stated 2022-09-02 date exactly, substitute
        // LocalDate.of(2022, 9, 2) for both LocalDate.now() calls.
    }

    // Main 3: the Account code below is intentionally unchanged from the
    // question. Its package-private, reassignable lock is the problem to identify.
    private static void question3() {
        System.out.println("\nquestion3");
        Account account = new Account();
        account.balance = 100.0; // Set up a nonzero value only for this demonstration.
        account.withdraw(10.0);
        System.out.println("balance=" + account.balance);
        System.out.println("Fix: declare lock private final (one lock per Account).");
    }

    private static final class Account {
        private String id;
        private double balance;

        ReentrantLock lock = new ReentrantLock();

        public void withdraw(double amt) {
            try {
                lock.lock();
                if (balance > amt) balance = balance - amt;
            } finally {
                lock.unlock();
            }
        }
    }

    // Main 4: get() has the collection's declared element type.
    private static void question4() {
        System.out.println("\nquestion4");
        ArrayList<SUV> al3 = new ArrayList<>();
        al3.add(new SUV());
        Drivable d1 = al3.get(0); // Option C compiles.

        ArrayList<SUV> al4 = new ArrayList<>();
        al4.add(new SUV());
        Car c2 = al4.get(0); // Option D compiles.
        System.out.println("C=" + d1.getClass().getSimpleName()
                + ", D=" + c2.getClass().getSimpleName());

        // The other options from the question do not compile:
        // ArrayList<Vehicle> al1 = new ArrayList<>(); SUV s = al1.get(0);
        // ArrayList<Drivable> al2 = new ArrayList<>(); Car c1 = al2.get(0);
        // ArrayList<Vehicle> al5 = new ArrayList<>(); Drivable d2 = al5.get(0);
    }

    private abstract static class Vehicle { }
    private interface Drivable { }
    private static class Car extends Vehicle implements Drivable { }
    private static final class SUV extends Car { }

    // Main 5: retain all three loops and the labeled break exactly.
    private static void question5() {
        System.out.println("\nquestion5");
        var counter = 0;
        outer:
        for (var i = 0; i < 3; i++) {
            middle:
            for (var j = 0; j < 3; j++) {
                inner:
                for (var k = 0; k < 3; k++) {
                    if (k - j > 0) {
                        break middle;
                    }
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    // Reserve 1: takeWhile stops at the first non-red card.
    private static void reserve1() {
        System.out.println("\nreserve1");
        Arrays.stream(Card.values()).takeWhile(c -> c.isRed()).forEach(System.out::print);
        System.out.println();
    }

    private enum Card {
        HEART, CLUB, SPADE, DIAMOND;

        public boolean isRed() {
            return switch (this) {
                case HEART, DIAMOND -> true;
                default -> false;
            };
        }
    }

    // Reserve 2: readObject observes default fields before
    // defaultReadObject, then restores the transient date separately.
    private static void reserve2() throws IOException, ClassNotFoundException {
        System.out.println("\nreserve2");
        // The question uses "c:\\temp\\data.ser", a Windows-specific path. Only
        // that path is replaced here so the same serialization code runs on macOS.
        Path file = Files.createTempFile("weekly-data-", ".ser");
        try {
            Data d = new Data(10, LocalDate.of(2024, 10, 10));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()));
            oos.writeObject(d);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()));
            d = (Data) ois.readObject();
            ois.close();
            System.out.println(d);
        } finally {
            Files.deleteIfExists(file);
        }
    }

    private static class Data implements Serializable {
        int id = 20;
        static LocalDate dummyDate = LocalDate.of(2000, 1, 1);
        transient LocalDate ld = LocalDate.of(2010, 1, 1);

        private void readObject(ObjectInputStream stream)
                throws IOException, ClassNotFoundException {
            System.out.println("in readObject before " + id + " " + ld);
            stream.defaultReadObject();
            System.out.println("in readObject after " + id + " " + ld);
            ld = dummyDate;
        }

        public Data(int id, LocalDate ld) {
            this.id = id;
            this.ld = ld;
        }

        @Override
        public String toString() {
            return "Data " + id + " " + ld;
        }
    }
}
