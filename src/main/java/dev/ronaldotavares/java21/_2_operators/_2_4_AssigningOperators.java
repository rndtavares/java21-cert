package dev.ronaldotavares.java21._2_operators;

public class _2_4_AssigningOperators {

    public static void main(String[] args) {
        primitiveAssignments();
    }

    private static void primitiveAssignments(){
        byte testByte = 1;
        short testShort = 1;
        int testInt = 1;
        long testLong = 1;
        float testFloat = 1;
//        float testFloat2 = 1.0;
        float testFloat3 = 1.0f;
        double testDouble = 1.0;
        System.out.println("testByte " + testByte);
        System.out.println("testInt " + testInt);
        System.out.println("testShort " + testShort);
        System.out.println("testLong " + testLong);
    }

    private static void castingValuesAndVariables(){
        byte hat = 1;
        byte gloves = 7 * 10;
        short scarf = 5;
        short boots = 2 + 1;
//        short boots = 2 + hat; // DOES NOT COMPILE
//        byte gloves = 7 * 100; // DOES NOT COMPILE
//        byte gloves1 = 7 * 100;
//        byte gloves2 = 700;
        byte gloves3 = (byte)700;

    }

    private static void primitiveOverflow(){
        int intOverflowOk = 2147483647+1;
//        int intOverflowNOk = 2147483648;
//        byte byteOverflowNotOk = 127+1;
        byte byteOverflowOk = (byte)(127+1);
    }
}
