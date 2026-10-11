package dev.ronaldotavares.java21._6_class_design;

public class _6_3_DeclaringConstructors {
    public static void main(String[] args) {
    }
}

class Bunny {
    public Bunny() {
        System.out.print("hop");
    }
//    public bunny() {} // DOES NOT COMPILE
    public void Bunny() {}
}

class Bonobo {
//    public Bonobo(var food) { // DOES NOT COMPILE
//    }
}

class Turtle {
    private String name;
    public Turtle() {
        name = "John Doe";
    }
    public Turtle(int age) {}
    public Turtle(long age) {}
    public Turtle(String newName, String... favoriteFoods) {
        name = newName;
    }

    public static void main(String[] args) {
        new Turtle(15);
    }
}

class Rabbit {
    public Rabbit() {}
    public static void main(String[] args) {
//        new Rabbit(); // Calls the default constructor
        new Rabbit(); // Calls the user-defined constructor
    }
}

class Rabbit1 {}

class Rabbit2 {
    public Rabbit2() {}
}

class Rabbit3 {
    public Rabbit3(boolean b) {}
}

class Rabbit4 {
    private Rabbit4() {}
}

class RabbitsMultiply {
    public static void main(String[] args) {
        var r1 = new Rabbit1();
        var r2 = new Rabbit2();
        var r3 = new Rabbit3(true);
//        var r4 = new Rabbit4(); // DOES NOT COMPILE
} }

class Hamster {
    private String color;
    private int weight;
    public Hamster(int weight, String color) { // First constructor
        this.weight = weight;
        this.color = color;
    }
    public Hamster(int weight) {
        this.weight = weight;
        color = "brown";
//        Hamster(weight, "brown"); // DOES NOT COMPILE
        new Hamster(weight, "brown"); // Compiles, but creates an extra object
    }
//    public Hamster(int weight) { // Second constructor
//        this(weight, "brown");
//    }
//    public Hamster(int weight) {
//        System.out.println("chew");
//        // Set weight and default color
//        this(weight, "brown"); // DOES NOT COMPILE
//    }
}

class Gopher {
    public Gopher(int dugHoles) {
//        this(5); // DOES NOT COMPILE
//        this(); // DOES NOT COMPILE
    }
    public Gopher() {
        this(5); // DOES NOT COMPILE
    }
}

class Animal1 {
    protected int age;
    public Animal1(int age) {
        super(); // Refers to constructor in java.lang.Object
        this.age = age;
    }
}

class Zebra extends Animal1 {
    public Zebra(int age) {
        super(age); // Refers to constructor in Animal
    }
    public Zebra() {
        this(4); // Refers to constructor in Zebra with int argument
    }

    public static void main(String[] args) {
        var zebra = new Zebra();
        System.out.println(zebra.age);
    }
}

class Zoo {
    public Zoo() {
        System.out.println("Zoo created");
//        super(); // DOES NOT COMPILE
    }
}

class Zoo2 {
    public Zoo2() {
        super();
        System.out.println("Zoo created");
//        super(); // DOES NOT COMPILE
    }
}

class Animal2 {
    private int age;
    private String name;
    public Animal2(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    public Animal2(int age) {
        super();
        this.age = age;
        this.name = null;
    }
}

class Gorilla extends Animal2 {
    public Gorilla(int age) {
        super(age, "Gorilla"); // Calls the first Animal constructor
    }
    public Gorilla() {
        super(5); // Calls the second Animal constructor
    }
}

class Donkey1 {}

class Donkey2 {
    public Donkey2() {}
}

class Donkey3 {
    public Donkey3() {
        super();
    }
}

class Mammal1 {
    public Mammal1(int age) {}
}

//class Seal extends Mammal1 {} // DOES NOT COMPILE

//class Elephant1 extends Mammal1 {
//    public Elephant1() {} // DOES NOT COMPILE
//}

//class Seal extends Mammal1 {
//    public Seal() {
//        super(); // DOES NOT COMPILE
//    }
//}

class Seal1 extends Mammal1 {
    public Seal1() {
        super(6); // Explicit call to parent constructor
    }
}

class Elephant2 extends Mammal1 {
    public Elephant2() {
        super(4); // Explicit call to parent constructor
    }
}

class AfricanElephant extends Elephant2 {}