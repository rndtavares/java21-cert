package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.*;

public class _9_6_SortingData {
    public static void main(String[] args) {
        comparable();
        comparator();
        sorting();
    }
    private static void comparable() {
        System.out.println("comparable - delegating comparison to existing compareTo method");

        var ducks = new ArrayList<Duck>();
        ducks.add(new Duck("Quack", 0));
        ducks.add(new Duck("Puddles", 0));
        Collections.sort(ducks); // sort by name
        System.out.println(ducks);  // [Duck[name=Puddles], Duck[name=Quack]]

        System.out.println("comparable - implementing especific compareTo method");

        var d1 = new ZooDuck(5, "Daffy");
        var d2 = new ZooDuck(7, "Donald");
        System.out.println(d1.compareTo(d2)); // -2
        System.out.println(d1.compareTo(d1)); // 0
        System.out.println(d2.compareTo(d1)); // 2

        System.out.println("comparable - implementing compareTo method with Object parameter");

        var ld1 = new LegacyDuck("Daffy");
        var ld2 = new LegacyDuck("Donald");
        System.out.println(ld1.compareTo(ld2)); // -14
        System.out.println(ld1.compareTo(ld1)); // 0
        System.out.println(ld2.compareTo(ld1)); // 14
    }

    private static void comparator(){
        System.out.println("comparator");

//        Comparator<Duck> byWeight = new Comparator<>() {
//            public int compare(Duck d1, Duck d2) {
//                return d1.weight() - d2.weight();
//            }
//        };
//        Comparator<Duck> byWeight = (d1, d2) -> d1.weight()-d2.weight();
        Comparator<Duck> byWeight = Comparator.comparing(Duck::weight);

        var ducks = new ArrayList<Duck>();
        ducks.add(new Duck("Quack", 7));
        ducks.add(new Duck("Puddles", 10));
        Collections.sort(ducks);   // by name
        System.out.println(ducks); // [Puddles, Quack]
        Collections.sort(ducks, byWeight); //by weight
        System.out.println(ducks); // [Quack, Puddles]

//        var byWeight = new Comparator<Duck>() { // DOES NOT COMPILE
//            public int compareTo(Duck d1, Duck d2) {
//                return d1.getWeight()-d2.getWeight();
//            }
//        };

        //multiple comparisons
        Comparator<Squirrel> c = Comparator.comparing(Squirrel::species)
                .thenComparingInt(Squirrel::weight);

        //DESC order
        Comparator<Squirrel> reversed = Comparator.comparing(Squirrel::species).reversed();
    }

    private static void sorting(){
        System.out.println("sorting");
        List<Integer> list = Arrays.asList(6, 9, 1, 8);
        System.out.println(list);
        Collections.sort(list);                      // [1, 6, 8, 9]
        System.out.println(list);
        System.out.println(Collections.binarySearch(list, 6)); // 1
        System.out.println(Collections.binarySearch(list, 3)); // -2

        var names = Arrays.asList("Fluffy", "Hoppy");
        Comparator<String> c = Comparator.reverseOrder();
        var index = Collections.binarySearch(names, "Hoppy", c);
        System.out.println(index); // -1 Invalid binary search while not natural list order

        List<String> bunnies = new ArrayList<>();
        bunnies.add("long ear");
        bunnies.add("floppy");
        bunnies.add("hoppy");
        System.out.println(bunnies);
        bunnies.sort((b1, b2) -> b1.compareTo(b2));
        System.out.println(bunnies); // [floppy, hoppy, long ear]

    }
}

record Duck(String name, int weight) implements Comparable<Duck> {
    public int compareTo(Duck d) {
        return name.compareTo(d.name);  // Sorts ascendingly by name
    }
    public String toString() { return name; }
}

record ZooDuck(int id, String name) implements Comparable<ZooDuck> {
    public int compareTo(ZooDuck d) {
        return id - d.id;
    }
}

record LegacyDuck(String name) implements Comparable {
    public int compareTo(Object obj) {
        if(obj instanceof LegacyDuck d)
            return name.compareTo(d.name);
        throw new UnsupportedOperationException("Not a duck");
    }
}

record MissingDuck(String name) implements Comparable<MissingDuck> {
    public int compareTo(MissingDuck quack) {
        if (quack == null)
            throw new IllegalArgumentException("Poorly formed duck!");
        if (this.name == null && quack.name == null)
            return 0;
        else if (this.name == null)
            return -1;
        else if (quack.name == null)
            return 1;
        else
            return name.compareTo(quack.name);
    }
}

class Product implements Comparable<Product> {
    private int id;
    private String name;
    public int hashCode() { return id; }
    public boolean equals(Object obj) {
        if (obj instanceof Product other)
            return this.id == other.id;
        return false;
    }
    public int compareTo(Product obj) {
        return this.name.compareTo(obj.name); // Inconsistent with equals
    }
}

record Squirrel(int weight, String species) {}

class MultiFieldComparator implements Comparator<Squirrel> {
    public int compare(Squirrel s1, Squirrel s2) {
        int result = s1.species().compareTo(s2.species());
        if (result != 0) return result;
        else return s1.weight() - s2.weight();
    }
}

class SortRabbits {
    static record Rabbit(int id) {}
    public static void main(String[] args) {
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit(3));
        rabbits.add(new Rabbit(1));

        //Collections.sort(rabbits); // DOES NOT COMPILE
        Comparator<Rabbit> c = (r1, r2) -> r1.id - r2.id;

        Collections.sort(rabbits, c);
        System.out.println(rabbits);  // [Rabbit[id=1], Rabbit[id=3]]
        Collections.reverse(rabbits);
        System.out.println(rabbits);  // [Rabbit[id=3], Rabbit[id=1]]
    }
}

class UseTreeSet {
    record Rabbit(int id) {}
    public static void main(String[] args) {
        Set<Duck> ducks = new TreeSet<>();
        ducks.add(new Duck("Puddles",0));

//        Set<Rabbit> rabbits = new TreeSet<>();
        Set<Rabbit> rabbits = new TreeSet<>((r1, r2) -> r1.id - r2.id);
        rabbits.add(new Rabbit(1)); // ClassCastException
    }
}