package dev.ronaldotavares.java21._13_concurrency;

import java.util.concurrent.*;

public class _13_2_CreatingThreadsWithTheConcurrencyAPI {
    public static void main(String[] args) {
        System.out.println("Creating Threads with the Concurrency API");

        var creatingThreads = new _13_2_CreatingThreadsWithTheConcurrencyAPI();
        creatingThreads.introducingTheSingleThreadExecutor();
        creatingThreads.waitingForResults();
        creatingThreads.investigatingCallable();
        creatingThreads.shuttingDownAThreadExecutor();
        creatingThreads.schedulingTasks();
    }

    void introducingTheSingleThreadExecutor(){
        System.out.println("Introducing the Single Thread Executor");

        Runnable printInventory = () -> System.out.println("Printing zoo inventory");
        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++)
                System.out.println("Printing record:" + i);
        };

        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            System.out.println("begin");
            service.execute(printInventory);
            service.execute(printRecords);
            service.execute(printInventory);
            System.out.println("end");
        }
    }

    void waitingForResults(){
        System.out.println("Waiting for Results");

        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<?> future = service.submit(() -> System.out.println("Hello"));

            try {
                CheckResults.main(null);
            } catch (Exception e) {
                System.out.println(e);
            }
        };
    }

    void investigatingCallable(){
        System.out.println("Investigating Callable");
        try (var service = Executors.newSingleThreadExecutor()) {
            Future<Integer> result = service.submit(() -> 30 + 11);
            System.out.println(result.get());   // 41
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void shuttingDownAThreadExecutor(){
        System.out.println("Shutting Down a Thread Executor");

        var service = Executors.newSingleThreadExecutor();
        service.submit(() -> System.out.println("Never stops"));

        service.close(); //if absent program will hang forever
    }

    void schedulingTasks(){
        System.out.println("Scheduling Tasks");

        ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();
        scheduledService.close();

        try (var service = Executors.newSingleThreadScheduledExecutor()) {
            Runnable task1 = () -> System.out.println("Hello Zoo");
            Callable<String> task2 = () -> "Monkey";
            ScheduledFuture<?> r1 = service.schedule(task1, 2, TimeUnit.SECONDS);
            ScheduledFuture<?> r2 = service.schedule(task2, 800, TimeUnit.MILLISECONDS);

            service.scheduleAtFixedRate(task1, 5, 1, TimeUnit.SECONDS);

            service.scheduleWithFixedDelay(task1, 0, 2, TimeUnit.MINUTES);
        }
    }
}

class CheckResults {
    private static int counter = 0;
    public static void main(String[] unused) throws Exception {
        try (var service = Executors.newSingleThreadExecutor()) {
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 1_000_000; i++) counter++;
            });
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        } catch (TimeoutException e) {
            System.out.println("Not reached in time");
        }
    }
}

