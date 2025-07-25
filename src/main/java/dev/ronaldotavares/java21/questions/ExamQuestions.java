package dev.ronaldotavares.java21.questions;

import java.util.concurrent.atomic.AtomicInteger;

public class ExamQuestions {
    public static void main(String[] args) {
        var examQuestions = new ExamQuestions();
        examQuestions.concurrencyAtomicVsVolatileQuestion();
    }

    void concurrencyAtomicVsVolatileQuestion() {
        System.out.println("What will be printed when the following code is executed?");
        ConcurrencyAtomicVsVolatile.main(null);
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