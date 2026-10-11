package dev.ronaldotavares.java21._7_beyond_classes;

public class _7_1_ImplementingInterfaces {
}

abstract interface WalksOnTwoLegs {}

//final interface WalksOnEightLegs {} // DOES NOT COMPILE

class Biped {
    public static void main(String[] args) {
//        var e = new WalksOnTwoLegs(); // DOES NOT COMPILE
    }
}

interface Climb {
    Number getSpeed(int age);
}

class FieldMouse implements Climb {
    public Float getSpeed(int age) {
        return 8.5f;
    }
}

interface Nocturnal {
    public int hunt();
}

interface CanFly {
    public void flap();
}

interface HasBigEyes extends Nocturnal, CanFly {}

class Owl implements HasBigEyes {
    public int hunt() { return 5; }
    public void flap() { System.out.println("Flap!"); }
}

interface HasTail { public int getTailLength(); }

interface HasWhiskers { public int getNumberOfWhiskers(); }

abstract class HarborSeal implements HasTail, HasWhiskers {}

//class CommonSeal extends HarborSeal {} // DOES NOT COMPILE

interface CanRun {}
//class Cheetah extends CanRun {} // DOES NOT COMPILE
class Hyena {}
//interface HasFur extends Hyena {} // DOES NOT COMPILE

interface Herbivore { public int eatPlants(int plantsLeft); }
interface Omnivore { public int eatPlants(int foodRemaining); }
class Bear implements Herbivore, Omnivore {
    public int eatPlants(int plants) {
        System.out.print("Eating plants");
        return plants - 1;
    }
}

interface Herbivore1 { public void eatPlants(int plantsLeft); }
interface Omnivore1 { public int eatPlants(int foodRemaining); }
//class Tiger implements Herbivore1, Omnivore1 { // DOES NOT COMPILE
// Doesn't matter!
//}

interface Soar1 {
    int MAX_HEIGHT = 10;
    final static boolean UNDERWATER = true;
    void fly(int speed);
    abstract void takeoff();
    public abstract double dive();
}
abstract interface Soar2 {
    public static final int MAX_HEIGHT = 10;
    public final static boolean UNDERWATER = true;
    public abstract void fly(int speed);
    public abstract void takeoff();
    public abstract double dive();
}

interface Dance {
//    private int count = 4; // DOES NOT COMPILE
//    protected void step(); // DOES NOT COMPILE
}

abstract class Husky { // abstract modifier required
    abstract void play(); // abstract modifier required
}

interface Poodle { // abstract modifier optional
    void play(); // abstract modifier optional
}

class Webby extends Husky {
    void play() {} // play() is declared with package access in Husky
}

//class Georgette implements Poodle {
//    void play() {} // DOES NOT COMPILE - play() is public in Poodle
//}

interface IsColdBlooded {
    boolean hasScales();
    default double getTemperature() {
        return 10.0;
    }
}

class Snake implements IsColdBlooded {
    public boolean hasScales() { // Required override
        return true;
    }
    public double getTemperature() { // Optional override
        return 12.2;
    }
}

interface Carnivore {
//    public default void eatMeat(); // DOES NOT COMPILE
//    public int getRequiredFoodAmount() { // DOES NOT COMPILE
//        return 13;
//    }
}

interface Walk {
    public default int getSpeed() { return 5; }
}

interface Run {
    public default int getSpeed() { return 10; }
}

//class Cat implements Walk, Run {} // DOES NOT COMPILE

class Cat implements Walk, Run {
    public int getSpeed() { return 1; }
    public int getWalkSpeed() {
        return Walk.super.getSpeed();
    }
}

interface Dance1 {
    default int getRhythm() { return 33; }
}

class Snake1 implements Dance1 {
    static void move() {
        var snake = new Snake1();
        System.out.print(snake.getRhythm());
//        System.out.print(Dance1.getRhythm()); // DOES NOT COMPILE
    }
}

interface Hop {
    static int getJumpHeight() {
        return 8;
    }
}

class Skip implements Hop {
    public int skip() {
        return Hop.getJumpHeight();
    }
}

class Bunny implements Hop {
    public void printDetails() {
//        System.out.println(getJumpHeight()); // DOES NOT COMPILE
        System.out.println(Hop.getJumpHeight());
    }
}

interface Schedule {
    default void wakeUp() { checkTime(7); }
    private void haveBreakfast() { checkTime(9); }
    static void workOut() { checkTime(18); }
    private static void checkTime(int hour) {
        if (hour > 17) {
            System.out.println("You’re late!");
        } else {
            System.out.println("You have "+(17-hour)+" hours left "
                    + "to make the appointment");
        }
    }
}

interface ZooTrainTour {
    abstract int getTrainName();
    private static void ride() {}
    default void playHorn() { getTrainName(); ride(); }
//    public static void slowDown() { playHorn(); }
    static void speedUp() { ride(); }
}