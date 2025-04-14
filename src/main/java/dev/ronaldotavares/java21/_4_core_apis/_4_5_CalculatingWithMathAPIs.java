package dev.ronaldotavares.java21._4_core_apis;

import java.math.BigDecimal;
import java.math.BigInteger;

public class _4_5_CalculatingWithMathAPIs {
    public static void main(String[] args) {
        minMax();
        rounding();
        ceilingAndFlooring();
        expoents();
        random();
        bigIntegerBigDecimal();
    }
    
    private static void minMax() {
        System.out.println("minMax");
        int first = Math.max(3, 7);      // 7 
        int second = Math.min(7, -9);   // -9
        System.out.println(first);
        System.out.println(second);
    }
    
    private static void rounding() {
        System.out.println("rounding");
        long low = Math.round(123.45);          // 123 
        long high = Math.round(123.50);         // 124 
        int fromFloat = Math.round(123.45f);    // 123
        System.out.println(low);
        System.out.println(high);
        System.out.println(fromFloat);
    }

    private static void ceilingAndFlooring() {
        System.out.println("ceilingAndFlooring");
        double c = Math.ceil(3.14);   // 4.0 
        double f = Math.floor(3.14); // 3.0
        System.out.println(c);
        System.out.println(f);
    }

    private static void expoents() {
        System.out.println("expoents");
        double squared = Math.pow(5, 2); // 25.0
        System.out.println(squared);
    }

    private static void random() {
        System.out.println("random");
        double num = Math.random();
        System.out.println(num);
    }

    private static void bigIntegerBigDecimal() {
        System.out.println("bigIntegerBigDecimal");
        var bigInt = BigInteger.valueOf(5_000L);
        var bigDecimal = BigDecimal.valueOf(5_000L);
        bigDecimal = BigDecimal.valueOf(5_000.00);
        System.out.println(bigInt);
        System.out.println(bigDecimal);

        var bigInt1 = BigInteger.valueOf(199)
                .add(BigInteger.valueOf(1))
		        .divide(BigInteger.TEN)
		        .max(BigInteger.valueOf(6));
        System.out.println(bigInt1);   // 20

        System.out.println(new BigInteger("12345123451234512345"));
//        System.out.println(12345123451234512345L);   // DOES NOT COMPILE

        double amountInCents1 = 64.1 * 100;
        System.out.println(amountInCents1);   // 6409.999999999999

        BigDecimal amountInCents2 = BigDecimal.valueOf(64.1)
                .multiply(BigDecimal.valueOf(100));
        System.out.println(amountInCents2);   // 6410.0
    }
}
