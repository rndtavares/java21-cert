package dev.ronaldotavares.java21._2_operators;

public class _2_2_ApplyingUnaryOperators {
    public static void main(String[] args) {
        complementAndNegationOperands();
        incrementAndDecrementOperators();
    }

    private static void complementAndNegationOperands() {
        boolean isAnimalAsleep = false;
        System.out.println(isAnimalAsleep); // false
        isAnimalAsleep = !isAnimalAsleep;
        System.out.println(isAnimalAsleep); // true

        int number = 70;
        int negated = ~number;
        System.out.println(negated);  // -71
        //~ turns all bits to the opposite
        //70 = 00000000_00000000_00000000_01000110
        //-71= 11111111_11111111_11111111_10111001
        //So, ~70 = -71
        //To get back to 70, we do the same operation again:
        //-71= 11111111_11111111_11111111_10111001
        //70 = 00000000_00000000_00000000_01000110
        //So, ~-71 = 70
        //In summary, the bitwise NOT operator (~) inverts all bits of its operand
        //and the result is equivalent to -(X + 1) for any integer X.
        //11111111_11111111_11111111_10111001
        System.out.println(Integer.toBinaryString(negated));
        System.out.println(~negated); // 70
        //00000000_00000000_00000000_01000110
        System.out.println(Integer.toBinaryString(number));

        shiftOperators();

        double zooTemperature = 1.21;
        System.out.println(zooTemperature); // 1.21
        zooTemperature = -zooTemperature;
        System.out.println(zooTemperature); // -1.21
        zooTemperature = -(-zooTemperature);
        System.out.println(zooTemperature); // -1.21

//        int pelican = !5; // DOES NOT COMPILE
//        boolean penguin = -true; // DOES NOT COMPILE
//        boolean parrot = ~true;  // DOES NOT COMPILE
//        boolean peacock = !0;  // DOES NOT COMPILE
    }

    private static void shiftOperators(){
        /*
        Shift Operators
        << Left shift: Shifts the bits to the left, filling the new bits with zeros. This is equivalent to multiplying by 2.
        >> Right shift: Shifts the bits to the right. If the number is positive, it fills the new bits with zeros. If the number is negative, it fills the new bits with ones (sign extension). This is equivalent to dividing by 2.
        >>> Unsigned right shift: Shifts the bits to the right, filling the new bits with zeros, regardless of the sign of the number.
         */

        int number = 70; // Binary: 00000000 00000000 00000000 01000110

        // Left shift (<<)
        int leftShifted = number << 1; // Shift left by 1 bit
        // Original: 00000000 00000000 00000000 01000110
        // New:      00000000 00000000 00000000 10001100 (140 in decimal)
        System.out.println("Original: " + Integer.toBinaryString(number));
        System.out.println("Left Shifted (<< 1): " + Integer.toBinaryString(leftShifted) + " = " + leftShifted);

        // Right shift (>>)
        int rightShifted = number >> 1; // Shift right by 1 bit
        // Original: 00000000 00000000 00000000 01000110
        // New:      00000000 00000000 00000000 00100011 (35 in decimal)
        System.out.println("Original: " + Integer.toBinaryString(number));
        System.out.println("Right Shifted (>> 1): " + Integer.toBinaryString(rightShifted) + " = " + rightShifted);

        // Unsigned right shift (>>>)
        int unsignedRightShifted = number >>> 1; // Shift right by 1 bit
        // Original: 00000000 00000000 00000000 01000110
        // New:      00000000 00000000 00000000 00100011 (35 in decimal)
        System.out.println("Original: " + Integer.toBinaryString(number));
        System.out.println("Unsigned Right Shifted (>>> 1): " + Integer.toBinaryString(unsignedRightShifted) + " = " + unsignedRightShifted);

        int negativeNumber = -70; // Binary: 11111111 11111111 11111111 10111010

        // Right shift (>>) with negative number
        int negativeRightShifted = negativeNumber >> 1; // Shift right by 1 bit
        // Original: 11111111 11111111 11111111 10111010
        // New:      11111111 11111111 11111111 11011101 (-35 in decimal)
        System.out.println("Original: " + Integer.toBinaryString(negativeNumber));
        System.out.println("Right Shifted (>> 1) with negative number: " + Integer.toBinaryString(negativeRightShifted) + " = " + negativeRightShifted);

        // Unsigned right shift (>>>) with negative number
        int negativeUnsignedRightShifted = negativeNumber >>> 1; // Shift right by 1 bit
        // Original: 11111111 11111111 11111111 10111010
        // New:      01111111 11111111 11111111 11011101 (2147483613 in decimal)
        System.out.println("Original: " + Integer.toBinaryString(negativeNumber));
        System.out.println("Unsigned Right Shifted (>>> 1) with negative number: " + Integer.toBinaryString(negativeUnsignedRightShifted) + " = " + negativeUnsignedRightShifted);
    }

    private static void incrementAndDecrementOperators() {
        int parkAttendance = 0;
        System.out.println(parkAttendance); // 0
        System.out.println(++parkAttendance); // 1
        System.out.println(parkAttendance); // 1
        System.out.println(parkAttendance--); // 1
        System.out.println(parkAttendance); // 0
    }
}
