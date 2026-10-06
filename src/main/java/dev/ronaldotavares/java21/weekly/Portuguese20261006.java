package dev.ronaldotavares.java21.weekly;

/** Code snippets for the Portuguese weekly meeting on October 6, 2026. */
public class Portuguese20261006 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: return type alone cannot distinguish overloaded methods.
    private static void question1() {
        System.out.println("\nquestion1");
        final var test = new Portuguese20261006();
        final int i = test.getLoad();
        final double d = test.getLoad(1);
        System.out.println(i + d);
    }

    private int getLoad() {
        return 1;
    }

    // Remove the int parameter to reproduce the compilation error in the Form.
    private double getLoad(int ignored) {
        return 3.0;
    }

    // Main 2: each array dimension is evaluated from left to right.
    private static void question2() {
        System.out.println("\nquestion2");
        var i = 4;
        final int[][][] array = new int[i][i = 3][i];
        System.out.println(array.length + ", " + array[0].length + ", " + array[0][0].length);
        System.out.println("i=" + i);
        // Change the assignment to i = 2 and predict all three dimensions.
    }

    // Main 3: moving the closing text-block delimiter changes the content.
    private static void question3() {
        System.out.println("\nquestion3");
        final String s1 = "Hello World";
        final String s2 = """
                Hello World""";
        final String s3 = """
                Hello World
                """;
        System.out.println((s1 == s2) + " " + s2.equals(s3) + " " + s2.intern().equals(s3.intern()));
        System.out.println("s2 length=" + s2.length() + ", s3 length=" + s3.length());
    }

    // Main 4: static initialization runs once; instance initialization runs per object.
    private static void question4() {
        System.out.println("\nquestion4");
        final Delimiter first = new Delimiter();
        final Delimiter second = new Delimiter('#');
        System.out.println(first + "" + second);
        // Swap the constructors or change the field initializer to trace the order again.
    }

    private static final class Delimiter {
        private char value = '|';

        static {
            System.out.print('.');
        }

        {
            System.out.print(value);
        }

        private Delimiter() {
            this('-');
        }

        private Delimiter(char ch) {
            value = ch;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Main 5: the array index is evaluated before the right-hand assignment.
    private static void question5() {
        System.out.println("\nquestion5");
        var i = 0;
        final int[] array = {10, 20};
        array[i] = i = 30;
        System.out.println(array[0] + " " + array[1] + "  " + i);
    }

    // Intermediate reserve: the Java launcher selects main(String[]), which can be overloaded.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final String[] args = {"a", "b", "c"};
        final String[][] nestedArgs = {args};
        main(nestedArgs);
    }

    public static void main(String[][] args) {
        System.out.println(args[0][1]);
    }

    // Difficult reserve: neither overload is more specific for test(point, point).
    private static void reserve2() {
        System.out.println("\nreserve2");
        final ColoredPoint point = new ColoredPoint();
        // test(point, point); // Ambiguous: uncomment to reproduce the compilation error.
        test(point, (Point) point);
        test((Point) point, point);
    }

    private static void test(ColoredPoint first, Point second) {
        System.out.println("(ColoredPoint, Point)");
    }

    private static void test(Point first, ColoredPoint second) {
        System.out.println("(Point, ColoredPoint)");
    }

    private static class Point {
        private int x;
        private int y;
    }

    private static final class ColoredPoint extends Point {
        private int color;
    }
}
