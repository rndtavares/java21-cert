# JEP 513 — Flexible Constructor Bodies

## Certification context

JEP 513 was finalized in Java 25. It removes the historical rule that an
explicit constructor invocation — `super(...)` or `this(...)` - had to be the
first statement in a constructor body.

## Mental model: prologue and epilogue

When there is an explicit invocation, a constructor body now has two parts:

```java
class Employee extends Person {
    Employee(String name, int age) {
        // prologue
        if (age < 18) throw new IllegalArgumentException("age");
        super(name);
        // epilogue
        System.out.println("Employee created");
    }
}
```

- The **prologue** contains statements before `super(...)` or `this(...)`.
- The **epilogue** contains statements after that invocation.

If there is no explicit invocation, the compiler still inserts `super()` at the
start. The written statements are then all in the epilogue; this JEP does not
make it possible to put statements before an implicit `super()`.

## Why it matters

Before Java 25, validation that needed to happen before `super(...)` generally
required a helper method inside the invocation arguments:

```java
class Employee extends Person {
    Employee(int age) { super(validateAge(age)); }

    private static int validateAge(int age) {
        if (age < 18) throw new IllegalArgumentException();
        return age;
    }
}
```

In Java 25, the intent is direct and the constructor fails before executing the
superclass constructor:

```java
class Employee extends Person {
    Employee(int age) {
        if (age < 18) throw new IllegalArgumentException();
        super(age);
    }
}
```

## Early construction context

The prologue and the arguments of `super(...)` or `this(...)` are an **early
construction context**. At this point, the current instance cannot be used.

```java
class Child extends Parent {
    int number;

    Child() {
        System.out.println(this);          // invalid: uses `this`
        System.out.println(number);        // invalid: reads an instance field
        instanceMethod();                  // invalid: calls an instance method
        System.out.println(super.number);  // invalid: accesses the superclass
        super();
    }

    void instanceMethod() { }
}
```

The compiler also rejects implicit uses of `this`: writing only `number` or
`instanceMethod()` already refers to the object under construction. Removing the
word `this` is not enough; the question is whether the expression needs the
instance that is still being initialized.

Static operations, parameters, local variables, and static methods are safe at
this stage:

```java
class Child extends Parent {
    Child(String raw) {
        var normalized = raw.trim();
        validate(normalized);
        super(normalized);
    }

    static void validate(String value) {
        if (value.isEmpty()) throw new IllegalArgumentException();
    }
}
```

## Exception: initialize an own field without an initializer

The prologue may use a simple assignment to a field declared in the same class,
provided that field has no declaration initializer:

```java
class Child extends Parent {
    private final String storedId;
    private String status = "new";

    Child(String id) {
        storedId = id;       // allowed: own field without an initializer
        // status = "ready"; // invalid: `status` has an initializer
        super();
    }
}
```

This exception helps a subclass put its fields into a valid state before a
superclass can potentially call overridden code. It does not allow reading the
field, calling instance methods, or accessing inherited fields in the prologue.

## `super(...)` versus `this(...)`

The same rules apply to both forms of delegation:

```java
class Ticket {
    Ticket(String code) {
        if (code.isBlank()) throw new IllegalArgumentException();
        this(code.trim(), 1);
    }

    Ticket(String code, int priority) { }
}
```

Each construction path must contain exactly one appropriate delegation. A
constructor cannot use both `super(...)` and `this(...)` on the same path.

## Records and enums

The added flexibility does not remove rules specific to these types:

- a canonical record constructor cannot explicitly invoke another constructor;
- a non-canonical record constructor must delegate with `this(...)`, not
  `super(...)`;
- enum constructors may delegate with `this(...)`, but not with `super(...)`.

Where `this(...)` is allowed, prologue statements can now appear before it while
respecting the early construction context.

## Quick checklist

- Java 25 allows statements before `super(...)` and `this(...)`.
- The prologue is appropriate for validation, normalization, and local work.
- `throw` is allowed in a prologue; `return` is not.
- Do not use `this`, instance fields or methods, or superclass members before
  delegation.
- The exception is a simple assignment to an own field with no initializer.
- Apply the usual rules too: each path delegates exactly once, constructors are
  not inherited, and implicit `super()` remains at the start without an explicit
  invocation.

## Sources

- Official JEP: https://openjdk.org/jeps/513
