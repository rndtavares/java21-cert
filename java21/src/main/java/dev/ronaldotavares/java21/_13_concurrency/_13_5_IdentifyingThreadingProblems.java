package dev.ronaldotavares.java21._13_concurrency;

import java.util.concurrent.Executors;

public class _13_5_IdentifyingThreadingProblems {
    public static void main(String[] args) {
        System.out.println("Identifying Threading Problems");

        //The program will deadlock and never finish, you will need to stop it manually.
        Fox.main(null);
    }
}

class Food {}
class Water {}
record Fox(String name) {
    public void eatAndDrink(Food food, Water water) {
        synchronized(food) {
            System.out.println(name() + " Got Food!");
            move();
            synchronized(water) {
                System.out.println(name() + " Got Water!");
            }
        }
    }
    public void drinkAndEat(Food food, Water water) {
        synchronized(water) {
            System.out.println(name() + " Got Water!");
            move();
            synchronized(food) {
                System.out.println(name() + " Got Food!");
            }
        }
    }
    public void move() {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
    public static void main(String[] args) {
        // Create participants and resources
        var foxy = new Fox("Foxy");
        var tails = new Fox("Tails");
        var food = new Food();
        var water = new Water();
        // Process data
        try (var service = Executors.newScheduledThreadPool(10)) {
            service.submit(() -> foxy.eatAndDrink(food,water));
            service.submit(() -> tails.drinkAndEat(food,water));
        }
    }
}