package dev.ronaldotavares.java21.questions;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Test16 {
    public static void main(String[] args) {
        var test16 = new Test16();
        test16._4();
        test16._7();
        test16._11();
        test16._15();
        test16._20();
        test16._29();
        test16._42();
        test16._43();
    }

    private void _4() {
        System.out.println("question 4");
        JustLooping.main(null);
        System.out.println();
    }

    private void _7() {
        System.out.println("question 7");
        List<String> keys = List.of("b", "a");
        try {
            keys.sort(String::compareTo); //throws java.lang.UnsupportedOperationException at run time
        } catch (Exception e) {
            System.out.println(e);
        }
        keys.stream().sorted().forEach(System.out::print);//OK
        System.out.println();
    }

    private void _11() {
        System.out.println("question 11");
        TestClass16_11.main(null);
    }

    private void _15() {
        System.out.println("question 15");
        try {
            AX.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void _20() {
        System.out.println("question 20");

//        Given:
        List<Integer> names = Arrays.asList(1, 2, 3);
//        How many of the following lines will print exactly 6?
//    1.
        System.out.println(names.stream().mapToInt(x -> x).sum());
//    2.
//        System.out.println(names.stream().forEach((sum, x) -> sum = sum + x));
//    3.
        System.out.println(names.stream().reduce(0, (a, b) -> a + b));
//    4.
        System.out.println(names.stream().collect(Collectors.mapping(x -> x, Collectors.summarizingInt(x -> x))).getSum());
//    5.
        System.out.println(names.stream().collect(Collectors.summarizingInt(x -> x)).getSum());
    }

    private void _29() {
        System.out.println("question 29");
        Locale locale = new Locale.Builder().setLanguageTag("en-US").build();
    }

    private void _42() {
        System.out.println("question 42");
        Logger logger = new Logger();
        logger.logMsg("location1", "message1");
        logger.dumpLog();

    }

    private void _43() {
        System.out.println("question 43");
        TestClass16_43.main(null);
    }
}

//        Given:  package loops; public
class JustLooping {
    private int j;

    void showJ() {
        while (j <= 5) {
            for (int j = 1; j <= 5; ) {
                System.out.print(j + " ");
                j++;
            }
//            int j = 0;
//            System.out.println(j);
            j++;
        }
    }

    public static void main(String[] args) {
        new JustLooping().showJ();
    }
}
//What is the result?


//Which statement(s) about the following code are correct?
interface House /*extends Office*/ /*extends Third*/ {
    public default void lockTheGates() {
        System.out.println("Locking House");
    }
}

interface Office /*extends House*/ {
    public void lockTheGates();
}

interface Third {
    public static void lockTheGates() {
        System.out.println("Locking Third");
    }
}

class HomeOffice implements House, Office, Third { //1
    public void lockTheGates() {
        System.out.println("Locking Home Office");
    }
}

class TestClass16_11 {
    public static void main(String[] args) {
//        Office off = new HomeOffice();  //2
//        off.lockTheGates(); //3
//        House home = (House) off; //4
//        home.lockTheGates(); //5

        HomeOffice homeOffice = new HomeOffice();
        homeOffice.lockTheGates();
        Third.lockTheGates();
    }
}


//What will happen when the following code is compiled and run?
class AX {
    static int[] x = new int[0];

    static {
//        x[0] = 10;
    }

    public static void main(String[] args) {
        var ax = new AX();
    }
}

//Consider the following code:  public
class Logger {
    private StringBuilder sb = new StringBuilder();

    public void logMsg(String location, String message) {
        sb.append(location);
        sb.append("-");
        sb.append(message);
    }

    public void dumpLog() {
        System.out.println(sb.toString());     //Empty the contents of sb here
        sb.delete(0, sb.length());
        System.out.println(sb.toString());
    }
}
// Which of the following options will empty the contents of the StringBuilder referred to by variable sb in method dumpLog()?


//Which statements, when inserted in the code below, will cause an exception at run time?
class B16 {
}

class B1 extends B16 {
}

class B2 extends B16 {
}

class ExtendsTest {
    public static void main(String args[]) {
        B16 b = new B16();
        B1 b1 = new B1();
        B2 b2 = new B2();      // insert statement here
    }
}

class Writer16_43 {
    private static final int LOOPSIZE = 5;

    public synchronized void write(Data16_43... da) {
        for (int i = 0; i < LOOPSIZE; i++) {
            while (!da[0].own(this));
            while (!da[1].own(this));
            da[0].write();
            da[1].write();
            da[1].release();
            da[0].release();
        }
    }
}

class Data16_43 {
    private Writer16_43 writer;

    public synchronized boolean own(Writer16_43 w) {
        if (writer == null) {
            writer = w;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        writer = null;
    }

    public synchronized void write() {
        System.out.println("writing by " + Thread.currentThread().getName());
    }
}

class TestClass16_43 {
    public static void main(String[] args) {
        Writer16_43 w1 = new Writer16_43();
        Writer16_43 w2 = new Writer16_43();

        Data16_43 d1 = new Data16_43();
        Data16_43 d2 = new Data16_43();

        new Thread(() -> w1.write(d1, d2), "T1").start();
        new Thread(() -> w2.write(d1, d2), "T2").start();

        System.out.println("Subject to starvation, not deadlock or livelock.");
    }
}


