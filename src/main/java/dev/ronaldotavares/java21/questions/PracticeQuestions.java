package dev.ronaldotavares.java21.questions;

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