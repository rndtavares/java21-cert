package dev.ronaldotavares.java25;

import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/** Examples of stream gatherers. */
public class StreamGatherers {

    static void main(String[] args) {
        question1();
        question2();
        question3();
    }

    // windowFixed emits immutable windows and retains an incomplete final window.
    private static void question1() {
        System.out.println("\nquestion1");
        var windows = Stream.of("a", "b", "c", "d", "e")
                .gather(Gatherers.windowFixed(2))
                .toList();
        System.out.println(windows);
    }

    // A custom gatherer can transform each element before pushing it downstream.
    private static void question2() {
        System.out.println("\nquestion2");
        var lettersOnly = Stream.of("Java 25", "Gatherers!")
                .gather(onlyLetters())
                .toList();
        System.out.println(lettersOnly);
    }

    // Returning false cancels upstream processing before this gatherer emits a value.
    private static void question3() {
        System.out.println("\nquestion3");
        var acceptedBeforeCancellation = Stream.of(1, 2, 3)
                .gather(stopBeforePushing())
                .count();
        System.out.println("Accepted before cancellation: " + acceptedBeforeCancellation);
    }

    private static Gatherer<String, Void, String> onlyLetters() {
        return Gatherer.ofSequential((state, text, downstream) -> {
            var letters = text.replaceAll("[^A-Za-z]", "");
            return letters.isEmpty() || downstream.push(letters);
        });
    }

    private static Gatherer<Integer, Void, Integer> stopBeforePushing() {
        return Gatherer.ofSequential((state, number, downstream) -> false);
    }
}
