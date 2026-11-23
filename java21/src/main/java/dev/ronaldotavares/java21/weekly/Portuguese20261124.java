package dev.ronaldotavares.java21.weekly;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Code snippets for the Portuguese weekly meeting on November 24, 2026.
 * Question logic is preserved; enclosing classes and portable temporary file
 * paths are the execution harness. The four msgs*.properties resources contain
 * the original question's bundle entries, including its intentional typo.
 */
public class Portuguese20261124 {
    public static void main(String[] args) throws Exception {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // 2.4038 — F: compact constructor transformation, record pattern, fall-through.
    private static void question1() {
        System.out.println("\nquestion1 — 2.4038");
        Student s = new Student(123, null);
        switch(s){
            case Student(var id, var subjects) : System.out.println(s);
            default : System.out.println("default");
        }
        // The array's identity suffix in toString() is not a fixed value.
    }

    record Student(int id, String... subjects) {
        public Student{
            if(id<0) throw new IllegalArgumentException();

            if( subjects == null || subjects.length == 0)  {
                subjects = new String[] {"english" };
            }
        }
    }

    // 2.3321 — A: reversed() reverses the complete comparator chain.
    private static void question2() {
        System.out.println("\nquestion2 — 2.3321");
        var books = new ArrayList<Book>(List.of(new Book("The Outsider", "fiction"),
                new Book("Becoming", "non-fiction"),new Book("Uri", "non-fiction")));

        books.sort(Comparator.comparing(Book::getGenre)
             .thenComparing(Book::getTitle).reversed());
        System.out.println(books);
    }

    public static class Book {
        private String title;
        private String genre;
        public Book(String title, String genre){
            this.title = title; this.genre = genre;
        }
        // Accessors and toString are explicitly omitted in the source question.
        public String getTitle() { return title; }
        public String getGenre() { return genre; }
        public String toString() { return genre + ":" + title; }
    }

    // 2.4018 — D: the French bundle inherits the correctly spelled root key;
    // currency formatting independently uses the default en_GB locale.
    private static void question3() {
        System.out.println("\nquestion3 — 2.4018");
        Locale original = Locale.getDefault();
        Locale originalDisplay = Locale.getDefault(Locale.Category.DISPLAY);
        Locale originalFormat = Locale.getDefault(Locale.Category.FORMAT);
        try {
            showBalance("Bob");
        } finally {
            // Harness cleanup only: do not change the caller's locale permanently.
            Locale.setDefault(original);
            Locale.setDefault(Locale.Category.DISPLAY, originalDisplay);
            Locale.setDefault(Locale.Category.FORMAT, originalFormat);
        }
    }

    public static void showBalance(String ownerName){
        Locale.setDefault(Locale.of("en", "GB"));
        ResourceBundle rb = ResourceBundle.getBundle("msgs", Locale.of("fr"));
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String message = rb.getString("accountinfo");
        message = MessageFormat.format(message, ownerName, nf.format(100));
        System.out.println(message);
    }

    // 2.3756 — D: writing the entire buffer retains 34 on the second iteration.
    private static void question4() throws IOException {
        System.out.println("\nquestion4 — 2.3756");
        Path directory = Files.createTempDirectory("weekly-3756-");
        Path input = directory.resolve("test1.txt");
        Path output = directory.resolve("test2.txt");
        try {
            Files.writeString(input, "01234567");
            // Only the two Windows-specific paths are adapted from the source.
            char[] buf = new char[5];
            try(   FileReader fr = new FileReader(input.toFile());
                   FileWriter fw = new FileWriter(output.toFile())){

                while( fr.read(buf) != -1){
                    fw.write(buf);
                }
            }
            System.out.println(Files.readString(output));
        } finally {
            Files.deleteIfExists(output);
            Files.deleteIfExists(input);
            Files.deleteIfExists(directory);
        }
    }

    // 2.1009 — B: the field is declared in Super, so Sub is not initialized.
    private static void question5() {
        System.out.println("\nquestion5 — 2.1009");
        System.out.println(Sub.ID);
    }

    static class Super { static String ID = "QBANK"; }

    static class Sub extends Super{
        static { System.out.print("In Sub"); }
    }

    // 2.4016 — B. The source explanation's final output block has a typo:
    // its second line must be "DvD: DvD playing", not "Media: DvD playing".
    private static void reserve1() {
        System.out.println("\nreserve1 — 2.4016");
        Media m = new DvD();
        DvD d = new DvD();
        CD c = new CD();
        play(m);
        play(d);
        play(c);
    }

    static interface Media{
        default void play(){
            System.out.println("Media playing");
        }
    }
    static class DvD implements Media{
        public void play(){
            System.out.println("DvD playing");
        }
    }
    static class CD implements Media{
        public void play(){
            System.out.println("CD playing");
        }
    }
    static void play(Media m){
        System.out.print("Media: ");
        m.play();
    }
    static void play(DvD d){
        System.out.print("DvD: ");
        d.play();
    }

    // 2.4044 — C: defaultReadObject restores id but not transient ld.
    private static void reserve2() throws IOException, ClassNotFoundException {
        System.out.println("\nreserve2 — 2.4044");
        // Only c:\temp\data.ser is replaced by a portable, temporary path.
        Path file = Files.createTempFile("weekly-4044-", ".ser");
        try {
            Data d = new Data(10, LocalDate.of(2024, 10, 10));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()));
            oos.writeObject(d); oos.close();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()))) {
                d = (Data) ois.readObject();
                System.out.println(d);
            }
        } finally {
            Files.deleteIfExists(file);
        }
    }

    static class Data implements Serializable{
        int id = 20;
        static LocalDate dummyDate = LocalDate.of(2000, 01, 01);
        transient LocalDate ld = LocalDate.of(2010, 01, 01);

        private void readObject(ObjectInputStream stream)
              throws IOException, ClassNotFoundException {
            System.out.println("in readObject before " +id+" "+ld);
            stream.defaultReadObject();
            System.out.println("in readObject after " + id+" "+ld);
            ld = dummyDate;
        }
        public Data(int id, LocalDate ld){
            this.id = id; this.ld = ld;
        }
        public String toString(){
            return "Data "+id+" "+ld;
        }
    }
}
