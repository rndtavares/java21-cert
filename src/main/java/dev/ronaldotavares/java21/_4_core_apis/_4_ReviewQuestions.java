package dev.ronaldotavares.java21._4_core_apis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class _4_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Core APIs");

        var reviewQuestions = new _4_ReviewQuestions();
        reviewQuestions._16();

        var practiceExam1 = reviewQuestions.new PracticeExam1();
        practiceExam1._10();

        var practiceExam3 = reviewQuestions.new PracticeExam3();
        practiceExam3._1();
    }
    void _16(){
        System.out.println("16 - Which of these statements are true? (Choose all that apply.)");
//        A. letters.substring(1, 2) returns a single‐character String.
//        B. letters.substring(2, 2) returns a single‐character String.
//        C. letters.substring(6, 5) returns a single‐character String.
//        D. letters.substring(6, 6) returns a single‐character String.
//        E. letters.substring(1, 2) throws an exception.
//        F. letters.substring(2, 2) throws an exception.
//        G. letters.substring(6, 5) throws an exception.
//        H. letters.substring(6, 6) throws an exception.
        var letters = new StringBuilder("abcdefg");

        try {
            System.out.print("A:");
            System.out.println(letters.substring(1, 2));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("B:");
            System.out.println(letters.substring(2, 2));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("C:");
            System.out.println(letters.substring(6, 5));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("D:");
            System.out.println(letters.substring(6, 6));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("E:");
            System.out.println(letters.substring(1, 2));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("F:");
            System.out.println(letters.substring(2, 2));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("G:");
            System.out.println(letters.substring(6, 5));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            System.out.print("H:");
            System.out.println(letters.substring(6, 6));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    class PracticeExam1{
        public void _10(){
            System.out.println("""
                    10 - You have a task to calculate the length of a side of a triangle using the Pythagorean theorem and the area of a triangle. 
                    Luckily, you are given the formulas. 
                    For the Pythagorean theorem, you need the square root (one‐half power) of a2 + b2. 
                    For the area, you need half of the base times the height. Fill in the blanks to implement these algorithms (Choose all that apply.)
                    """);

            class Triangle {
                public static void main(String... args) {
                    var math = new ArrayList<>();
                    math.add(pythagorean(3, 4));
                    math.add(area(3, 8));
                    System.out.println("pythagorean " + math.getFirst());
                    System.out.println("add " + math.getLast());
                }
                public static double pythagorean(int a, int b) {
//                    return ____________________;
                    var optionA = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 1/2);
                    System.out.println("A: " + optionA); //A

                    var optionB = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), .5);
                    System.out.println("B: " + optionB); //B

//                    Math.squareRoot(Math.pow(a, 2) + Math.pow(b, 2)); //C

                    return optionB;
                }
                public static double area(int base, int height) {
//                    return _____________________;
                    var c = (1/2) * (base*height); //C
                    System.out.println("C: " + c);

                    var d = ((double)1/2) * (base*height); //D
                    System.out.println("D: " + d);

                    var e = (double) (1/2) * (base*height); //E
                    System.out.println("E: " + e);

                    System.out.println("cast first element in 1/2: " + (double)1/2);
                    System.out.println("cast entire operation (1/2): " + (double)(1/2));

                    return d;
                }
            }
            Triangle.main(null);
        }
    }

    class PracticeExam3{
        public void _1() {
            System.out.println("Practice Exam 3 - Question 1");
            List<Double> numbers = new ArrayList<>();
            numbers.add(12.0);
            numbers.add(3.14);
            numbers.add(3.14);
            numbers.add(2.718);
            numbers.add(2.718);

            Predicate<Double> pred =
                    n -> Math.round(n) == (int) n.doubleValue();

            System.out.println(12l == 12);
            System.out.println(12l == 12.0d);
            System.out.println(12l == 12.0f);
            System.out.println(12 == 12.0d);
            System.out.println(12 == 12.0f);
            System.out.println(12d == 12.0f);

            numbers.removeIf(pred);

            System.out.println(numbers);
        }
    }
}
