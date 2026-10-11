package dev.ronaldotavares.java21.questions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Test13 {
    public static void main(String[] args) {
        var test13 = new Test13();
        test13._8();
        test13._9();
        test13._17();
        test13._19();
        test13._23();
        test13._35();
        test13._43();
        test13._47();
    }

    void _8(){
        System.out.println("question 8");
        Device.main(null);
    }

    void _9(){
        System.out.println("question 9");
//      What will the following program print?
        var c = 0;
        var flag = true;
        for(var i = 0; i < 3; i++){
            while(flag){
                c++;
                if(i>c || c>5) flag = false;
            }
        }
        System.out.println(c);
    }

    void _17(){
        System.out.println("question 17");
//        Given:
        List<Integer> lon = List.of(1, 2, 3, 4, 5, 6, 7);
//        Which of the following may return a different value every time it is executed?
        int result;
        result = lon.stream().reduce(5, (a, b)->a+b); //A
        System.out.println("A" + result);
        result = lon.stream().reduce(0, Integer::sum) + 5; //B
        System.out.println("B" + result);
        result = lon.parallelStream().reduce(0, Integer::sum) + 5; //C
        System.out.println("C" + result);
        boolean alwaysTheSame = true;
        for(int i = 0; i < 1_000; i++) {
            result = lon.parallelStream().reduce(5, Integer::sum); //D
            if(result != 63){
                System.out.println("D" + result);
                alwaysTheSame = false;
                break;
            }
        }
        if(alwaysTheSame) {
            System.out.println("D is always 63");
        }
        result = lon.parallelStream().reduce(Integer::sum).orElse(5)+5; //E
        System.out.println("E" + result);
    }

    void _19(){
        System.out.println("question 19");
//      What will be the result of attempting to compile and run the following program?
        StringBuilder sb = new StringBuilder("12345678");
        sb.setLength(5);
        sb.setLength(10);
        System.out.println(sb.length());
        System.out.println(sb);
    }

    void _23(){
        System.out.println("question 23 in a machine with " + Runtime.getRuntime().availableProcessors() + " processors");
        boolean alwaysTheSame = true;
        for(int i = 0; i < 10; i++) {
//        What will the following code print?
            AtomicInteger ai = new AtomicInteger();
            Stream<String> stream = Stream.of("old", "king", "cole", "was", "a", "merry", "old", "soul")
                    .parallel();
            stream.filter(e -> {
                ai.incrementAndGet();
                return e.contains("o");
            }).allMatch(x -> x.indexOf("o") > 0);
            if(ai.get() != 8) {
                System.out.println("AI = " + ai);
                alwaysTheSame = false;
                break;
            }
        }
        if(alwaysTheSame) {
            System.out.println("AI is always = " + 8);
        }

        AtomicInteger ai = new AtomicInteger();
        Stream<String> stream = Stream.of("old", "king", "cole", "was", "a", "merry", "old", "soul");
        stream.filter(e -> {
            ai.incrementAndGet();
            return e.contains("o");
        }).allMatch(x -> x.indexOf("o") > 0);
        System.out.println("AI = " + ai);
    }

    void _35(){
        System.out.println("question 35");

        Derived d = new Derived();

        List<String> list = new ArrayList<>();
        d.transform(list);

        Collection<String> col = new ArrayList<>();
        d.transform(col);
    }

    void _43(){
        final int x = 0;
//      int x = 0;
        int y = 0;
        switch (y) {
            case x:
                System.out.println("0");//valid because x is a compile time constant }
        }
    }

    void _47(){
        System.out.println("question 47");
        System.out.println(switchTest((byte) 'b'));
//        Given:
            // What is the result?
        System.out.println(test(null));
    }

    int switchTest ( byte x){
        return switch (x) { //1
            case 'b', 'c' -> 10; //2
            case -2 -> 20;    // 3
//            case 80, default  -> 30;    // 4
            case 80 -> 30;
            default -> 30;
        };
    }

    static String test(Object obj) {
        return switch (obj) {
            case String s -> "String";
            case Integer i -> "Integer";
            case null, default -> "Outro ou null";
        };
    }
}

//What will be printed when the following code is compiled and run?
class Device implements AutoCloseable {
    String header = null;

    public void open() {
        header = "OPENED";
        System.out.println("Device Opened");
    }

    public String read() throws IOException {
        throw new IOException("Unknown");
    }

    public void writeHeader(String str) throws IOException {
        System.out.println("Writing : " + str);
        header = str;
    }

    public void close() {
        header = null;
        System.out.println("Device closed");
    }

    public static void testDevice() {
        try (Device d = new Device()) {
            d.open();
            d.read();
            d.writeHeader("TEST");
            d.close();
        } catch (IOException e) {
            System.out.println("Got Exception");
        }
    }

    public static void main(String[] args) {
        Device.testDevice();
    }
}


//Given:
class Base {
    public <T extends CharSequence> Collection<String> transform(Collection<T> list) {
        return new ArrayList<String>();
    }
}

class Derived extends Base {
//    public Collection<String> transform(Collection<String> list) {
//        return new HashSet<String>();
//    } //1

//    public <T extends String> Collection<T> transform(Collection<T> list) {
//        return new HashSet<T>();
//    } //2

//    public <T extends CharSequence> List<T> transform(Collection<T> list) {
//        return new ArrayList<T>();
//    } //3

    public <T extends CharSequence> Collection<T> transform(List<T> list) {
        return new HashSet<T>();
    } //4

//    public <T super String> Collection<T> transform(List<String> list) {
//        return new HashSet<T>();
//    } //5

//    public Collection<CharSequence> transform(Collection<CharSequence> list) {
//        return new HashSet<CharSequence>();
//    } //6
}
// Identify correct statements about the methods defined in Derived assuming they are uncommented one at a time individually. (Ignore the extra line breaks.)

class Test11111{
    public static void main(String[] args) {
//        boolean flag = true;
//        if(false = flag){
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//        boolean b1 = false;
//        boolean b2 = false;
//        if (b2 != b1 = !b2) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }

        boolean b = true;
        boolean b1 = b = true;
        if(b != (b = !b1)){
            System.out.println("true");
        }
    }
}
