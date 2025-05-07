package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.*;

public class _9_1_UsingCommonCollectionAPIs {
    public static void main(String[] args) {
        understandingGenericTypes();
        add();
        remove();
        isEmptyAndSize();
        clear();
        contains();
        removeif();
        foreach();
        equals();
        unboxingNulls();
    }
    private static void understandingGenericTypes(){
        System.out.println("Understanding generic types");
        //Without generics
        List numbers = new ArrayList(List.of(1,2,3));
        Integer element = (Integer)numbers.get(0);  // Required cast to compil
        numbers.add("Welcome to the zoo!");         // Unrelated types allowed
        System.out.println(numbers); // [1, 2, 3, Welcome to the zoo!]

        //with generics
        List<Integer> numbersGen = new ArrayList<Integer>(List.of(1,2,3));
        Integer elementGen = numbersGen.get(0); // No cast required
//        numbersGen.add("Welcome to the zoo!"); // DOES NOT COMPILE
        System.out.println(numbersGen);


        //long generic code
        List<Integer> list = new ArrayList<Integer>();
        Map<Long,List<Integer>> mapOfLists = new HashMap<Long,List<Integer>>();

        //diamond operator
        List<Integer> listDiamond = new ArrayList<>();
        Map<Long,List<Integer>> mapOfListsDiamond = new HashMap<>();

//        List<> list1 = new ArrayList<Integer>(); // DOES NOT COMPILE
//        class InvalidUse {
//            void use(List<> data) {} // DOES NOT COMPILE
//        }

        var listVar = new ArrayList<Integer>();
        var mapOfListsVar = new HashMap<Long,List<Integer>>();

        //compile
        var map = new HashMap<>();
        //same as following
        HashMap<Object, Object> map1 = new HashMap<Object, Object>();
    }

    private static void add(){
        System.out.println("Add");
        Collection<String> list = new ArrayList<>();
        System.out.println(list.add("Sparrow")); // true
        System.out.println(list.add("Sparrow")); // true

        Collection<String> set = new HashSet<>();
        System.out.println(set.add("Sparrow")); // true
        System.out.println(set.add("Sparrow")); // false
    }

    private static void remove() {
        System.out.println("Remove");
        Collection<String> birds = new ArrayList<>();
        birds.add("hawk");                            // [hawk, hawk]
        birds.add("hawk");                            // [hawk]
        System.out.println(birds.remove("cardinal")); // false
        System.out.println(birds.remove("hawk"));     // true
        System.out.println(birds);                    // [hawk]

    }

    private static void isEmptyAndSize(){
        System.out.println("IsEmpty and Size");

        Collection<String> birds = new ArrayList<>();
        System.out.println(birds.isEmpty()); // true
        System.out.println(birds.size()); // 0
        birds.add("hawk"); // [hawk]
        birds.add("hawk"); // [hawk, hawk]
        System.out.println(birds.isEmpty()); // false
        System.out.println(birds.size()); // 2
    }

    private static void clear() {
        System.out.println("clear");

        Collection<String> birds = new ArrayList<>();
        birds.add("hawk"); // [hawk]
        birds.add("hawk"); // [hawk, hawk]
        System.out.println(birds.isEmpty()); // false
        System.out.println(birds.size()); // 2
        birds.clear(); // []
        System.out.println(birds.isEmpty()); // true
        System.out.println(birds.size()); // 0
    }

    private static void contains() {
        System.out.println("contains");


        Collection<String> birds = new ArrayList<>();
        birds.add("hawk");
        System.out.println(birds.contains("hawk"));  // true
        System.out.println(birds.contains("robin")); // false
    }

    private static void removeif(){
        System.out.println("removeIf");

        Collection<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list); // [Magician, Assistant]
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list); // [Magician]

        Collection<String> set = new HashSet<>();
        set.add("Wand");
        set.add("");
        set.removeIf(String::isEmpty); // s -> s.isEmpty()
        System.out.println(set); // [Wand]
    }

    private static void foreach(){
        System.out.println("forEach");

        Collection<String> cats = List.of("Annie", "Ripley");
        cats.forEach(System.out::println);
        cats.forEach(c -> System.out.println(c));

        //other iteration approaches
        for (String element: cats)
            System.out.println(element);

        Iterator<String> iter = cats.iterator();
        while(iter.hasNext()) {
            String name = iter.next();
            System.out.println(name);
        }
    }

    private static void equals(){
        System.out.println("equals");

        var list1 = List.of(1, 2);
        var list2 = List.of(2, 1);
        var set1 = Set.of(1, 2);
        var set2 = Set.of(2, 1);

        System.out.println(list1.equals(list2)); // false
        System.out.println(set1.equals(set2));   // true
        System.out.println(list1.equals(set1));  // false
    }

    private static void unboxingNulls() {
        System.out.println("Unboxing nulls");

        var heights = new ArrayList<Integer>();
        heights.add(null);
//        int h = heights.get(0);  // NullPointerException
    }
}
