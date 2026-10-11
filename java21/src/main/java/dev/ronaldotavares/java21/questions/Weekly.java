package dev.ronaldotavares.java21.questions;

public class Weekly {
    public static void main(String[] args) {
        bitwise();
    }

    private static void bitwise() {
        System.out.println("bitwise");
        int x = 2;
        int y = ~x;
        int z = x ^ y;

        System.out.println("x = " + x);
        System.out.println("y = ~x = " + y);
        System.out.println("z = x ^ y = " + z);

        boolean flag = x < y & x > z++;
        System.out.println("after x < y & x > z++: flag = " + flag + ", z = " + z);

        if (flag) {
            flag = x > y && x > --z;
        }
        System.out.println("after if(flag): flag = " + flag + ", z = " + z);

        if (z > -1) {
            --z;
        } else {
            z++;
        }

        System.out.println(flag + " " + z);
    }
}
