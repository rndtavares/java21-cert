package dev.ronaldotavares.java21._5_methods.pond.shore;

public class BirdWatcher {
    public void watchBird() {
        Bird bird = new Bird();
        bird.floatInWater(); // protected access is ok
        System.out.print(bird.text); // protected access is ok
    }
}
