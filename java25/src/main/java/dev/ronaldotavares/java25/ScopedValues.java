package dev.ronaldotavares.java25;

import java.util.NoSuchElementException;

/** Examples of scoped values. */
public class ScopedValues {

    private static final ScopedValue<Integer> LEVEL = ScopedValue.newInstance();
    private static final ScopedValue<String> USER = ScopedValue.newInstance();

    static void main(String[] args) throws InterruptedException {
        examples();
        question1();
        question2();
    }

    private static void examples() {
        System.out.println("\nexamples");
        ScopedValue.where(USER, "ana").run(() ->
                System.out.println("Bound user: " + USER.get()));
        System.out.println("Bound after run: " + USER.isBound());

        ScopedValue.where(USER, "outer").run(() -> {
            ScopedValue.where(USER, "inner").run(() ->
                    System.out.println("Nested user: " + USER.get()));
            System.out.println("Restored user: " + USER.get());
        });

        ScopedValue.where(USER, null).run(() ->
                System.out.println("Null is bound: " + USER.isBound()));
    }

    // Nested bindings shadow the outer binding and end with their dynamic scope.
    private static void question1() {
        System.out.println("\nquestion1");
        ScopedValue.where(LEVEL, 2).run(ScopedValues::printLevels);
        System.out.println("Bound after run: " + LEVEL.isBound());
    }

    // A thread created directly does not inherit the caller's scoped binding.
    private static void question2() throws InterruptedException {
        System.out.println("\nquestion2");
        var child = Thread.ofVirtual().start(() ->
                System.out.println("Child inherited binding: " + LEVEL.isBound()));
        child.join();

        try {
            LEVEL.get();
        } catch (NoSuchElementException exception) {
            System.out.println("An unbound scoped value cannot be read");
        }
    }

    private static void printLevels() {
        var level = LEVEL.get();
        System.out.println("Level " + level);
        if (level > 0) {
            ScopedValue.where(LEVEL, level - 1).run(ScopedValues::printLevels);
        }
    }
}
