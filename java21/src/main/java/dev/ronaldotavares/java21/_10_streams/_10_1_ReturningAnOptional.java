package dev.ronaldotavares.java21._10_streams;

import java.util.Optional;

public class _10_1_ReturningAnOptional {
    public static void main(String[] args) {
        System.out.println("Returning an Optional");

        System.out.println(average(90, 100)); // Optional[95.0]
        System.out.println(average());        // Optional.empty

        Optional<Double> opt = average(90, 100);
        if (opt.isPresent())
            System.out.println(opt.get()); // 95.0

//        Optional<Double> opt = average();
//        System.out.println(opt.get()); // NoSuchElementException

        String value = null;
        Optional o = (value == null) ? Optional.empty() : Optional.of(value);
        Optional o1 = Optional.ofNullable(value); // simplified

        Optional<Double> optional = average(90, 100);
        optional.ifPresent(System.out::println);

        Optional<Double> opt1 = average(90, 100);
        System.out.println(opt1.orElse(Double.NaN));
        System.out.println(opt1.orElseGet(() -> Math.random()));
        System.out.println(opt1.orElseThrow());

        dealingWithAnEmptyOptional();
    }

    private static Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score: scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }

    private static void dealingWithAnEmptyOptional(){
        System.out.println("Dealing with an empty Optional");

        Optional<Double> opt = average();
        System.out.println(opt.orElse(Double.NaN));              // NaN
        System.out.println(opt.orElseGet(() -> Math.random()));

//        System.out.println(opt.orElseThrow()); // NoSuchElementException
//        System.out.println(opt.orElseThrow(() -> new IllegalStateException()));

//        System.out.println(opt.orElseGet(() -> new IllegalStateException())); // DOES NOT COMPILE
    }
}
