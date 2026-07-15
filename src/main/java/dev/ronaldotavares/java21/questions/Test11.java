package dev.ronaldotavares.java21.questions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test11 {
    public static void main(String[] args) {
         var test11 = new Test11();
         test11._22();
         test11._35();
         test11._8();
    }
    void _22(){
        //Given:
        // Identify correct statements.
        System.out.println(parseFloat1(""+Float.NEGATIVE_INFINITY));
        System.out.println(parseFloat1(""+Float.POSITIVE_INFINITY));
        System.out.println(parseFloat1(" junk"));
        System.out.println(parseFloat1("-Infinity"));
        System.out.println(parseFloat1("NaN"));
    }
    public static float parseFloat1 (String s1){
        try {
            return Float.parseFloat(s1);
        } catch (NumberFormatException e) {
            return 0.0f;
        } catch (IllegalArgumentException e) {
            return Float.NaN;
        }
    }
    void _35(){
        try {
            TestClass35.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void _8(){
//        Given:
        record Book(String title, Double price) {
        }
//        What will the following code print?
        List<Book> books = Arrays.asList(
                new Book("Gone with the wind", 5.0),
                new Book("Gone with the wind", 10.0),
                new Book("Atlas Shrugged", 15.0));
        try {
            books.stream().collect(Collectors.toMap((b -> b.title()), b -> b.price()))
                    .forEach((a, b) -> System.out.println(a + " " + b));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class TestClass35 {
    public static void main(String[] args) throws Exception {
        try {
            amethod();
            System.out.println("try ");
        } catch (Exception e) {
            System.out.print("catch ");
        } finally {
            System.out.print("finally ");
        }
        System.out.print("out ");
        System.out.println();
    }

    public static void amethod() {
    }
}