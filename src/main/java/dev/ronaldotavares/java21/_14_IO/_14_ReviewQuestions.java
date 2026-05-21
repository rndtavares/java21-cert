package dev.ronaldotavares.java21._14_IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class _14_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Chapter 14 - IO - Review Questions");

         var reviewQuestions = new _14_ReviewQuestions();
         reviewQuestions._8();
    }

    void _8() {
        System.out.println("8");
        class PrintData {
            void printData(Path path) throws IOException {
//                Files.readAllLines(path) // r1
//                        .flatMap(p -> Stream.of(p.split(","))) // r2
//                        .map(q -> q.toUpperCase())  // r3
//                        .forEach(System.out::println);
            }
        }
    }
}
