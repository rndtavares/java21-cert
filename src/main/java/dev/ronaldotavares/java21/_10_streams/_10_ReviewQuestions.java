package dev.ronaldotavares.java21._10_streams;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class _10_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Chapter 10 - Streams");
        var reviewQuestions = new _10_ReviewQuestions();
        reviewQuestions._9();
        reviewQuestions._11();
        reviewQuestions._21();
    }

    void _9(){
        System.out.println("9 - Which of the following can we add after line 6 for the code to run without error and not produce any output? (Choose all that apply.)");
        var stream = LongStream.of(1, 2, 3);
        var opt = stream.map(n -> n * 10)
           .filter(n -> n < 5).findFirst();

//        A
//        if (opt.isPresent())
//            System.out.println(opt.get());

        //B
//        if (opt.isPresent())
//            System.out.println(opt.getAsLong());

//        C
//        opt.ifPresent(System.out.println);

        //D
        opt.ifPresent(System.out::println);
    }

    void _11(){
        System.out.println("11 - What changes need to be made together for this code to print the string 12345? (Choose all that apply.)");
        System.out.println(
//            Stream.iterate(1, x -> x++)
            Stream.iterate(1, x -> ++x)
                    .limit(5)
    //                .map(x -> x)
                    .map(x -> "" + x)
                    .collect(Collectors.joining())
        );
    }

    void _21(){
        System.out.println("21 - What is the output of the following?");
        var spliterator = Stream.generate(() -> "x")
                .spliterator();

        spliterator.tryAdvance(System.out::print);
        var split = spliterator.trySplit();
        split.tryAdvance(System.out::print);
    }
}
