package dev.ronaldotavares.java21._4_core_apis;

public class _4_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Core APIs");

        var reviewQuestions = new _4_ReviewQuestions();
        reviewQuestions._16();
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
}
