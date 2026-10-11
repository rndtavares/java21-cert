package dev.ronaldotavares.java21._5_methods.pond.duck;

public class FatherDuck {
    private String noise = "quack";
    private void quack() {
        System.out.print(noise); // private access is ok
    }
}
