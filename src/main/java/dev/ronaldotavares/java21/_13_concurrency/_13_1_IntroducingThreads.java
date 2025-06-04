package dev.ronaldotavares.java21._13_concurrency;

import java.util.stream.Stream;

public class _13_1_IntroducingThreads {
    public static void main(String[] args) {
        System.out.println("Introducing Threads");

        var introducingThreads = new _13_1_IntroducingThreads();
//        introducingThreads.platformThreadsExample(); //OOM error occours
        introducingThreads.threadConcurrency();
        introducingThreads.creatingThreads();
        introducingThreads.daemonThreads();
        introducingThreads.interrupt();
    }

    void platformThreadsExample() {
        System.out.println("Platform Threads Example");
        try {
            PlatformThreadsExample.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void threadConcurrency(){
        System.out.println("Thread Concurrency Example");

        var thread1 = new Thread(() -> System.out.println("Super Important"));
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();

        var thread2 = new Thread(() -> System.out.println("Less Important"));
        thread2.start();
        thread2.setPriority(2);
    }

    void creatingThreads(){
        System.out.println("Creating Threads");

        Thread.ofPlatform().start(() -> System.out.println("** Hello"));
        System.out.println("** World");

        simpleExample();
        complexExample();
    }

    void simpleExample() {
        System.out.println("Simple Example Virtual Thread");
        Thread t = Thread.ofVirtual().unstarted(() -> {
            System.out.println("Hello from a virtual thread!");
        });
        // Do some other stuff
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    void complexExample() {
        System.out.println("Complex example mixing platform and virtual threads");
        try {
            ComplexExample.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void daemonThreads(){
        System.out.println("Daemon Threads Example");
//        Zoo.main(new String[]{"false"}); // wait to finish
        Zoo.main(new String[]{"true"}); // daemon thread does not wait to finish
    }

    void interrupt(){
        System.out.println("Interrupting Threads Example");
        var thread = Thread.ofPlatform().start(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
        });
        thread.interrupt();

        var thread1 = Thread.ofPlatform().start(() -> {
            while(true) {
                if(Thread.interrupted())
                    System.out.println("Someone interrupted us!");
            }});
        thread1.interrupt();
    }
}

class PlatformThreadsExample {
    static void waitUp() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var threads = Stream.generate(() -> Thread.ofPlatform()
                        .unstarted(PlatformThreadsExample::waitUp))
                .limit(1_000_000)
                .toList();
        threads.forEach(Thread::start);
        for (var t : threads)
            t.join();
    }
}

class ComplexExample {
    public static void main(String[] args) throws InterruptedException {
        Runnable printInventory = () -> System.out.println("Printing zoo inventory");
        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++)
                System.out.println("Printing record:" + i);
        };
        System.out.println("begin");
        var platformThread = Thread.ofPlatform()
                .priority(10)
                .start(printInventory);
        var virtualThread = Thread.ofVirtual()
                .start(printRecords);
        var constructorThread = new Thread(printInventory);
        constructorThread.start();
        System.out.println("end");
        platformThread.join();
        virtualThread.join();
        constructorThread.join();

        //does not create a new thread, runs in the current thread
        new Thread(printInventory).run();
    }
}

class Zoo {
    public static void pause() { // Defines the thread task
        try {
            Thread.sleep(10_000); // Wait for 10 seconds
        } catch (InterruptedException e) {
        }
        System.out.println("Thread finished!");
    }

    public static void main(String[] args) {
        var job = Thread.ofPlatform().daemon(Boolean.getBoolean(args[0])).start(Zoo::pause);
        System.out.println("Main method finished!");
    }
}