package dev.ronaldotavares.java21._2_operators;

public class _2_4_AssigningOperators {

    public static void main(String[] args) {
        assignmentOperator();
        castingOperator();
        primitiveAssignments();
        appliyingCast();
        overflowUnderflow();
        castingValuesAndVariables();
        compoundAssignmentOperators();
        returnValueOfAssignmentOperator();
    }

    private static void assignmentOperator() {
        int herd =1;
    }

    private static void castingOperator() {
        int fur = (int)5;
        int hair = (short)2;
        String type = (String)"Bird";
        short tail = (short)(4 + 10);
//        long feathers = 10(long); // DOES NOT COMPILE

//        float egg = 2.0 / 9; // DOES NOT COMPILE
//        int tadpole = (int)5 * 2L; // DOES NOT COMPILE
//        short frog = 3 - 2.0;  // DOES NOT COMPILE
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
        System.out.println("testFloat " + testFloat);
        System.out.println("testDouble " + testDouble);

//        int fish = 1.0; // DOES NOT COMPILE
//        short bird = 1921222; // DOES NOT COMPILE
//        int mammal = 9f;  // DOES NOT COMPILE
//        long reptile = 192_301_398_193_810_323; // DOES NOT COMPILE
    }

    private static void appliyingCast() {
        int fish = (int)1.0;
        short bird = (short)1921222; // Stored as 20678
        int mammal = (int)9f;

        short mouse = 10;
        short hamster = 3;

        short capybara = (short)(mouse * hamster);

//        short capybara1 = (short)mouse * hamster;// DOES NOT COMPILE
//        short capybara2 = 1 + (short)(mouse * hamster); // DOES NOT COMPILE
    }

    private static void overflowUnderflow() {
//        long reptile = (long)192301398193810323; // DOES NOT COMPILE
        long reptile = 192301398193810323L;
        System.out.print(2147483647+1); // -2147483648

        int intOverflowOk = 2147483647+1;
//        int intOverflowNOk = 2147483648;
//        byte byteOverflowNotOk = 127+1;
        byte byteOverflowOk = (byte)(127+1);
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

    private static void compoundAssignmentOperators() {
        int camel = 2, giraffe = 3;
        camel = camel * giraffe; // Simple assignment operator
        camel *= giraffe; // Compound assignment operator

        long goat = 10;
        int sheep = 5;
//        sheep = sheep * goat; // DOES NOT COMPILE
        sheep *= goat;
    }


    private static void returnValueOfAssignmentOperator() {
        long wolf = 5;
        long coyote = (wolf = 3);
        System.out.println(wolf); // 3
        System.out.println(coyote); // 3

        boolean healthy = false;
        if(healthy = true)
            System.out.print("Good!");
    }
}
