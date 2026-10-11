# Java 21 — Operators: visual discussion guide

This guide supports Enthuware questions 2.1014, 2.1314, 2.3581, 2.972, 2.3570, 2.3550, and 2.3238. The core habit is to track **types**, **values**, and **when evaluation happens**, rather than trying to memorize answers.

## 1. References and `equals()`

**Related question:** 2.1014 (Foundation)

```java
Object obj1 = new Object();
Object obj2 = obj1;
System.out.println(obj1.equals(obj2));
```

These variables do not create two objects. They both hold a reference to the same object:

```text
obj1 ──┐
       ├──► Object #1
obj2 ──┘
```

`Object.equals()` has not been overridden, so it behaves like an identity comparison. The result is `true` because both references point to the same object.

> Practical rule: `==` compares object references. `equals()` compares contents only when the class defines such a rule, as `String` does.

## 2. Conversion, promotion, and casts

**Related questions:** 2.1314, 2.3581, and 2.972

### Promotion without a cast

```text
byte ─► short ─► int ─► long ─► float ─► double
                 ▲
char ────────────┘
```

Moving in this direction is a *widening conversion*, which Java performs automatically.

```java
char letter = 'A';
long value = letter;     // compiles: char promotes to long
int number = 10;
float decimal = number;  // compiles: int promotes to float
```

### Conversion that requires a cast

When conversion can lose information, or moves between `char` and smaller integral types, an explicit cast is required.

```java
short s = 10;
char c = (char) s;        // explicit cast required

char letter = 'A';
short n = (short) letter; // explicit cast required
```

Even if the current value fits in the destination type, Java applies the rule based on the **variable type**, not on the value currently stored.

```java
short s = 10;
// char c = s;      // does not compile
char c = (char) s;  // compiles
```

### Checklist before choosing an answer

1. What is the source type?
2. What is the destination type?
3. Does the promotion arrow naturally go from source to destination?
4. If not, is there an explicit cast?

> Watch out: `char` is an unsigned numeric type. It promotes to `int`, but `short` and `char` do not convert automatically into each other.

## 3. Prefix/postfix increment and compound assignment

**Related questions:** 2.3570 and 2.3550

### `++x` versus `x++`

| Expression | First action | Value produced by the expression |
| --- | --- | --- |
| `++x` | increment `x` | new value |
| `x++` | use `x` | old value; increment afterwards |

```java
int x = 4;
int a = x++; // a gets 4; x becomes 5
int b = ++x; // x becomes 6; b gets 6
```

| After the line | `x` | Created variable |
| --- | ---: | --- |
| `int x = 4` | 4 | — |
| `int a = x++` | 5 | `a = 4` |
| `int b = ++x` | 6 | `b = 6` |

### Trace a long expression

For `k += 3 + ++k`, do not calculate everything at once. Record each change:

```java
int k = 1;
k += 3 + ++k;
```

| Step | State / result |
| --- | --- |
| original left-hand value | `k = 1` |
| `++k` on the right | `k` becomes `2`; the expression produces `2` |
| right-hand side | `3 + 2`, therefore `5` |
| `+=` | combines the original left-hand value: `1 + 5` |
| final result | `k = 6` |

> Practical rule: when an expression has side effects, draw a value timeline. Do not simplify `+=` mentally before evaluating its right-hand side.

## 4. Default values, arrays, and assignment expressions

**Related question:** 2.3238 (Very Tough)

Fields and array elements receive default values. Local variables do not.

| Declaration | Default value |
| --- | --- |
| `boolean` | `false` |
| `int` | `0` |
| `char` | `\u0000` (numeric value `0`) |
| reference | `null` |
| `boolean[1]` | `[false]` |

In the question's pattern:

```java
static boolean b;                    // false
static char ch;                      // '\u0000', which is 0
static boolean[] ba = new boolean[1]; // [false]
```

Therefore, `ba[ch]` means `ba[0]`.

### Assignment also has a value

```java
boolean result = (ba[ch] = b);
```

This does more than update the array. An assignment expression evaluates to the assigned value:

```text
b = false
ba[0] = b  ──► stores false and the expression itself evaluates to false
result = false
```

> Practical rule: `=` inside parentheses can occur in a condition or another expression. Read it in two parts: “assigns” and “produces that same value”.

## Quick workflow for operator questions

1. Write down initial values and types.
2. Identify side effects: `++`, `--`, `=`, and `+=`.
3. Evaluate from left to right, recording every change.
4. Only then determine the printed value or whether the code compiles.

This workflow avoids the most common certification traps: nonexistent implicit conversions, mixing up prefix/postfix increments, and incorrect assumptions about default values.
