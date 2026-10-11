package dev.ronaldotavares.java21.weekly;

/** Code snippets for the English weekly meeting on October 1, 2026. */
public class English20261001 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: compare the output with each proposed statement in the form.
    private static void question1() {
        System.out.println("\nquestion1");
        System.out.println(1 + 2 + "3");
        System.out.println("1" + 2 + 3);
        System.out.println(4 + 1.0f);
        System.out.println(5 / 4);
        System.out.println('a' + 1);
    }

    // Main 2: assignments on the right are skipped after a becomes true.
    private static void question2() {
        System.out.println("\nquestion2");
        boolean a = false;
        boolean b = false;
        boolean c = false;
        final boolean result = (a = true) || (b = true) && (c = true);
        System.out.println(a + ", " + b + ", " + c);
        System.out.println("result=" + result);
    }

    // Main 3: replace returns a new String, leaving the original unchanged.
    private static void question3() {
        System.out.println("\nquestion3");
        final String original = "1234";
        final String replaced = original.replace('1', '9');
        System.out.println("original=" + original);
        System.out.println("replaced=" + replaced);
    }

    // Main 4: valid forms corresponding to options A, C, D, and F.
    private static void question4() {
        System.out.println("\nquestion4");
        final int[][] a = {{1, 2}, {1}, {}, {1, 2, 3}};
        final int[][] c = new int[][]{{1, 2, 3}, {4, 5, 6}};
        final int[][] d = {{1, 2}, new int[2]};
        final var f = new int[][]{{1, 2, 3}, {4, 5, 6}};
        // int[] b = new int[2]{1, 2}; // B does not compile.
        // int e[4] = {1, 2, 3, 4}; // E does not compile.
        System.out.println(a.length + " " + c.length + " " + d.length + " " + f.length);
    }

    // Main 5: options A and D are valid switch expressions.
    private static void question5() {
        System.out.println("\nquestion5");
        final int x = getValue();
        final String a = switch (x) {
            case 0 -> "Zero";
            case 1 -> "One";
            default -> "Invalid";
        };
        final String d = switch (x) {
            case 0 -> "Zero";
            case 1 -> "One";
            default -> {
                yield "Invalid";
            }
        };
        System.out.println(a + " / " + d);
    }

    private static int getValue() {
        return 1;
    }

    // Intermediate reserve: reference equality versus String content equality.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final String myStr = "good";
        final char[] myCharArr = {'g', 'o', 'o', 'd'};
        String newStr = "";
        for (final char ch : myCharArr) {
            newStr = newStr + ch;
        }
        System.out.println((newStr == myStr) + " " + newStr.equals(myStr));
    }

    // Difficult reserve: trace the labeled break across three nested loops.
    private static void reserve2() {
        System.out.println("\nreserve2");
        var counter = 0;
        outer:
        for (var i = 0; i < 3; i++) {
            middle:
            for (var j = 0; j < 3; j++) {
                inner:
                for (var k = 0; k < 3; k++) {
                    if (k - j > 0) {
                        break middle;
                    }
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
