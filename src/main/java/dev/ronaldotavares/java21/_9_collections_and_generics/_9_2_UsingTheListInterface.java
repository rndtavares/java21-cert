package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _9_2_UsingTheListInterface {
    public static void main(String[] args) {
        creatingList();
        listMethods();
    }

    private static void creatingList(){
        System.out.println("Creating List");

        String[] array = new String[] {"a", "b", "c"};
        List<String> asList = Arrays.asList(array);       // [a, b, c]
        List<String> of = List.of(array);                 // [a, b, c]
        List<String> copy = List.copyOf(asList);          // [a, b, c]

        array[0] = "z";
        System.out.println(asList);                       // [z, b, c]
        System.out.println(of);                           // [a, b, c]
        System.out.println(copy);                         // [a, b, c]

        asList.set(0, "x");
        System.out.println(Arrays.toString(array));       // [x, b, c]

//        of.set(0, "y");        // UnsupportedOperationException
//        copy.set(0, "y");      // UnsupportedOperationException
//        asList.add("z");       // UnsupportedOperationException
//        of.remove(0);          // UnsupportedOperationException

        creatinWithConstructors();
    }

    private static void creatinWithConstructors(){
        System.out.println("Creating List with constructors");
        var linked1 = new LinkedList<String>();
        var linked2 = new LinkedList<String>(linked1);

        var list1 = new ArrayList<String>();
        var list2 = new ArrayList<String>(list1);
        var list3 = new ArrayList<String>(10); // capacidade inicial
    }

    private static void listMethods(){
        System.out.println("List methods");

        List<String> list = new ArrayList<>();
        list.add("SD");                           // [SD]
        System.out.println(list);
        list.add(0, "NY");          // [NY,SD]
        System.out.println(list);
        list.set(1, "FL");                        // [NY,FL]
        System.out.println(list.get(0));          // NY
        list.remove("NY");                     // [FL]
        System.out.println(list);
        list.remove(0);                     // []
        System.out.println(list);
//        list.set(0, "?");                       // IndexOutOfBoundsException

        var numbers = Arrays.asList(1, 2, 3);
        System.out.println(numbers);
        numbers.replaceAll(x -> x*2);
        System.out.println(numbers);     // [2, 4, 6]

        remove();
        searching();
        toArray();
    }

    private static void remove(){
        System.out.println("remove");

        var list = new LinkedList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.remove(Integer.valueOf(2));
        System.out.println(list);

        var list1 = new LinkedList<Integer>();
        boolean remove = list1.remove(Integer.valueOf(100));// Returns false
        System.out.println(remove);
//        list1.remove(100); // IndexOutOfBoundsException
    }

    private static void searching(){
        System.out.println("searching");

        var list = List.of("peacock", "chicken", "peacock", "turkey");

        System.out.println(list.indexOf("peacock"));     // 0
        System.out.println(list.lastIndexOf("peacock")); // 2
        System.out.println(list.indexOf("penguin"));     // -1
    }

    private static void toArray(){
        System.out.println("toArray");

        List<String> list = new ArrayList<>();
        list.add("hawk");
        list.add("robin");
        System.out.println(list);
        Object[] objectArray = list.toArray();
        System.out.println(objectArray);
        String[] stringArray = list.toArray(new String[0]);
        System.out.println(stringArray);
        list.clear();
        System.out.println(list);
        System.out.println(objectArray.length); // 2
        System.out.println(stringArray.length); // 2
    }
}
