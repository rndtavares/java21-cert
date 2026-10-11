package dev.ronaldotavares.java21._9_collections_and_generics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class _9_9_WorkingWithGenerics {
    public static void main(String[] args) {
        creatingGenericClasses();
        writingGenericMethods();
        genericRecords();
        boundingGenericTypes();
        puttingItAllTogether();
    }

    private static void creatingGenericClasses(){
        System.out.println("creatingGenericClasses");
        Elephant elephant = new Elephant();
        Crate<Elephant> crateForElephant = new Crate<>();
        crateForElephant.packCrate(elephant);
        Elephant inNewHome = crateForElephant.lookInCrate();

        Crate<Zebra> crateForZebra = new Crate<>();

        Robot joeBot = new Robot();
        Crate<Robot> robotCrate = new Crate<>();
        robotCrate.packCrate(joeBot);
        // ship to Houston
        Robot atDestination = robotCrate.lookInCrate();

        //two generic parameters
        Integer numPounds = 15_000;
        SizeLimitedCrate<Elephant, Integer> c1 = new SizeLimitedCrate<>(elephant, numPounds);

        typeErasure();
    }

    private static void typeErasure(){
        System.out.println("typeErasure");

        Crate<Robot> robotCrate = new Crate<>();
        //What you create:
        Robot r = robotCrate.lookInCrate();

        CrateAfterCompiling crate = new CrateAfterCompiling();
        //What the compiler converts:
        Robot r1 = (Robot) crate.lookInCrate(); // cast needed
    }

    private static void writingGenericMethods() {
        System.out.println("writingGenericMethods");

        More.<String>sink("package");
        More.<String[]>sink(new String[]{});

        TrickyCrate<Robot> crate = new TrickyCrate<>();
        crate.tricky("bot");
    }

    private static void genericRecords(){
        System.out.println("genericRecords");

        Robot robot = new Robot();
        CrateRecord<Robot> record = new CrateRecord<>(robot);
    }

    private static void boundingGenericTypes(){
        System.out.println("boundingGenericTypes");
        creatingUnboundedWildcards();
        creatingUpperBoundedWildcards();
        creatingLowerBoundedWildcards();
    }

    private static void creatingUnboundedWildcards(){
        System.out.println("creatingUnboundedWildcards");

        List<String> keywords = new ArrayList<>();
        keywords.add("java");
//        printList(keywords); // DOES NOT COMPILE
        printGeneticUnboundedList(keywords);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(Integer.valueOf(42));
//        List<Object> objects = numbers; // DOES NOT COMPILE
//        objects.add("forty two");
//        System.out.println(numbers.get(1));

        List<?> x1 = new ArrayList<>();
        var x2 = new ArrayList<>();
    }

    private static void printList(List<Object> list) {
        for (Object x: list)
            System.out.println(x);
    }

    private static void printGeneticUnboundedList(List<?> list) {
        for (Object x: list)
            System.out.println(x);
    }

    private static void creatingUpperBoundedWildcards(){
        System.out.println("creatingUpperBoundedWildcards");

//        ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE

        List<? extends Number> list = new ArrayList<Integer>();

        //logically immutable
        List<? extends Bird> birds = new ArrayList<Bird>();
//        birds.add(new Sparrow()); // DOES NOT COMPILE
//        birds.add(new Bird()); // DOES NOT COMPILE

        List<Goose> gooseList = new ArrayList<>();
        gooseList.add(new Goose());
//        anyFlyer(gooseList); //DOES NOT COMPILE
        groupOfFlyers(gooseList);
    }

    private static long total(List<? extends Number> list) {
        long count = 0;
        for (Number number: list)
            count += number.longValue();
        return count;
    }

    private static long totalAfterCompiled(List list) {
        long count = 0;
        for (Object obj: list) {
            Number number = (Number) obj;
            count += number.longValue();
        }
        return count;
    }

    private static void anyFlyer(List<Flyer> flyer) {}
    private static void groupOfFlyers(List<? extends Flyer> flyer) {}

    private static void creatingLowerBoundedWildcards(){
        System.out.println("creatingLowerBoundedWildcards");

        List<String> strings = new ArrayList<String>();
        strings.add("tweet");

        List<Object> objects = new ArrayList<Object>(strings);
        addSound(strings);
        addSound(objects);

        //Could be any of these: List<IOException>, List<Exception> or List<Object>
        List<? super IOException> exceptions = new ArrayList<Exception>();
//        exceptions.add(new Exception()); // DOES NOT COMPILE - Exception does not fit in IOException
        exceptions.add(new IOException());
        exceptions.add(new FileNotFoundException());
    }

//    static void addSound(List<?> list){ //DOES NOT COMPILE
//    static void addSound(List<? extends String> list){ //DOES NOT COMPILE
//    static void addSound(List<Object> list){ //COMPILE, BUT CAN`T RECEIVE List<String>
    static void addSound(List<? super String> list){ //OK
        list.add("quack");
    }

    private static void puttingItAllTogether(){
        System.out.println("puttingItAllTogether");

        List<?> list1 = new ArrayList<A>();
        List<? extends A> list2 = new ArrayList<A>();
        List<? super A> list3 = new ArrayList<A>();

//        List<? extends B> list4 = new ArrayList<A>(); // DOES NOT COMPILE
        List<? super B> list5 = new ArrayList<A>();
//        List<?> list6 = new ArrayList<? extends A>(); // DOES NOT COMPILE - you need to know what that type will be when instantiating the ArrayList
    }

    static <T> T first(List<? extends T> list) {
        return list.get(0);
    }

// return type need to be explicit
//    <T> <? extends T> second(List<? extends T> list) { // DOES NOT COMPILE
//        return list.get(0);
//    }

// B is the generic type by coincidence, can receive List<A>, List<B> or List<C>
//    <B extends A> B third(List<B> list) {
//        return new B(); // DOES NOT COMPILE
//    }

// using T to be clearer, does not compile like the above
//    <T extends A> T third(List<T> list) {
//        return new B(); // DOES NOT COMPILE
//    }
}

class Crate<T> {
    private T contents;
    public T lookInCrate() {
        return contents;
    }
    public void packCrate(T contents) {
        this.contents = contents;
    }
}

class Elephant {}

class Zebra {}

class Robot {}

class SizeLimitedCrate<T, U> {
    private T contents;
    private U sizeLimit;
    public SizeLimitedCrate(T contents, U sizeLimit) {
        this.contents = contents;
        this.sizeLimit = sizeLimit;
    }
}

class CrateAfterCompiling {
    private Object contents;

    public Object lookInCrate() {
        return contents;
    }

    public void packCrate(Object contents) {
        this.contents = contents;
    }
}

class LongTailAnimal {
    protected void chew(List<Object> input) {}
//    protected void chew(List<Double> input) {} // DOES NOT COMPILE
}

class Anteater extends LongTailAnimal {
//    protected void chew(List<Double> input) {} // DOES NOT COMPILE
}

class Mammal {
    public List<CharSequence> play() { return null; }
    public CharSequence sleep() { return null; }
}

class Monkey extends Mammal {
    public ArrayList<CharSequence> play() { return null; } // OK
}

class Goat extends Mammal {
//    public List<String> play() { return null; } // NÃO COMPILA
//    public ArrayList<String> play() { return null; } // NÃO COMPILA
    public String sleep() { return null; }
}

interface Shippable<T> {
    void ship(T t);
}

class ShippableRobotCrate implements Shippable<Robot> {
    public void ship(Robot t) {}
}

class ShippableAbstractCrate<U> implements Shippable<U> {
    public void ship(U t) {}
}

class ShippableCrate implements Shippable {
    public void ship(Object t) {} // raw type
}

class Handler {
    public static <T> void prepare(T t) {
        System.out.println("Preparing " + t);
    }
    public static <T> Crate<T> ship(T t) {
        System.out.println("Shipping " + t);
        return new Crate<T>();
    }
}

class More {
    public static <T> void sink(T t) { }
    public static <T> T identity(T t) { return t; }
//    public static T noGood(T t) { return t; } // DOES NOT COMPILE
}

class TrickyCrate<T> {
    public <T> T tricky(T t) {
        return t;
    }
}

record CrateRecord<T>(T contents) {
    @Override
    public T contents() {
        if (contents == null)
            throw new IllegalStateException("missing contents");
        return contents;
    }
}

class Sparrow extends Bird { }
class Bird { }

interface Flyer { void fly(); }
class HangGlider implements Flyer { public void fly() {} }
class Goose implements Flyer { public void fly() {} }

class A {}
class B extends A {}
class C extends B {}
