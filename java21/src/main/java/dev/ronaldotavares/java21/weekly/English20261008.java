package dev.ronaldotavares.java21.weekly;

/** Code snippets for the English weekly meeting on October 8, 2026. */
public class English20261008 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: array elements receive default values, unlike local variables.
    private static void question1() {
        System.out.println("\nquestion1");
        var text = "111";
        final boolean[] values = new boolean[1];
        if (values[0]) {
            text = "222";
        }
        System.out.println(text);
        System.out.println("values[0]=" + values[0]);
        // Set values[0] = true before the if statement and predict the output.
    }

    // Main 2: compare | with || when the right operand dereferences null.
    private static void question2() {
        System.out.println("\nquestion2");
        final String text = null; // Try an empty String after the first run.
        final int i = 0;

        try {
            System.out.println((text != null) | (i == text.length())); // A
        } catch (NullPointerException exception) {
            System.out.println("A: NullPointerException");
        }
        try {
            System.out.println((text == null) | (i == text.length())); // B
        } catch (NullPointerException exception) {
            System.out.println("B: NullPointerException");
        }
        try {
            System.out.println((text != null) || (i == text.length())); // C
        } catch (NullPointerException exception) {
            System.out.println("C: NullPointerException");
        }
        System.out.println("D: " + ((text == null) || (i == text.length())));
        // Also try text != null && i == text.length(): it safely skips the right side.
    }

    // Main 3: B uses arrow expressions; E uses arrow blocks with yield.
    private static void question3() {
        System.out.println("\nquestion3");
        System.out.println(colorWithDefault(Card.CLUB));
        System.out.println(colorWithYield(Card.DIAMOND));
        // Remove the default from colorWithDefault, or replace yield with return.
    }

    private enum Card {
        CLUB, DIAMOND, HEART, SPADE
    }

    private static String colorWithDefault(Card card) {
        return switch (card) {
            case CLUB, SPADE -> "BLACK";
            case DIAMOND, HEART -> "RED";
            default -> "YELLOW";
        };
    }

    private static String colorWithYield(Card card) {
        return switch (card) {
            case CLUB, SPADE -> {
                yield "BLACK";
            }
            case DIAMOND, HEART -> {
                yield "RED";
            }
        };
    }

    // Main 4: break middle starts the next outer iteration, not the next j.
    private static void question4() {
        System.out.println("\nquestion4");
        var counter = 0;
        outer:
        for (var i = 0; i < 3; i++) {
            middle:
            for (var j = 0; j < 3; j++) {
                inner:
                for (var k = 0; k < 3; k++) {
                    if (k - j > 0) {
                        break middle; // Try break inner or break outer.
                    }
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    // Main 5: changing only a return type does not create a valid overload.
    private static void question5() {
        System.out.println("\nquestion5");
        final var test = new English20261008();
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

    // Intermediate reserve: a switch pattern must be exhaustive.
    private static void reserve1() {
        System.out.println("\nreserve1");
        System.out.println(testPointWithDefault(new Point(4, 2)));
        System.out.println(testPointWithRecordPattern(new Point(2, 4)));
        // Neither valid form has case null; passing null throws NullPointerException.
    }

    private record Point(int x, int y) { }

    private static boolean testPointWithDefault(Point point) {
        return switch (point) {
            case Point(var x, var y) when x > y -> true;
            default -> false;
        };
    }

    private static boolean testPointWithRecordPattern(Point point) {
        return switch (point) {
            case Point first when first.x() > first.y() -> true;
            case Point(var x, var y) -> false;
        };
    }

    // Difficult reserve: the sealed hierarchy makes both switches exhaustive.
    private static void reserve2() {
        System.out.println("\nreserve2");
        final Building hotel = new Hotel(10);
        final Building hospital = new Hospital(20);
        System.out.println(printDetails(hotel));
        System.out.println(printDetailsWithYield(hospital));
        System.out.println(printDetails(null));
    }

    private sealed interface Building permits Hospital, Hotel { }

    private record Hospital(int beds) implements Building { }

    private record Hotel(int rooms) implements Building { }

    private static String printDetails(Building building) {
        return switch (building) {
            case Hotel(var rooms) -> "Hotel has " + rooms + " rooms";
            case Hospital(var beds) -> "Hospital has " + beds + " beds";
            case null -> "Building not recognized.";
        };
    }

    private static String printDetailsWithYield(Building building) {
        return switch (building) {
            case Hotel(var rooms): yield "Hotel has " + rooms + " rooms";
            case Hospital(var beds): yield "Hospital has " + beds + " beds";
            case null: yield "Building not recognized.";
        };
    }
}
