# JEP 506 — Scoped Values

## Certification context

JEP 506 was finalized in Java 25. `ScopedValue<T>` shares an immutable value
with methods called within a well-defined dynamic scope. It is appropriate as an
alternative to `ThreadLocal` when context is passed to callees without arbitrary
mutation. 

## Create, bind, and read

```java
private static final ScopedValue<String> USER = ScopedValue.newInstance();

ScopedValue.where(USER, "ana").run(() -> {
    System.out.println(USER.get()); // ana
});
```

Outside `run`, the binding no longer exists:

```java
USER.get(); // NoSuchElementException when there is no outer binding
```

`ScopedValue` is normally a `static final` field. The object is the key; a value
is temporarily associated with that key for execution within the scope.

### `null` is not the same as unbound

A binding can contain `null`. This differs from having no binding: `get()`
returns `null` in the first case and throws `NoSuchElementException` in the
second. `isBound()` reports whether a binding exists, not whether the value is
non-null.

## An unexecuted `Carrier` does not create a scope

`ScopedValue.where(key, value)` produces a `ScopedValue.Carrier`. Merely
creating it neither executes code nor makes a binding visible to the rest of the
method:

```java
ScopedValue.where(USER, "ana"); // discarded carrier; USER remains unbound
```

Execute `run(...)` or `call(...)` instead:

```java
ScopedValue.where(USER, "ana").run(task);

String result = ScopedValue.where(USER, "ana")
    .call(() -> USER.get().toUpperCase());
```

## Important lookups

| Operation | Result |
| --- | --- |
| `key.get()` | Current value or `NoSuchElementException` when unbound |
| `key.orElse(fallback)` | Current value or the fallback |
| `key.isBound()` | Whether a binding is visible |
| `carrier.get(key)` | The value held by that carrier |

Printing the key object does not print its bound value:

```java
System.out.println(USER);       // ScopedValue representation
System.out.println(USER.get()); // value in the current scope
```

## Rebinding and automatic restoration

A nested scope can replace the visible value. When it ends, the prior binding
is restored:

```java
ScopedValue.where(USER, "outer").run(() -> {
    System.out.println(USER.get()); // outer
    ScopedValue.where(USER, "inner").run(() ->
        System.out.println(USER.get()) // inner
    );
    System.out.println(USER.get()); // outer
});
```

This is not mutation of the outer binding. It is a new binding visible only in
the child scope.

## Multiple values in one carrier

```java
ScopedValue.where(USER, "ana")
    .where(ROLE, "admin")
    .run(() -> log(USER.get(), ROLE.get()));
```

A chained carrier installs a consistent set of bindings. Calling `.where(...)`
without `.run(...)` or `.call(...)` only constructs another carrier.

## `run` versus `call`

- `run(Runnable)` has no return value;
- `call(Callable<R>)` returns a value computed inside the scope.

A local variable captured by a lambda must still be final or effectively final.
`ScopedValue` does not make an otherwise invalid lambda assignment valid.

## Threads and context inheritance

Bindings belong to bounded execution and must not be treated as global state.
Creating an arbitrary thread inside a binding does not, by itself, copy the
context to that thread. To make a value available in another thread, execute the
task inside a suitable carrier. Structured-concurrency mechanisms compatible
with scoped values provide structured propagation.

When a snippet creates a `new Thread(...)`, analyze separately:

1. which thread calls the carrier's `run` method;
2. which binding is active in that thread;
3. whether a read occurs inside or outside the scope;
4. whether the same carrier explicitly executes the task.

## Binding immutability

There is no `set()` or `remove()` on a binding. Treat the referenced value as
immutable context. If `T` is mutable, `ScopedValue` does not make the object
itself immutable; it only prevents replacing the binding in the same scope.

## Quick checklist

- `newInstance()` creates an initially unbound key.
- `where(...)` creates a carrier; by itself it does not install a useful binding.
- `run` executes without a return value; `call` executes and returns one.
- `get()` on an unbound key throws `NoSuchElementException`.
- `orElse()` supplies a fallback.
- A nested binding temporarily hides the outer one.
- On scope exit, the prior binding is restored automatically.
- Printing the key is not equivalent to calling `get()`.
- Arbitrary threads must not be assumed to inherit a binding.

## Sources

- Official JEP: https://openjdk.org/jeps/506
