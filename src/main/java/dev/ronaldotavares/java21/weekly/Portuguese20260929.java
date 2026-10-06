package dev.ronaldotavares.java21.weekly;

/** Code snippets for the Portuguese weekly meeting on September 29, 2026. */
public class Portuguese20260929 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: arrow switch statement. Try changing x and compare with colon cases.
    private static void question1() {
        System.out.println("\nquestion1");
        final int x = 2;
        switch (x) {
            case 1, 2 -> {
                System.out.println("A");
                break; // Legal in a block, although unnecessary here.
            }
            case 3 -> System.out.println("B");
            default -> System.out.println("C");
        }
    }

    // Main 2: same characters, different String objects.
    private static void question2() {
        System.out.println("\nquestion2");
        final String myStr = "good";
        final char[] myCharArr = {'g', 'o', 'o', 'd'};
        String newStr = "";
        for (final char ch : myCharArr) {
            newStr = newStr + ch;
        }
        final boolean b1 = newStr == myStr;
        final boolean b2 = newStr.equals(myStr);
        System.out.println(b1 + " " + b2);
    }

    // Main 3: alternatives 4 and 5 compile. Uncomment the others individually.
    private static void question3() {
        System.out.println("\nquestion3");
        // for (var i = 5; i = 0; i--) { } // 1: condition is not boolean.
        // var j = 5;
        // for (int i = 0, j += 5; i < j; i++) { j--; } // 2: invalid initializer.
        // int i, j;
        // for (j = 10; i < j; j--) { i += 2; } // 3: i is not initialized.

        var i = 10;
        for (; i > 0; i--) { } // 4

        for (int left = 0, right = 10; left < right; left++, --right) { } // 5
        // for (var left = 0, right = 10; left < right; left++, --right) { } // 6
    }

    // Main 4: valid array forms corresponding to options A, C, D, and F.
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

    // Main 5: the backslash suppresses the line break inside the text block.
    private static void question5() {
        System.out.println("\nquestion5");
        final String str = """
                0123\
                4567""";
        System.out.println("length=" + str.length());
        System.out.println(str.substring(4, 7));
    }

    // Intermediate reserve: overload resolution uses the argument's type.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final int a = 'a';
        final char c = 6;
        overloaded(a);
        overloaded(c);
    }

    private static void overloaded(int value) {
        System.out.println("In int");
    }

    private static void overloaded(char value) {
        System.out.println("In char");
    }

    // Difficult reserve: 'c' | 'd' is one constant expression, not two cases.
    private static void reserve2() {
        System.out.println("\nreserve2");
        final var ca = new char[]{'a', 'b', 'c', 'd'};
        var i = 0;
        for (final var c : ca) {
            switch (c) {
                case 'a': i++;
                case 'b': ++i;
                case 'c' | 'd': i++;
            }
        }
        System.out.println("i = " + i);
        System.out.println("'c' | 'd' = " + (char) ('c' | 'd'));
    }
}
