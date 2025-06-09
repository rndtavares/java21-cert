package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class _11_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Exceptions and Localization");

        var reviewQuestions = new _11_ReviewQuestions();
        reviewQuestions._9();
        reviewQuestions._13();
    }

    void _9(){
        System.out.println("For what value of pattern will the following print ˂005.21˃ ˂008.49˃ ˂1,234.0˃?");
//        A. ##.#
//        B. 0,000.0#
//        C. #,###.0
//        D. #,###,000.0#
        String pattern = "#,###,000.0#";
        var message = DoubleStream.of(5.21, 8.49, 1234)
                .mapToObj(v -> new DecimalFormat(pattern).format(v))
                .collect(Collectors.joining("> <"));
        System.out.println("<"+message+">");
    }

    void _13() {
        System.out.println("Which of the following are true statements about exception handling in Java? (Choose all that apply.)");

        /*
            A. A traditional try statement without a catch block requires a finally block.
            B. A traditional try statement without a finally block requires a catch block.
            C. A traditional try statement with only one statement can omit the {}.
            D. A try‐with‐resources statement without a catch block requires a finally block.
            E. A try‐with‐resources statement without a finally block requires a catch block.
            F. A try‐with‐resources statement with only one statement can omit the {}.
         */
        try{
            System.out.println("try without catch");
        }finally {
            System.out.println("finally without catch");
        }
    }
}
