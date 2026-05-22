package dev.ronaldotavares.java21._14_IO;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class _14_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Chapter 14 - IO - Review Questions");

         var reviewQuestions = new _14_ReviewQuestions();
         reviewQuestions._8();

         var practiceExam1 = reviewQuestions.new PracticeExam1();
         try {
             InputStream is = new ByteArrayInputStream("LYNX".getBytes());
             practiceExam1.printData(is);
         } catch (IOException ex){
             System.out.println(ex);
         }
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

    class PracticeExam1{
        public void printData(InputStream in) throws IOException {
            System.out.println("1 - Assume in is a valid stream whose next bytes are LYNX. What is the result of calling the following method on the stream?");
            var w = new StringBuilder();
            try(in; var o = new BufferedOutputStream(System.out)) {
                w.append((char) in.read());
                in.skip(1);
                in.read();
                in.skip(0);
                w.append((char)in.read());
                o.flush();
            }
            System.out.println(w);
        }
    }
}
