package dev.ronaldotavares.java21.questions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
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
        practiceQuestions.localeNYDaylightSavingQuestion();
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

    void localeNYDaylightSavingQuestion() {
        System.out.println("Q - What will be printed when the following LOCALE code is executed?");
        LocaleNYDaylightSaving.main(null);
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

class LocaleNYDaylightSaving {
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