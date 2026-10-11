package dev.ronaldotavares.java21.questions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.*;

public class Test8 {
    public static void main(String[] args) {
        var test8 = new Test8();
        test8._4();
        test8._14();
        test8._33();
    }

    void _4(){
        StringArrayTest.main(null);
    }

    void _14(){
        try {
            Soccer.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void _24(){
        int[] iarray = null;
    }

    void _33(){
        TestClass33.main(null);
    }
    void _44(){
        FileCopier.main(null);
    }
}
class StringArrayTest {
    public static void main(String args[]) {
        String[][][] arr = {
                {{"a", "b", "c"}, {"d", "e", null}},
                {{"x"}, null}, {{"y"}}, {{"z", "p"}, {}
                }
        };
        System.out.println(arr[0][1][2]);
    }
}
class Game{
    public void play() throws Exception{
        System.out.println("Playing...");
    }
}

class Soccer extends Game{
    public void play(){
        System.out.println("Playing Soccer...");
    }
    public static void main(String[] args) throws Exception {
        Game g = new Soccer();
        g.play();
    }
}


class TestClass33 {
    public static int operate(IntUnaryOperator iuo) {
        return iuo.applyAsInt(5);
    }

    public static void main(String[] args) {
        IntFunction<IntUnaryOperator> fo = a -> b -> a - b;  //1
        int x = operate(fo.apply(20)); //2
        System.out.println(x);
        int y = fo.apply(20).applyAsInt(5);
        System.out.println(y);

        IntFunction<IntFunction<IntUnaryOperator>> foo = c -> d -> e -> c - d - e;  //1
        int z = foo.apply(20).apply(10).applyAsInt(5);
        System.out.println(z);

        ToIntFunction<String> toIntFunction = String::length;
        System.out.println(toIntFunction.applyAsInt("Hello"));
        DoubleToIntFunction doubleToIntFunction = d -> (int) d;
        System.out.println(doubleToIntFunction.applyAsInt(1.2));

        LongFunction<String> longFunction = Long::toString;
        System.out.println(longFunction.apply(123L));
    }
}

class TestClass36 {
    public static void main(String[] args) {
        switch (5) {
            default:
        }
        switch (5) {
            default:
                break;
        }
//        switch(8);
        var x = 0;
        switch (x) {
        }
    }

    public int switchTest(byte x) {
        return switch (x) {
            default -> 0;
            case '1' -> 1;
        };
    }
}


//Consider the following code:
class FileCopier {
    public static void copy(String records1, String records2) {
        try (InputStream is = new FileInputStream(records1);
             OutputStream os = new FileOutputStream(records2);) {  //1
//            if (os == null) os = new FileOutputStream("c:\\default.txt");  //2
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {  //3
                os.write(buffer, 0, bytesRead);
                System.out.println("Read and written bytes " + bytesRead);
            }
        } catch (IOException e) { //4
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        copy("c:\\temp\\test1.txt", "c:\\temp\\test2.txt");
        try {
            Files.copy(
                    Path.of("c:\\temp\\test1.txt"),
                    Path.of("c:\\temp\\test2.txt")
                    //, StandardCopyOption.REPLACE_EXISTING //FileAlreadyExistsException
            );
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
// Assuming appropriate import statements and the existence of both the files, what will happen when the program is compiled and run?

