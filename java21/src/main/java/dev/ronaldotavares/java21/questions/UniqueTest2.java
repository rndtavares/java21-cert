package dev.ronaldotavares.java21.questions;

import java.io.*;
import java.text.DateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;

public class UniqueTest2 {
    public static void main(String[] args) {
        var uniqueTest2 = new UniqueTest2();
        uniqueTest2._6();
        uniqueTest2._8();
        uniqueTest2._15();
        uniqueTest2._23();
        uniqueTest2._24();
        uniqueTest2._43();
        uniqueTest2._46();
        uniqueTest2._52();
        uniqueTest2._53();
        uniqueTest2._54();
    }

    private void _6() {
        System.out.println("question 6");
        TestClassU2_6.main(null);
    }

    private void _8() {
        System.out.println("question 8");
        try {
            AU2_8.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void _15() {
        System.out.println("question 15");
        Book b1 = new Book("Java in 24 hrs", null);
        DoubleSupplier ds1 = b1::getPrice;
        try {
            System.out.println(b1.getTitle() + " " + ds1.getAsDouble());
        }catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    private void _23() {
        System.out.println("question 23");

//        Identify correct statement(s) about the following code:
//        var value = 1,000,000; //1
        var value = 1_000_000; //1
        switch (value) {
//            case 1_000_000 -> {  //2
            case 1000001 -> {  //2
                System.out.println("A million 1");
                break; //3
            }
            case 1000000 -> { //4
                System.out.println("A million 2");
            }
        }

        var result = switch (value) {
//            case 1_000_000 -> {  //2
            case 1000001 -> {  //2
                yield "A million 1";
//                break; //3
            }
            case 1000000 -> { //4
                yield "A million 2";
            }
            default -> {
                yield "Not a million";
            }
        };
    }

    private void _24() {
        System.out.println("question 24");
        try {
            FileCopierU2_24.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void _43() {
        System.out.println("question 43");
        Date dt = new Date();
//        LocalDate ld = LocalDate.now();
        Locale l = Locale.getDefault();
        DateFormat df = DateFormat.getDateInstance();
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.FULL);
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.FULL, new Locale.Builder().setLanguage("pt").setRegion("BR").build());
        DateFormat df4 = DateFormat.getInstance();

        System.out.println(l.getCountry()+" "+ df.format(dt));
        System.out.println(l.getCountry()+" "+ df2.format(dt));
        System.out.println(l.getCountry()+" "+ df3.format(dt));
        System.out.println(l.getCountry()+" "+ df4.format(dt));
//        System.out.println(l.getCountry()+" "+ df.format(ld));
    }

    private void _46() {
        System.out.println("question 46");
//        Given that listOfWords points to a List<String> of words, which of the following code snippets will create a list of two letter words?
        var listOfWords = List.of("apple", "banana", "orange", "hi", "kiwi", "mango", "ah", "pineapple", "uh", "watermelon");

        List<String> list1 = listOfWords.stream().filter(s -> s.length() == 2).parallel().collect(Collectors.toList());
        System.out.println(list1);

        List<String> list2 = listOfWords.stream().parallel().filter(s -> s.length() == 2).collect(Collectors.toList());
        System.out.println(list2);

        var list3 = new ArrayList<String>();
//        int i =0;
        listOfWords.parallelStream().forEach(s -> {
            if (s.length() == 2) {
                synchronized (list3) {
                    list3.add(s);
//                    i++;
                }
            }
        });
        System.out.println(list3);
    }

    private void _52() {
        System.out.println("question 52");
//        Given that New York is 3 hours ahead of Los Angeles, what will the following code print?
        ZoneId  nyZone = ZoneId.of("America/New_York");
        ZoneId  laZone = ZoneId.of("America/Los_Angeles");
        LocalDateTime ldt = LocalDateTime.of(2022, 12, 02, 6, 0, 0);
        ZonedDateTime nyZdt = ldt.atZone(nyZone);
        ZonedDateTime laZdt = ldt.atZone(laZone);
        System.out.println(nyZdt);
        System.out.println(laZdt);
        Duration d = Duration.between(nyZdt, laZdt);
        System.out.println(d);
    }

    private void _53() {
        System.out.println("question 53");
        try {
            SerialTest.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void _54() {
        System.out.println("question 54");
        MicroService.main(null);
    }
}


class TestClassU2_6 {
    static interface Media {
        default void play() {
            System.out.println("Media playing");
        }
    }

    static class ROM implements Media {
        public void play() {
            System.out.println("ROM playing");
        }
    }

    static class CdROM extends ROM implements Media {
    }

    static void play(Media m) {
        System.out.print("Media: ");
        m.play();
    }

    static void play(CdROM d) {
        System.out.print("CdROM: ");
        d.play();
    }

    public static void main(String[] args) {
        ROM r1 = new ROM();
        Media r2 = new CdROM();
        play(r1);
        play(r2);
    }
}


//Consider the following code:
class AU2_8 {
    public void doA(int k) throws Exception {  // 0
        for(int i=0; i< 10; i++) {
//            try {
                if (i == k) throw new Exception("Index of k is " + i); // 1
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
        }
    }
    public void doB(boolean f) throws Exception { // 2
        if(f) {
            doA(15); // 3
        }
        else return;
    }
    public static void main(String[] args) throws Exception { // 4
        AU2_8 a = new AU2_8();
        a.doB(args.length>0); // 5
    }
}
//Which of the following statements are correct?


class Book {
    private String title;
    private Double price;

    public Book(String title, Double price) {
        this.title = title;
        this.price = price;
    }
    //accessor methods not shown  What will the following code print when compiled and run?
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }
}


//Consider the following code:
class FileCopierU2_24 {
    public static void copy(String records1, String records2) throws IOException {
        try (InputStream is = new FileInputStream(records1); OutputStream os = new FileOutputStream(records2);) {
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
                System.out.println("Read and written bytes " + bytesRead);
            }
        } catch (IOException | IndexOutOfBoundsException e) {
//            e = new FileNotFoundException();
//            e.printStackTrace();
            System.out.println(e);
        } catch (Exception e) {
            e = new FileNotFoundException();
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        copy("c:\\temp\\test1.txt", "c:\\temp\\test2.txt");
    }
}
//Assuming appropriate import statements and the existence of both the files, what will happen when the program is compiled and run?


//What will be the output when the following code is compiled and run?  
class PersonU2_53 implements Serializable {
    private String name;

    PersonU2_53(String name) {
        this.name = name;
        System.out.print("Person ");
    }

    public String toString() {
        return name;
    }
}

class StudentU2_53 extends PersonU2_53 {
    public String school;

    public StudentU2_53(String name, String school) {
        super(name);
        this.school = school;
        System.out.print("Student ");
    }

    public String toString() {
        return super.toString() + " " + school;
    }
}

class SerialTest {
    public static void main(String[] args) throws Exception {
        PersonU2_53 p = new StudentU2_53("Bob Dylan", "NYU");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\temp\\student.ser")); ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:\\temp\\student.ser"));) {
            oos.writeObject(p);
            oos.flush();
            System.out.println((PersonU2_53) ois.readObject());
        }
    }
}


class InternalException extends RuntimeException {
    public InternalException(String message) {
        super(message);
    }
}

class ExternalException extends Exception {
    public ExternalException(String message) {
        super(message);
    }
}

class MicroService implements AutoCloseable {
    private String name;

    public MicroService(String name) {
        this.name = name;
        System.out.println(name + " started");
    }

    public void availService(String name) {
        if (!this.name.equals(name)) {
            throw new InternalException("Unknown service " + name);
        }
    }

    @Override
    public void close() throws ExternalException {
        if (name.equals("X")) {
            throw new ExternalException("Can't close X service");
        }
        System.out.println(name + " closed");
    }

    public static void main(String[] args) {
        try (MicroService ms = new MicroService("X")) {
            ms.availService("test");
        } catch (Exception e) {
            System.out.println(e);
            for (Throwable t : e.getSuppressed()) {
                System.out.println(t);
            }
        }
    }
}
//What will be the result?