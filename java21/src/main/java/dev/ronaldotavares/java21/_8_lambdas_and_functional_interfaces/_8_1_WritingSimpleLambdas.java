package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

import java.util.ArrayList;
import java.util.List;

public class _8_1_WritingSimpleLambdas {
    public static void main(String[] args) {
        // list of animals
        var animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        // pass class that does check
        print(animals, new CheckIfHopper());
        print(animals, a -> a.canHop());
        print(animals, a -> a.canSwim());
        print(animals, a -> !a.canSwim());
        print(animals, (Animal a) -> { return a.canHop(); });
        print(animals, a -> { return a.canHop(); });
        print(animals, (Animal a) -> a.canHop());

//        var invalid = (Animal a) -> a.canHop(); // DOES NOT COMPILE
    }
    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            // General check
            if (checker.test(animal))
                System.out.print(animal + " ");
        }
        System.out.println();
    }
}

record Animal(String species, boolean canHop, boolean canSwim) { }

interface CheckTrait {
    boolean test(Animal a);
}

class CheckIfHopper implements CheckTrait {
    public boolean test(Animal a) {
        return a.canHop();
    }
}