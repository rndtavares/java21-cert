package dev.ronaldotavares.java25;

/** Examples of flexible constructor bodies. */
public class FlexibleConstructorBodies {

    static void main(String[] args) {
        examples();
        question1();
        question2();
    }

    private static void examples() {
        System.out.println("\nexamples");
        ValidatedTicket validatedTicket = new ValidatedTicket("  JAVA-25  ");
        System.out.println(validatedTicket.code());
        InitializedBeforeSuper initializedBeforeSuper = new InitializedBeforeSuper("ready");
        System.out.println(initializedBeforeSuper.status());
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

    private static class Parent {
        private final String status;

        private Parent() {
            status = describe();
        }

        String describe() {
            return "parent";
        }
    }

    private static final class InitializedBeforeSuper extends Parent {
        private final String status;

        private InitializedBeforeSuper(String initialStatus) {
            status = initialStatus;
            super();
        }

        @Override
        String describe() {
            return status;
        }

        private String status() {
            return status;
        }
    }

    private static final class ValidatedTicket {
        private final String code;

        private ValidatedTicket(String rawCode) {
            var normalizedCode = rawCode.trim();
            if (normalizedCode.isEmpty()) {
                throw new IllegalArgumentException("A ticket needs a code");
            }
            this.code = normalizedCode;
        }

        private String code() {
            return code;
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
