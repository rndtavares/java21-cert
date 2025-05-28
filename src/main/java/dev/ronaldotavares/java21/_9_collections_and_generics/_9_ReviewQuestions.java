package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.List;

public class _9_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions");
        var reviewQuestions = new _9_ReviewQuestions();

        reviewQuestions._2();
        reviewQuestions._5();
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