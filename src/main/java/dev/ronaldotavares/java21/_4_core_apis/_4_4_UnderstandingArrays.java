package dev.ronaldotavares.java21._4_core_apis;

import java.util.Arrays;

public class _4_4_UnderstandingArrays {
    public static void main(String[] args) {
        char[] letters; // String option

        creatingArrayOfPrimitives();
        creatingArrayOfReferenceVariables();
        usingAnArray();
        sorting();
        searching();
        comparing();
        arraysOfArrays();
        usingAnArraysOfArrays();
    }

    private static void creatingArrayOfPrimitives() {
        int[] moreNumbers = new int[]{42, 55, 99};
        int[] moreNumbers1 = {42, 55, 99};

        int[] numAnimals;
        int [] numAnimals2;
        int []numAnimals3;
        int numAnimals4[];
        int numAnimals5 [];

        int[] ids, types;
//        int ids[], types; DOES NOT COMPILE
    }
    
    private static void creatingArrayOfReferenceVariables() {
        String[] bugs = { "cricket", "beetle", "ladybug" };
        String[] alias = bugs;
        String[] anotherArray = { "cricket", "beetle", "ladybug" };
        System.out.println(bugs.equals(alias));        // true 
        System.out.println(bugs.equals(anotherArray)); // false 
        System.out.println(bugs.toString());           // [Ljava.lang.String;@160bc7c0

        class Names {   
            String names[];
        }

        class Names1 {
            String names[] = new String[2];
        }

        String[] strings = { "stringValue" };
        Object[] objects = strings;
        String[] againStrings = (String[]) objects;
//        againStrings[0] = new StringBuilder();   // DOES NOT COMPILE
//        objects[0] = new StringBuilder();        // Careful!
    }

    private static void usingAnArray() {
        String[] mammals = {"monkey", "chimp", "donkey"};
        System.out.println(mammals.length);           // 3
        System.out.println(mammals[0]);               // monkey
        System.out.println(mammals[1]);               // chimp
        System.out.println(mammals[2]);               // donkey

        String[] mammals1 = {"monkey", "chimp", "donkey"};
//        System.out.println(mammals1.length());           // DOES NOT COMPILE

        var birds = new String[6];
        System.out.println(birds.length);

        var numbers = new int[10];
        for (int i = 0; i < numbers.length; i++)
           numbers[i] = i + 5;
        for(int n : numbers)
           System.out.println(n);

        var numbers1 = new int[10];
//        numbers1[10] = 3;
        
//        numbers1[numbers1.length] = 5;
        
//        for (int i = 0; i <= numbers1.length; i++)
//           numbers1[i] = i + 5;
    }
    
    private static void sorting() {
        int[] numbers = { 6, 9, 1 };
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++)   
            System.out.print(numbers[i] +  " ");

        String[] strings = { "10", "9", "100" };
        Arrays.sort(strings);
        for (String s : strings)   
            System.out.print(s + " ");
    }

    private static void searching() {
        int[] numbers = {2,4,6,8};
        System.out.println(Arrays.binarySearch(numbers, 2)); // 0
        System.out.println(Arrays.binarySearch(numbers, 4)); // 1
        System.out.println(Arrays.binarySearch(numbers, 1)); // -1
        System.out.println(Arrays.binarySearch(numbers, 3)); // -2
        System.out.println(Arrays.binarySearch(numbers, 9)); // -5

        int[] numbers1 = new int[] {3,2,1};
        System.out.println(Arrays.binarySearch(numbers1, 2));
        System.out.println(Arrays.binarySearch(numbers1, 3));
    }
    
    private static void comparing() {
        System.out.println(new int[] {1} == new int[] {1});                 // false 
        System.out.println(Arrays.equals(new int[] {1}, new int[] {1}));    // true
        System.out.println(Arrays.equals(new int[] {1}, new int[] {2}));    // false 
        System.out.println(Arrays.equals(new int[] {1}, new int[] {1, 2})); // false

        System.out.println(Arrays.compare(new int[] {1}, new int[] {2}));

//        System.out.println(Arrays.compare(new int[] {1}, new String[] {"a"})); // DOES NOT COMPILE

        mismatch();
    }

    private static void mismatch() {
        System.out.println(Arrays.mismatch(new int[] {1}, new int[] {1}));
        System.out.println(Arrays.mismatch(new String[] {"a"}, new String[] {"A"}));
        System.out.println(Arrays.mismatch(new int[] {1, 2}, new int[] {1}));
    }
    
    private static void arraysOfArrays() {
        int[][] vars1;               // 2D array 
        int vars2 [][];              // 2D array 
        int[] vars3[];               // 2D array 
        int[] vars4 [], space [][];  // 2D and 3D arrays

        String [][] rectangle = new String[3][2];
        rectangle[0][1] = "set";

        int[][] differentSizes = {{1, 4}, {3}, {9,8,7}};

        int [][] args = new int[2][];
        args[0] = new int[5];
        args[1] = new int[3];
    }

    private static void usingAnArraysOfArrays() {
        var twoD = new int[3][2];
        for(int i = 0; i < twoD.length; i++) {   
            for(int j = 0; j < twoD[i].length; j++)       
                System.out.print(twoD[i][j] + " "); // print element
            System.out.println();                  // time for a new row 
        }

        for(int[] inner : twoD) {   
            for(int num : inner)       
                System.out.print(num + " ");
            System.out.println();
        }
    }
    
}
