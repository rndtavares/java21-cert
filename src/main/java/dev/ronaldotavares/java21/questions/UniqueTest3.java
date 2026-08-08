package dev.ronaldotavares.java21.questions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UniqueTest3 {
    public static void main(String[] args) {
        var uniqueTest3 = new UniqueTest3();
        uniqueTest3._1();
        uniqueTest3._4();
        uniqueTest3._7();
        uniqueTest3._12();
        uniqueTest3._13();
        uniqueTest3._14();
        uniqueTest3._18();
        uniqueTest3._22();
        uniqueTest3._24();
        uniqueTest3._39();
        uniqueTest3._42();
        uniqueTest3._43();
        uniqueTest3._45();
        uniqueTest3._46();
    }

    private void _1() {
        System.out.println("question 1");
        var i = 4;
        int[][][] ia = new int[i][i = 3][i];
        System.out.println(ia.length + ", " + ia[0].length + ", " + ia[0][0].length);
        System.out.println(Arrays.deepToString(ia));
    }

    private void _4() {
        System.out.println("question 4");
//        Map<Object, ? super ArrayList> m = new LinkedHashMap<>();
//        m.put("1", new ArrayList());
//        m.put(1, new Object());
//        m.put(1.0, "Hello");

//        Map m = new TreeMap();
//        m.put("1", new ArrayList());
//        m.put(1, new Object());
//        m.put(1.0, "Hello"); // ClassCastException, keys are not mutually comparable.

        Map m = new HashMap();
//        Map<Object, ?> m = new LinkedHashMap<Object, Object>();
        m.put("1", new ArrayList());
        m.put(1, new Object());
        m.put(1.0, "Hello");
        System.out.println(m);
    }

    private void _7() {
        System.out.println("question 7");
        List<BookU3> books = getBooksByAuthor("Ludlum");
        try {
            books.stream().sorted().forEach(b -> System.out.println(b.getIsbn()));
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }

    private void _12() {
        System.out.println("question 12");
        File file = new File(System.getProperty("java.io.tmpdir"), "unique-test3-1402.txt");
        System.out.println("exists before create: " + file.exists());
        try {
            System.out.println("created: " + file.createNewFile());
            System.out.println("exists after create: " + file.exists());
            System.out.println("deleted: " + file.delete());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void _13() {
        System.out.println("question 13");
        try {
            DeviceU3.main(null);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("suppressed: " + suppressed);
            }
        }
    }

    private void _14() {
        System.out.println("question 14");
//        module abc.print {
//            requires org.pdf;
//            provides org.pdf.Print with com.abc.print.SimplePrintImpl;
//            provides org.pdf.Print with com.abc.print.ComplexPrintImpl;
//        }
//
//        ServiceLoader<Print> psLoader = ServiceLoader.load(Print.class);
//        for (Print p : psLoader) {
//            p.print("Hello");
//        }
        System.out.println("Does not compile: more than one provides directive specifies the same service.");
        System.out.println(ServiceLoader.class.getSimpleName());
    }

    private void _18() {
        System.out.println("question 18");
        var books = List.of(
                new BookU3("0001", "The Outsider", 2.99),
                new BookU3("0002", "Where the Crawdads Sing", 4.99),
                new BookU3("0003", "Elevation", 2.99),
                new BookU3("0004", "Coffin from Hong Kong", 1.99));
        Stream<BookU3> bkStrm = books.stream();

        double total = bkStrm.map(b -> b.getPrice()).reduce(0.0, (a, b) -> {
            return a + b;
        });
        System.out.println(total);

        DoubleBinaryOperator dbo = (a, b) -> a + b;
        double equivalentTotal = books.stream().mapToDouble(b -> b.getPrice()).reduce(0.0, dbo);
        System.out.println(equivalentTotal);

        double equivalentTotal2 = books.stream().map(b->b.getPrice())
                .reduce((a, b)->{ return a+b;})
//                .ifPresent(p->p.doubleValue());
                .get();
        System.out.println(equivalentTotal2);

        BinaryOperator<Double> bo =(a, b)->a+b;
        double equivalentTotal3 = books.stream()
                /*.mapToDouble(b->b.getPrice())*/
                .map(b->b.getPrice())
                .reduce(0.0, bo);
        System.out.println(equivalentTotal3);

        DoubleBinaryOperator dbo1 =(a, b)->a+b;
//        double equivalentTotal4 = bkStrm.mapToDouble(b->b.getPrice()).reduce(dbo1).get();
        double equivalentTotal4 = books.stream().mapToDouble(b->b.getPrice()).reduce(dbo1).getAsDouble();
        System.out.println(equivalentTotal4);
    }

    private void _22() {
        System.out.println("question 22");
//        A modular jar can still be placed on the classpath by non-modular applications.
        System.out.println("Non-modular apps can use the modular jar on the classpath without changes.");
    }

    private void _24() {
        System.out.println("question 24");
        OfficeU3 off = new HomeOfficeU3();
//        System.out.println(off.getAddress()); // illegal static interface method call.
        System.out.println(OfficeU3.getAddress());
        System.out.println(((HomeOfficeU3) off).getAddress());
    }

    private void _39() {
        System.out.println("question 39");
        try {
            System.out.println(getDateString(LocalDateTime.now()));
        } catch (UnsupportedTemporalTypeException e) {
            System.out.println(e);
        }
    }

    private void _42() {
        System.out.println("question 42");
        List<Integer> ls = List.of(11, 11, 22, 33, 33, 55, 66);
//        System.out.println(ls.stream().anyMatch(44));
//        System.out.println(ls.stream().anyMatch(11));
        System.out.println(ls.stream().distinct().anyMatch(x -> x == 11));
//        System.out.println(ls.stream().distinct().allMatch(11));
        System.out.println(ls.stream().noneMatch(x -> x % 11 > 0));
    }

    private void _43() {
        System.out.println("question 43");
        var b = false;
        var i = 1;
        do {
            i++;
        } while (b = !b);
        System.out.println(i);
    }

    private void _45() {
        System.out.println("question 45");
//        Exception e = null;
//        throw e; // Does not compile without a throws clause or try/catch.
        try {
            throwNullCheckedException();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void _46() {
        System.out.println("question 46");
        var nums1 = List.of(1, 2, 3, 4).stream();
        double average1 = nums1
//                .mapToInt(i -> i)
//                .mapToLong(i -> i)
//                .mapToDouble(i -> i)
//                .mapToObj(Integer::valueOf)
                .collect(Collectors.averagingInt(i -> i));
        System.out.println(average1);

        var nums2 = List.of(1, 2, 3, 4).stream();
        double average2 = nums2.parallel().mapToDouble(i -> i).average().getAsDouble();
        System.out.println(average2);
    }

    private List<BookU3> getBooksByAuthor(String author) {
        return List.of(
                new BookU3("978-0553566032", "The Bourne Identity", 9.99),
                new BookU3("978-0553260114", "The Matarese Circle", 8.99));
    }

    private String getDateString(LocalDateTime ldt) {
        return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(ldt);
    }

    private void throwNullCheckedException() throws Exception {
        Exception e = null;
        throw e;
    }
}

class DeviceU3 implements AutoCloseable {
    String header = null;

    public void open() throws IOException {
        header = "OPENED";
        System.out.println("Device Opened");
        throw new IOException("Unknown");
    }

    public String read() throws IOException {
        return "";
    }

    public void close() {
        System.out.println("Closing device");
        header = null;
        throw new RuntimeException("rte");
    }

    public static void main(String[] args) throws Exception {
        try (DeviceU3 d = new DeviceU3()) {
            throw new Exception("test");
        }
    }
}

class BookU3 {
    private final String isbn;
    private final String title;
    private final Double price;

    BookU3(String isbn, String title, Double price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    BookU3(String title, Double price) {
        this("", title, price);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }
}

interface HouseU3 {
    default String getAddress() {
        return "101 Main Str";
    }
}

interface OfficeU3 {
    static String getAddress() {
        return "101 Smart Str";
    }
}

interface WfhU3 extends HouseU3, OfficeU3 {
    private boolean isOffice() {
        return true;
    }
}

class HomeOfficeU3 implements HouseU3, OfficeU3 {
    public String getAddress() {
        return "R No 1, Home";
    }
}

class LockThreadU3 extends Thread {
    static Lock lock = new ReentrantLock();

    LockThreadU3(String name) {
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
