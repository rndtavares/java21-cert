package dev.ronaldotavares.java21._7_beyond_classes;

public class _7_ReviewQuestions {
    public static void main(String[] args) {
        _15();
    }

    private static void _15(){
        Ghost.main(null);
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