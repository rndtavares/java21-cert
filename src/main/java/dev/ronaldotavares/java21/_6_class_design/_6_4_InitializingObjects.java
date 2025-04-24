package dev.ronaldotavares.java21._6_class_design;

public class _6_4_InitializingObjects {
}

class Animal3 {
    static { System.out.print("A"); }
}

class Hippo3 extends Animal3 {
    public static void main(String[] grass) {
        System.out.print("C");
        new Hippo3();
        new Hippo3();
        new Hippo3();
    }
    static { System.out.print("B"); }
}

class HippoFriend {
    public static void main(String[] grass) {
        System.out.print("C");
        new Hippo3();
    }
}

class MouseHouse {
    private final int volume;
    private final String name = "The Mouse House"; // Declaration assignment
    {
        volume = 10; // Instance initializer assignment
    }
}

class MouseHouse1 {
    private final int volume;
    private final String name;
    public MouseHouse1() {
        this.name = "Empty House"; // Constructor assignment
    }
    {
        volume = 10; // Instance initializer assignment
    }
}

class MouseHouse2 {
    private final int volume;
    private final String type;
    {
        this.volume = 10;
    }
    public MouseHouse2(String type) {
        this.type = type;
    }
//    public MouseHouse2() { // DOES NOT COMPILE
//        this.volume = 2; // DOES NOT COMPILE
//    }
    public MouseHouse2() {
        this(null);
    }
}

class ZooTickets {
    private String name = "BestZoo";
    { System.out.print(name + "-"); }
    private static int COUNT = 0;
    static { System.out.print(COUNT + "-"); }
    static { COUNT += 10; System.out.print(COUNT + "-"); }

    public ZooTickets() {
        System.out.print("z-");
    }

    public static void main(String... patrons) {
        new ZooTickets();
} }

class Primate {
    public Primate() {
        System.out.print("Primate-");
    } }

class Ape extends Primate {
    public Ape(int fur) {
        System.out.print("Ape1-");
    }
    public Ape() {
        System.out.print("Ape2-");
    } }

class Chimpanzee extends Ape {
    public Chimpanzee() {
        super(2);
        System.out.print("Chimpanzee-");
    }
    public static void main(String[] args) {
        new Chimpanzee();
    } }

class Cuttlefish {
    private String name = "swimmy";
    { System.out.println(name); }
    private static int COUNT = 0;
    static { System.out.println(COUNT); }
    { COUNT++; System.out.println(COUNT); }

    public Cuttlefish() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        System.out.println("Ready");
        new Cuttlefish();
    } }

class GiraffeFamily {
    static { System.out.print("A"); }
    { System.out.print("B"); }

    public GiraffeFamily(String name) {
        this(1);
        System.out.print("C");
    }

    public GiraffeFamily() {
        System.out.print("D");
    }

    public GiraffeFamily(int stripes) {
        System.out.print("E");
    }
}
class Okapi extends GiraffeFamily {
    static { System.out.print("F"); }

    public Okapi(int stripes) {
        super("sugar");
        System.out.print("G");
    }
    { System.out.print("H"); }

    public static void main(String[] grass) {
        new Okapi(1);
        System.out.println();
        new Okapi(2);
    }
}