package dev.ronaldotavares.java21._6_class_design;

import java.io.FileNotFoundException;
import java.io.IOException;

public class _6_5_InheritingMembers {
}

class Marsupial {
    public double getAverageWeight() {
        return 50;
    }
}
class Kangaroo extends Marsupial {
    public double getAverageWeight() {
        return super.getAverageWeight()+20;
//        return getAverageWeight()+20; // StackOverflowError
    }
    public static void main(String[] args) {
        System.out.println(new Marsupial().getAverageWeight());  // 50.0
        System.out.println(new Kangaroo().getAverageWeight());   // 70.0
    }
}

class Camel {
    int getNumberOfHumps() {
        return 1;
    }
}
class BactrianCamel extends Camel {
    protected int getNumberOfHumps() {  // DOES NOT COMPILE
        return 2;
    }
}

class Rider {
    public static void main(String[] args) {
        Camel c = new BactrianCamel();
        System.out.print(c.getNumberOfHumps()); // ???
    } }

class Reptile1 {
    protected void sleep() throws IOException {}
    protected void hide() {}
    protected void exitShell() throws FileNotFoundException {}
}
//public class GalapagosTortoise extends Reptile1 {
//    public void sleep() throws FileNotFoundException {}
//    public void hide() throws FileNotFoundException {} // DOES NOT COMPILE
//    public void exitShell() throws IOException {}      // DOES NOT COMPILE
//}


class Rhino {
    protected CharSequence getName() {
        return "rhino";
    }
    protected String getColor() {
        return "grey, black, or white";
    }
}
class JavanRhino extends Rhino {
    public String getName() {
        return "javan rhino";
    }
//    public CharSequence getColor() {  // DOES NOT COMPILE
//        return "grey";
//    }
}

class Fish {
    public void swim() {}
}
class Shark extends Fish {
//    @Override
//    public void swim(int speed) {}  // DOES NOT COMPILE
}

class Beetle1 {
    private String getSize() {
        return "Undefined";
    }
}
class RhinocerosBeetle extends Beetle1 {
    private int getSize() {
        return 5;
    }

    public static void main(String[] args) {
        var rhinocerosBeetle = new RhinocerosBeetle();
        System.out.println(rhinocerosBeetle.getSize()); // 5
    }
}

class Bear {
    public static void eat() {
        System.out.println("Bear is eating");
    }
    public static void sneeze() {
        System.out.println("Bear is sneezing");
    }
    public void hibernate() {
        System.out.println("Bear is hibernating");
    }
    public static void laugh() {
        System.out.println("Bear is laughing");
    }
}
class Panda extends Bear {
    public static void eat() {
        System.out.println("Panda is chewing");
    }
    public static void main(String[] args) {
        eat();
        sneeze();
        new Panda().hibernate();
        laugh();
    }
}

class SunBear extends Bear {
//    public void sneeze() {           // DOES NOT COMPILE
//        System.out.println("Sun Bear sneezes quietly");
//    }
//    public static void hibernate() { // DOES NOT COMPILE
//        System.out.println("Sun Bear is going to sleep");
//    }
//    protected static void laugh() {  // DOES NOT COMPILE
//        System.out.println("Sun Bear is laughing");
//    }
}

class Carnivore {
    protected boolean hasFur = false;
}
class Meerkat extends Carnivore {
    protected boolean hasFur = true;

    public static void main(String[] args) {
        Meerkat m = new Meerkat();
        Carnivore c = m;
        System.out.println(m.hasFur);  // true
        System.out.println(c.hasFur);  // false
    }
}

class Bird {
    public final boolean hasFeathers() {
        return true;
    }
    public final static void flyAway() {}
}
class Penguin extends Bird {
//    public final boolean hasFeathers() {  // DOES NOT COMPILE
//        return false;
//    }
//    public final static void flyAway() {} // DOES NOT COMPILE
}