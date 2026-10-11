package dev.ronaldotavares.java21._7_beyond_classes;

import java.util.ArrayList;
import java.util.List;

public class _7_4_EncapsulatingDataWithRecords {
    public static void main(String[] args) {
        var mommy = new Crane(4, "Cammy");
        System.out.println(mommy.numberEggs()); // 4
        System.out.println(mommy.name()); // Cammy

//        var mommy1 = new Crane("Cammy", 4); // DOES NOT COMPILE
//        var mommy2 = new Crane("Cammy"); // DOES NOT COMPILE

        var father = new Crane(0, "Craig");
        System.out.println(father); // Crane[numberEggs=0, name=Craig]
        var copy = new Crane(0, "Craig");
        System.out.println(copy); // Crane[numberEggs=0, name=Craig]
        System.out.println(father.equals(copy)); // true
        System.out.println(father.hashCode() + ", " + copy.hashCode()); // 1007, 1007

        var cousin = new Crane(3, "Jenny");
        var friend = new Crane(cousin.numberEggs(), "Janeice");

        patternMatchinWithRecords();
    }

    private static void patternMatchinWithRecords(){
        System.out.println("Pattern matching with records");
        Object animal = new Monkey("George", 3);
        if(animal instanceof Monkey(String name, int myAge)) {
            System.out.println("Hello " + name);
            System.out.println("Your age is " + myAge);
        }

        if(animal instanceof Monkey myMonkey) {}
//        if(animal instanceof Monkey(String n, int a) myMonkey) {} // You can name records or it's fields, not both
//        if(animal instanceof Monkey(String n, long a)) {} //Numeric promotion not supported
        if(animal instanceof Monkey(Object n, int a)) {}

        matchingRecords();
        nestingRecordPatterns();
        matchingRecordsWithVarAndGenerics();
        System.out.println(appliyingPatternMatchingRecordsToSwitch(new SnakeRecord(1))); // 11
        System.out.println(appliyingPatternMatchingRecordsToSwitch(new SnakeRecord(2L))); // 3
        System.out.println(appliyingPatternMatchingRecordsToSwitch(new SnakeRecord(3.0))); // 103
    }

    private static void matchingRecords(){
        System.out.println("Matching records");
        FishRecord f1 = new FishRecord("Nemo");
        FishRecord f2 = new FishRecord(Integer.valueOf(1));

        if(f1 instanceof FishRecord(Object t)) {
            System.out.print("Match1- ");
        }
        if(f1 instanceof FishRecord(String t)) {
            System.out.print("Match2- ");
        }
        if(f1 instanceof FishRecord(Integer t)) {
            System.out.print("Match3- ");
        }
        if(f2 instanceof FishRecord(String t)) {
            System.out.print("Match4- ");
        }
        if(f2 instanceof FishRecord(Integer x)) {
            System.out.print("Match5");
        }
    }

    private static void nestingRecordPatterns(){
        System.out.println("Nesting record patterns");
        var c = new Couple(new BearRecord("Yogi", List.of("PicnicBaskets")),
                new BearRecord("Fozzie", List.of("BadJokes")));

        if(c instanceof Couple(BearRecord a, BearRecord b)) {
            System.out.print(a.name() + " " + b.name());
        }
        if(c instanceof Couple(
                BearRecord(String firstName, List<String> f),
                BearRecord b)) {
            System.out.print(firstName + " " + b.name());
        }
//        if(c instanceof Couple(Bear(String name, List<String> f1),
//                               Bear(String name, List<String> f2))) {
//            System.out.print(name + " " + name);
//        }
        if(c instanceof Couple(
                BearRecord(String name1, List<String> f1),
                BearRecord(String name2, List<String> f2))) {
            System.out.print(name1 + " " + name2);
        }
    }

    private static void matchingRecordsWithVarAndGenerics(){
        System.out.println("Matching records with var and generics");
        var c = new Couple(new BearRecord("Yogi", List.of("PicnicBaskets")),
                new BearRecord("Fozzie", List.of("BadJokes")));

        if (c instanceof Couple(var a, var b)) {
            System.out.print(a.name() + " " + b.name());
        }
        if (c instanceof Couple(BearRecord(var firstName, List<String> f), var b)) {
            System.out.print(firstName + " " + b.name());
        }

        if(c instanceof Couple(BearRecord(var n, Object f), var b)) {}
        if(c instanceof Couple(BearRecord(var n, List f), var b)) {}
        if(c instanceof Couple(BearRecord(var n, List<?> f), var b)) {}
        if(c instanceof Couple(BearRecord(var n, List<? extends Object> f), var b)) {}
        if(c instanceof Couple(BearRecord(var n, ArrayList<String> f), var b)) {}

//        if(c instanceof Couple(Bear(var n, List<> f), var b)) {}
//        if(c instanceof Couple(Bear(var n, List<Object> f), var b)) {}

//        if(c instanceof Couple(Bear(var n, List f), var b)
//                && f.getFirst().toLowerCase().contains("p")) { // DOES NOT COMPILE
//            System.out.print("Yummy");
//        }
    }

    private static long appliyingPatternMatchingRecordsToSwitch(SnakeRecord snake){
        return switch(snake) {
            case SnakeRecord(Long hiss) -> hiss + 1;
            case SnakeRecord(Integer nagina) -> nagina + 10;
            case SnakeRecord(Number crowley) -> crowley.intValue() + 100;
            case SnakeRecord(Object kaa) -> -1;
        };

//        return switch(snake) {
//            case Snake(Object kaa) when kaa.doubleValue() > 0 -> -1; // Object does not have doubleValue()
//            default -> 1 _ 000;
//        };
    }
}

class CranePOJO {
    int numberEggs;
    String name;
    public CranePOJO(int numberEggs, String name) {
        this.numberEggs = numberEggs;
        this.name = name;
    }
}

class Poacher {
    public void badActor() {
        var mother = new CranePOJO(5,"Cathy");
        mother.numberEggs = -100;
    }
}

final class CranePOJO2 {
    private final int numberEggs;
    private final String name;
    public CranePOJO2(int numberEggs, String name) {
        if (numberEggs >= 0) this.numberEggs = numberEggs; // guard
        else throw new IllegalArgumentException();
        this.name = name;
    }
    public int getNumberEggs() { // getter
        return numberEggs;
    }
    public String getName() { // getter
        return name;
    }
 }

class Vet {
    private String name = "Dr Rogers";
    private int yearsExperience = 25;
}

//public final record Crane(int numberEggs, String name) {}
record Crane(int numberEggs, String name) {
//    public Crane(int numberEggs, String name) { // Long constructor
//        if (numberEggs < 0) throw new IllegalArgumentException();
//        this.numberEggs = numberEggs;
//        this.name = name;
//    }
//    public Crane(int numberEggs, String name) {} // DOES NOT COMPILE
    public Crane { // Compact constructor
        if (name == null || name.length() < 1)
            throw new IllegalArgumentException();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
//    public Crane {
//        this.numberEggs = 10; // DOES NOT COMPILE
//    }
    public Crane(String firstName, String lastName) { // Overloaded constructor
        this(0, firstName + " " + lastName);
    }
    public Crane(int numberEggs, String firstName, String lastName) {
        this(numberEggs + 1, firstName + " " + lastName);
        numberEggs = 10; // NO EFFECT (applies to parameter, not instance field)
//        this.numberEggs = 20; // DOES NOT COMPILE
    }
//    public Crane(String name) {
//        this(1); // DOES NOT COMPILE
//    }
//    public Crane(int numberEggs) {
//        this(""); // DOES NOT COMPILE
//    }
}

record CraneEmpty() {}

//public record BlueCrane() extends Crane {} // DOES NOT COMPILE

interface Bird {}
record CraneImplementer(int numberEggs, String name) implements Bird {}

record WhoopingCrane(String name, int position) {
    private static int counter = 0;
    public WhoopingCrane(String name) {
        this(name, counter++);
    }
}

record Monkey(String name, int age) {}

record FishRecord(Object type) {}

record BearRecord(String name, List<String> favoriteThings) {}
record Couple(BearRecord a, BearRecord b) {}

record SnakeRecord(Object data) {}

record CraneCustomized(int numberEggs, String name) {
    private static int TYPE = 10;
//    public int size; // DOES NOT COMPILE
//    private final boolean friendly = true; // DOES NOT COMPILE

    static { System.out.print("Hello Bird!"); }
//    { System.out.print("Goodbye Bird!"); } // DOES NOT COMPILE
//    { this.name = "Big"; } // DOES NOT COMPILE

    @Override public int numberEggs() { return 10; }
    @Override public String toString() { return name; }
}