package dev.ronaldotavares.java21._13_concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _13_3_WritingThreadSafeCode {
    public static void main(String[] args) {
        System.out.println("Writing Thread Safe Code");

        var writingThreadSafeCode = new _13_3_WritingThreadSafeCode();

        writingThreadSafeCode.understandingThreadSafety();
        writingThreadSafeCode.protectingDataWithAtomicClasses();
        writingThreadSafeCode.improvingAccessWithSynchronizedBlocks();
        writingThreadSafeCode.synchronizingMethods();
        writingThreadSafeCode.understandingTheLockFramework();
        writingThreadSafeCode.orchestratingTasksWithACyclicBarrier();
    }

    void understandingThreadSafety() {
        System.out.println("Understanding Thread Safety");

        SheepManager.main(null);
        System.out.println();
    }

    void protectingDataWithAtomicClasses() {
        System.out.println("Protecting Data with Atomic Classes");

        AtomicSheepManager.main(null);
        System.out.println();
    }

    void improvingAccessWithSynchronizedBlocks() {
        System.out.println("Improving Access with Synchronized Blocks");

        var manager = new SheepManager();
        synchronized (manager) {
            // Work to be completed by one thread at a time
        }

        SynchronizedSheepManager.main(null);
        System.out.println();
    }

    void synchronizingMethods() {
        System.out.println("Synchronizing Methods");

        sing();
        System.out.println();
        song();
        System.out.println();
    }

    void sing() {
        synchronized (this) {
            System.out.print("La la la!");
        }
    }

    synchronized void song() {
        System.out.print("La la la!");
    }

    void understandingTheLockFramework(){
        System.out.println("Understanding the Lock Framework");

        applyingAReentrantLock();
        attemptingToAcquireALock();
        acquiringTheSameLockTwice();
        reentrantReadWriteLock();
    }

    void applyingAReentrantLock(){
        System.out.println("Applying a Reentrant Lock");

        // Implementation #1 with a synchronized block
        var object = new Object();
        synchronized(object) {
            // Protected code
        }

        // Implementation #2 with a Lock
        var myLock = new ReentrantLock();
        try {
            myLock.lock();
            // Protected code
        } finally {
            myLock.unlock();
        }

        try {
            var lock = new ReentrantLock();
            lock.unlock(); // IllegalMonitorStateException
        }catch (IllegalMonitorStateException e){
            System.out.println(e);
        }
    }

    void attemptingToAcquireALock(){
        System.out.println("Attempting to Acquire a Lock");

        var myLock = new ReentrantLock();
        Thread.ofPlatform().start(() -> printHello(myLock));
        if (myLock.tryLock()) {
            try {
                System.out.println("Lock obtained, entering protected code");
            } finally {
                myLock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock, doing something else");
        }
    }

    static void printHello(Lock myLock) {
        try {
            myLock.lock();
            System.out.println("Hello");
        } finally {
            myLock.unlock();
        }
    }

    void acquiringTheSameLockTwice(){
        System.out.println("Acquiring the Same Lock Twice");

        var myLock = new ReentrantLock();
        if (myLock.tryLock()) { //true
            try {
                myLock.lock(); //second lock
                System.out.println("Lock obtained, entering protected code");
            } finally {
                myLock.unlock(); //first release
            } }

        Thread.ofPlatform().start(() -> System.out.println("twice lock=" + myLock.tryLock())); //false
    }

    void reentrantReadWriteLock(){
        System.out.println("reentrantReadWriteLock");

        var lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();
        lock.readLock().lock();
        System.out.println(lock.isWriteLocked());     // true
        System.out.println(lock.getReadLockCount());  // 1

        lock.writeLock().unlock();
        System.out.println(lock.isWriteLocked());     // false
        System.out.println(lock.getReadLockCount());  // 1

        lock.readLock().unlock();
        System.out.println(lock.getReadLockCount());  // 0

//        lock.readLock().lock();
//        lock.writeLock().lock(); // Wait forever, read lock cannot be upgraded to a write lock, must release first
    }

    void orchestratingTasksWithACyclicBarrier(){
        System.out.println("Orchestrating Tasks with a Cyclic Barrier");

        LionPenManager.main(null);

        LionPenManagerCyclicBarrier.main(null);
    }
}

class SheepManager {
    private int sheepCount = 0;
    private void incrementAndReport() {
        System.out.print((++sheepCount) + " ");
    }
    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(20)) {
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++)
                service.submit(() -> manager.incrementAndReport());
        }
    }
}

class AtomicSheepManager {
    private AtomicInteger sheepCount = new AtomicInteger(0);
    private void incrementAndReport() {
        System.out.print(sheepCount.incrementAndGet() + " ");
    }
    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(20)) {
            AtomicSheepManager manager = new AtomicSheepManager();
            for (int i = 0; i < 10; i++)
                service.submit(() -> manager.incrementAndReport());
        }
    }
}

class SynchronizedSheepManager {
    private int sheepCount = 0;

//    private final Object herd = new Object();
    private void incrementAndReport() {
//        synchronized(herd) { would also work, we need only to sinchronize with the same object
        synchronized(this) {
            System.out.print((++sheepCount) + " ");
        }
    }
    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(20)) {
            var manager = new SynchronizedSheepManager();
            for (int i = 0; i < 10; i++)
                service.submit(() -> manager.incrementAndReport());
//            for (int i = 0; i < 10; i++) {
//                synchronized(manager) { // Synchronize only the thread creation, not the execution
//                    service.submit(() -> manager.incrementAndReport());
//                }
//            }
        }
    }

//    static void dance() {
//        synchronized (SheepManager.class) {
//            System.out.print("Time to dance!");
//        }
//    }
    static synchronized void dance() {
            System.out.print("Time to dance!");
    }
}

class LionPenManager {
    private void removeLions() { System.out.println("Removing lions"); }
    private void cleanPen() { System.out.println("Cleaning the pen"); }
    private void addLions() { System.out.println("Adding lions"); }
    public void performTask() {
        removeLions();
        cleanPen();
        addLions();
    }
    public static void main(String[] args) {
        System.out.println("LionPenManager");
        try (var service = Executors.newFixedThreadPool(4)) {
            var manager = new LionPenManager();
            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask());
        }
    }
}

class LionPenManagerCyclicBarrier {
    private void removeLions() { System.out.println("Removing lions"); }
    private void cleanPen() { System.out.println("Cleaning the pen"); }
    private void addLions() { System.out.println("Adding lions"); }
    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeLions();
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            // Handle checked exceptions here
        }
    }
    public static void main(String[] args) {
        System.out.println("LionPenManagerCyclicBarrier");
        try (var service = Executors.newFixedThreadPool(4)) {
            var manager = new LionPenManagerCyclicBarrier();
            var c1 = new CyclicBarrier(4);
            var c2 = new CyclicBarrier(4,
                    () -> System.out.println("*** Pen Cleaned!"));
            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask(c1, c2));
        }
    }
}