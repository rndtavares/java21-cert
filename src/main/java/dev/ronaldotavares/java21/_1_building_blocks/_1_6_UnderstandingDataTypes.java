package dev.ronaldotavares.java21._1_building_blocks;

public class _1_6_UnderstandingDataTypes {

    public static void main(String[] args) {
        testLiterals();
        testUnderscore();
        testBoolean();
        testInteger();
        testDouble();
        prepare();
        testTextBlock();
    }

    public static void testLiterals() {
        //        long max = 3123456789;  // DOES NOT COMPILE
        long max = 3123456789L;  // Now Java knows it is a long

        int testBinary = 0b10;
        int testOctal = 017;
        int testHex = 0xFF;
        System.out.println("**testLiterals**");
        System.out.println(testBinary);
        System.out.println(testOctal);
        System.out.println(testHex);
    }

    public static void testUnderscore() {
        int million1 = 1000000;
        int million2 = 1_000_000;
//        double notAtStart = _1000.00;          // DOES NOT COMPILE
//        double notAtEnd = 1000.00_;            // DOES NOT COMPILE
//        double notByDecimal = 1000_.00;        // DOES NOT COMPILE
        double annoyingButLegal = 1_00_0.0_0;  // Ugly, but compiles
        double reallyUgly = 1_2;   // Also compiles
        System.out.println("**testUnderscore**");
        System.out.println(reallyUgly);
    }

    private void reference(){
        String greeting;
        greeting = new String("How are you?");

        String reference = "hello";
        int len = reference.length();
//        int bad = len.length(); // DOES NOT COMPILE

//        int value = null;   // DOES NOT COMPILE
        String name = null;
    }

    private void wrapper(){
        int primitive = Integer.parseInt("123");
        Integer wrapper = Integer.valueOf("123");
//        System.out.println(Integer.valueOf("five")); // NumberFormatException
    }

    public static void testBoolean() {
        System.out.println("**testBoolean**");
        System.out.println(Boolean.valueOf("true"));      // true
        System.out.println(Boolean.valueOf("TrUe"));      // true
        System.out.println(Boolean.valueOf("false"));     // false
        System.out.println(Boolean.valueOf("FALSE"));     // false
        System.out.println(Boolean.valueOf("kangaroo"));  // false
        System.out.println(Boolean.valueOf(null));
    }

    public static void testInteger() {
        System.out.println("**testInteger**");
        System.out.println(Integer.valueOf("5", 16)); // 5
        System.out.println(Integer.valueOf("a", 16)); // 10
        System.out.println(Integer.valueOf("F", 16)); // 15
//        System.out.println(Integer.valueOf("G", 16)); // NumberFormatException
    }

    public static void testDouble() {
        System.out.println("**testDouble**");
        Double apple = Double.valueOf("200.99");
        System.out.println(apple.byteValue());    // -56
        System.out.println(apple.intValue());     // 200
        System.out.println(apple.doubleValue());  // 200.99
    }

    public static String label(String title, String author) {
        return """
           Book:
           """ + title + " by " + author;
    }
    public static void prepare() {
        System.out.println("**prepare**");
        String labelled = label("""
      Java Study Guide
      For Java 21
      2024 Edition""", "Jeanne & Scott");
        System.out.println(labelled);
    }

    public static void testTextBlock() {
        System.out.println("**testTextBlock**");
        String pyramid = """
          *
         * *
        * * * 
        """;
//        String invalidTextBlock = """text block
//                """;
        System.out.print(pyramid);
        String block = """
                       doe \
                       deer""";
        System.out.println(block);
        String anotherBlock = """
                               doe \n
                               deer
                               """;
        System.out.println(anotherBlock);
        String crazyBlock = """
                               "doe\"\"\"
                               \"deer\"""
                              """;
        System.out.println("*"+ crazyBlock  + "*");
    }
}
