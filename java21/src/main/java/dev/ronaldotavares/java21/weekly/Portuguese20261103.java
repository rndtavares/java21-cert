package dev.ronaldotavares.java21.weekly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/** Code snippets for the Portuguese weekly meeting on November 3, 2026. */
public class Portuguese20261103 {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        reserve1();
        reserve2();
    }

    // Main 1: a record can declare static fields, but not additional instance fields.
    private static void question1() {
        System.out.println("\nquestion1");
        final var student = new Student(123, "math", "science");
        System.out.println("id=" + student.id() + ", subjects=" + student.subjects().length);
        System.out.println("rank=" + student.getRank());
        // Change rank to an instance field to see the compilation error.
        // A varargs record component is valid when it is the last component.
    }

    private record Student(int id, String... subjects) {
        private static int rank = 1;

        private int getRank() {
            return rank;
        }
    }

    // Main 2: computeIfPresent passes the key and current value to a BiFunction.
    private static void question2() {
        System.out.println("\nquestion2");
        final Map<String, Account> accounts = new HashMap<>();
        accounts.put("111", new Account("111"));
        accounts.put("222", new BankAccount("222", 200.0));

        final BiFunction<String, Account, Account> replacement = (key, account) ->
                account instanceof BankAccount
                        ? new BankAccount(key, 300.0)
                        : new Account(key);
        accounts.computeIfPresent("222", replacement);
        final var bankAccount = (BankAccount) accounts.get("222");
        System.out.println("balance=" + bankAccount.getBalance());
        // Try computeIfPresent("missing", replacement): the function is not called.
    }

    private static class Account {
        private final String id;

        private Account(String id) {
            this.id = id;
        }

        private String getId() {
            return id;
        }
    }

    private static final class BankAccount extends Account {
        private final double balance;

        private BankAccount(String id, double balance) {
            super(id);
            this.balance = balance;
        }

        private double getBalance() {
            return balance;
        }
    }

    // Main 3: push/pop use the front of a Deque; offerLast/removeLast use its end.
    private static void question3() {
        System.out.println("\nquestion3");
        final Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        deque.offerLast(2);
        deque.push(3);
        System.out.println("before removals=" + deque);
        System.out.println("peekFirst=" + deque.peekFirst());
        System.out.println("removeLast=" + deque.removeLast());
        System.out.println("pop=" + deque.pop());
        System.out.println("remaining=" + deque);
        // Compare peekFirst() with removeFirst() on an empty deque.
    }

    // Main 4: Map.values() exposes the value type, even when its key is unknown.
    private static void question4() {
        System.out.println("\nquestion4");
        final Map<String, List<String>> typed = new HashMap<>();
        typed.put("NY", List.of("New York", "Albany"));

        final HashMap<?, List<String>> wildcardKeys =
                new HashMap<String, List<String>>(typed);
        for (final List<String> cities : wildcardKeys.values()) {
            System.out.println(cities);
        }
        // wildcardKeys.put("CA", List.of("Sacramento")) does not compile:
        // the key type represented by ? is unknown.
        // new HashMap<?, List<String>>() is not a valid constructor expression.
    }

    // Main 5: a tailMap is backed by the original NavigableMap.
    private static void question5() {
        System.out.println("\nquestion5");
        final NavigableMap<String, String> map = new TreeMap<>();
        map.put("a", "apple");
        map.put("b", "boy");
        map.put("c", "cat");
        map.put("aa", "apple1");
        map.put("bb", "boy1");
        map.put("cc", "cat1");

        System.out.println("pollLast=" + map.pollLastEntry());
        System.out.println("pollFirst=" + map.pollFirstEntry());
        final NavigableMap<String, String> tail = map.tailMap("bb", false);
        System.out.println("tail before removal=" + tail);
        System.out.println("tail.pollFirstEntry=" + tail.pollFirstEntry());
        System.out.println("original size=" + map.size());
        // Change false to true in tailMap() to include the bb entry.
    }

    // Intermediate reserve: reduce uses the identity only once for a sequential stream.
    private static void reserve1() {
        System.out.println("\nreserve1");
        final BinaryOperator<String> concatenate = String::concat;
        final List<String> names = List.of("Bill", "George", "Obama");
        final String result = names.stream().reduce("Hello : ", concatenate);
        System.out.println(result);
        // Without the identity, reduce(concatenate) returns Optional<String>.
    }

    // Difficult reserve: compare a lower-bounded value wildcard with a raw Map.
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void reserve2() {
        System.out.println("\nreserve2");
        final Map<Object, ? super ArrayList> limited =
                new LinkedHashMap<Object, ArrayList>();
        limited.put("1", new ArrayList());
        System.out.println("limited size=" + limited.size());
        // limited.put(1, new Object()) does not compile.

        final Map raw = new HashMap();
        raw.put("1", new ArrayList());
        raw.put(1, new Object());
        raw.put(1.0, "Hello");
        System.out.println("raw size=" + raw.size());

        final Map<Object, Object> sorted = new TreeMap<>();
        sorted.put("1", new ArrayList());
        try {
            sorted.put(1, new Object());
        } catch (ClassCastException exception) {
            System.out.println("TreeMap with mixed key types: "
                    + exception.getClass().getSimpleName());
        }
    }
}
