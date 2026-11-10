package dev.ronaldotavares.java21.weekly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Code snippets for the Portuguese weekly meeting on November 10, 2026. */
public class Portuguese20261110 {
    public static void main(String[] args) throws Exception {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: compare a nested ternary with independent if statements at the boundaries.
    private static void question1() {
        System.out.println("\nquestion1");
        for (final int grossIncome : new int[]{18_000, 18_001, 36_000, 36_001}) {
            final double ternary = grossIncome <= 18_000 ? 0
                    : grossIncome <= 36_000 ? .1 : .2;
            double withIf = 0;
            if (grossIncome > 36_000) withIf = .2;
            if (grossIncome > 18_000 && grossIncome <= 36_000) withIf = .1;
            System.out.println("income=" + grossIncome + ", ternary=" + ternary
                    + ", if=" + withIf);
        }
        // The order of assignments in the incorrect options overwrites a valid rate.
    }

    // Main 2: direct writes to public fields can break the area invariant.
    private static void question2() {
        System.out.println("\nquestion2");
        final var triangle = new Triangle(4, 6);
        System.out.println("initial area=" + triangle.getArea());
        triangle.setBase(8);
        System.out.println("after setBase=" + triangle.getArea());
        // If base, height, or area were public, callers could change them directly.
    }

    private static final class Triangle {
        private int base;
        private int height;
        private double area;

        private Triangle(int base, int height) {
            this.base = base;
            this.height = height;
            updateArea();
        }

        private void updateArea() {
            area = base * height / 2;
        }

        private void setBase(int base) {
            this.base = base;
            updateArea();
        }

        private double getArea() {
            return area;
        }
    }

    // Main 3: try-with-resources closes the device before entering catch.
    private static void question3() {
        System.out.println("\nquestion3");
        final var device = new Device();
        try (device) {
            device.open();
            device.read();
            System.out.println("unreachable after read");
        } catch (IOException exception) {
            System.out.println("Got Exception");
        }
    }

    private static final class Device implements AutoCloseable {
        private void open() {
            System.out.println("Device Opened");
        }

        private void read() throws IOException {
            throw new IOException("Unknown");
        }

        @Override
        public void close() {
            System.out.println("Device closed");
        }
    }

    // Main 4: submit accepts both Callable and Runnable; their Future types differ.
    private static void question4() throws ExecutionException, InterruptedException {
        System.out.println("\nquestion4");
        final Runnable runnable = () -> System.out.println("In Runnable");
        final Callable<Integer> callable = () -> {
            System.out.println("In Callable");
            return 0;
        };
        // The question uses a cached pool; this virtual-thread executor has the same
        // submit overloads while avoiding platform threads for the demonstration.
        try (final var executor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory())) {
            final Future<Integer> callableResult = executor.submit(callable);
            final Future<?> runnableResult = executor.submit(runnable);
            System.out.println("Callable result=" + callableResult.get());
            System.out.println("Runnable result=" + runnableResult.get());
            // execute(callable) does not compile: execute only accepts Runnable.
        }
    }

    // Main 5: raw Queue parameters allow inserting a String into a Queue<Person>.
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void question5() {
        System.out.println("\nquestion5");
        final Queue<Person> people = new LinkedList<>();
        people.offer(new Person("Pope"));
        people.offer(new Person("John"));
        final Queue<Person> helped = new LinkedList<>();
        while (!people.isEmpty()) {
            final Person person = people.poll();
            System.out.println("Helped : " + person);
            ((Queue) helped).offer(person.getName());
        }
        System.out.println("helped holds Strings despite its declared Person type: " + helped);
        // Assigning a raw Queue to Queue<Person> compiles with unchecked warnings.
    }

    private record Person(String name) {
        private String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Intermediate reserve: REPLACE_EXISTING permits a move to an existing target.
    private static void reserve1() throws IOException {
        System.out.println("\nreserve1");
        final Path source = Files.createTempFile("weekly-source-", ".txt");
        final Path target = Files.createTempFile("weekly-target-", ".txt");
        try {
            Files.writeString(source, "source content");
            Files.writeString(target, "old target content");
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("target now contains=" + Files.readString(target));
            System.out.println("source still exists=" + Files.exists(source));
            // Files.copy(source, target, REPLACE_EXISTING), then Files.delete(source)
            // also works; a bare Files.move(source, target) would fail here.
        } finally {
            Files.deleteIfExists(source);
            Files.deleteIfExists(target);
        }
    }

    // Difficult reserve: interrupting a virtual thread's sleep affects that thread,
    // while join only waits for it; without join, daemon virtual threads may be cut off.
    private static void reserve2() throws InterruptedException {
        System.out.println("\nreserve2");
        final Thread worker = Thread.startVirtualThread(() -> {
            System.out.println("Starting loop");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException exception) {
                System.out.println("Worker sleep interrupted: "
                        + exception.getClass().getSimpleName());
            }
            System.out.println("Ending loop");
        });
        Thread.sleep(100);
        worker.interrupt();
        worker.join();
        // Remove join() to observe why a daemon virtual thread might not finish.
    }
}
