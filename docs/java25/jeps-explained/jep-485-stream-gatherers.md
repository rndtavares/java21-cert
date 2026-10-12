# JEP 485 — Stream Gatherers

## Certification context

JEP 485 was finalized in Java 24. It adds `Stream.gather(...)`, a way to express
stateful intermediate operations that cannot be conveniently represented by
`map`, `filter`, `reduce`, or `collect`.

They focus on:

- `Gatherers.fold(...)` and `Gatherers.scan(...)`;
- `Gatherers.windowFixed(...)` and `Gatherers.windowSliding(...)`;
- `Gatherer.of(...)` and `Gatherer.ofSequential(...)`;
- `Gatherer.Integrator.of(...)` and `ofGreedy(...)`;
- the difference between asking for more upstream input and emitting downstream.

## Mental model

```java
Stream<R> result = source.gather(gatherer);
```

A gatherer receives upstream elements, may keep state, and decides which values
to send downstream. Unlike `collect`, `gather` is an intermediate operation, so
it still returns a `Stream`.

## Built-in gatherers

### `fold`

`fold` emits only the final accumulated result:

```java
Stream.of(1, 2, 3)
    .gather(Gatherers.fold(() -> 0, Integer::sum))
    .forEach(System.out::println); // 6
```

The result is still a stream, here with one element. Therefore, `findFirst()`
returns `Optional<Integer>`, not `int`.

### `scan`

`scan` emits each intermediate accumulated result:

```java
Stream.of(1, 2, 3)
    .gather(Gatherers.scan(() -> 0, Integer::sum))
    .forEach(System.out::println); // 1, 3, 6
```

The common trap is that `scan(...).findFirst()` returns the first accumulated
value, not the final total.

### `windowFixed(n)`

This creates consecutive, non-overlapping windows:

```text
Input:  a, b, c, d, e
n = 2:  [a,b], [c,d], [e]
```

The final window can be smaller when the input ends before it is full.

### `windowSliding(n)`

This creates overlapping windows:

```text
Input:  a, b, c, d
n = 2:  [a,b], [b,c], [c,d]
```

When evaluating a pipeline, track the lists emitted by the gatherer before
applying the next `map`, `gather`, or terminal operation.

### `mapConcurrent`

`Gatherers.mapConcurrent(limit, mapper)` transforms elements concurrently, up to
the given limit. It remains an intermediate operation and needs a terminal
operation to evaluate. Distinguish the maximum number of concurrent tasks from
the order observed downstream: concurrency does not automatically mean a
different output order.

## Anatomy of a custom gatherer

A `Gatherer<T, A, R>` can have four functions:

| Part | Role |
| --- | --- |
| Initializer | Creates state `A` |
| Integrator | Receives state, an element, and the downstream |
| Combiner | Merges states in parallel evaluation |
| Finisher | Emits any remaining results at the end |

Example of a sequential gatherer that uppercases text:

```java
var upper = Gatherer.<String, Void, String>ofSequential(
    (_, element, downstream) -> downstream.push(element.toUpperCase())
);
```

The integrator does not forward an element automatically. A value reaches the
next stage only if `downstream.push(value)` is called.

## Two booleans that must not be confused

The integrator return value says whether it wants more elements from upstream:

- `true`: it may continue;
- `false`: it stops receiving new input.

`downstream.push(value)` instead returns whether the downstream still accepts
elements. An integrator that simply forwards values normally returns it directly:

```java
(_, element, downstream) -> downstream.push(element)
```

Returning `true` without calling `push()` keeps consuming upstream input but
produces no results. This separation between consumption and emission is a
central custom-gatherer trap.

## Greedy, sequential, and parallel gatherers

`Gatherer.Integrator.ofGreedy(...)` declares that the integrator always consumes
all input it receives. Its effective return is treated as `true`, unlike a
regular integrator where `false` short-circuits processing.

`Gatherer.ofSequential(...)` has no combiner and forces the gathering portion to
run sequentially. It does not necessarily make the rest of a parallel pipeline
sequential; a later `forEach` can still observe non-deterministic execution or
ordering.

For a stateful parallel gatherer, the combiner must merge partial states
correctly. A gatherer that sums a parallel stream, for example, combines an
initializer, integrator, combiner, and finisher. Its result must remain
compatible with sequential evaluation of the same pipeline, especially for an
ordered source.

## Quick checklist

- `gather()` is intermediate and returns `Stream`.
- `fold` emits one final value; `scan` emits intermediate values.
- `windowFixed` does not overlap; `windowSliding` overlaps.
- Without `downstream.push()`, no value is emitted.
- The integrator boolean controls demand for more input.
- `ofGreedy` must not be used by an integrator that actually short-circuits.
- Parallel pipelines require attention to the combiner and terminal ordering.

## Sources

- Official JEP: https://openjdk.org/jeps/485
