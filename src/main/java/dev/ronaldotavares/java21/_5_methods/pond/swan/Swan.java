package dev.ronaldotavares.java21._5_methods.pond.swan;

import dev.ronaldotavares.java21._5_methods.pond.shore.Bird;

public class Swan extends Bird { // Swan is a subclass of Bird
    public void swim() {
        floatInWater(); // protected access is ok
        System.out.print(text); // protected access is ok
    }
    public void helpOtherSwanSwim() {
        Swan other = new Swan();
        other.floatInWater(); // subclass access to superclass
        System.out.print(other.text); // subclass access to superclass
    }
    public void helpOtherBirdSwim() {
        Bird other = new Bird();
//        other.floatInWater(); // DOES NOT COMPILE
//        System.out.print(other.text); // DOES NOT COMPILE
    }
}
