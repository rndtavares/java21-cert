package dev.ronaldotavares.java21.questions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Test12 {
    public static void main(String[] args) {
        var test12 = new Test12();
        test12._3();
        test12._15();
        test12._23();
        test12._29();
        test12._40();
        test12._44();
        test12._48();
    }

    void _3() {
        System.out.println("question 3");
        var rounded = Math.round(9.0);
        System.out.println(rounded);
        for (int i = 0; i < 10; i++) {
            var original = Math.random();
            var multiplied = original * 9;
            var random = 1 + multiplied;
            System.out.println(original + " " + multiplied + " " + random);
        }
    }

    void _15() {
        System.out.println("question 15");
        Fruit.main(null);
    }

    void _23() {
        System.out.println("question 23");
//        Files
        Path p1 = Paths.get("/temp/test1.txt");
        Path p2 = Paths.get("/temp/test2.txt");
        Path p3 = p1.resolveSibling(p2);
        System.out.println(p3);
        Path p4 = p1.resolve(p2);
        System.out.println(p4);
        Path p5 = p1.relativize(p2);
        System.out.println(p5);
        Path p6 = p1.resolve("test2.txt");
        System.out.println(p6);
    }

    void _29() {
        System.out.println("question 29");
//        What letters will be printed by this program?
        char i;
        LOOP:
        for (i = 0; i < 5; i++) {
            switch (i++) {
                case '0':
                    System.out.println("A");
                case 1:
                    System.out.println("B");
                    break LOOP;
                case 2:
                    System.out.println("C");
                    break;
                case 3:
                    System.out.println("D");
                    break;
                case 4:
                    System.out.println("E");
                case 'E':
                    System.out.println("F");
            }
        }
        System.out.println("again");
        for (i = 0; i < 5; i++) {
            switch (i) {
                case '\u0000':
                    System.out.println("A");
                    break;
                case '\u0001':
                    System.out.println("B");
                    break;
                case '\u0002':
                    System.out.println("C");
                    break;
                case '\u0003':
                    System.out.println("D");
                    break;
                case '\u0004':
                    System.out.println("E");
                    break;
            }
        }
    }

    void _40(){
        System.out.println("question 40");
        new Random().doubles(10).forEach(System.out::print);
        System.out.println();
        Random r = new Random();
        DoubleStream rDoubles = r.doubles(100, 110).limit(10);
        rDoubles.forEach(System.out::print);
        System.out.println();
        DoubleStream.generate(()->r.nextDouble()).limit(10).forEach(System.out::print);
        System.out.println();
        DoubleStream rDoubles1 = r.doubles(10, 30, 34);
        rDoubles1.forEach(System.out::print);
        System.out.println();
        DoubleStream.generate(()->r.nextGaussian()).limit(10).forEach(System.out::print);
        System.out.println();

    }

    void _44(){
        System.out.println("question 44");
//        Consider the following code.
        LocalDate d = LocalDate.now();
        Locale loc = new Locale("fr", "FR");
        // 1 insert code here.
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy", loc);
        System.out.println(df.format(d));
        // What should be inserted at //1 above so that it will print the date in French format?
    }

    void _48(){
        System.out.println("question 48");

//        Consider the following code snippet:
        //INSERT LINE OF CODE HERE
//        int condition;
//        long condition = 2;
        var condition = Integer.valueOf("1");
//        String condition = "1";
//        var condition = Short.valueOf(1);
//        Byte condition = 1;
        switch (condition) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            case 3 -> System.out.println("3");
        }
//        What type can be inserted in the code above so that the above code compiles and runs as expected ?
        String conditions = "3";
        switch (conditions) {
            case "1" -> System.out.println("1");
            case "2" -> System.out.println("2");
            case "3" -> System.out.println("3");
        }
    }

    void _1(){
        System.out.println("question 1");
        final int i = 50;
//        int i = 50;
        byte b = i;
    }


    //What will the following code print when compiled and run?
    interface Eatable {
        int types = 10;

        default void test() {
            System.out.println("Eatable");
        }
    }

    class Food /*implements Eatable*/ {
        public static int types = 20;
    }

    class Fruit extends Food implements Eatable {  //LINE1
        public static void main(String[] args) {
            int foodTypes = Food.types;
            int eatableTypes = Eatable.types;
            System.out.println(foodTypes);
            System.out.println(eatableTypes);
//        types = 30; //LINE 2
//        System.out.println(types); //LINE 3
        }

        public void test() {
            int foodTypes = super.types;
            int eatableTypes = Eatable.types;
            Eatable.super.test();
//        super.test();
        }
    }
}
