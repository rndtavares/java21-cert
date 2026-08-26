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
import java.util.stream.Stream;

public class PracticeQuestions {
    public static void main(String[] args) {
        var practiceQuestions = new PracticeQuestions();

        practiceQuestions.concurrencyAtomicVsVolatileQuestion();
        practiceQuestions.enumValueOfQuestion();
        practiceQuestions.streamReduceQuestion();
        practiceQuestions.streamTakeWhileDropWhileQuestion();
        practiceQuestions.parallelStreamConcatDistinctQuestion();
        practiceQuestions.stringComparisonQuestion();
        practiceQuestions.stringBuilderTextBlockQuestion();
        practiceQuestions.arraysCompareMismatchQuestion();
        practiceQuestions.virtualThreadsQuestion();
        practiceQuestions.virtualThreadsWorkStealingPoolQuestion();
        practiceQuestions.moduleVisibilityQuestion();
        practiceQuestions.jdepsClassPathQuestion();
        practiceQuestions.nestedLoopQuestion();
        practiceQuestions.tryWithResourcesQuestion();
        practiceQuestions.localDateTimeNYDaylightSavingQuestion();
        practiceQuestions.objectInitializationOrderQuestion();
        practiceQuestions.localVariableTypeInferenceValidityQuestion();
        practiceQuestions.overloadMethodQuestion();
        practiceQuestions.meanOverloadTypeConversionQuestion();
        practiceQuestions.switchPatternMatchingQuestion();
        practiceQuestions.buildingEqualsPatternMatchingQuestion();
        practiceQuestions.jImageQuestion();
        practiceQuestions.consoleQuestion();
        practiceQuestions.pathResolveRelativizeQuestion();
        practiceQuestions.ioBufferedWriterQuestion();
        practiceQuestions.sequencedCollectionQuestion();
        practiceQuestions.serializationQuestion();

    }

    void concurrencyAtomicVsVolatileQuestion() {
        System.out.println("Q - What will be printed when the following CONCURRENCY code is executed?");
        ConcurrencyAtomicVsVolatile.main(null);
        System.out.println("""
                Explanation:
                    The volatile modifier guarantees visibility of the latest written value, but volatileCounter++ is still a
                    read-modify-write operation and is not atomic. Since two threads increment the same volatileCounter, updates
                    may be lost and the printed values may be repeated, skipped, or interleaved.
                    AtomicInteger.incrementAndGet() performs the increment atomically, so each atomic thread gets a distinct
                    increment result, even though the order of lines printed by different threads is still unpredictable.
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

    void streamTakeWhileDropWhileQuestion() {
        System.out.println("""
                Q - What will be printed when the following STREAM code is executed?

                    List<Integer> numbers = List.of(1, 2, 3, 4, 6, 7, 8, 9, 11, 12);

                    numbers.stream()
                           .takeWhile(n -> n < 20)
                           .dropWhile(n -> n % 5 == 0)
                           .forEach(System.out::print);

                A) 123467891112
                B) 23467891112
                C) 1234
                D) Nothing is printed.
                E) The code does not compile.
                """);
        StreamTakeWhileDropWhile.main(null);
        System.out.println("""
                Explanation:
                    Correct answer: A.

                    The stream source is an ordered list with the values 1, 2, 3, 4, 6, 7, 8, 9, 11, and 12.

                    The takeWhile predicate `n -> n < 20` is true for every element, so all numbers continue to the
                    next stage of the pipeline.

                    The dropWhile predicate `n -> n % 5 == 0` is false for the first element, 1. Once dropWhile sees
                    the first element that does not match the predicate, it stops dropping and passes that element and
                    the rest of the stream forward. Since none of the numbers is divisible by 5, no element is removed.

                    Therefore, forEach receives 1, 2, 3, 4, 6, 7, 8, 9, 11, and 12, and the output is: 123467891112
                """);
    }

    void parallelStreamConcatDistinctQuestion() {
        System.out.println("""
                Q - Identify the correct statement about the following PARALLEL STREAM code.

                    Stream<Integer> a1 = Stream.of(1, 5, 3, 1, 4);
                    Stream<Integer> a2 = Stream.of(4, 1, 2);

                    Stream.concat(a1, a2)
                          .parallel()
                          .distinct()
                          .forEach(System.out::print);

                A) It prints the distinct values 1, 2, 3, 4, and 5, but their order is unpredictable.
                B) It always prints 15342.
                C) It always prints 15314412.
                D) The code does not compile.
                """);
        ParallelStreamConcatDistinct.main(null);
        System.out.println("""
                Explanation:
                    Correct answer: A.

                    Stream.of creates ordered streams. Stream.concat creates a stream containing all elements from the
                    first stream followed by all elements from the second stream.

                    The call to distinct removes duplicate values, so the resulting values are 1, 5, 3, 4, and 2.
                    However, the pipeline is made parallel and the terminal operation is forEach, which does not guarantee
                    encounter order. Therefore, the exact printed order is unpredictable.

                    If encounter order were required, forEachOrdered would be the appropriate terminal operation.
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

    void stringBuilderTextBlockQuestion() {
        System.out.println("""
                Q - Which of the following are valid expressions to create a String with the value "hello world"?

                A)
                    String s = \"\"\"
                            hello\\
                             world\\
                            \"\"\";

                B)
                    String s = "hello" + new String("world");

                C)
                    String s = "hello".concat(" world");

                D)
                    String s = new StringBuilder("world").insert(0, "hello ").toString();

                E)
                    String s = new StringBuilder("world").append(0, "hello ").toString();

                F)
                    String s = new StringBuilder("world").append("hello ", 0, 6).toString();

                G)
                    String s = new StringBuilder("world").add(0, "hello ").toString();

                H)
                    String s = \"\"\"
                            hello
                            world
                            \"\"\".trim();

                I)
                    String s = \"\"\"
                            hello \\
                            world
                            \"\"\";
                """);
        StringBuilderTextBlock.main(null);
        System.out.println("""
                Explanation:
                    Correct answers: A, C and D.

                    A is valid. In a text block, a backslash at the end of a line suppresses the line terminator.
                    Therefore, the two text block lines become a single String with the value "hello world".

                    B produces "helloworld", without a space between the two words.

                    C produces "hello world" because concat appends the second String exactly as provided.

                    D produces "hello world" because StringBuilder.insert(0, "hello ") inserts the text at the beginning
                    of "world".

                    E does not compile. StringBuilder.append does not have an overload that receives an int followed by
                    a String.

                    F compiles, but it appends the selected portion of "hello " to the end of "world", producing
                    "worldhello ".

                    G does not compile because StringBuilder has no add method.

                    H produces "hello\\nworld". The trim call removes leading and trailing whitespace, but it does not
                    remove the newline between the two words.

                    I produces "hello world\\n" because the first backslash suppresses the newline after "hello ", but
                    there is still a trailing line terminator after "world".
                """);
    }

    void arraysCompareMismatchQuestion() {
        System.out.println("""
                Q - What will be printed when the following ARRAYS code is executed?

                    int[] a = { 'h', 'e', 'l' };
                    int[] b = { 'h', 'e', 'l', 'l', 'o' };

                    int x = Arrays.compare(a, b);
                    int y = Arrays.mismatch(a, b);
                    System.out.println(x + " " + y);

                A) 1 3
                B) -1 3
                C) -2 3
                D) -2 -3
                E) The code does not compile.
                """);
        ArraysCompareMismatch.main(null);
        System.out.println("""
                Explanation:
                    Correct answer: C.

                    A char value is an integral value, so character literals such as 'h', 'e', and 'l' can be used in
                    an int array initializer.

                    Arrays.compare compares the two int arrays lexicographically. The first three elements are equal, and
                    array `a` is a proper prefix of array `b`. Since `a` has two fewer elements, compare returns -2.

                    Arrays.mismatch returns the index of the first mismatch. Because the first three elements match and
                    the arrays have different lengths, the first mismatch is at index 3.

                    Therefore, the output is: -2 3
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

    void virtualThreadsWorkStealingPoolQuestion() {
        System.out.println("""
                            Q - Identify correct statements about virtual threads.

                            A) The Executors.newWorkStealingPool() method is used to improve virtual thread performance by reusing threads in a thread pool.
                            B) A virtual thread is a daemon thread by default but it can be made non-daemon by calling setDaemon(false) on it.
                            C) A benefit of virtual threads is that they honor thread prority better than a platform threads.
                            D) A virtual thread can be created by creating an instance of VirtualThread, just like Thread.
                            E) Virtual threads run faster than platform threads.

                            Explanation:
                            Correct answer: A.

                            A) TRUE - Although it is possible to create a large number of virtual threads, there is a limit to
                               how many of them can actually be executed in parallel. This limit is determined by the number
                               of processors. Since virtual threads execute on top of platform threads, and platform threads
                               execute on available processors, it makes sense to use only as many platform threads as there
                               are processors to optimize the parallelization of virtual threads.
                               The work-stealing thread pool uses the number of available processors as its target parallelism
                               level and is therefore considered ideal for executing a large number of virtual threads in this
                               exam question. It creates a pool of optimum number of platform threads, depending on the
                               underlying hardware, which are then used to execute virtual threads.

                            B) FALSE - Virtual threads are always daemon threads. Calling setDaemon(false) on them causes an
                               IllegalArgumentException to be thrown. Calling setDaemon(true) is allowed.

                            C) FALSE - Virtual threads have a fixed thread priority that cannot be changed. It is set to
                               Thread.NORM_PRIORITY. A call to change its priority using setPriority(int) is ignored, with no
                               exception thrown, which means virtual threads do not honor thread priority at all.

                            D) FALSE - Virtual threads are instances of java.lang.VirtualThread, but this class is not public
                               and cannot be referred to directly in Java code. You cannot use new VirtualThread().
                               Use Thread.Builder start/unstarted methods or Thread.startVirtualThread(Runnable) to create one.

                            E) FALSE - A virtual thread utilizes a platform thread to execute code, so it cannot be faster than
                               a platform thread.
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

    void jdepsClassPathQuestion() {
        System.out.println("""
                            Q - Your application uses one modular jar (a.jar), which, in turn, uses one non-modular jar (b.jar).
                            Which of the following commands will cause jdeps to include the non-modular jar in its analysis?

                            A) jdeps --module-path lib\\a.jar; -classpath lib\\b.jar
                            B) jdeps --module-path lib\\a.jar; lib\\b.jar
                            C) jdeps --class-path lib\\a.jar; lib\\b.jar
                            D) jdeps -cp lib\\b.jar lib\\a.jar
                            E) jdeps -cp lib\\b.jar;lib\\a.jar

                            Explanation:
                            Correct answer: C.

                            jdeps can analyze one or more target class files, directories, or jar files. If the target depends
                            on a module, that module must be supplied on the module path. If the target depends on a
                            non-modular jar or class, that dependency must be supplied on the class path.

                            A) FALSE - This is invalid usage. After specifying options, jdeps still needs a target file to analyze.

                            B) FALSE - If a.jar is placed on the module path, jdeps tries to build the module graph. Since a.jar
                               requires b.jar, and b.jar is not on the module path, jdeps reports that the required module is
                               not found.

                            C) TRUE - This command gives jdeps a class path and a target jar to analyze. The -cp, -classpath,
                               and --class-path options are equivalent.

                            D) FALSE - The problem says a.jar uses b.jar. Since a.jar is modular, it cannot read b.jar from the
                               class path as an unnamed-module dependency. b.jar would need to be on the module path as an
                               automatic module when analyzing a.jar as a module.

                            E) FALSE - A target path is required separately from the class path option.
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

    void objectInitializationOrderQuestion() {
        System.out.println("""
                Q - What will the following OBJECT INITIALIZATION code print?

                class InitTest {
                   public InitTest() {
                      s1 = sM1("1");
                   }
                   static String s1 = sM1("a");
                   String s3 = sM1("2"); {
                      s1 = sM1("3");
                   }
                   static {
                      s1 = sM1("b");
                   }
                   static String s2 = sM1("c");
                   String s4 = sM1("4");
                   public static void main(String args[]) {
                      InitTest it = new InitTest();
                   }
                   private static String sM1(String s) {
                      System.out.println(s);
                      return s;
                   }
                }

                A) The program will not compile.
                B) It will print: a b c 2 3 4 1
                C) It will print: 2 3 4 1 a b c
                D) It will print: 1 a 2 3 b c 4
                E) It will print: 1 a b c 2 3 4
                """);
        InitTestQuestion.main(null);
        System.out.println("""
                Explanation:
                    Correct answer: B.

                    Static fields and static initializer blocks run first, in the exact order in which they appear in the class.
                    Therefore, these lines run before any object is created:
                    - static String s1 = sM1("a");
                    - static { s1 = sM1("b"); }
                    - static String s2 = sM1("c");

                    After the class is initialized, main creates a new InitTest object. Instance fields and instance initializer
                    blocks run next, also in the exact order in which they appear in the class:
                    - String s3 = sM1("2");
                    - instance initializer block: s1 = sM1("3");
                    - String s4 = sM1("4");

                    Finally, the constructor body runs and calls sM1("1").

                    The output is: a b c 2 3 4 1
                """);
    }

    void localVariableTypeInferenceValidityQuestion() {
        System.out.println("""
                Q - Consider the following code appearing in a file named TestClass.java. Which lines are valid?

                class Test { }  // 1

                public class TestClass {

                   var v1; // 2

                   public int main(String[] args) { // 3

                       var v2; // 4

                       double x = 10, double y;  // 5

                       var v3 = null; // 6

                       for (var k = 0; k < x; k++) { } // 7

                       Float params[][] = {1.1f, 1.2f, 3.2f};  // 8

                       return 0;
                   }
                }

                A) // 1
                B) // 2
                C) // 3
                D) // 4
                E) // 5
                F) // 6
                G) // 7
                H) // 8

                Explanation:
                    Correct answers: A, C and G.

                    A) TRUE - `class Test { }` is a valid class declaration.

                    B) FALSE - `var` cannot be used for instance fields. Local variable type inference is allowed only
                       in local contexts, such as inside methods, constructors, initializers, and for-loop declarations.

                    C) TRUE - `public int main(String[] args)` is a valid method declaration. It is not the standard
                       JVM entry point because it returns int instead of void, but it is still a valid method.

                    D) FALSE - A `var` local variable declaration must have an initializer. Without an initializer, the
                       compiler cannot infer the type.

                    E) FALSE - `double x = 10, double y;` is syntactically invalid. It should be either
                       `double x = 10; double y;` or `double x = 10, y;`.

                    F) FALSE - `var v3 = null;` is invalid because the compiler cannot infer a type from null alone.

                    G) TRUE - `var` can be used in a for-loop declaration, so `for (var k = 0; k < x; k++)` is valid.

                    H) FALSE - `params` is declared as a two-dimensional Float array, but the initializer is a
                       one-dimensional array. `Float[] params = {1.1f, 1.2f, 3.2f};` would be valid.
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

    void meanOverloadTypeConversionQuestion() {
        System.out.println("""
                Q - Given the following overloaded methods, which option inserted at // INSERT CODE HERE
                will produce the output: 10.0 10 10?

                class MeanTest {
                    int mean(int i, String s) {
                        return (Integer.parseInt(s) + i) / 2;
                    }
                    double mean(int i, int j) {
                        return Math.sqrt(i * j);
                    }
                    double mean(double i, long j) {
                        return Math.sqrt(i * j);
                    }
                    float mean(String s) {
                        return Integer.parseInt(s) / 1f;
                    }
                    float mean(int i) {
                        return i / 1.0f;
                    }

                    public static void main(String[] args) {
                        MeanTest ot = new MeanTest();
                        var a = "10";
                        var b = ot.mean(a);

                        // INSERT CODE HERE

                        System.out.println(c + " " + d + " " + e);
                    }
                }

                A) var c = ot.mean(Math.round(b));
                   var d = ot.mean(Math.round(c), a);
                   var e = ot.mean(d, d);

                B) var c = ot.mean(Math.round(b), a);
                   var d = ot.mean(Math.round(c));
                   var e = ot.mean(c, c);

                C) var c = ot.mean(Math.round(b), Math.round(b));
                   var d = ot.mean(c, c);
                   var e = ot.mean(Math.round(b), a);

                D) var c = ot.mean(Math.round(b));
                   var d = ot.mean(Math.round(c), a);
                   var e = ot.mean(d, a);
                """);
        MeanOverloadTypeConversion.main(null);
        System.out.println("""
                Explanation:
                    Correct answer: D.

                    `var a = "10"` makes `a` a String. Therefore, `ot.mean(a)` invokes mean(String),
                    which returns a float value: 10.0f. This means `b` is inferred as float.

                    Math.round(float) returns int, while Math.round(double) returns long. Since b is float,
                    Math.round(b) returns int.

                    In option D:
                    - c = ot.mean(Math.round(b)) calls mean(int), returning float 10.0.
                    - d = ot.mean(Math.round(c), a) calls mean(int, String), returning int 10.
                    - e = ot.mean(d, a) also calls mean(int, String), returning int 10.

                    A compiles but prints: 10.0 10 10.0
                    B compiles but prints: 10 10.0 10.0
                    C does not compile because c is double after calling mean(int, int), and there is no mean(double, double).
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

    void buildingEqualsPatternMatchingQuestion() {
        System.out.println("""
                Q - Given Hospital and Hotel as permitted subclasses of Building, which Building definitions
                would make the program print: false true false?

                class Hospital extends Building {
                    String type;
                    Hospital(String name, String type) {
                        super(name);
                        this.type = type;
                    }
                    String type() { return type; }
                }

                class Hotel extends Building {
                    String type;
                    Hotel(String name, String type) {
                        super(name);
                        this.type = type;
                    }
                    String type() { return type; }
                }

                Building b1 = new Hospital("Unique", "commercial");
                Building b2 = new Hotel("Unique", "commercial");
                Building b3 = new Hotel("Unique", "commercial");
                System.out.print(b1.equals(b2) + " " + b2.equals(b3) + " " + b3.equals(null));

                A) sealed abstract Building with abstract type(), and equals() using switch patterns for Hospital and Hotel.
                B) sealed abstract Building with abstract type(), and equals() using:
                   o instanceof Building b
                   && this.getClass().equals(o.getClass())
                   && this.type() == b.type()
                C) sealed Building with equals() returning:
                   o instanceof Hotel h1 && this instanceof Hotel h2 && h1.type().equals(h2.type())
                D) sealed Building without type(), but equals() tries to call this.type() in switch pattern cases.
                """);
        BuildingEqualsPatternMatching.main(null);
        System.out.println("""
                Explanation:
                    Correct answers: B and C.

                    B is valid because `o instanceof Building b` is false for null, so b3.equals(null) returns false.
                    It also compares runtime classes with getClass(), making Hospital vs Hotel false and Hotel vs Hotel true.
                    The example uses the same string literal "commercial", so the `==` comparison between the two Hotel type
                    references evaluates to true.

                    C is also valid, although it is a poor logical equals() implementation. It returns true only when both
                    objects are Hotel instances and their type values are equal. Therefore:
                    - Hospital compared with Hotel returns false.
                    - Hotel compared with Hotel returns true.
                    - Hotel compared with null returns false because `null instanceof Hotel` is false.

                    A is not correct because it compares only the type value for Hospital and Hotel cases, so Hospital and
                    Hotel with the same type would be considered equal.

                    D does not compile because Building does not declare type(), but equals() tries to call this.type().
                    The compiler checks the declared type of `this`, which is Building.
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

    void consoleQuestion() {
        System.out.println("""
                Q - Given the following CONSOLE code, which statements are correct? Select TWO.

                    import java.io.Console;

                    public class ConsoleQuestion {
                        public static void main(String[] args) {
                            Console c = System.console();

                            String user = c.readLine("User: %d", "duke");
                            char[] password = c.readPassword("Password: ");

                            System.out.println(user + " " + password.length);
                        }
                    }

                A) If the JVM is not connected to a console, `System.console()` returns null, and this code may throw
                   a NullPointerException.

                B) The call to `readLine("User: %d", "duke")` may throw an IllegalFormatException.

                C) The call to `readPassword("Password: ")` returns a String.

                D) The call to `readPassword("Password: ")` must be handled with a catch block for IOException.

                E) If a console is available, `readLine()` never returns null.
                """);
        System.out.println("""
                Explanation:
                    Correct answers: A and B.

                    A) TRUE - `System.console()` may return null when the JVM is not attached to an interactive console,
                       for example when running from many IDEs or build tools. In that case, calling an instance method on
                       `c` may throw NullPointerException.

                    B) TRUE - The formatted Console methods use format strings. The `%d` conversion expects a numeric
                       argument, but `"duke"` is a String, so an IllegalFormatException subtype may be thrown.

                    C) FALSE - `readPassword(String, Object...)` returns a char[], not a String.

                    D) FALSE - Console methods do not declare IOException, so this code is not required to catch it.

                    E) FALSE - `readLine()` may return null if an end-of-input condition is reached.
                """);
    }

    void pathResolveRelativizeQuestion() {
        System.out.println("""
                Q - What will be printed when the following PATH code is executed?

                    Path p1 = Path.of("projects/./java/../src");
                    Path p2 = Path.of("resources/messages.txt");

                    System.out.println(p1.resolve(p2));
                    p1.normalize();
                    System.out.println(p1.relativize(p2));

                A)
                    projects/src/resources/messages.txt
                    ../resources/messages.txt

                B)
                    projects/./java/../src/resources/messages.txt
                    ../../resources/messages.txt

                C)
                    projects/./java/../src/resources/messages.txt
                    ../../../resources/messages.txt

                D)
                    An IllegalArgumentException is thrown.

                E)
                    projects/src/resources/messages.txt
                    ../../resources/messages.txt
                """);
        PathResolveRelativizeExample.main(null);
        System.out.println("""
                Explanation:
                    Correct answer: B.

                    `Path` objects are immutable. The call to `p1.normalize()` returns a normalized Path, but because the
                    return value is not assigned to any reference, `p1` still refers to `projects/./java/../src`.

                    Both paths are relative, so `relativize()` is valid. The `resolve()` method does not normalize the result;
                    it appends the second relative path to the unchanged first path. Therefore, the first line keeps the `.`
                    and `..` segments: `projects/./java/../src/resources/messages.txt`.

                    The `relativize()` method calculates a path from `projects/./java/../src` to `resources/messages.txt`.
                    The `.` segment does not add a name, and the `java/..` pair cancels out while calculating the relative
                    path. This leaves two name elements to move up from, producing `../../resources/messages.txt`.

                    Option E would be tempting if `p1.normalize()` changed p1 in place, but it does not.
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
        final Runnable volatileCounterRunnable = () -> {
            for (int i = 0; i < 3; i++) {
                volatileCounter++;
                System.out.println(Thread.currentThread().getName() + " " + volatileCounter);
            }
        };

        final Runnable atomicCounterRunnable = () -> {
            for (int i = 0; i < 3; i++) {
                final int counterValue = atomicCounter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " " + counterValue);
            }
        };

        final Thread volatileThread1 = new Thread(volatileCounterRunnable, "volatile-1");
        final Thread volatileThread2 = new Thread(volatileCounterRunnable, "volatile-2");
        final Thread atomicThread1 = new Thread(atomicCounterRunnable, "atomic-1");
        final Thread atomicThread2 = new Thread(atomicCounterRunnable, "atomic-2");

        volatileThread1.start();
        volatileThread2.start();
        atomicThread1.start();
        atomicThread2.start();

        try {
            volatileThread1.join();
            volatileThread2.join();
            atomicThread1.join();
            atomicThread2.join();
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

class StreamTakeWhileDropWhile {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 6, 7, 8, 9, 11, 12);

        numbers.stream()
                .takeWhile(n -> n < 20)
                .dropWhile(n -> n % 5 == 0)
                .forEach(System.out::print);
        System.out.println();
    }
}

class ParallelStreamConcatDistinct {
    public static void main(String[] args) {
        Stream<Integer> a1 = Stream.of(1, 5, 3, 1, 4);
        Stream<Integer> a2 = Stream.of(4, 1, 2);

        Stream.concat(a1, a2)
                .parallel()
                .distinct()
                .forEach(System.out::print);
        System.out.println();
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

class StringBuilderTextBlock {
    public static void main(String[] args) {
        String a = """
                hello\
                 world\
                """;
        String c = "hello".concat(" world");
        String d = new StringBuilder("world").insert(0, "hello ").toString();

        System.out.println(a.equals("hello world"));
        System.out.println(c.equals("hello world"));
        System.out.println(d.equals("hello world"));
    }
}

class ArraysCompareMismatch {
    public static void main(String[] args) {
        int[] a = { 'h', 'e', 'l' };
        int[] b = { 'h', 'e', 'l', 'l', 'o' };

        int x = Arrays.compare(a, b);
        int y = Arrays.mismatch(a, b);
        System.out.println(x + " " + y);
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

class InitTestQuestion {
    public InitTestQuestion() {
        s1 = sM1("1");
    }

    static String s1 = sM1("a");

    String s3 = sM1("2");

    {
        s1 = sM1("3");
    }

    static {
        s1 = sM1("b");
    }

    static String s2 = sM1("c");

    String s4 = sM1("4");

    public static void main(String[] args) {
        InitTestQuestion it = new InitTestQuestion();
    }

    private static String sM1(String s) {
        System.out.println(s);
        return s;
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
//                         Both method(int,double) and method(float,float) match
        method(2.0F, 1);
    }
}

class MeanOverloadTypeConversion {
    int mean(int i, String s) {
        return (Integer.parseInt(s) + i) / 2;
    }

    double mean(int i, int j) {
        return Math.sqrt(i * j);
    }

    double mean(double i, long j) {
        return Math.sqrt(i * j);
    }

    float mean(String s) {
        return Integer.parseInt(s) / 1f;
    }

    float mean(int i) {
        return i / 1.0f;
    }

    public static void main(String[] args) {
        MeanOverloadTypeConversion ot = new MeanOverloadTypeConversion();
        var a = "10";
        var b = ot.mean(a);

        var c = ot.mean(Math.round(b));
        var d = ot.mean(Math.round(c), a);
        var e = ot.mean(d, a);

        System.out.println(c + " " + d + " " + e);
    }
}

class SwitchPatternMatching {

    static String switchExpressionPatternMatching(Object obj) {
        String result = "switchExpression ";
        return switch (obj) {
            case LocalDate l when l.isAfter(LocalDate.now()) -> result + "future";
//            case null -> result + "wrong date"; // null case could be in any position
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

abstract sealed class Building permits Hospital, Hotel {
    String name;

    Building(String name) {
        this.name = name;
    }

    abstract String type();

    @Override
    public boolean equals(Object o) {
        if (o instanceof Building b) {
            return this.getClass().equals(o.getClass()) && this.type() == b.type();
        }
        return false;
    }
}

final class Hospital extends Building {
    String type;

    Hospital(String name, String type) {
        super(name);
        this.type = type;
    }

    String type() {
        return type;
    }
}

non-sealed class Hotel extends Building {
    String type;

    Hotel(String name, String type) {
        super(name);
        this.type = type;
    }

    String type() {
        return type;
    }
}

class BuildingEqualsPatternMatching {
    public static void main(String[] args) {
        Building b1 = new Hospital("Unique", "commercial");
        Building b2 = new Hotel("Unique", "commercial");
        Building b3 = new Hotel("Unique", "commercial");

        System.out.println(b1.equals(b2) + " " + b2.equals(b3) + " " + b3.equals(null));
    }
}

class PathResolveRelativizeExample {
    public static void main(String[] args) {
        Path p1 = Path.of("projects/./java/../src");
        Path p2 = Path.of("resources/messages.txt");

        p1.normalize();

        System.out.println(p1.resolve(p2));
        System.out.println(p1.relativize(p2));
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
        // a, f, c, e, d
        List<String> data = List.of("c", "b", "a", "e", "d");

        // HashSet is not a sequenced collection
//        SequencedCollection<String> hashSet = new HashSet<>(data);
        SequencedCollection<String> hashSet = new LinkedHashSet<>(data);
        hashSet.remove("b");
        hashSet.add("f");
        System.out.println("LinkedHashSet: " + hashSet);
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
