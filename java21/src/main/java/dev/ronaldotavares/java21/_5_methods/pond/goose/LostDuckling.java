package dev.ronaldotavares.java21._5_methods.pond.goose;

import dev.ronaldotavares.java21._5_methods.pond.duck.DuckTeacher;

public class LostDuckling {
    public void swim() {
        var teacher = new DuckTeacher();
        teacher.swim(); // allowed
        System.out.print("Thanks " + teacher.name); // allowed
    }
}
