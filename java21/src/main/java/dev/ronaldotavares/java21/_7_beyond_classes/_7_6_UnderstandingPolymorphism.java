package dev.ronaldotavares.java21._7_beyond_classes;

import java.util.Collections;
import java.util.List;

public class _7_6_UnderstandingPolymorphism {
    public static void main(String[] args) {

    }

    //commom practice to use interfaces as parameters
    void sortAndPrintZooAnimals(List<String> animals) {
        Collections.sort(animals);
        for(String a : animals) System.out.println(a);
    }
}

class Primate {
    public boolean hasHair() { return true; }
}

interface HasTail1 {
    public abstract boolean isTailStriped();
}

class Lemur extends Primate implements HasTail1 {
    public boolean isTailStriped() { return false; }
    public int age = 10;

    public static void main(String[] args) {
        Lemur lemur = new Lemur();
        System.out.println(lemur.age);

        HasTail1 hasTail = lemur;
        System.out.println(hasTail.isTailStriped());

        Primate primate = lemur;
        System.out.println(primate.hasHair());

        HasTail1 hasTail1 = new Lemur();
//        System.out.println(hasTail.age); // DOES NOT COMPILE

        Primate primate1 = new Lemur();
//        System.out.println(primate.isTailStriped()); // DOES NOT COMPILE

        Lemur lemur1 = new Lemur();
        Object lemurAsObject = lemur;

        casting();
    }

    private static void casting() {
        System.out.println("Casting");

        Lemur lemur = new Lemur();

        Primate primate = lemur;            // implicit cast
        Lemur lemur2 = (Lemur)primate;      // explicit cast

//        Lemur lemur3 = primate;             // DOES NOT COMPILE
    }
}

//Disallowed Casts
class Bird1 {}
class Fish2 {
    public static void main(String[] args) {
        Fish2 fish = new Fish2();
//        Bird1 bird = (Bird1)fish; // DOES NOT COMPILE

//        if (fish instanceof Bird1 b) { // DOES NOT COMPILE
//             Do stuff
//        }
    }
}

//Casting Interfaces
interface Canine {}
interface Dog {}

class Wolf1 implements Canine {}
//final class Wolf1 implements Canine {}

class BadCasts {
    public static void main(String[] args) {
        Wolf1 wolfy = new Wolf1();
        Dog badWolf = (Dog)wolfy; // Compiles, but throws ClassCastException
    }
}

//Instanceof
class Rodent {}
class Capybara extends Rodent {
    public static void main(String[] args) {
        Rodent rodent = new Rodent();
//        var capybara = (Capybara)rodent; // ClassCastException
        if (rodent instanceof Capybara c) {
            // Do stuff
        }
    }
}

//Polymorphism and Method Overriding
class Penguin {
    public int getHeight() { return 3; }
    public void printInfo() {
        System.out.print(this.getHeight());
//        System.out.print(super.getHeight()); // DOES NOT COMPILE
    }
}

class EmperorPenguin extends Penguin {
    public int getHeight() { return 8; }
//    public void printInfo() {
//        System.out.print(super.getHeight()); //3
//    }
    public static void main(String[] args) {
        new EmperorPenguin().printInfo(); // 8
    }
}

//Overriding vs. Hiding Members
class Penguin1 {
    public static int getHeight() { return 3; }
    public void printInfo() {
        System.out.println(this.getHeight());
    }
}
class CrestedPenguin extends Penguin1 {
    public static int getHeight() { return 8; }
    public static void main(String... fish) {
        new CrestedPenguin().printInfo(); //3
    }
}

class Marsupial {
    protected int age = 2;
    public static boolean isBiped() { return false; }
}

class Kangaroo extends Marsupial {
    protected int age = 6;
    public static boolean isBiped() { return true; }

    public static void main(String[] args) {
        Kangaroo joey = new Kangaroo();
        Marsupial moey = joey;
        System.out.println(joey.isBiped()); // true
        System.out.println(moey.isBiped()); // false
        System.out.println(joey.age);       // 6
        System.out.println(moey.age);       // 2
    }
}