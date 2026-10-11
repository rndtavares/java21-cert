package dev.ronaldotavares.java25;

/** Examples of flexible constructor bodies. */
public class FlexibleConstructorBodies {

    static void main(String[] args) {
        question1();
        question2();
    }

    // Validation can run before this(...) as long as it does not access this.
    private static void question1() {
        System.out.println("\nquestion1");
        var greeting = new Greeting(null, "world");
        System.out.println(greeting.message());
    }

    // A constructor may still initialize a final field directly.
    private static void question2() {
        System.out.println("\nquestion2");
        var binaryValue = new BinaryValueHolder("10");
        System.out.println(binaryValue.value());
    }

    private static final class Greeting {
        private final String message;

        private Greeting(String message) {
            this.message = message;
        }

        private Greeting(String firstPart, String secondPart) {
            validate(secondPart);
            this((firstPart == null ? "hello" : firstPart) + " " + secondPart);
        }

        private static void validate(String text) {
            if (text == null || text.isBlank()) {
                throw new IllegalArgumentException("A greeting needs text");
            }
        }

        private String message() {
            return message;
        }
    }

    private static final class BinaryValueHolder {
        private final int value;

        private BinaryValueHolder(String binaryText) {
            value = Integer.parseInt(binaryText, 2);
        }

        private int value() {
            return value;
        }
    }
}
