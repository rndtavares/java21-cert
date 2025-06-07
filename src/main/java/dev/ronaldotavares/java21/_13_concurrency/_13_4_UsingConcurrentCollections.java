package dev.ronaldotavares.java21._13_concurrency;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class _13_4_UsingConcurrentCollections {
    public static void main(String[] args) {
        System.out.println("Using Concurrent Collections");

        var workingWithParallelStreams = new _13_4_UsingConcurrentCollections();

        workingWithParallelStreams.understandingMemoryConsistencyErrors();
        workingWithParallelStreams.workingWithConcurrentClasses();
    }

    void understandingMemoryConsistencyErrors(){
        System.out.println("Understanding Memory Consistency Errors");

        try {
            var foodData = new HashMap<String, Integer>();

            foodData.put("penguin", 1);
            foodData.put("flamingo", 2);
            for (String key : foodData.keySet())
                foodData.remove(key);
        } catch (Exception e) {
            System.out.println(e);
        }

        var concurrentFoodData = new ConcurrentHashMap<String, Integer>();

        concurrentFoodData.put("penguin", 1);
        concurrentFoodData.put("flamingo", 2);
        for (String key : concurrentFoodData.keySet())
            concurrentFoodData.remove(key);

    }

    void workingWithConcurrentClasses(){
        System.out.println("Working with Concurrent Classes");

        List<Integer> favNumbers = new CopyOnWriteArrayList<>(List.of(4, 3, 42));
        for (var n : favNumbers) {
            System.out.print(n + " ");         // 4 3 42
            favNumbers.add(n + 1);
        }
        System.out.println();
        System.out.println("Size: " + favNumbers.size());  // Size: 6
    }
}
