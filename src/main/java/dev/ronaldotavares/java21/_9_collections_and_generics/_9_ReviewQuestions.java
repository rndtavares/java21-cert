package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.*;

public class _9_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions");
        var reviewQuestions = new _9_ReviewQuestions();

        reviewQuestions._2();
        reviewQuestions._5();
        reviewQuestions._assessment_12();
    }

    void _2(){
        try {
            List<?> q = List.of("mouse", "parrot");
            var v = List.of("mouse", "parrot");
//            q.removeIf(String::isEmpty);
//            q.removeIf(s -> s.length() == 4);
            v.removeIf(String::isEmpty);
            v.removeIf(s -> s.length() == 4);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    void _5(){
        Hello.main(null);
    }

    void _assessment_12() {
        System.out.println("question 12 from assessment test:");

        //Set<? extends RuntimeException> mySet = new _________ ();

//        Set<? extends RuntimeException> mySet = new HashSet<? extends RuntimeException>();
//        Set<? extends RuntimeException> mySet1 = new HashSet<Exception>();
        Set<? extends RuntimeException> mySet2 = new TreeSet<RuntimeException>();
        Set<? extends RuntimeException> mySet3 = new TreeSet<NullPointerException>();
        Set<? extends RuntimeException> mySet4 = new LinkedHashSet<>();
//        Set<? extends RuntimeException> mySet5 = new LinkedHashSet<?>();

        System.out.println("mySet2 is a " + mySet2.getClass());
        System.out.println("mySet3 is a " + mySet3.getClass());
        System.out.println("mySet4 is a " + mySet4.getClass());

        //can't add nothing but null
//        mySet4.add(new RuntimeException());
//        mySet4.add(new NullPointerException());
//        mySet4.add(new Exception());
//        mySet4.add(new Object());
        mySet4.add(null);

        List<? extends Number> src = List.of(1, 2, 3);
//        src.add(4);
        src.add(null);
    }
}

record Hello<T>(T t) {
    public Hello(T t) { this.t = t; }
    private <T> void println(T message) {
        System.out.print(t + "-" + message);
    }
    public static void main(String[] args) {
        new Hello<String>("hi").println(1);
        new Hello("hola").println(true);
    }
}