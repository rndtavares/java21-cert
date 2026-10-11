package dev.ronaldotavares.java21._5_methods.pond.shore;

public class Bird {
    protected String text = "floating";
    protected void floatInWater() {
        System.out.print(text); // protected access is ok
    }
}
