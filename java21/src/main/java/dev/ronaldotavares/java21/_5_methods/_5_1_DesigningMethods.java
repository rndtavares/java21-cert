package dev.ronaldotavares.java21._5_methods;

public class _5_1_DesigningMethods {
    public static void main(String[] args) {
        swim(2);
    }

    public static void swim(int distance) {
        if(distance <= 0) {
            // Exit early, nothing to do!
            return;
        }
        System.out.print("Fish is swimming " + distance + " meters");
    }
}

class ParkTrip {  
    public void skip1() {}  
//    default void skip2() {} // DOES NOT COMPILE
//    void public skip3() {}  // DOES NOT COMPILE
    void skip4() {}
}

class Exercise {
    public void bike1() {}   
    public final void bike2() {}   
    public static final void bike3() {}   
    public final static void bike4() {}   
//    public modifier void bike5() {}       // DOES NOT COMPILE
//    public void final bike6() {}          // DOES NOT COMPILE
    final public void bike7() {}
}

class Hike {
    public void hike1() {}
    public void hike2() { return; }
    public String hike3() { return ""; }
//    public String hike4() {} // DOES NOT COMPILE
//    public hike5() {} // DOES NOT COMPILE
//    public String int hike6() { } // DOES NOT COMPILE
//    String hike7(int a) { // DOES NOT COMPILE
//        if (1 < 2) return "orange";
//    }
    String hike8(int a) {
        if (1 < 2) return "orange";
        return "apple"; // COMPILER WARNING
    }
}

class Measurement {
    int getHeight1() {
        int temp = 9;
        return temp;
    }
//    int getHeight2() {
//        int temp = 9L; // DOES NOT COMPILE
//        return temp;
//    }
//    int getHeight3() {
//        long temp = 9L;
//        return temp; // DOES NOT COMPILE
//    }
}

class BeachTrip {
    public void jog1() {}
//    public void 2jog() {} // DOES NOT COMPILE
//    public jog3 void() {} // DOES NOT COMPILE
    public void Jog_$() {}
//    public _() {} // DOES NOT COMPILE
//    public void() {} // DOES NOT COMPILE
}

class Sleep {
    void nap() {}
}

class PhysicalEducation {
    public void run1() {}
//    public void run2 {} // DOES NOT COMPILE
    public void run3(int a) {}
//    public void run4(int a; int b) {} // DOES NOT COMPILE
    public void run5(int a, int b) {}
}

class Trip {
//    public void visitZoo(String name, int waitTime) {}
//    public void visitZoo(String attraction, int rainFall) {}
}

class ZooMonorail {
    public void zeroExceptions() {}

    public void oneException() throws IllegalArgumentException {}

    public void twoExceptions() throws
            IllegalArgumentException, InterruptedException {}
}

class Bird {
    public void fly1() {}
//    public void fly2() // DOES NOT COMPILE
    public void fly3(int a) { int name = 5; }
}