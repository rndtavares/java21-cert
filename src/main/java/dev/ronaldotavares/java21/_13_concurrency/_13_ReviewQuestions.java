package dev.ronaldotavares.java21._13_concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.*;

public class _13_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Chapter 13 - Concurrency - Review Questions");

        var reviewQuestions = new _13_ReviewQuestions();
        reviewQuestions._5();
        reviewQuestions._6();
        reviewQuestions._8();
        reviewQuestions._10();
        reviewQuestions._11();
        reviewQuestions._12();
        reviewQuestions._13();
        reviewQuestions._19();
        reviewQuestions._20();
        reviewQuestions._23();
    }

    void _5() {
        System.out.println("5 - What statement about the following code is true?");

        var value1 = new AtomicLong(0);
        final long[] value2 = {0};
        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> {
                    value1.incrementAndGet();
//                    System.out.print(value1.incrementAndGet() + ",");
//                    System.out.println();
                });
//        synchronized (this) {
            IntStream.iterate(1, i -> 1).limit(100).parallel()
//                    .sorted()
                    .forEach(i -> ++value2[0]);
//                    .forEachOrdered(i -> ++value2[0]);
//        }
        System.out.println(value1 + " " + value2[0]);
    }

    void _6() {
        System.out.println("6 - About parallel streams");

        var data = List.of(2, 5, 1, 9, 8);
        data.stream().parallel()
                .mapToInt(s -> s)
                .peek(i -> System.out.println("peek " + i + " in thread " + Thread.currentThread().getName()))
                .forEachOrdered(System.out::println);
        System.out.println();
    }

    private void _8() {
        System.out.println("8 - Assuming this class is accessed by only a single thread at a time, what is the result of calling the method?");

        class Flavors {
            private static int counter;
            public static void countIceCreamFlavors()  {
                counter = 0;
                Runnable task = () -> counter++;
                LongStream.range(0, 500)
                        .forEach(m -> Thread.ofPlatform()
                                .priority(1)
                                .unstarted(task)
                                .run());
//                                .start());
                System.out.println(counter);
            } }

        Flavors.countIceCreamFlavors();
    }

    void _10() {
        System.out.println("10 - What is the result of executing the following code snippet?");

        SequencedCollection<Integer> lions = new ArrayList<>(List.of(1, 2, 3));
        SequencedCollection<Integer> tigers = new CopyOnWriteArrayList<>(lions);
        Set<Integer> bears = new ConcurrentSkipListSet<>();
        bears.addAll(lions);
        for (Integer item: tigers) tigers.add(4); // x1
        for (Integer item: bears) bears.add(5);   // x2
        System.out.println(lions.size() + " " + tigers.size()
                + " " + bears.size());
    }

    void _11() {
        System.out.println("11");

        Integer i1 = List.of(1, 2, 3, 4, 5).stream().findAny().get();
        synchronized(i1) { // y1
            Integer i2 = List.of(6, 7, 8, 9, 10)
                    .parallelStream()
                    .sorted()
                    .findAny().get(); // y2
            System.out.println(i1 + " " + i2);
        }
    }

    void _12() {
        System.out.println("12");

        class Class12 {
            void shutdown () throws InterruptedException {
                var service = Executors.newFixedThreadPool(4);
                var start = Instant.now();
                try {
                    service.execute(() -> takeNap(5000));
                    service.execute(() -> takeNap(5000));
                    service.execute(() -> takeNap(5000));
                } finally {
                    service.shutdown();
                }
                service.awaitTermination(2, TimeUnit.SECONDS);
                System.out.println("DONE! after " + Duration.between(start, Instant.now()).toMillis() + " ms");
            }
            void refactored () {
                var start = Instant.now();
                try (var service = Executors.newFixedThreadPool(4)) {
                    service.execute(() -> takeNap(5000));
                    service.execute(() -> takeNap(5000));
                    service.execute(() -> takeNap(5000));
                }
                System.out.println("DONE! after " + Duration.between(start, Instant.now()).toMillis() + " ms");
            }
        }

        Class12 class12 = new Class12();
        try {
            class12.shutdown();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        class12.refactored();

    }

    void _13() {
        System.out.println("13. What is the result of executing the following code snippet?");
        System.out.println(List.of("duck","flamingo","pelican")
                        .parallelStream().parallel()   // q1
                        .reduce(0,
//                                (c1, c2) -> c1.length() + c2.length(),  // q2
                                (c1, c2) -> c1 + c2.length(),  // q2
                (s1, s2) -> s1 + s2));      // q3
    }

    void _19(){
        System.out.println("""
         19. What is the result of executing the following application? (Choose all that apply.)
         A. It compiles and outputs the two numbers followed by Printed.
         B. The code will not compile because of line b1.
         C. The code will not compile because of line b2.
         D. The code will not compile because of line b3.
         E. It compiles, but the output cannot be determined ahead of time.
         F. It compiles but throws an exception at runtime.
         G. It compiles but waits forever at runtime.
         
         Answers on the book and wiley, but they are WRONG: E, G
         G is wrong because virtual threads are daemon threads, so an unclosed virtual-thread executor does not 
         keep the JVM alive forever; this differs from traditional platform-thread executors, which can keep the 
         application running if not shut down.
         
         So, the only correct answer is E, the question is a multiple choice question, what suggest you to answer more 
         than one option, this question should be canceled
         """);

        class PrintConstants {
            public static void main(String[] args) {
                var s = Executors.newVirtualThreadPerTaskExecutor();
                DoubleStream.of(3.14159, 2.71828)   // b1
                        .forEach(c -> s.submit(          // b2
                                () -> System.out.println(10 * c))); // b3
                s.execute(() -> System.out.println("Printed"));
            }
        }

        PrintConstants.main(null);
    }

    private void _20() {
        System.out.println("20");

        class PrintCounter {
            static int count = 0;
            public static void main(String[] args) throws
                    InterruptedException, ExecutionException {
                try (var service = Executors.newSingleThreadExecutor()) {
                    var r = new ArrayList<Future<?>>();
                    IntStream.iterate(0,i -> i + 1).limit(5).forEach(
//                            i -> r.add(service.execute(() -> {count++;})) // n1
                            i -> r.add(service.submit(() -> {count++;})) // n1
                    );
                    for (Future<?> result : r) {
                        System.out.print(result.get() + " "); // n2
                    }
                    System.out.println();
                }
            } }

        try {
            PrintCounter.main(null);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void _23() {
        System.out.println("23");
        class StockRoomTracker {
            public static void await(CyclicBarrier cb) { // j1
                try { cb.await(); } catch (Exception e) {}
            }
            public static void main(String[] args) {
                var cb = new CyclicBarrier(10,
                        () -> System.out.println("Stock Room Full!")); // j2
//                IntStream.iterate(1, i -> 1).limit(9).parallel()
                IntStream.iterate(1, i -> 1).limit(10).parallel()
                        .forEach(i -> await(cb)); // j3
            } }
        StockRoomTracker.main(null);
    }

    public static void takeNap(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // ok
        }
    }
}