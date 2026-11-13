package dev.ronaldotavares.java21.weekly;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/** Code snippets for the English weekly meeting on November 19, 2026. */
public class English20261119 {
    public static void main(String[] args) throws IOException {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: the update runs before the condition is tested again.
    private static void question1() {
        System.out.println("\nquestion1");
        int i;
        int j;
        for (i = 0, j = 0; j < 1; ++j, i++) {
            System.out.println(i + " " + j);
        }
        System.out.println(i + " " + j);
    }

    // Main 2: overload resolution uses the declared parameter type.
    private static void question2() {
        System.out.println("\nquestion2");
        Flyer f = new AirPlane();
        AirPlane a = new AirPlane();
        flyIt(f); // 1
        flyIt(a); // 2
    }

    private interface Flyer { }

    private static class AirPlane implements Flyer { }

    private static void flyIt(Flyer f) {
        System.out.println("Flyer Flying");
    }

    private static void flyIt(AirPlane a) {
        System.out.println("AirPlane Flying");
    }

    // Main 3: C, D, and F are valid Locale declarations.
    private static void question3() {
        System.out.println("\nquestion3");
        Locale myLocale = Locale.getDefault(); // C
        System.out.println("C: " + myLocale);
        myLocale = Locale.US; // D
        System.out.println("D: " + myLocale);
        myLocale = new Locale("ru", "RU"); // F; valid in Java 21
        System.out.println("F: " + myLocale);

        // A: Locale myLocale = System.getDefaultLocale(); // no such method
        // B: Locale myLocale = Locale.getDefaultLocale(); // no such method
        // E: Locale myLocale = Locale.getInstance();      // no such method
    }

    // Main 4: write(int) writes one byte, even on DataOutputStream.
    private static void question4() throws IOException {
        System.out.println("\nquestion4");
        Path optionA = Files.createTempFile("weekly-1090-a-", ".bin");
        Path optionB = Files.createTempFile("weekly-1090-b-", ".bin");
        Path optionC = Files.createTempFile("weekly-1090-c-", ".bin");
        try {
            createFileOptionA(optionA.toString());
            createFileOptionB(optionB.toString());
            createFileOptionC(optionC.toString());
            System.out.println("A: " + Files.size(optionA) + " byte, not 4");
            System.out.println("B: " + Files.size(optionB) + " byte");
            System.out.println("C: " + Files.size(optionC) + " byte");
        } finally {
            Files.deleteIfExists(optionA);
            Files.deleteIfExists(optionB);
            Files.deleteIfExists(optionC);
        }
        // D: OutputStream has no writeInt(int) method.
    }

    private static void createFileOptionA(String name) throws IOException {
        try (OutputStream os = new FileOutputStream(name)) {
            DataOutputStream dos = new DataOutputStream(os);
            dos.write(1);
            dos.close();
        }
    }

    private static void createFileOptionB(String name) throws IOException {
        try (OutputStream os = new FileOutputStream(name)) {
            os.write(1);
        }
    }

    private static void createFileOptionC(String name) throws IOException {
        try (OutputStream os = new FileOutputStream(name)) {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            dos.write(1);
            dos.close(); // Flush and close all streams opened above.
        }
    }

    // Main 5: the source is intentionally invalid; retain it verbatim.
    private static void question5() {
        System.out.println("\nquestion5");
        System.out.println("""
                interface Identifier{
                    int id();
                }

                sealed class Person
                    permits Student  //LINE A
                {
                }

                record Student(int id,
                        String subject)

                        extends Person //LINE B

                        implements Identifier //LINE C
                {
                   public static final long serialVersionUID  = 1L; //LINE D

                   private String name = "unknown"; //LINE E

                   String name(){ return "unknown"; } //LINE F

                   public int id(){ return id; } //LINE G
                }
                """);
        // A and B fail because a record cannot extend Person; E is an instance field.
        // To try a valid variation in the IDE, make Person a sealed interface:
        ValidPerson student = new ValidStudent(123, "Java");
        System.out.println("Valid variation: " + student);
    }

    private interface Identifier {
        int id();
    }

    private sealed interface ValidPerson permits ValidStudent { }

    private record ValidStudent(int id, String subject)
            implements ValidPerson, Identifier { }

    // Reserve 1: the comparator compares lengths, not contents.
    private static void reserve1() {
        System.out.println("\nreserve1");
        String[] sa = {"d", "bbb", "aaaa"};
        System.out.println(Arrays.binarySearch(sa, "cc", new MyStringComparator()));
        System.out.println(Arrays.binarySearch(sa, "c", new MyStringComparator()));
    }

    @SuppressWarnings("rawtypes")
    private static class MyStringComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            int s1 = ((String) o1).length();
            int s2 = ((String) o2).length();
            return s1 - s2;
        }
    }

    // Reserve 2: parallel reduction may apply the identity twice.
    private static void reserve2() {
        System.out.println("\nreserve2");
        List<String> vals = Arrays.asList("a", "b");
        String join = vals.parallelStream()
                .reduce("_", (a, b) -> a.concat(b));
        System.out.println(join);
        // Depending on partitioning, this can print _ab or _a_b.
    }
}
