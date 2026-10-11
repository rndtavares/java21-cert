package dev.ronaldotavares.java21._7_beyond_classes;

import dev.ronaldotavares.java21._7_beyond_classes.animal.Frog;

public class _7_ReviewQuestions {
    public static void main(String[] args) {
        _13();
        _15();

        var _7_ReviewQuestions = new _7_ReviewQuestions();
        _7_ReviewQuestions._assessment_11();

        var practiceExam2 = new PracticeExam2();
        practiceExam2._3();
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

    private void _assessment_11() {
        System.out.println("question 11 from assessment test:");
        Tadpole.main(null);
    }

}
    // Tadpole.java
class Tadpole extends Frog {
    public static void main(String[] args) {
        Tadpole t = new Tadpole();
        t.ribbit();
//        t.jump();
        Frog f = new Tadpole();
//        f.ribbit();
//        f.jump();
        System.out.println("Done");
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

class PracticeExam2 {
    public void _3(){
        System.out.println("Practice Exam 2 - Question 3");

        class ColorLocal {
            private int hue = 10;
            private enum Range { HIGH, LOW; }
            public class Shade {
                public int hue = ColorLocal.this.hue;
            }
//            public static void main(String... lighting) {
            public void main(String... lighting) {
                var colorLocal = new ColorLocal(); // static members are not allowed in local classes
                System.out.println("Local class: " + colorLocal.new Shade().hue);
            }
        }

        new ColorLocal().main();
        ColorNested.main();
    }

    class ColorNested {
        private int hue = 10;
        private enum Range { HIGH, LOW; }
        public class Shade {
            public int hue = ColorNested.this.hue;
        }
        public static void main(String... lighting) {
//            System.out.println(new Shade().hue);
            var practiceExam2 = new PracticeExam2();
            var color = practiceExam2.new ColorNested();
            System.out.println("Nested class: " + color.new Shade().hue);
        }
    }
}