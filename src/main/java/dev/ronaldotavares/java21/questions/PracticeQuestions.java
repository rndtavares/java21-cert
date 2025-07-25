package dev.ronaldotavares.java21.questions;

import java.util.concurrent.atomic.AtomicInteger;

public class PracticeQuestions {
    public static void main(String[] args) {
        var practiceQuestions = new PracticeQuestions();
        practiceQuestions.concurrencyAtomicVsVolatileQuestion();
        practiceQuestions.enumValueOfQuestion();
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
                System.out.println(enumValue + ",");
            } catch (Exception e) {
                System.out.println("INVALID,");
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
    }
}