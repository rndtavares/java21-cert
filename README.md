# Java 21 Certification Study Repository

This repository contains Java 21 code examples, notes, experiments, and practice-question scratch files for preparing for the **Oracle Certified Professional: Java SE 21 Developer** certification, exam **1Z0-830**.

The main goal of this project is practical study: each package focuses on a topic from the certification scope and contains small, runnable examples that make language rules, API behavior, and common exam traps easier to observe directly in code.

The examples are heavily inspired by the topics covered in the book [OCP Oracle Certified Professional Java SE 21 Developer Study Guide: Exam 1Z0-830](https://www.amazon.com/Oracle-Certified-Professional-Developer-Study/dp/1394286619), by Jeanne Boyarsky and Scott Selikoff, and by additional practice with mock exam questions.

## Why This Repository Exists

The Java 21 certification is not just about memorizing syntax. It requires precise understanding of:

- how the compiler resolves overloads, overrides, generics, pattern matching, and switch expressions
- how runtime behavior differs from compile-time type checking
- how Java core APIs behave in edge cases
- how streams, concurrency, localization, modules, and I/O work under exam-style constraints
- how to reason quickly about code that may compile, fail to compile, throw exceptions, or produce non-obvious output

I wrote more about the value of this certification in my article: [The Importance of Getting the Java 21 Certification](https://ronaldotavares.dev/2025/03/18/the-importance-of-getting-the-java-21-certification/).

## Repository Structure

The code lives mainly under:

```text
src/main/java/dev/ronaldotavares/java21
```

The packages are organized by certification topic and by study-guide chapter.

## Covered Topics

### 1. Building Blocks

Package:

```text
_1_building_blocks
```

Topics include:

- Java class structure
- package declarations and imports
- `main()` method rules
- object creation
- primitive and reference types
- variable declaration and initialization
- scope
- garbage collection basics

### 2. Operators

Package:

```text
_2_operators
```

Topics include:

- unary, binary, and assignment operators
- numeric promotion
- compound assignment
- equality and relational operators
- ternary expressions
- operator precedence
- tricky increment/decrement behavior

### 3. Making Decisions

Package:

```text
_3_making_decisions
```

Topics include:

- `if` statements
- `switch` statements and expressions
- pattern matching in switch
- `while`, `do while`, and `for` loops
- labels, `break`, and `continue`
- flow-control edge cases

### 4. Core APIs

Package:

```text
_4_core_apis
```

Topics include:

- `String`
- `StringBuilder`
- equality and identity
- arrays and multidimensional arrays
- `Math` APIs
- dates, times, durations, periods, zones, and daylight-saving behavior

### 5. Methods

Package:

```text
_5_methods
```

Topics include:

- method declarations
- access modifiers
- static members
- passing data among methods
- varargs
- overloading
- package and protected access examples

### 6. Class Design

Package:

```text
_6_class_design
```

Topics include:

- inheritance
- constructors
- initialization order
- overriding and hiding
- abstract classes
- immutable objects
- superclass and subclass behavior

### 7. Beyond Classes

Package:

```text
_7_beyond_classes
```

Topics include:

- interfaces
- default, static, and private interface methods
- enums
- sealed classes
- records
- nested classes
- polymorphism

### 8. Lambdas and Functional Interfaces

Package:

```text
_8_lambdas_and_functional_interfaces
```

Topics include:

- lambda syntax
- functional interfaces
- method references
- built-in functional interfaces
- effectively final variables
- lambda scoping

### 9. Collections and Generics

Package:

```text
_9_collections_and_generics
```

Topics include:

- common collection APIs
- `List`, `Set`, `Queue`, `Deque`, and `Map`
- sorting and comparators
- sequenced collections
- generic classes and methods
- wildcards: `?`, `? extends T`, and `? super T`
- compile-time vs runtime behavior with generics

### 10. Streams

Package:

```text
_10_streams
```

Topics include:

- `Optional`
- stream creation and terminal operations
- primitive streams
- `map`, `flatMap`, `filter`, `reduce`, `collect`
- lazy evaluation
- parallel streams
- advanced stream pipeline behavior

### 11. Exceptions and Localization

Package:

```text
_11_exceptions_and_localization
```

Topics include:

- checked and unchecked exceptions
- exception hierarchy
- `try`, `catch`, `finally`
- try-with-resources
- suppressed exceptions
- formatting values
- `Locale`
- `ResourceBundle`
- properties files
- message formatting

### 12. Modules

Package:

```text
_12_modules
```

Topics include:

- Java Platform Module System
- `module-info.java`
- `requires`, `exports`, `opens`, `uses`, and `provides`
- modular JARs
- unnamed modules and automatic modules
- classpath vs module path
- tools such as `jdeps`, `jlink`, `jmod`, `jimage`, and `jpackage`

### 13. Concurrency

Package:

```text
_13_concurrency
```

Topics include:

- thread creation
- thread states
- `ExecutorService`
- `Future`
- synchronization
- locks
- concurrent collections
- race conditions
- deadlock, livelock, and starvation
- parallel streams

### 14. I/O

Package:

```text
_14_IO
```

Topics include:

- `File`
- `Path`
- `Files`
- file and directory operations
- streams and readers/writers
- serialization
- console interaction
- advanced NIO.2 APIs

## Practice Questions and Experiments

The package:

```text
src/main/java/dev/ronaldotavares/java21/questions
```

contains runnable files used to study mock exam questions and edge cases, including:

These classes are intentionally exploratory. Many snippets include commented-out lines that do not compile, because the point is to compare valid and invalid alternatives side by side.

The files are useful for testing individual questions, checking:

- compile-time failures
- runtime exceptions
- overload and override resolution
- wildcard behavior
- stream laziness
- concurrency ordering
- module-system rules
- resource-bundle fallback behavior

## Requirements

This project targets Java 21.

The `pom.xml` sets:

```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

Recommended environment:

- JDK 21 or newer
- Maven, if you want to compile through the Maven build
- an IDE with Java 21 language support

## How to Compile

Using Maven:

```bash
mvn compile
```

Using `javac` directly for one file:

```bash
javac --release 21 -cp src/main/java src/main/java/dev/ronaldotavares/java21/questions/UniqueTest3.java
```

To avoid `.class` files being generated inside `src/main/java`, compile to a temporary output directory:

```bash
javac --release 21 -d /tmp/java21-cert-classes -cp src/main/java src/main/java/dev/ronaldotavares/java21/questions/UniqueTest3.java
```

## How to Run Examples

After compiling to `/tmp/java21-cert-classes`, run a class with:

```bash
java -cp /tmp/java21-cert-classes dev.ronaldotavares.java21.questions.UniqueTest3
```

Or run a specific helper class:

```bash
java -cp /tmp/java21-cert-classes dev.ronaldotavares.java21.questions.TestClassU3_54
```

Many classes in this repository have their own `main()` method and are designed to be run independently.

## Study Approach

A useful workflow for this repository is:

1. Pick a topic package, such as streams, generics, exceptions, modules, or concurrency.
2. Run the examples before reading the comments.
3. Predict whether each snippet compiles.
4. Predict the output or exception.
5. Uncomment invalid alternatives when useful and observe compiler errors.
6. Compare compile-time type rules with runtime behavior.
7. Revisit the same topic through practice-question files.

The certification often tests small distinctions, such as:

- overload selection vs polymorphic method dispatch
- `List<? extends T>` vs `List<? super T>`
- checked exception declaration vs catch reachability
- stream intermediate operations vs terminal operations
- classpath behavior vs module-path behavior
- `Thread.start()` vs submitting a `Thread` object as a `Runnable`
- resource-bundle locale fallback
- `Math.round(float)` returning `int` vs `Math.round(double)` returning `long`

This repository is meant to make those distinctions visible through code.

## Java 21 Certification Study Community

Beyond the code in this repository, there is also a Java 21 certification study community with more than **450 members**.

You can join here:

[Java 21 Certification Study Group](https://sendfox.com/ronaldotavares)

The community has weekly study meetings in both **Portuguese** and **English**, focused on helping developers prepare consistently for the Java 21 certification.

The study group discusses:

- Java 21 certification topics
- mock exam questions
- difficult language rules
- real examples from the JDK
- study strategies
- career impact of certification
- practical Java knowledge beyond the exam

## Special Guests

The community has hosted many special guests from the Java ecosystem, including:

<table>
  <tr>
    <td width="33%" valign="top">
      <a href="https://lnkd.in/p/dFvuYFdg">
        <img src="https://media.licdn.com/dms/image/v2/D4D22AQGJIizb3bvp_Q/feedshare-shrink_800/B4DZaJfengGcAg-/0/1746063463229?e=2147483647&amp;v=beta&amp;t=I7J_XDVQcVyxV7LHCxNQopLtVue4RZUIMXhO1pKYdd4" alt="Jeanne Boyarsky session with the Java 21 certification study community" width="100%">
      </a>
      <br>
      <strong><a href="https://lnkd.in/p/dFvuYFdg">Jeanne Boyarsky</a></strong><br>
      Java Champion and author of the OCP Java 21 Study Guide
    </td>
    <td width="33%" valign="top">
      <a href="https://lnkd.in/p/dpcjsErd">
        <img src="https://media.licdn.com/dms/image/v2/D4D22AQFP9f1cEqWhnA/feedshare-shrink_800/B4DZ7VUDEzI8Ac-/0/1781695263573?e=2147483647&amp;v=beta&amp;t=F2RBvjUv80Pmm2Gn_4H0KHhuX5a_J9wrldo8isvlOVI" alt="Professor Isidro session with the Java 21 certification study community" width="100%">
      </a>
      <br>
      <strong><a href="https://lnkd.in/p/dpcjsErd">Professor Isidro</a></strong><br>
      Java Champion
    </td>
    <td width="33%" valign="top">
      <a href="https://lnkd.in/p/dqZjxtum">
        <img src="https://media.licdn.com/dms/image/v2/D4D22AQHureM82O5kpw/feedshare-shrink_800/B4DZekDosnG8Ao-/0/1750804115990?e=2147483647&amp;v=beta&amp;t=OVVLjT2dd19pHUpxBdN_wEIG2piKJgU-OuKZjUPtupQ" alt="Elder Moraes session with the Java 21 certification study community" width="100%">
      </a>
      <br>
      <strong><a href="https://lnkd.in/p/dqZjxtum">Elder Moraes</a></strong><br>
      Java Champion
    </td>
  </tr>
  <tr>
    <td width="33%" valign="top">
      <a href="https://lnkd.in/p/d3NmJjdJ">
        <img src="https://media.licdn.com/dms/image/v2/D4D22AQHAYresBy7qww/feedshare-shrink_800/B4DZhY0XrLGgAk-/0/1753836790219?e=2147483647&amp;v=beta&amp;t=L5KWhQbmyReidIcTgT7QouM_TC0ZakEOPNIJs3oowW0" alt="Rafael del Nero session with the Java 21 certification study community" width="100%">
      </a>
      <br>
      <strong><a href="https://lnkd.in/p/d3NmJjdJ">Rafael del Nero</a></strong><br>
      Java Champion
    </td>
    <td width="33%" valign="top">
      <a href="https://lnkd.in/p/dPGnE3Ue">
        <img src="https://media.licdn.com/dms/image/v2/D5622AQHnUh8eyyQPvw/feedshare-shrink_800/B56ZssLlLXKAAg-/0/1765972794711?e=2147483647&amp;v=beta&amp;t=hHz-7Z1jgEBtxuqpOvO-CA0MfaO9Vr8b76-qLDphKu4" alt="Bruno Souza session with the Java 21 certification study community" width="100%">
      </a>
      <br>
      <strong><a href="https://lnkd.in/p/dPGnE3Ue">Bruno Souza</a></strong><br>
      Java Champion
    </td>
    <td width="33%" valign="top">
      <a href="https://lnkd.in/p/dHXsxeAa">
        <img src="https://media.licdn.com/dms/image/v2/D4E22AQEmhCmFNj8jpg/feedshare-shrink_800/B4EZ5FIN8kIcAc-/0/1779276244982?e=2147483647&amp;v=beta&amp;t=4hsCMp1uWaTt4YqTSIATd09lyqO3fW8EdU5cnVci98g" alt="Nicolai Parlog session with the Java 21 certification study community" width="100%">
      </a>
      <br>
      <strong><a href="https://lnkd.in/p/dHXsxeAa">Nicolai Parlog</a></strong>
    </td>
  </tr>
</table>

The community is a complement to this repository: the repo provides the runnable code and examples, while the group provides discussion, accountability, weekly practice, and explanations in both languages.

## Related Links

- [The Importance of Getting the Java 21 Certification](https://ronaldotavares.dev/2025/03/18/the-importance-of-getting-the-java-21-certification/)
- [Java 21 Certification Study Group](https://sendfox.com/ronaldotavares)
- [OCP Oracle Certified Professional Java SE 21 Developer Study Guide: Exam 1Z0-830](https://www.amazon.com/Oracle-Certified-Professional-Developer-Study/dp/1394286619)

## Notes

This repository is a study workspace. Some files intentionally contain commented code that does not compile, raw types, examples that throw exceptions, and snippets created to reproduce exam traps. That is expected and part of the learning process.

When reviewing or modifying examples, prefer preserving the original behavior of each question unless the goal is explicitly to test an alternative.
