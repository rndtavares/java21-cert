package dev.ronaldotavares.java21._5_methods;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

//import static zoo.A.TYPE;
//import static zoo.B.TYPE; // DOES NOT COMPILE

public class _5_5_AccessingStaticData {
    private static final int NUM_SECONDS_PER_MINUTE;
    private static final int NUM_MINUTES_PER_HOUR;
    private static final int NUM_SECONDS_PER_HOUR;
    static {
        NUM_SECONDS_PER_MINUTE = 60;
        NUM_MINUTES_PER_HOUR = 60;
    }
    static {
        NUM_SECONDS_PER_HOUR = NUM_SECONDS_PER_MINUTE * NUM_MINUTES_PER_HOUR;
    }

    private static int one;
    private static final int two;
    private static final int three = 3;
//    private static final int four; // DOES NOT COMPILE
    static {
        one = 1;
        two = 2;
//        three = 3;  // DOES NOT COMPILE
//        two = 4;    // DOES NOT COMPILE
    }

    public static void main(String[] args) {
        var p1 = new Penguin();
        p1.name = "Lilly";
        p1.nameOfTallestPenguin = "Lilly";
        var p2 = new Penguin();
        p2.name = "Willy";
        p2.nameOfTallestPenguin = "Willy";
        System.out.println(p1.name); // Lilly
        System.out.println(p1.nameOfTallestPenguin); // Willy
        System.out.println(p2.name); // Willy
        System.out.println(p2.nameOfTallestPenguin); // Willy

        System.out.println(Snake.hiss);

        Snake s = new Snake();
        System.out.println(s.hiss); // s is a Snake
        s = null;
        System.out.println(s.hiss); // s is still a Snake

        Snake.hiss = 4;
        Snake snake1 = new Snake();
        Snake snake2 = new Snake();
        snake1.hiss = 6;
        snake2.hiss = 5;
        System.out.println(Snake.hiss);
    }
}

class Penguin {
    String name;
    static String nameOfTallestPenguin;
}

class Koala {
    public static int count = 0; // static variable
    public static void main(String[] args) {
        System.out.print(count); // static method
    }
}

class KoalaTester {
    public static void main(String[] args) {
        Koala.main(new String[0]); // call static method
    }
}

class Snake {
    public static long hiss = 2;
}

class MantaRay {
    private String name = "Sammy";
//    private static String name = "Sammy";
    public static void first() { }
    public static void second() { }
    public void third() { System.out.print(name); }
//    public static void third() { System.out.print(name); } // DOES NOT COMPILE
    public static void main(String args[]) {
        first();
        second();
//        third(); // DOES NOT COMPILE
//        var ray = new MantaRay();
//        ray.third();
    }
}

class Giraffe {
    public void eat(Giraffe g) {}
    public void drink() {};
    public static void allGiraffeGoHome(Giraffe g) {}
    public static void allGiraffeComeOut() {}
}

class Gorilla {
    public static int count;
    public static void addGorilla() { count++; }
    public void babyGorilla() { count++; }
    public void announceBabies() {
        addGorilla();
        babyGorilla();
    }
    public static void announceBabiesToEveryone() {
        addGorilla();
//        babyGorilla(); // DOES NOT COMPILE
    }
    public int total;
//    public static double average
//      = total / count; // DOES NOT COMPILE
}

class Counter {
    private static int count;
    public Counter() { count++; }
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        System.out.println(count); // 3
    }
}

class ZooPen {
    private static final int NUM_BUCKETS = 45;
    public static void main(String[] args) {
//        NUM_BUCKETS = 5; // DOES NOT COMPILE
    }
}

class ZooInventoryManager {
    private static final String[] treats = new String[10];
    public static void main(String[] args) {
        treats[0] = "popcorn";
    }
}

class Panda {
    final static String name = "Ronda";
    static final int bamboo;
//    static final double height; // DOES NOT COMPILE
    static { bamboo = 5;}
}

class Imports {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two");
    }
}

class ZooParking {
    public static void main(String[] args) {
        List<String> list = asList("one", "two"); // No Arrays. prefix
    }
}