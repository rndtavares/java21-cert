package dev.ronaldotavares.java21._6_class_design;

public class _6_ReviewQuestions {
    public static void main(String[] args) {
        _6();
    }
    private static void _6(){
        Moose moose = new Moose();
    }
}

final class Moose {
//    private final int antlers;
    private int antlers1;

    public Moose() {
        System.out.println(antlers1);
    }
}

class Caribou {
    private int antlers = 10;
}

class Reindeer {
    private final int antlers = 5;
}

final class Elk {}

final class Deer {
    private final Object o = new Object();
}