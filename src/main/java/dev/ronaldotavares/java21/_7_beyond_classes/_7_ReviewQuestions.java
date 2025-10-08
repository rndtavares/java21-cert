package dev.ronaldotavares.java21._7_beyond_classes;

public class _7_ReviewQuestions {
    public static void main(String[] args) {
        _13();
        _15();
    }

    private static void _15(){
        System.out.println("question 15:");
        Ghost.main(null);
    }
    private static void _13(){
        System.out.println("question 13:");
        try{
            Weather.main(null);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

class Ghost {
    public static void boo() {
        System.out.println("Not scared");
    }
    protected final class Spirit {
        public void boo() {
            System.out.println("Booo!!!");
        }
    }
    public static void main(String... haunt) {
        var g = new Ghost().new Spirit();
        new Ghost().boo();
        StringBuilder sb = new StringBuilder();
    }
}

class Weather {
    enum Seasons {
        WINTER, SPRING, SUMMER, FALL
    }

    public static void main(String[] args) {
        Seasons v = null;
        int i = 1;
        switch (v) {
            case Seasons.SPRING -> System.out.print("s");
            case Seasons.WINTER -> System.out.print("w");
//            case null -> System.out.println("null");
            case Seasons.SUMMER -> System.out.print("m");
            default -> System.out.println("missing data");
        }
    }  }