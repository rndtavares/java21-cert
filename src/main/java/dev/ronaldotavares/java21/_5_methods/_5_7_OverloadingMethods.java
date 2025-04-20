package dev.ronaldotavares.java21._5_methods;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class _5_7_OverloadingMethods {

    public static void walk(int[] ints) {}
    public static void walk(Integer[] integers) {}
}

class Falcon {
    public void fly(int numMiles) {}
    public void fly(short numFeet) {}
    public boolean fly() { return false; }
    void fly(int numMiles, short numFeet) {}
    public void fly(short numFeet, int numMiles) throws Exception {}
}

class Eagle {
    public void fly(int numMiles) {}
//    public int fly(int numMiles) { return 1; } // DOES NOT COMPILE
}

class Hawk {
    public void fly(int numMiles) {}
//    public static void fly(int numMiles) {} // DOES NOT COMPILE
//    public void fly(int numKilometers) {} // DOES NOT COMPILE
}

class Dove {
    public void fly(int numMiles) {
        System.out.println("int");
    }
    public void fly(short numFeet) {
        System.out.println("short");
    }
}

class Pelican {
    public void fly(String s) {
        System.out.print("string");
    }
    public void fly(Object o) {
        System.out.print("object");
    }
    public static void main(String[] args) {
        var p = new Pelican();
        p.fly("test");
        System.out.print("-");
        p.fly(56);
    }
}

class Parrot {
    public static void print(List<Integer> i) {
        System.out.print("I");
    }
    public static void print(CharSequence c) {
        System.out.print("C");
    }
    public static void print(Object o) {
        System.out.print("O");
    }
    public static void main(String[] args){
        print("abc");
        print(Arrays.asList(3));
        print(LocalDate.of(2019, Month.JULY, 4));
    }
}

class Ostrich {
    public void fly(int i) {
        System.out.print("int");
    }
    public void fly(long l) {
        System.out.print("long");
    }
    public static void main(String[] args) {
        var p = new Ostrich();
        p.fly(123);
        System.out.print("-");
        p.fly(123L);
    }
}

class Kiwi {
    public void fly(int numMiles) {}
    public void fly(Integer numMiles) {}
}

class Toucan {
    public static void main(String[] args) {
        fly(new int[] { 1, 2, 3 }); // Allowed to call either fly() method
//        fly(1, 2, 3); // Allowed to call only the fly() method using varargs
    }
    public static void fly(int[] lengths) {}
//    public static void fly(int... lengths) {} // DOES NOT COMPILE
}

class Glider {
    public static String glide(String s) {
        return "1";
    }
    public static String glide(String... s) {
        return "2";
    }
    public static String glide(Object o) {
        return "3";
    }
    public static String glide(String s, String t) {
        return "4";
    }
    public static void main(String[] args) {
        System.out.print(glide("a"));
        System.out.print(glide("a","b"));
        System.out.print(glide("a","b","c"));
    }
}
