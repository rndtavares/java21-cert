package dev.ronaldotavares.java21.questions;

import java.util.concurrent.atomic.AtomicInteger;

public class PracticeQuestions {
    public static void main(String[] args) {
        var practiceQuestions = new PracticeQuestions();
        practiceQuestions.concurrencyAtomicVsVolatileQuestion();
    }

    void concurrencyAtomicVsVolatileQuestion() {
        System.out.println("What will be printed when the following code is executed?");
        ConcurrencyAtomicVsVolatile.main(null);
        System.out.println("""
                Explanation:
                    Because volatileCounter++ is not atomic, it's possible (though less likely with such small loop counts) to see interleaved and potentially incorrect results for volatileCounter. AtomicInteger guarantees atomic increments, so atomicCounter will always be correct.
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