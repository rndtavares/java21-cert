package dev.ronaldotavares.java21._7_beyond_classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class _7_5_CreatingNestedClasses {
    public static void main(String[] args) {
        var home = new Home();
        Home.Room room = home.new Room(); // Create the inner class instance
        room.enter();

        new Home().new Room().enter(); // Sorry, it looks ugly to us too!
    }
}

class Home {
    private String greeting = "Hi"; // Outer class instance variable

    protected class Room { // Inner class declaration
        public int repeat = 3;
        public void enter() {
            for (int i = 0; i < repeat; i++) greet(greeting);
        }
        private static void greet(String message) {
            System.out.println(message);
        }
    }

    public void enterRoom() {
        var room = new Room();
        room.enter();
    }
    public static void main(String[] args) {
        var home = new Home();
        home.enterRoom();
    }
}

class A {
    private int x = 10;
    class B {
        private int x = 20;
        class C {
            private int x = 30;
            public void allTheX() {
                System.out.println(x);           // 30
                System.out.println(this.x);      // 30
                System.out.println(B.this.x);    // 20
                System.out.println(A.this.x);    // 10
            }
        }
    }
    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
        c.allTheX();
    }
}

class Fox {
    private class Den {}
    public void goHome() {
        new Den();
    }
    public static void visitFriend() {
//        new Den(); // DOES NOT COMPILE - static method
        new Fox().new Den(); // OK - static method
    }
}

class Squirrel {
    public void visitFox() {
//        new Den(); // DOES NOT COMPILE - outside fox class
//        new Fox().new Den(); // DOES NOT COMPILE - Den is private to Fox
    }
}

class Park {
    static class Ride {
        private int price = 6;
    }

    public static void main(String[] args) {
        var ride = new Ride();
        System.out.println(ride.price); // 6
    }
}

class Emu1 {
    String name = "Emmy";
    static Feathers createFeathers() {
        return new Feathers("grey");
    }
    record Feathers(String color) {
        void fly() {
//            System.out.print(name + " is flying"); // DOES NOT COMPILE
        }
    }
}

class Emu2 {
    String name = "Emmy";
    static Feathers createFeathers() {
//        return new Feathers("grey"); // DOES NOT COMPILE
        return new Emu2().new Feathers("grey"); // DOES NOT COMPILE
    }
    class Feathers {
        String color;
        public Feathers(String color) {
            this.color = color;
        }

        void fly() {
            System.out.print(name + " is flying"); // OK
        }
    }
}

class PrintNumbers {
    private int length = 5;

    public void calculate() {
        final int width = 20;
        class Calculator {
            public void multiply() {
                System.out.print(length * width);
            }
        }
        new Calculator().multiply(); // 100
    }
    public static void main(String[] args) {
        var printer = new PrintNumbers();
        printer.calculate(); // 100
    }

    public void processData() {
        final int length = 5;
        int width = 10;
        int height = 2;
        class VolumeCalculator {
//            public int multiply() {
//                return length * width * height; // DOES NOT COMPILE
//            }
        }
        width = 2;
    }
}

class ZooGiftShop {
    abstract class SaleTodayOnly {
        abstract int dollarsOff();
    }

    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {
            int dollarsOff() { return 3; }
        }; // Don't forget the semicolon!
        return basePrice - sale.dollarsOff();
    }
}

class ZooGiftShopInterface {
    interface SaleTodayOnly {
        int dollarsOff();
    }

    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {
            public int dollarsOff() { return 3; }
        };
        return basePrice - sale.dollarsOff();
    }

    public int admissionLambda(int basePrice) {
        SaleTodayOnly sale = () -> 3;
        return basePrice - sale.dollarsOff();
    }
}

class Gorilla {
    interface Climb {}
    Climb climbing = new Climb() {};
}