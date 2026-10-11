package dev.ronaldotavares.java21._5_methods;

public class _5_2_DeclaringLocalAndInstanceVariables {
    public static void main(String[] args) {
        zooAnimalCheckup(true);
        zooAnimalCheckup1(true);
        zooAnimalCheckup();
    }

    private static void zooAnimalCheckup(boolean isWeekend) {
        final int rest;
        if(isWeekend) rest = 5; else rest = 20;
        System.out.print(rest);

        final var giraffe = new Animal();
        final int[] friends = new int[5];

//        rest = 10; // DOES NOT COMPILE
//        giraffe = new Animal(); // DOES NOT COMPILE
//        friends = null; // DOES NOT COMPILE
    }

    private static void zooAnimalCheckup1(boolean isWeekend) {
        final int rest;
        if(isWeekend) rest = 5;
//        System.out.print(rest); // DOES NOT COMPILE
    }

    private static void zooAnimalCheckup() {
        final int rest = 5;
        final Animal giraffe = new Animal();
        final int[] friends = new int[5];

        giraffe.setName("George");
        friends[2] = 2;
    }

    private static String zooFriends() {
        String name = "Harry the Hippo";
        var size = 10;
        boolean wet;
        if(size> 100) size++;
        name.substring(0);
        wet = true;
        return name;
    }
}

class Lion {
    int hunger = 4;

    public int feedZooAnimals() {
        int snack = 10; // Local variable
        if(snack> 4) {
            long dinnerTime = snack++;
            hunger--;
        }
        return snack;
    }
}

class Animal {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}

class PolarBear {
    final int age = 10;
    final int fishEaten;
    final String name;

    { fishEaten = 10; }

    public PolarBear() {
        name = "Robert";
    }
}
