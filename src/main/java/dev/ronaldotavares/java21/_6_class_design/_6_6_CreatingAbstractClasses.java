package dev.ronaldotavares.java21._6_class_design;

public class _6_6_CreatingAbstractClasses {
    public static void main(String[] args) {
        Fox w = new Fox();
        w.bark();
    }
}

abstract class Canine {
    public abstract String getSound();
    public void bark() {
        System.out.println(getSound());
    }
}

class Wolf extends Canine {
    public String getSound() {
        return "Wooooooof!";
    }
}
class Fox extends Canine {
    public String getSound() {
        return "Squeak!";
    }
}
class Coyote extends Canine {
    public String getSound() {
        return "Roar!";
    }
}

//class FennecFox extends Canine {
//    public int getSound() {
//        return 10;
//    }
//}

//class ArcticFox extends Canine {}

//class Direwolf extends Canine {
//    public abstract rest();
//    public String getSound() {
//        return "Roof!";
//    }
//}

//class Jackal extends Canine {
//    public abstract String name;
//    public String getSound() {
//        return "Laugh";
//    }
//}

abstract class Alligator {
    public static void main(String... food) {
//        var a = new Alligator(); // DOES NOT COMPILE
    }
}

abstract class Llama {
    public void chew() {}
}

class Egret { // DOES NOT COMPILE
//    public abstract void peck();
}

abstract class Tiger {
    abstract public int claw();
}

//class abstract Bear { // DOES NOT COMPILE
//    public int abstract howl(); // DOES NOT COMPILE
//}

abstract class Animal66 {
    public abstract String getName();
}
//public class Walrus extends Animal10 {} // DOES NOT COMPILE

abstract class Mammal66 {
    abstract void showHorn();
    abstract void eatLeaf();
}

abstract class Rhino66 extends Mammal66 {
    void showHorn() {} // Inherited from Mammal
}

class BlackRhino extends Rhino66 {
    void eatLeaf() {} // Inherited from Mammal
}

//class Rhino66 extends Mammal66 { // DOES NOT COMPILE
//    void showHorn() {}
//}

abstract class BigCat66 extends Animal66 {
    protected abstract void roar();
}

class Lion66 extends BigCat66 {
    public String getName() {
        return "Lion"
                ;
    }
    public void roar() {
        System.out.println("The Lion lets out a loud ROAR!");
    }
}

abstract class Mammal662 {
    abstract CharSequence chew();
    public Mammal662() {
        System.out.println(chew()); // Does this line compile?
    }
}

class Platypus extends Mammal662 {
    String chew() { return "yummy!"; }
    public static void main(String[] args) {
        new Platypus();
    }
}

abstract class Turtle66 {
//    public abstract long eat() // DOES NOT COMPILE
//    public abstract void swim() {}; // DOES NOT COMPILE
//    public abstract int getAge() { // DOES NOT COMPILE
//        return 10;
//    }
//    public abstract void sleep; // DOES NOT COMPILE
//    public void goInShell(); // DOES NOT COMPILE
}

//abstract final class Tortoise { // DOES NOT COMPILE
//    public abstract final void walk(); // DOES NOT COMPILE
//}

abstract class Whale {
//    private abstract void sing(); // DOES NOT COMPILE
}
class HumpbackWhale extends Whale {
    private void sing() {
        System.out.println("Humpback whale is singing");
    }
}

abstract class Whale1 {
    protected abstract void sing();
}

//class HumpbackWhale1 extends Whale1 {
//    private void sing() { // DOES NOT COMPILE
//        System.out.println("Humpback whale is singing");
//    }
//}

abstract class Hippopotamus {
//    abstract static void swim(); // DOES NOT COMPILE
}