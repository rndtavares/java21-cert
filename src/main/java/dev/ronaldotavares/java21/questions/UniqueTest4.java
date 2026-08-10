package dev.ronaldotavares.java21.questions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Stream;

public class UniqueTest4 {
    public static void main(String[] args) {
        var uniqueTest4 = new UniqueTest4();
        uniqueTest4._5();
        uniqueTest4._6();
        uniqueTest4._7();
        uniqueTest4._8();
        uniqueTest4._9();
        uniqueTest4._11();
        uniqueTest4._13();
        uniqueTest4._15();
        uniqueTest4._18();
        uniqueTest4._20();
        uniqueTest4._21();
        uniqueTest4._22();
        uniqueTest4._31();
        uniqueTest4._34();
        uniqueTest4._37();
        uniqueTest4._39();
        uniqueTest4._41();
        uniqueTest4._50();
    }

    private void _5() {
        System.out.println("question 5");
        String fullPhoneNumber = "123-456-7890";
        System.out.println(hidePhoneB(fullPhoneNumber));
        System.out.println(hidePhoneD(fullPhoneNumber));
//        String mask = "xxx-xxx-";
//        mask.append(fullPhoneNumber.substring(8));
//        return mask;
        System.out.println(new StringBuilder(fullPhoneNumber).replace(0, 8, "xxx-xxx-").toString());

        StringBuilder usingInsert = new StringBuilder("xxx-xxx-");
        usingInsert.insert(8, fullPhoneNumber, 8, 12);
        System.out.println("usingInsert: " + usingInsert);

        StringBuilder usingReplace = new StringBuilder(fullPhoneNumber);
        usingReplace.replace(0, 8, "xxx-xxx-");
        System.out.println("usingReplace: " + usingReplace);

        StringBuilder usingAppend = new StringBuilder("xxx-xxx-");
        usingAppend.append(fullPhoneNumber, 8, 12);
        System.out.println("usingAppend: " + usingAppend);

        StringBuilder original = new StringBuilder(fullPhoneNumber);
        String usingSubstring = "xxx-xxx-" + original.substring(8, 12);
        System.out.println("usingSubstring: " + usingSubstring);
    }

    private void _6() {
        System.out.println("question 6");
//        package days;
//        public sealed class WeekDay permits Monday {}
//        non-sealed class Monday extends WeekDay {}
        WeekDayU4 monday = new MondayU4();
        System.out.println(monday.getClass().getSimpleName());
    }

    private void _7() {
        System.out.println("question 7");
        double principle = 100;
        int interestrate = 5;
        double amount1 = CalculatorU4.computeA(principle, x -> x * interestrate);
        double amount2 = CalculatorU4.computeD(principle, x -> x * interestrate);
//        double amount3 = CalculatorU4.computeC(principle, x -> x * interestrate);
        System.out.println(amount1);
        System.out.println(amount2);
    }

    private void _8() {
        System.out.println("question 8");
        Locale locale = Locale.of("en", "US");
        ResourceBundle rb = new MyBundleU4();
        Object obj = rb.getObject("key1");
        String[] vals = rb.getStringArray("key2");
//        String str = rb.getObject("key1");
//        Object invalid = rb.getValue("key3");
//        Object invalidKey = rb.getObject(1);
        System.out.println(locale);
        System.out.println(obj);
        System.out.println(Arrays.toString(vals));
    }

    private void _9() {
        System.out.println("question 9");
//        open module m1 {}
//        module m2 { opens pkg; }
//        module m3 { opens pkg to other.module; }
        System.out.println("Correct statements: open/opens allow reflection; modules are strongly encapsulated by default; modular jars can be used on classpath.");
    }

    private void _11() {
        System.out.println("question 11");
//        Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("If target is a symbolic link, REPLACE_EXISTING replaces the link itself.");
        System.out.println("If source is a symbolic link, the final target is copied by default.");
        try {
            Path p1 = Files.createTempFile("unique-test4-source", ".txt");
            Path p2 = Files.createTempFile("unique-test4-target", ".txt");
            Files.writeString(p1, "source");
            Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(Files.readString(p2));
            Files.deleteIfExists(p1);
            Files.deleteIfExists(p2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void _13() {
        System.out.println("question 13");
        AccountU4 account = new AccountU4("A1", 100);
        account.withdraw(25);
        System.out.println(account.getBalance());
    }

    private void _15() {
        System.out.println("question 15");
        ParamTestU4.main(null);
    }

    private void _18() {
        System.out.println("question 18");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
//        pw.writeBoolean(true);
//        pw.write(true);
        pw.print(true);
        pw.print(" ");
        pw.println(true);
        pw.flush();
        System.out.print(sw);
    }

    private void _20() {
        System.out.println("question 20");
//        class BookList extends ArrayList<Book> {
//            public boolean add(Object o) {
//                if (o instanceof Book b) return super.add(b);
//                else return count++ == -1;
//            }
//        }
//        Does not compile: add(Object) and add(Book) have the same erasure, yet neither overrides the other.
        BookListU4 list = new BookListU4();
        System.out.println(list.add(new BookU4_20()));
        System.out.println(list.add(new TextBookU4_20()));
        System.out.println(list.addIfBook("hello"));
        System.out.println(list.count);
    }

    private void _21() {
        System.out.println("question 21");
        GroupedValuesU4 groupedValues = new GroupedValuesU4();
        groupedValues.process("a", 10.0);
        groupedValues.process("a", 20.0);
        groupedValues.process("b", 30.0);
        System.out.println(groupedValues.groupedValues);
    }

    private void _22() {
        System.out.println("question 22");
//        module broker {
//            exports org.broker.api;
//            provides org.broker.api.Broker with org.broker.api.MyBroker;
//        }
        System.out.println("Other modules can provide the same service.");
        System.out.println("Keeping service API and provider in separate modules is usually cleaner.");
        System.out.println("Putting API and implementation together lets users depend on the implementation accidentally.");
    }

    private void _31() {
        System.out.println("question 31");
        String[] sa = {"charlie", "bob", "andy", "dave"};
        Collections.sort(Arrays.asList(sa), null);
        System.out.println(sa[0]);
    }

    private void _34() {
        System.out.println("question 34");
        float foo = 2, bar = 3, baz = 4;
        float mod1 = foo % baz, mod2 = baz % foo;
        float val = mod1 > mod2 ? bar : baz;
        System.out.println(val);
    }

    private void _37() {
        System.out.println("question 37");
        for (; Math.random() < 0.5;) {
            System.out.println("true");
            break;
        }
//        for (;; Math.random() < 0.5) {
//            System.out.println("true");
//        }
        for (int j = 0;; Math.random()) {
            System.out.println("true");
            if (j++ == 0) {
                break;
            }
        }
//        for (;;) {
//            Math.random() < .05 ? break : continue;
//        }
        for (;;) {
            if (Math.random() < .05 || true) break;
        }
    }

    private void _39() {
        System.out.println("question 39");
        Stream<Integer> strm1 = Stream.of(2, 3, 5, 7, 11, 13, 17, 19);
        Stream<Integer> strm2 = strm1.parallel().filter(i -> i > 5).filter(i -> i < 15).sequential();
        strm2.forEach(System.out::print);
        System.out.println();
    }

    private void _41() {
        System.out.println("question 41");
        var ca = new char[]{'a', 'b', 'c', 'd'};
        var i = 0;
        for (var c : ca) {
            switch (c) {
                case 'a' : i++;
                case 'b' : ++i;
                case 'c' | 'd' : i++;
            }
        }
        System.out.println("i = " + i);
        System.out.println("'c' | 'd' = " + (char) ('c' | 'd'));
    }

    private void _50() {
        System.out.println("question 50");
        LockThreadU4.lock = new ReentrantLock();
        LockThreadU4 t1 = new LockThreadU4("T1");
        t1.start();
        LockThreadU4 t2 = new LockThreadU4("T2");
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String hidePhoneB(String fullPhoneNumber) {
        return new StringBuilder("xxx-xxx-") + fullPhoneNumber.substring(8);
    }

    public static String hidePhoneD(String fullPhoneNumber) {
        return "xxx-xxx-" + fullPhoneNumber.substring(8, 12);
    }
}

sealed class WeekDayU4 permits MondayU4 {
}

non-sealed class MondayU4 extends WeekDayU4 {
}

class CalculatorU4 {
    public static double computeA(double base, Function<Integer, Integer> func) {
        return func.apply((int) base);
    }

    public static double computeD(double base, Function<Double, Double> func) {
        return func.apply(base);
    }

    public static double computeC(double base, Function<Double, Integer> func) {
        return func.apply(base);
    }
}

class MyBundleU4 extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][]{
                {"key1", "value1"},
                {"key2", new String[]{"value2a", "value2b"}}
        };
    }
}

class AccountU4 {
    private final String id;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    AccountU4(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(double amt) {
        try {
            lock.lock();
            if (balance > amt) {
                balance = balance - amt;
            }
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }
}

class ParamTestU4 {
    public static void printSum(double a, double b) {
        System.out.println("In double " + (a + b));
    }

    public static void printSum(float a, float b) {
        System.out.println("In float " + (a + b));
    }

    public static void main(String[] args) {
        printSum(1, 2.0);
        printSum(1, 2);
        printSum(1.0, 2.0);
    }
}

class BookU4_20 {
}

class TextBookU4_20 extends BookU4_20 {
}

class BookListU4 extends ArrayList<BookU4_20> {
    public int count = 0;

    public boolean addIfBook(Object o) {
//  public boolean add(Object o) {
        if (o instanceof BookU4_20 b) {
            return super.add(b);
        } else {
            return count++ == -1;
        }
    }
}

class GroupedValuesU4 {
    Map<String, List<Double>> groupedValues = new HashMap<>();

    public void process(String name, Double value) {
        groupedValues.computeIfAbsent(name, a -> {
            System.out.println(a);
            return new ArrayList<>();
        }).add(value);

        groupedValues.computeIfPresent(name, (a, b) -> {
            System.out.println(a + " " + b);
            return b;
        });
    }
}

class LockThreadU4 extends Thread {
    static Lock lock = new ReentrantLock();

    LockThreadU4(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            if (lock.tryLock()) {
                System.out.println(getName() + " got lock. " + getState());
            } else {
                System.out.println(getName() + " could not get lock. " + getState());
            }
        }
    }
}
