package dev.ronaldotavares.java21._6_class_design;

public class _6_ReviewQuestions {
    public static void main(String[] args) {
        _6();
        _8();
        _12();
    }
    private static void _6(){
        System.out.println("question 6");
        Moose moose = new Moose();
    }
    private static void _8(){
        System.out.println("question 8");
        Pelican.main(null);
    }
    private static void _12(){
        System.out.println("question 12");
        Beaver.main(null);
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

abstract class Bird1 {
    private final void fly() { System.out.println("Bird"); }
    protected Bird1() { System.out.print("Wow-"); }
}
class Pelican extends Bird1 {
    public Pelican() { System.out.print("Oh-"); }
    protected void fly() { System.out.println("Pelican"); }
    public static void main(String[] args) {
        var chirp = new Pelican();
        chirp.fly();

//        Bird1 chirp1 = new Pelican();
//        chirp1.fly();
    }
}

class Rodent {
    public Rodent(Integer x) {}

//    static Integer chew() throws Exception {
//    private static Integer chew() throws Exception {
    protected static Integer chew() throws Exception {
        System.out.println("Rodent is chewing");
        return 1;
    }
}
class Beaver extends Rodent {
    //missing constructor
    public Beaver(Integer x) {
        super(x);
    }
    public static Integer chew() throws RuntimeException {
//    public Integer chew() throws RuntimeException {
//    public Number chew() throws RuntimeException {
        System.out.println("Beaver is chewing on wood");
        return 2;
    }
    public static void main(String[] args) {
        var beaver = new Beaver(1);
        try {
            Beaver.chew();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}