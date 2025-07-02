package dev.ronaldotavares.java21._13_concurrency;

import java.util.concurrent.*;
import java.util.stream.*;

public class _13_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Chapter 13 - Concurrency - Review Questions");

        var reviewQuestions = new _13_ReviewQuestions();
        reviewQuestions._19();
    }

    void _19(){
        System.out.println("19. What is the result of executing the following application? (Choose all that apply.)");
        PrintConstants.main(null);
    }
}

class PrintConstants {
    public static void main(String[] args) {
        var s = Executors.newVirtualThreadPerTaskExecutor();
        DoubleStream.of(3.14159, 2.71828)   // b1
                .forEach(c -> s.submit(          // b2
                        () -> System.out.println(10 * c))); // b3
        s.execute(() -> System.out.println("Printed"));
    }
}