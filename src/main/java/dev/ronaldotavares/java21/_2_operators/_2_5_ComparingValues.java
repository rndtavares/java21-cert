package dev.ronaldotavares.java21._2_operators;

import java.io.File;

public class _2_5_ComparingValues {
    public static void main(String[] args) {
        equalityOperators();
        numericComparisonOperators();
        instanceofOperator();
        logicalOperators();
        bitwiseOperators();
        conditionalOperators();
    }

    private static void equalityOperators() {
//        boolean monkey = true == 3;  // DOES NOT COMPILE
//        boolean ape = false != "Grape"; // DOES NOT COMPILE
//        boolean gorilla = 10.2 == "Koko"; // DOES NOT COMPILE

        boolean bear = false;
        boolean polar = (bear = true);
        System.out.println(polar); // true

        var monday = new File("schedule.txt");
        var tuesday = new File("schedule.txt");
        var wednesday = tuesday;
        System.out.println(monday == tuesday);// false
        System.out.println(tuesday == wednesday); // true

        System.out.print(null == null); // true
    }

    private static void numericComparisonOperators() {
        int gibbonNumFeet = 2, wolfNumFeet = 4, ostrichNumFeet = 2;
        System.out.println(gibbonNumFeet < wolfNumFeet);      // true
        System.out.println(gibbonNumFeet <= wolfNumFeet);     // true
        System.out.println(gibbonNumFeet >= ostrichNumFeet);  // true
        System.out.println(gibbonNumFeet > ostrichNumFeet);   // false
    }

    private static void instanceofOperator() {
        Integer zooTime = Integer.valueOf(9);
        Number num = zooTime;
        Object obj = zooTime;
        System.out.println(zooTime instanceof Object);   // true
        System.out.println(zooTime instanceof Number);   // true
        System.out.println(num instanceof Object);   // true
        System.out.println(num instanceof Integer);   // true
        System.out.println(obj instanceof Integer);  // true
        System.out.println(obj instanceof Number);  // true


        openZoo(1);

        System.out.println(null instanceof Object); // false
        String noObjectHere = null;
        System.out.println(noObjectHere instanceof String);  // false

//        System.out.print(null instanceof null);  // DOES NOT COMPILE
    }

    public static void openZoo(Number time) {
        if (time instanceof Integer)
            System.out.print((Integer)time + " O'clock");
        else
            System.out.print(time);

//        if(time instanceof String) // DOES NOT COMPILE
//            System.out.print(time);
    }


    private static void logicalOperators() {
        boolean eyesClosed = true;
        boolean breathingSlowly = true;
        boolean resting = eyesClosed | breathingSlowly;
        boolean asleep = eyesClosed & breathingSlowly;
        boolean awake = eyesClosed ^ breathingSlowly;
        System.out.println(resting);  // true
        System.out.println(asleep);   // true
        System.out.println(awake);    // false
    }

    private static void bitwiseOperators() {
        int number = 70;
        System.out.println(number); // 70
        System.out.println(number & number);   // 70
        System.out.println(number | number);   // 70

        int negated = ~number;
        System.out.println(negated);           // -71
        System.out.println(number & negated);  // 0
        System.out.println(number | negated);  // -1

        System.out.println(number ^ number);   // 0
        System.out.println(number ^ negated);  // -1
    }

    private static void conditionalOperators() {
        int hour = 10;
        boolean zooOpen = true || (hour < 4);
        System.out.println(zooOpen);  // true

        Duck duck = new Duck();

        if(duck != null & duck.getAge() < 5) { // Could throw a NullPointerException
            // Do something
        }

        if(duck != null && duck.getAge()<5) {
            // Do something
        }

        int rabbit = 6;
        boolean bunny = (rabbit>= 6) || (++rabbit <= 7);
        System.out.println(bunny);
        System.out.println(rabbit);
    }
}
class Duck{
    int age;
    public int getAge() {
        return age;
    }
}
