package dev.ronaldotavares.java21.questions;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PracticeQuestions {
    public static void main(String[] args) {
        var practiceQuestions = new PracticeQuestions();
        practiceQuestions.concurrencyAtomicVsVolatileQuestion();
        practiceQuestions.enumValueOfQuestion();
        practiceQuestions.streamReduceQuestion();
    }

    void concurrencyAtomicVsVolatileQuestion() {
        System.out.println("Q - What will be printed when the following CONCURRENCY code is executed?");
        ConcurrencyAtomicVsVolatile.main(null);
        System.out.println("""
                Explanation:
                    Because volatileCounter++ is not atomic, it's possible (though less likely with such small loop counts) 
                    to see interleaved and potentially incorrect results for volatileCounter. AtomicInteger guarantees atomic 
                    increments, so atomicCounter will always be correct.
                """);
    }

    void enumValueOfQuestion() {
        System.out.println("Q - What will be printed when the following ENUM code is executed?");
        EnumValueOf.main(null);
        System.out.println("""
                Explanation:
                    The EnumValueOf class defines an enum Seasons with constants SPRING, SUMMER, and WINTER. 
                    The toString method is overridden to return the lowercase version of the enum name. 
                    The main method iterates through the Seasons enum values, calling the processEnumValue method with the 
                    lowercase string representation of each enum value. The processEnumValue method attempts to convert the 
                    lowercase string back to a Seasons enum value using Seasons.valueOf(enumString). 
                    Because the valueOf method expects the enum constant names to be in uppercase, an IllegalArgumentException 
                    is always thrown, and "INVALID," is printed for each enum value.
                """);
    }

    void streamReduceQuestion() {
        System.out.println("Q - What will be printed when the following STREAM code is executed?");
        StreamReduce.main(null);
        System.out.println("""
                Explanation:
                    The StreamReduce class initializes a list of strings items with the values "Candy" and "Gum". 
                    The code then performs a reduce operation on a parallel stream.
                    Here's a step-by-step breakdown:
                    Inner reduce:
                    It starts with an identity of "" (empty string).
                    It concatenates all elements of the items list. The result of the inner reduce is "CandyGum".
                    Outer reduce:
                    The outer reduce now uses the items.parallelStream().reduce(...) as identity, which is "CandyGum"
                    In parallel stream processing, identity is applied to multiple elements in the stream.
                    So, it effectively does: "CandyGum" + "Candy" + "CandyGum" + "Gum".
                    Therefore, the final output is "CandyGumCandyCandyGumGum".
                """);
    }
}

class ConcurrencyAtomicVsVolatile {
    static AtomicInteger atomicCounter = new AtomicInteger(0);
    static volatile int volatileCounter = 0;

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                volatileCounter++;
                System.out.println(Thread.currentThread().getName() + " " + volatileCounter);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                atomicCounter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " " + atomicCounter);
            }
        });

        thread1.setName("T1");
        thread2.setName("T2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class EnumValueOf{
    enum Seasons {
        SPRING, SUMMER, WINTER;

        static void processEnumValue(String enumString) {
            try {
                Seasons enumValue = Seasons.valueOf(enumString);
                System.out.print(enumValue + ",");
            } catch (Exception e) {
                System.out.print("INVALID,");
            }
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public static void main(String[] args) {
        for (int i=0; i < Seasons.values().length; i++) {
            Seasons.processEnumValue(Seasons.values()[i].toString());
        }
        System.out.println();
    }
}

class StreamReduce {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("Candy", "Gum");

        var result = items.parallelStream()
                .reduce(items.parallelStream()
                                .reduce("",
                                        (a, b) -> a + b,
                                        (a, b) -> a + b),
                        (a, b) -> a + b,
                        (a, b) -> a + b);

        System.out.println(result);
    }
}