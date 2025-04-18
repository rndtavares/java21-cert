package dev.ronaldotavares.java21._5_methods.pond.swan;
import dev.ronaldotavares.java21._5_methods.pond.duck.MotherDuck;

public class BadCygnet {
    public void makeNoise() {
        var duck = new MotherDuck();
//        duck.quack(); // DOES NOT COMPILE
//        System.out.print(duck.noise); // DOES NOT COMPILE
    }

}
