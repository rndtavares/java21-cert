package dev.ronaldotavares.java21.questions;

import java.util.Date;
import java.util.TreeSet;

public class PracticeExam3 {
    public static void main(String[] args) {
        var practiceExam3 = new PracticeExam3();
        practiceExam3.treeSet();
        try {
            practiceExam3.booleanTest();
        } catch (Exception e) {
            System.out.println(e);
        }
        practiceExam3.cast();
        practiceExam3.format();
    }

    void treeSet() {
        System.out.println("treeSet");
        var points = new TreeSet<Object>();
        points.add(7);
        points.add(5);
        points.add(-4);

        points.forEach((n) -> System.out.print(n));
        points.forEach(n -> System.out.print(n));
        points.forEach(System.out::println);

    }

    void booleanTest() {
        System.out.println("booleanTest");
        Boolean tested = null;
        Boolean fixed = 1 > 3 ? true : null;
        if (fixed && tested) {
            throw new NullPointerException("shipped");
        }
        throw new NullPointerException("broken");
    }

    void cast() {
        System.out.println("cast");
        int numberReindeer = 9;
        System.out.println((double) numberReindeer);
        System.out.println((int) numberReindeer);
        System.out.println((long) numberReindeer);
        System.out.println((Integer) numberReindeer);
        System.out.println((Object) numberReindeer);
    }

    void format() {
        System.out.println("consoleFormat");
        // One example of many common Formatter conversion types
        System.out.println(String.format("literal percent: %%"));                     // %% -> '%'
        System.out.println(String.format("newline with %n end"));                    // %n -> platform newline
        System.out.println(String.format("string: %s", "hello"));                   // %s
        System.out.println(String.format("upper String: %S", "hello"));             // %S
        System.out.println(String.format("boolean: %b", true));                      // %b
        System.out.println(String.format("hash hex: %h", new Object()));             // %h
        System.out.println(String.format("char: %c", 'Z'));                          // %c
        System.out.println(String.format("decimal: %d", 42));                        // %d
        System.out.println(String.format("octal: %o", 42));                          // %o
        System.out.println(String.format("hex: %x", 255));                           // %x
        System.out.println(String.format("scientific: %e", 123.45));                 // %e
        System.out.println(String.format("fixed float: %f", 1.2345));                // %f
        System.out.println(String.format("general: %g", 123.45));                    // %g
        System.out.println(String.format("hexfloat: %a", 1.5));                      // %a
        // date/time example using java.util.Date with indexed argument
        var now = new Date();
        System.out.println(String.format("date/time ISO: %1$tF %1$tT", now));        // %tF, %tT
        System.out.println("bye");


    }
}