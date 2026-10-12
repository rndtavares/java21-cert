# JEP 456 — Unnamed Variables & Patterns

## Certification context

JEP 456 was finalized in Java 22. 

## Core idea

The `_` character declares that a variable or part of a pattern exists because
the syntax requires it, but its value will not be used. It is not an identifier
that can be read later.

```java
boolean _ = 10 < 11;       // compiles
System.out.println(_);     // does not compile: there is no name in scope
```

The initializer still runs, including any side effects:

```java
var _ = queue.remove();    // removes the element; only its result is discarded
```

## Three related forms

| Form | Example | Meaning |
| --- | --- | --- |
| Unnamed variable | `var _ = read();` | A local value has no name |
| Unnamed pattern variable | `case Book(String _)` | The type matters, but the value does not |
| Unnamed pattern | `case Box(_)` | Neither an explicit type nor a name is needed for that component |

## Where `_` is allowed

```java
var _ = operation();
for (String _ : names) { }
for (int i = 0, _ = setup(); i < 3; i++) { }
try (var _ = openResource()) { }
catch (IOException _) { }
stream.forEach(_ -> doSomething());
```

It is also allowed in type patterns and record patterns:

```java
if (value instanceof String _) {
    System.out.println("it is a String");
}

switch (value) {
    case Book(String _) -> System.out.println("book");
    default -> System.out.println("other");
}
```

## Where `_` is not allowed

`_` cannot be used as a field, method parameter, or constructor parameter:

```java
class Invalid {
    int _;                 // does not compile
    void run(String _) { } // does not compile
    Invalid(int _) { }     // does not compile
}
```

A local declaration without an initializer is also invalid:

```java
int _; // does not compile
```

## Important rules for the exam

### `_` does not introduce a name into scope

Since there is no name, multiple `_` declarations can coexist in the same
block:

```java
var _ = first();
var _ = second();
```

This is not redeclaration, and one `_` does not shadow another variable.

### The expression still has to be valid

Using `_` does not relax typing, initialization, or exception-handling rules.
For example, the right-hand side of `var _` cannot be `null` when no type can be
inferred.

### Patterns still follow matching rules

An `_` in a pattern only discards a value; it does not make a pattern win or
remove the other `switch` rules. Evaluate separately:

1. whether the type or record pattern matches the value;
2. whether a `when` guard is true;
3. the order of the `case` labels and dominance between them.

Therefore, a pattern such as `Book(String _)` can be valid, while execution
still depends on the runtime type and any applicable guards.

## Quick checklist

- `_` alone is not a usable variable name.
- The initializer and its side effects still run.
- Multiple `_` declarations in the same scope are allowed.
- Local variables, `for`, `catch`, resources, and lambdas are valid contexts.
- Fields and method or constructor parameters are not valid contexts.
- Type patterns and record patterns can discard components using `_`.

## Sources

- Official JEP: https://openjdk.org/jeps/456
