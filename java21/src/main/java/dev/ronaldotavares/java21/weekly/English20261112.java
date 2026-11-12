package dev.ronaldotavares.java21.weekly;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/** Code snippets for the English weekly meeting on November 12, 2026. */
public class English20261112 {
    public static void main(String[] args) throws Exception {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: casting after addition differs from casting each operand first.
    private static void question1() {
        System.out.println("\nquestion1");
        int i = 5;
        float f = 5.5f;
        double d = 3.8;
        char c = 'a';
        if (i == f) c++;
        if (((int) (f + d)) == ((int) f + (int) d)) c += 2;
        System.out.println(c);
        System.out.println("cast sum=" + (int) (f + d)
                + ", sum casts=" + ((int) f + (int) d));
    }

    // Main 2: the static block runs once; the instance block runs twice.
    private static void question2() {
        System.out.println("\nquestion2");
        Delimiter d1 = new Delimiter();
        Delimiter d2 = new Delimiter('#');
        System.out.println(d1 + "" + d2);
    }

    private static class Delimiter {
        char value = '|';

        Delimiter() {
            this('-');
        }

        Delimiter(char ch) {
            this.value = ch;
        }

        static {
            System.out.print('.');
        }

        {
            System.out.print(value);
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Main 3: Runnable has run(), while Callable<V> has call() and returns V.
    private static void question3() throws Exception {
        System.out.println("\nquestion3");
        Runnable runnable = () -> System.out.println("Runnable.run()");
        Callable<String> callable = () -> "Callable.call() returns a value";
        Thread virtualThread = Thread.startVirtualThread(runnable);
        virtualThread.join();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println(executor.submit(callable).get());
            System.out.println("Runnable Future=" + executor.submit(runnable).get());
        }
        // new Thread(callable) does not compile; new Thread(runnable) does.
        // Both task types can be submitted to an ExecutorService.
    }

    // Main 4: try-with-resources closes the device before the catch block runs.
    private static void question4() {
        System.out.println("\nquestion4");
        Device.testDevice();
    }

    private static class Device implements AutoCloseable {
        String header = null;

        public void open() {
            header = "OPENED";
            System.out.println("Device Opened");
        }

        public String read() throws IOException {
            throw new IOException("Unknown");
        }

        public void writeHeader(String str) throws IOException {
            System.out.println("Writing : " + str);
            header = str;
        }

        @Override
        public void close() {
            header = null;
            System.out.println("Device closed");
        }

        public static void testDevice() {
            Device d = new Device();
//            Device d;
            try (d) {
                d.open();
                d.read();
                d.writeHeader("TEST");
                d.close();
            } catch (IOException e) {
                System.out.println("Got Exception");
            }
        }
    }

    // Main 5: TreeSet compares even its first element, so this throws before A.
    private static void question5() {
        System.out.println("\nquestion5");
        try {
            StudyGroup.main(new String[0]);
        } catch (ClassCastException exception) {
            System.out.println(exception.getClass().getSimpleName());
        }
    }

    private static class Person implements Comparable {
        private static int count = 0;
        private String id = "0";
        private String interest;

        public Person(String interest) {
            this.interest = interest;
            this.id = "" + ++count;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        @Override
        public String toString() {
            return id;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    private static class StudyGroup {
        String name = "MATH";
        TreeSet<Person> set = new TreeSet<Person>();

        public void add(Person p) {
            if (name.equals(p.getInterest())) set.add(p);
        }

        public static void main(String[] args) {
            StudyGroup mathGroup = new StudyGroup();
            mathGroup.add(new Person("MATH"));
            System.out.println("A");
            mathGroup.add(new Person("MATH"));
            System.out.println("B");
            System.out.println(mathGroup.set);
        }
    }

    // Reserve 1: module-info.java exports a package, not individual classes.
    private static void reserve1() {
        System.out.println("\nreserve1");
        System.out.println("""
                module abc.payroll {
                    exports com.abc.payroll;
                }
                """);
        // Run this separately as module-info.java when discussing the alternatives.
    }

    // Reserve 2: Duration adds 24 hours; Period preserves the local clock time.
    private static void reserve2() {
        System.out.println("\nreserve2");
        LocalDateTime ld = LocalDateTime.of(2022, Month.NOVEMBER, 5, 10, 0);

        ZonedDateTime date = ZonedDateTime.of(ld, ZoneId.of("US/Eastern"));
        date = date.plus(Duration.ofDays(1));
        System.out.println(date);

        date = ZonedDateTime.of(ld, ZoneId.of("US/Eastern"));
        date = date.plus(Period.ofDays(1));
        System.out.println(date);
    }
}
