package dev.ronaldotavares.java21._6_class_design;

public class _6_2_CreatingClasses {
}

class Animal {
    private int age;
    protected String name;
    public int getAge() {
        return age;
    }
    public void setAge(int newAge) {
        age = newAge;
    }
}

// Lion.java
class Lion extends Animal {
    protected void setProperties(int age, String n) {
        setAge(age);
        name = n;
    }
    public void roar() {
        System.out.print(name + ", age " + getAge() + ", says: Roar!");
//        System.out.print("Lion's age:" + age); // DOES NOT COMPILE
    }
    public static void main(String[] args) {
        var lion = new Lion();
        lion.setProperties(3,"kion");
        lion.roar();
    }
}

class Flamingo {
    private String color = null;
    public void setColor(String color) {
//        color = color;
        this.color = color; // Sets the instance variable with method parameter
    }
    public static void main(String... unused) {
        var f = new Flamingo();
        f.setColor("PINK");
        System.out.print(f.color);
    }
}

class Duck {
    private String color;
    private int height;
    private int length;

    public void setData(int length, int theHeight) {
        length = this.length; // Backwards -- no good!
        height = theHeight; // Fine, because a different name
        this.color = "white"; // Fine, but this. reference not necessary
    }

    public static void main(String[] args) {
        Duck b = new Duck();
        b.setData(1,2);
        System.out.print(b.length + " " + b.height + " " + b.color);
} }

class Reptile {
    protected int speed = 10;
}

// Crocodile.java
class Crocodile extends Reptile {
    protected int speed = 20;
    public int getSpeed() {
//        return speed;
        return super.speed; // Causes the program to now print 10
    }
    public static void main(String[] data) {
        var croc = new Crocodile();
        System.out.println(croc.getSpeed()); // 20
} }

class Insect {
    protected int numberOfLegs = 4;
    String label = "buggy";
}

class Beetle extends Insect {
    protected int numberOfLegs = 6;
    short age = 3;
    public void printData() {
        System.out.println(this.label);
        System.out.println(super.label);
        System.out.println(this.age);
//        System.out.println(super.age);
        System.out.println(numberOfLegs);
    }
    public static void main(String []n) {
        new Beetle().printData();
    }
}