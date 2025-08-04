package dev.ronaldotavares.java21.questions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PracticeQuestions {
    public static void main(String[] args) {
        var practiceQuestions = new PracticeQuestions();

        practiceQuestions.concurrencyAtomicVsVolatileQuestion();
        practiceQuestions.enumValueOfQuestion();
        practiceQuestions.streamReduceQuestion();
        practiceQuestions.stringComparisonQuestion();
        practiceQuestions.virtualThreadsQuestion();
        practiceQuestions.moduleVisibilityQuestion();
        practiceQuestions.nestedLoopQuestion();
        practiceQuestions.tryWithResourcesQuestion();
        practiceQuestions.localDateTimeNYDaylightSavingQuestion();
        practiceQuestions.overloadMethodQuestion();
        practiceQuestions.switchPatternMatchingQuestion();
        practiceQuestions.jImageQuestion();
        practiceQuestions.ioBufferedWriterQuestion();
        practiceQuestions.sequencedCollectionQuestion();
        practiceQuestions.serializationQuestion();

    }

    void concurrencyAtomicVsVolatileQuestion() {
        System.out.println("Q - What will be printed when the following CONCURRENCY code is executed?");
        ConcurrencyAtomicVsVolatile.main(null);
        System.out.println("""
                Explanation:
                    Because volatileCounter++ is not atomic, it's possible (though less likely with such small loop counts) 
                    to see interleaved and potentially incorrect results for volatileCounter. AtomicInteger guarantees atomic 
                    increments, so atomicCounter will always be correct.
                """);
    }

    void enumValueOfQuestion() {
        System.out.println("Q - What will be printed when the following ENUM code is executed?");
        EnumValueOf.main(null);
        System.out.println("""
                Explanation:
                    The EnumValueOf class defines an enum Seasons with constants SPRING, SUMMER, and WINTER. 
                    The toString method is overridden to return the lowercase version of the enum name. 
                    The main method iterates through the Seasons enum values, calling the processEnumValue method with the 
                    lowercase string representation of each enum value. The processEnumValue method attempts to convert the 
                    lowercase string back to a Seasons enum value using Seasons.valueOf(enumString). 
                    Because the valueOf method expects the enum constant names to be in uppercase, an IllegalArgumentException 
                    is always thrown, and "INVALID," is printed for each enum value.
                """);
    }

    void streamReduceQuestion() {
        System.out.println("Q - What will be printed when the following STREAM code is executed?");
        StreamReduce.main(null);
        System.out.println("""
                Explanation:
                    The StreamReduce class initializes a list of strings items with the values "Candy" and "Gum". 
                    The code then performs a reduce operation on a parallel stream.
                    Here's a step-by-step breakdown:
                    Inner reduce:
                    It starts with an identity of "" (empty string).
                    It concatenates all elements of the items list. The result of the inner reduce is "CandyGum".
                    Outer reduce:
                    The outer reduce now uses the items.parallelStream().reduce(...) as identity, which is "CandyGum"
                    In parallel stream processing, identity is applied to multiple elements in the stream.
                    So, it effectively does: "CandyGum" + "Candy" + "CandyGum" + "Gum".
                    Therefore, the final output is "CandyGumCandyCandyGumGum".
                """);
    }

    void stringComparisonQuestion() {
        System.out.println("Q - What will be printed when the following STRING INTERN code is executed?");
        StringComparison.main(null);
        System.out.println("""
                Explanation:
                    String s1 is created using a text block.
                    String s2 is created using a string literal.
                    String s3 is created by interning s1.
                    s1 == s2 will return false because they are different objects in memory because their literal are different
                    text blocks include line break at the end of a line (/n), s1.equals(s2) will return also false.
                    s1 == s3 will return true as they are the same object in memory, s1.equals(s2) will return also true.
                    s2 == s3 will return false by the same reason s1 and s2 are different.
                """);
    }

    void virtualThreadsQuestion() {
        System.out.println("""
                            Q - Which of the following statements about virtual threads is TRUE?
                            
                            A) Virtual threads must remain permanently attached to a platform thread.
                            B) Virtual threads are always daemon threads.
                            C) Virtual threads can have their priority changed after creation.
                            D) Virtual threads will keep the JVM alive after the main() method ends.
                            
                            Explanation:
                            A) FALSE - Virtual threads do not remain permanently attached to a platform thread.
                               They borrow a carrier thread (a type of platform thread) only while they are running.
                            B) TRUE - Virtual threads are always daemon threads.
                               This means the JVM can exit if only virtual threads are running.
                            C) FALSE - The priority of a virtual thread is always 5 (Thread.NORM_PRIORITY) and cannot be changed.
                               Calling setPriority() on a virtual thread has no effect.
                            D) FALSE - Since virtual threads are daemon threads, they do not keep the JVM alive.
                               Only non-daemon threads can prevent the JVM from shutting down after main() finishes.
                            """);
    }

    void moduleVisibilityQuestion(){
        System.out.println("""
                            Q - Which THREE of the following statements about module visibility are TRUE?
                            
                            A) A named module on the module path can read from JARs on the classpath.
                            B) An automatic module exports all its packages to named and automatic modules.
                            C) An unnamed module can read from any module on the module path.
                            D) A JAR with a module-info.java file on the classpath is treated as a named module.
                            E) An unnamed module exports no packages to named modules.
                            F) An automatic module must have a module-info.java file in its JAR.
                
                            Explanation:
                            A) FALSE - Named modules on the module path CANNOT read from the classpath.
                               Only unnamed modules (on the classpath) can access both classpath and module path.
                
                            B) TRUE - Automatic modules export ALL packages automatically to both named and automatic modules.
                               They behave as if all packages were declared 'exports' in a module-info.java file.
                
                            C) TRUE - Unnamed modules can read from any JARs on the classpath OR module path.
                               They have wide visibility, though they export nothing to named modules.
                
                            D) FALSE - A JAR with a module-info.java file placed on the classpath is NOT treated as a named module.
                               It is treated as an unnamed module, and the module-info.java is ignored.
                
                            E) TRUE - An unnamed module exports NO packages to named modules.
                               This means named modules cannot access code from unnamed modules.
                
                            F) FALSE - Automatic modules DO NOT have a module-info.java file.
                               That’s what makes them 'automatic'. Their module name is inferred by the JAR name or manifest.
                
                            Correct answers: B, C and E.
                            """);
    }

    void nestedLoopQuestion() {
        System.out.println("Q - What will be the final value of 'result' after the following code is executed?");
        NestedLoop.main(null);
        System.out.println("""
                Explanation:
                    The outer loop iterates from i = 0 to 2 (3 iterations). The inner loop iterates from j = 3 to 0 (4 iterations).
                    If i is equal to 1, the 'continue outerLoop;' statement skips the rest of the inner loop
                    and continues with the next iteration of the outer loop. Otherwise, the sum of i and j is added to the result.
                    - When i = 0, result += 0 + 2 + 0 + 1 + 0 + 0 + 0 + (-1) = 2
                    - When i = 1, i is incremented by 1, so i = 2 and the inner loop is skipped.
                    - The first statement evaluated in the for loop is the increment of i, which is now 3, so the loop condition is
                    evaluated to false and the outer loop ends and so the execution.
                    The final result is 2.
                """);
    }

    void tryWithResourcesQuestion() {
        System.out.println("Q - What will be printed when the following TRY WITH RESOURCES code is executed?");
        try {
            TryWithResources.main(null);
        } catch (Exception e){
            System.out.println(e);
            for(Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed);
            }
        }
        System.out.println("""
                Explanation:
                    In this scenario, `resource1` is declared outside the try-with-resources block and is only referenced within the try block.
                    This is fine because a variable used as a try-with-resources resource should be final or effectively final.
                    Both `resource1` and `resource2` throw exceptions in their `operation()` and `close()` methods.
                    The exception thrown by `resource1.operation()` is not caught by the catch block because the thrown exception was a RuntimeException
                    and the exception in the catch block is an IOException.
                    The exceptions thrown by `resource2.close()` and `resource1.close()` respectively are suppressed and added to the suppressed exceptions of the exception throwed.
                    The TryWithResources's catch block is never executed.
                    The tryWithResourcesQuestion's method catch block catches the RuntimeException thrown and print it and it's supressed IOExceptions 
                """);
    }

    void localDateTimeNYDaylightSavingQuestion() {
        System.out.println("Q - What will be printed when the following LOCALDATETIME code is executed?");
        LocalDateTimeNYDaylightSaving.main(null);
        System.out.println("""
                Explanation:
                    The code creates two ZonedDateTime objects, zdt1 and zdt2, zdt1 is initialized to November 2, 2025, at 01:00 AM in the America/New_York time zone.
                    The code then adds one hour to zdt1, resulting in zdt2.
                    November 2, 2025, is the date when daylight saving time ends in the America/New_York time zone.
                    At 2:00 AM, the clock is turned backward to 1:00 AM.
                    Therefore, adding one hour to 01:00 AM on that date results in 01:00 AM due to the DST transition.
                    The code compares the hour of zdt1 (01) with the hour of zdt2 (01). Since 01 is equal to 01, the output is true.
                """);
    }

    void overloadMethodQuestion() {
        System.out.println("Q - What will be printed when the following OVERLOAD code is executed?");
        Overload.main(null);
        System.out.println("""
                Explanation:
                    The code defines a class Overload with overloaded methods.
                    When Overload.main is executed, it calls the overloaded methods with different parameters.
                    The method with the most specific matching parameter types will be called.
                    The call method(1.0, 2.0) invokes the method(double... a) because java converts 1.0 and 2.0 to double by default.
                    The call method(1, 2.0F) cause a compilation error because it is ambiguous. Both method(int,double) and method(float,float) match
                    The call method(2.0F, 1) invokes the method(float a, float b) because java converts int to float by default.
                """);
    }

    void switchPatternMatchingQuestion() {
        System.out.println("Q - What will be printed when the following SWITCH code is executed?");
        SwitchPatternMatching.main(null);
        System.out.println("""
            Explanation:
                The code defines a class SwitchPatternMatching with methods that uses switch expression and statement pattern matching.
                The switch checks the type of the input object and performs different actions based on the type.
                - If the input is null, it returns "wrong date".
                - If the input is a LocalDate object and is in the future, it returns "future".
                - If the input is a LocalDate object and is in the past, it returns "past".
                - If the input is a LocalDate object and is equal to today, it returns "today".
                - Otherwise, it returns "wrong date".
                The main method calls switchExpressionPatternMatching and switchStatementPatternMatching methods 
                with different inputs and prints the results.
            """);
    }

    void jImageQuestion() {
        System.out.println("""
                Q - Which of the following can be stored in a JImage file?

                A) Compiled Java class files (.class files)
                B) Resource files (.properties, .txt, etc.)
                C) Native libraries (.dll, .so, .dylib)
                D) All of the above
                """);

        System.out.println("""
                            Explanation:
                            A) True - Compiled Java class files (.class files) can be stored in a JImage file.
                            B) True - Resource files (.properties, .txt, etc.) can be stored in a JImage file.
                            C) False - Native libraries (.dll, .so, .dylib) cannot be directly stored in a JImage file. 
                            JImage is primarily for Java class files and resource files.
                            Correct answer: A and B
                            """);

        System.out.println("""
                JImage is a tool and file format in Java used for storing the modular runtime image. 
                It's essentially an archive that contains compiled Java class files (.class files), 
                resource files (.properties, .txt, etc.), and other necessary components for the Java runtime environment. 
                It is not used to store native libraries (.dll, .so, .dylib).
                """);
    }

    void ioBufferedWriterQuestion() {
        System.out.println("Q - What will be the output of the following IO code?");
        IOBufferedWriter.main(null);
        System.out.println("""
                Explanation:
                    The code reads from `input.txt` and writes to `output.txt` using buffered streams.
                    The output is `speedboatd` instead of `speedboat` because of the `write` method used in conjunction with the buffer size.
                    The `IOBufferedWriter` class reads from `input.txt` and writes to `output.txt` using buffered streams. 
                    The `input.txt` file contains the text "speedboat". 
                    The `BufferedReader` reads the input in chunks of 5 characters due to `batchSize = 5`.
                    The `while` loop reads the input in chunks:
                    1.  First iteration: reads "speed"
                    2.  Second iteration: reads "boatd"
                    The `writer.write(buffer)` method writes the entire buffer content to the file. 
                    Because the `input.txt` contains "speedboat" (length 9), the `reader.read` method in the second iteration reads "boatd". 
                    The `writer.write(buffer)` then writes "boatd" to the file.
                    Therefore, the final output in `output.txt` is "speedboatd".
                    The `outputString` variable is used to read the content of `output.txt` after the writing is complete.
                    The text block uses `String.format` to embed the value of `outputString` into the output.
                """);
    }

    void sequencedCollectionQuestion() {
        System.out.println("Q - Which of the following implementations of SequencedCollection will produce the sequence: a, f, c, e, d?");
        SequencedCollectionExample.main(null);
        System.out.println("""
                Explanation:
                    The code initializes a list with the elements "c", "b", "a", "e", "d".
                    It then performs operations on different SequencedCollection implementations.
                    - TreeSet sorts the elements, so it will not produce the desired sequence.
                    - LinkedHashSet maintains insertion order you can not change the order, it does not provide index base acess like List
                    - ArrayList maintains insertion order and allows adding/removing elements at the beginning/end (not at index base in this case).
                    - LinkedList maintains insertion order and allows adding/removing elements at the beginning/end .
                    One important thing to remeber is that the reference type of all options is SequencedCollection<String>, 
                    and only SequencedCollection methods can be used to change the elements (addFirst, addLast, removeFirst, removeLast),
                    or methods in the Collection interface because SequencedCollection extends Collection (add, addAll, remove, removeAll, removeIf, clear).
                    The correct option is ArrayList
                """);
    }

    void serializationQuestion() {
        System.out.println("Q - What will be the output of the following code after serializing and deserializing the object?");
        SerializationExample.main(null);
        System.out.println("""
                Explanation:
                    The code serializes an object of type `MyClass` to a file and then deserializes it back.
                    Here's a breakdown of the output:

                    Original object serialized: `MyClass{id=1, name='Test', age=25, count=1}`
                    - `id`: 1 (This value is from the constructor of `MyClass`)
                    - `name`: "Test" (Initialized in the constructor)
                    - `age`: 25 (Initialized in the constructor and marked as transient, so it won't be serialized)
                    - `count`: 1 (Static variable incremented in the constructor, static field also won't be serialized)

                    Deserialized object: `MyClass{id=10, name='Test', age=0, count=2}`
                    - `id`: 10 (The base class constructor without parameters is called during deserialization, initializing `id` to 10,
                    remember that the default constructor of the first class in the hierarchy that does not implements Serializable is called
                    and that the constructor of MyClass is not called during deserialization)
                    - `name`: "Test" (Successfully serialized and deserialized)
                    - `age`: 0 (Because `age` is `transient`, it's not serialized, and it defaults to 0)
                    - `count`: 2 (Static variable `count` is not serialized. The value is incremented to 1 when the first 
                    object is created and incremented to 2 when the second object is created, it's a class variable, so the last value will be printed)
                """);
    }
}

class ConcurrencyAtomicVsVolatile {
    static AtomicInteger atomicCounter = new AtomicInteger(0);
    static volatile int volatileCounter = 0;

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                volatileCounter++;
                System.out.println(Thread.currentThread().getName() + " " + volatileCounter);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                atomicCounter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " " + atomicCounter);
            }
        });

        thread1.setName("T1");
        thread2.setName("T2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class EnumValueOf{
    enum Seasons {
        SPRING, SUMMER, WINTER;

        static void processEnumValue(String enumString) {
            try {
                Seasons enumValue = Seasons.valueOf(enumString);
                System.out.print(enumValue + ",");
            } catch (Exception e) {
                System.out.print("INVALID,");
            }
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public static void main(String[] args) {
        for (int i=0; i < Seasons.values().length; i++) {
            Seasons.processEnumValue(Seasons.values()[i].toString());
        }
        System.out.println();
    }
}

class StreamReduce {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("Candy", "Gum");

        var result = items.parallelStream()
                .reduce(items.parallelStream()
                                .reduce("",
                                        (a, b) -> a + b),
                        (a, b) -> a + b);

        System.out.println(result);
    }
}

class StringComparison {
    public static void main(String[] args) {
        String s1 = """
                    hello
                    """;
        String s2 = "hello";
        String s3 = s1.intern();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
        System.out.println(s2 == s3);
        System.out.println(s2.equals(s3));
    }
}

class NestedLoop {
    public static void main(String[] args) {
        int result = 0;
        outerLoop:
        for (int i = 0; i < 3; i++) {
            int j = 3;
            while (j >= 0) {
                j--;
                if (i == 1) {
                    i++;
                    continue outerLoop;
                }
                result += i + j;
            }
        }
        System.out.println(result);
    }
}

class MyResource implements AutoCloseable {
    String resourceName;

    public MyResource(String resourceName) {
        this.resourceName = resourceName;
    }

    public void operation() {
        System.out.println("operating " + resourceName);
        throw new RuntimeException("Exception operating " + resourceName);
    }

    @Override
    public void close() throws IOException {
        System.out.println("closing " + resourceName);
        throw new IOException("Exception closing " + resourceName );
    }
}

class TryWithResources {
    public static void main(String[] args) {
        MyResource resource1 = new MyResource("r1");
        try (resource1;
             MyResource resource2 = new MyResource("r2")) {
            resource1.operation();
            resource2.operation();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSuppressed().length);
        }
    }
}

class LocalDateTimeNYDaylightSaving {
    public static void main(String[] args) {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime zdt1 = ZonedDateTime.of(LocalDateTime.of(2025, 11, 2, 1, 0), zone);
        ZonedDateTime zdt2 = zdt1.plusHours(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        System.out.println("date 1 " + dateFormatter.format(zdt1));
        System.out.println("date 2 " + dateFormatter.format(zdt2));
        System.out.println(zdt1.getHour() == zdt2.getHour());
    }
}

class Overload {
    static void method(int a, int b) {
        System.out.println("int, int");
    }

    static void method(int a, double b) {
        System.out.println("int, double");
    }

    static void method(float a, float b) {
        System.out.println("float, float");
    }

    static void method(double... a) {
        System.out.println("double...");
    }

    public static void main(String[] args) {
        method(1.0, 2.0);
//        method(1, 2.0F); // This line will cause a compilation error because it is ambiguous.
                        // Both method(int,double) and method(float,float) match
        method(2.0F, 1);
    }
}

class SwitchPatternMatching {

    static String switchExpressionPatternMatching(Object obj) {
        String result = "switchExpression ";
        return switch (obj) {
            case LocalDate l when l.isAfter(LocalDate.now()) -> result + "future";
            //case null -> result + "wrong date"; // null case could be in any position
            case LocalDate l when l.isBefore(LocalDate.now()) -> result + "past";
//            case default -> result + "wrong date"; //default case must be the last case, otherwise, it dominates all other cases
            case LocalDate l when l.isEqual(LocalDate.now()) -> result + "today";
            case null, default -> result + "wrong date"; //null and default can be combined
        };
    }

    static String switchStatementPatternMatching(Object obj) {
        String result = "switchStatement ";
        switch (obj) {
            case null:
                result += "wrong date";
                break;
            case LocalDate l when l.isAfter(LocalDate.now()):
                result += "future";
                break;
            case LocalDate l when l.isBefore(LocalDate.now()):
                result += "past";
                break;
            case LocalDate l when l.isEqual(LocalDate.now()):
                result += "today";
                break;
            default:
                result += "wrong date";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(switchExpressionPatternMatching(null));
        System.out.println(switchExpressionPatternMatching(LocalDate.now()));
        System.out.println(switchExpressionPatternMatching(LocalDate.now().plus(1, ChronoUnit.DAYS)));
        System.out.println(switchExpressionPatternMatching(LocalDate.now().plus(-1, ChronoUnit.DAYS)));

        System.out.println(switchStatementPatternMatching(null));
        System.out.println(switchStatementPatternMatching(LocalDate.now()));
        System.out.println(switchStatementPatternMatching(LocalDate.now().plus(1, ChronoUnit.DAYS)));
        System.out.println(switchStatementPatternMatching(LocalDate.now().plus(-1, ChronoUnit.DAYS)));
    }
}

class IOBufferedWriter {
    public static final String DEFAULT_PATH = "src/main/resources/io/practicequestion";
    public static void main(String[] args) {
        Path inputPath = Path.of(DEFAULT_PATH, "input.txt"); //input file with the text: speedboat
        Path outputPath = Path.of(DEFAULT_PATH, "output.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile()))) {

            int batchSize = 5;
            var buffer = new char[batchSize];
            int lengthRead;
            while ((lengthRead = reader.read(buffer, 0, batchSize)) > 0) {
                writer.write(buffer);
            }
            writer.flush();

            String outputString = Files.readString(outputPath);
            System.out.println("""
                                Output file text:
                                %s
                                """.formatted(outputString));
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

class SequencedCollectionExample {
    public static void main(String[] args) {
        List<String> data = List.of("c", "b", "a", "e", "d");

        // HashSet is not a sequenced collection
//        SequencedCollection<String> hashSet = new HashSet<>(data);
//        hashSet.remove("b");
//        hashSet.add("f");
//        System.out.println("HashSet: " + hashSet);

        // TreeSet
        SequencedCollection<String> treeSet = new TreeSet<>(data);
        treeSet.removeFirst();
//      treeSet does not support addLast/addFirst method, it always sorts the elements in natural order or by a specified comparator.
//        treeSet.addLast("f");
        System.out.println("TreeSet: " + treeSet);

        // LinkedHashSet
        SequencedCollection<String> linkedHashSet = new LinkedHashSet<>(data);
        linkedHashSet.remove("b");
        linkedHashSet.addFirst("f");
        System.out.println("LinkedHashSet: " + linkedHashSet);

        // ArrayList
        SequencedCollection<String> arrayList = new ArrayList<>(data);
        ArrayList<String> arrayList1 = new ArrayList<>(data);
        arrayList.remove("a");
        arrayList.addFirst("f");
        arrayList.addFirst("a");
        arrayList.remove("b");
        System.out.println("ArrayList: " + arrayList);

        // LinkedList
        SequencedCollection<String> linkedList = new LinkedList<>(data);
        linkedList.addLast("f");
        linkedList.remove("b");
        System.out.println("LinkedList: " + linkedList);
    }
}

class MyBaseClass {
    protected int id;

    public MyBaseClass() {
        this.id = 10;
    }

    public MyBaseClass(int id) {
        this.id = id;
    }
}

class MyClass extends MyBaseClass implements Serializable {
    private String name;
    private transient int age;
    private static int count;

    public MyClass(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
        count++;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", count=" + count +
                '}';
    }
}

class SerializationExample {
    public static final String DEFAULT_PATH = "src/main/resources/io/practicequestion";
    public static void main(String[] args) {
        MyClass obj1 = new MyClass(1,"Test", 25);
        String filename = DEFAULT_PATH + "/serialized.txt";

        // Serialization
        try (FileOutputStream file = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(obj1);
            System.out.println("Original object serialized: " + obj1);
        } catch (IOException ex) {
            System.out.println("IOException is caught: " + ex.getMessage());
        }

        MyClass obj2 = new MyClass(2,"New Test", 50);

        // Deserialization
        MyClass deserializedObj = null;
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(file)) {
            deserializedObj = (MyClass) in.readObject();
            System.out.println("Deserialized object: " + deserializedObj);
        } catch (IOException ex) {
            System.out.println("IOException is caught: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught: " + ex.getMessage());
        }

    }
}