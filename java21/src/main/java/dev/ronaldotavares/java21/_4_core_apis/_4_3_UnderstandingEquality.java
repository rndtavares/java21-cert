package dev.ronaldotavares.java21._4_core_apis;

public class _4_3_UnderstandingEquality {
    public static void main(String[] args) {
        comparingEqualsAndEqualsSignal();
        stringPool();
        intern();
    }

    private static void comparingEqualsAndEqualsSignal() {
        var one = new StringBuilder();
        var two = new StringBuilder();
        var three = one.append("a");
        System.out.println(one == two);    // false 
        System.out.println(one == three); // true

        var x = "Hello World";
        var z = " Hello World".trim();
        System.out.println(x.equals(z)); // true

        var name = "a";
        var builder = new StringBuilder("a");   
//        System.out.println(name == builder);          // DOES NOT COMPILE
    }

    private static void stringPool(){
        var x = "Hello World";
        var y = "Hello World";
        System.out.println(x == y);      // true

        var x1 = "Hello World";
        var z1 = " Hello World".trim();
        System.out.println(x1 == z1); // false

        var singleString = "hello world";
        var concat = "hello ";
        concat += "world";
        System.out.println(singleString == concat); // false

        var x2 = "Hello World";
        var y2 = new String("Hello World");
        System.out.println(x2 == y2); // false
    }

    private static void intern() {
        var name = "Hello World";
        var name2 = new String("Hello World").intern();
        System.out.println(name == name2);       // true

        var first = "rat" + 1;
        var second = "r" + "a" + "t" + "1";
        var third = "r" + "a" + "t" + new String("1");
        System.out.println(first == second);
        System.out.println(first == second.intern());
        System.out.println(first == third);
        System.out.println(first == third.intern());
    }
}
