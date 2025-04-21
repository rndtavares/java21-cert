package dev.ronaldotavares.java21._6_class_design;

public class _6_1_UnderstandingInheritance {
}

class BigCat {
    protected double size;
}

class Jaguar extends BigCat {
    public Jaguar() {
        size = 10.2;
    }
    public void printDetails() {
        System.out.print(size);
    }
}

class Spider {
    public void printDetails() {
//        System.out.println(size); // DOES NOT COMPILE
    }
}

class Mammal {}

final class Rhinoceros extends Mammal {}

//class Clara extends Rhinoceros {} // DOES NOT COMPILE

class Zoo1 { }

//class Zoo1 extends java.lang.Object { }