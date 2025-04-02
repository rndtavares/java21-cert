package dev.ronaldotavares.java21._2_operators;

public class _2_3_WorkingWithBinaryArithmeticOperators {
    public static void main(String[] args) {
        arithmeticOperators();
        addingParentesis();
        verifyingParenthesesSyntax();
        divisionAndModulusOperators();
        numericPromotionRules();
    }

    private static void arithmeticOperators() {
        System.out.println("arithmeticOperators");
        int price = 2 * 5 + 3 * 4 -8;
        System.out.println(price);
        int priceEquivalent = 10 + 12 - 8;
        System.out.println(priceEquivalent);
    }

    private static void addingParentesis() {
        System.out.println("addingParentesis");
        int price = 2 * ((5 + 3) * 4 - 8);
        System.out.println(price);
        int priceStep1 = 2 * (8 * 4 - 8);
        System.out.println(priceStep1);
        int priceStep2 = 2 * (32 - 8);
        System.out.println(priceStep2);
        int priceStep3 = 2 * 24;
        System.out.println(priceStep3);
    }

    private static void verifyingParenthesesSyntax() {
//        long pigeon = 1 + ((3 * 5) / 3; // DOES NOT COMPILE
//         int blueJay = (9 + 2) + 3) / (2 * 4; // DOES NOT COMPILE
//        short robin = 3 + [(4 * 2) + 4]; // DOES NOT COMPILE
    }

    private static void divisionAndModulusOperators() {
        System.out.println("divisionAndModulusOperators");
        System.out.println(9 / 3); // 3
        System.out.println(9 % 3); // 0
        System.out.println(10 / 3); // 3
        System.out.println(10 % 3); // 1
        System.out.println(11 / 3); // 3
        System.out.println(11 % 3); // 2
        System.out.println(12 / 3); // 4
        System.out.println(12 % 3); // 0

        System.out.println(2 % 5); // 2
        System.out.println(7 % 5); // 2
        System.out.println(2 % -5); // 2
        System.out.println(7 % -5); // 2
        System.out.println(-2 % 5); // -2
        System.out.println(-7 % 5); // -2
        System.out.println(-2 % -5); // -2
        System.out.println(-7 % -5); // -2
    }

    private static void numericPromotionRules() {
        System.out.println("numericPromotionRules");
        int x = 1;
        long y = 33;
        var z = x * y;
        System.out.println(z);

        double x1 = 39.21;
        float y1 = 2.1f;
        var z1 = x1 + y1;
        System.out.println(z1);

        short x2 = 10;
        short y2 = 3;
        var z2 = x2 * y2;
        System.out.println(z2);

        short w3 = 14;
        float x3 = 13;
        double y3 = 30;
        var z3 = w3 * x3 / y3;
        System.out.println(z3);
    }
}
