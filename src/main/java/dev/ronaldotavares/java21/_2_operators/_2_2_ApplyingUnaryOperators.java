package dev.ronaldotavares.java21._2_operators;

public class _2_2_ApplyingUnaryOperators {
    public static void main(String[] args) {
        complementAndNegationOperands();
        incrementAndDecrementOperators();
    }

    private static void complementAndNegationOperands() {
        boolean isAnimalAsleep = false;
        System.out.print(isAnimalAsleep); // false
        isAnimalAsleep = !isAnimalAsleep;
        System.out.print(isAnimalAsleep); // true

        int number = 70;
        int negated = ~number;
        System.out.println(negated);  // -71
        System.out.println(~negated); // 70

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

    private static void incrementAndDecrementOperators() {
        int parkAttendance = 0;
        System.out.println(parkAttendance); // 0
        System.out.println(++parkAttendance); // 1
        System.out.println(parkAttendance); // 1
        System.out.println(parkAttendance--); // 1
        System.out.println(parkAttendance); // 0
    }
}
