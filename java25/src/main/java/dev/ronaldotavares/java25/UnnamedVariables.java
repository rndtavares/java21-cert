package dev.ronaldotavares.java25;

import java.util.List;

/** Examples of unnamed variables. */
public class UnnamedVariables {

    static void main(String[] args) {
        question1();
        question2();
    }

    // The enhanced-for variable is intentionally unavailable in the body.
    private static void question1() {
        System.out.println("\nquestion1");
        for (var _ : List.of("first", "second")) {
            System.out.println("Processing one value");
        }
    }

    // Unnamed variables do not introduce a name, so they can share a scope.
    private static void question2() {
        System.out.println("\nquestion2");
        var _ = List.of("discarded intermediate result");
        var _ = 42;
        System.out.println("Unnamed variables are write-only placeholders");
    }
}
