package dev.ronaldotavares.java21.questions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Test14 {
    public static void main(String[] args) {
        var test14 = new Test14();
        test14._1();
        test14._4();
        test14._9();
        test14._23();
        test14._31();
        test14._33();
        test14._34();
        test14._45();
        test14._47();
        test14._50();
    }

    void _1() {
        System.out.println("question 1");
        TestClass14_1.main(null);
    }

    void _4() {
        System.out.println("question 4");
//        What is the most likely output of the following code:
        var list = List.of("X", "Y");
        var result = list.parallelStream().reduce(
                list.parallelStream().reduce(
                        (a, b) -> a + b
                ).get(),
                (i, j) -> i + j,
                String::concat
        );
        System.out.println(result);
    }

    void _9() {
        System.out.println("question 9");

//        Given:  public
        class TestClass {
            public static void main(String[] args) throws IOException {
                final Reader reader = new FileReader("aaa.a");  //1
                try (reader) {
                    reader.read(); //2
                } finally {
                    reader.read(); //3
                }
                reader.read(); //4
            }
        }
        // Assuming that the file named aaa.a does not exist, what will be the outcome?
        Path path = Paths.get("aaa.a");
        path.resolve("aaa.a");
        path.resolveSibling("aaa.a");
        path.relativize(path);
        path.normalize();
        try {
            TestClass.main(null);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void _23() {
        System.out.println("question 23");

//        Given:
        Instant now = Instant.now();

        Instant now2 = now.truncatedTo(ChronoUnit.DAYS); //INSERT CODE HERE
        System.out.println(now2);

        Instant now3 = now.truncatedTo(ChronoUnit.HOURS);
        System.out.println(now3);

        Instant now4 = now.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(now4);

        Instant now5 = now.truncatedTo(ChronoUnit.SECONDS);
        System.out.println(now5);

        Instant now6 = now.truncatedTo(ChronoUnit.MILLIS);
        System.out.println(now6);

        Instant now7 = now.truncatedTo(ChronoUnit.MICROS);
        System.out.println(now7);

        Instant now8 = now.truncatedTo(ChronoUnit.NANOS);
        System.out.println(now8);

        // Which of the following options can be inserted in the above code without causing any compilation or runtime errors?
    }

    void _31() {
        System.out.println("question 31");

//      Consider the following code:  import java.io.*; public class Test {    public static void main(String[] args)  throws Exception    {
        FileWriter fw = null;
        try {
//            fw = new FileWriter("text.txt");
            fw = new FileWriter("text.txt", true);
//            fw.write("hello"); //1
            fw.write("hello"); //1
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
//    } }  Which of the following statements are correct?
    }

    void _33(){
        System.out.println("question 33");
//        Identify correct statements about the following code:
        List<String> vals = Arrays.asList("a", "b");
        String join = vals.parallelStream()
                .reduce("_",
                        (a, b) -> a.concat(b));
        System.out.println(join);
        System.out.println();
        System.out.println("one more example in a computer with " + Runtime.getRuntime().availableProcessors() + " processors");
        System.out.println();

        List<String> values = Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g",
                "a", "b", "c", "d", "e", "f", "g",
                "a", "b", "c", "d", "e", "f", "g",
                "a", "b", "c", "d", "e", "f", "g");
        String reduced = values.parallelStream().peek(System.out::println) //this shows how the elements are retrieved from the stream
                .reduce("_",
                        (a, b) -> {
                            System.out.println("reducing " + a + " and " + b + " Thread: " + Thread.currentThread().getName());
                            return a.concat(b);
                        },
                        (a, b) -> {
                            System.out.println("combining " + a + " and " + b + " Thread: " + Thread.currentThread().getName());
                            return a.concat(b);
                        });
        System.out.println(reduced);
    }

    void _34(){
        System.out.println("question 34");
        TestClass14_34.main(null);
    }

    void _45(){
        System.out.println("question 45");

//        What will the following method print?
//        public static void iCanDoThis(){
        int x = 2;
        int y = ~x;  //LINE 2
        int z = x ^ y;  //LINE 3

        boolean flag = x < y & x > z++;   //LINE 4

        if (flag) {
            flag = x > y && x > --z;  //LINE 6
        }

        if (z > -1) {  //LINE 8
            --z;
        } else z++;

        System.out.println(flag + " " + z);   //LINE 11
//         }
    }

    void _47(){
        System.out.println("question 47");

//        What will the following code print when compiled and run?
        Collection<Number> col = new HashSet<>();
        col.add(1);
        var list1 = List.of(col); //1
        col.add(2); //2
        var list2 = List.copyOf(col); //3
        System.out.println(list1 + ", " + list2);
        col.add(3);
        System.out.println(list1 + ", " + list2 + ", " + col);
    }

    void _50(){
        System.out.println("question 50");
        var at = AccountType.valueOf("FD");
        System.out.println(at.ordinal() + " " + at);
    }
}


//Given: //in file Movable.java package p1;
interface Movable {
    int location = 0;

    void move(int by);

    public void moveBack(int by);
}
//in file Donkey.java package p2; import p1.Movable;
class Donkey implements Movable {
    int location = 200;

    public void move(int by) {
        location = location + by;
    }

    public void moveBack(int by) {
        location = location - by;
    }
}
//in file TestClass.java package px; import p1.Movable; import p2.Donkey;
class TestClass14_1 {
    public static void main(String[] args) {
        Movable m = new Donkey();
        m.move(10);
        m.moveBack(20);
        System.out.println(m.location);

        Donkey d = new Donkey();
        d.move(10);
        d.moveBack(20);
        System.out.println(d.location);
    }
}
//Identify the correct statement(s).

//------

//Given:
interface Carnivore {
    default int calories(List<String> food) {
        return food.size() * 100;
    }

    int eat(List<String> foods);
}

class Tiger implements Carnivore {
    public int eat(List<String> foods) {
        System.out.println("Eating " + foods);
        return foods.size() * 200;
    }
}

class TestClass14_34 {
    public static int size(List<String> names) {
        return names.size() * 2;
    }

    public static void process(List<String> names, Carnivore c) {
        System.out.println(c.eat(names));
    }

    public static void main(String[] args) {
        List<String> fnames = Arrays.asList("a", "b", "c");
        Tiger t = new Tiger();
//        INSERT CODE HERE
        process(fnames, t::eat);
        process(fnames, t::calories);
        process(fnames, TestClass14_34::size);
//        process(fnames, Carnivore::calories);
//        process(fnames, Tiger::eat);
    }
}
//Which of the following options can be inserted independent of each other in the code above without any compilation error?



//What will the following code print?
enum AccountType {
    CHECKING("Checking account"), SAVINGS("Savings account"), FD("Fixed Deposit");
    private String desc;

    AccountType(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Acct type:" + super.toString();
    }
}

//public class TestClass {
//    public static void main(String[] args) {
//        var at = AccountType.valueOf("FD");
//        System.out.println(at.ordinal() + " " + at);
//    }
//}