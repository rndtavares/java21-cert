package dev.ronaldotavares.java21._5_methods;

public class _5_3_WorkingWithVarargs {
    public static void main(String[] args) {
        var visitAttractions = new VisitAttractions();
        // Pass an array
        int[] data = new int[] {1, 2, 3};
        visitAttractions.walk1(data);

        // Pass a list of values
        visitAttractions.walk1(1,2,3);

        visitAttractions.walk1();

        run(11, 77); // 77


    }

    private static void run(int... steps) {
        System.out.println(steps[1]);
    }
}

class VisitAttractions {
    public void walk1(int... steps) {
        int[] step2 = steps; // Not necessary, but shows steps is of type int[]
        System.out.println(step2.length);
    }
    public void walk2(int start, int... steps) {}
//    public void walk3(int... steps, int start) {} // DOES NOT COMPILE
//    public void walk4(int... start, int... steps) {} // DOES NOT COMPILE
}

class DogWalker {
    public static void walkDog(int start, int... steps) {
        System.out.println(steps.length);
    }
    public static void main(String[] args) {
        walkDog(1); // 0
        walkDog(1, 2); // 1
        walkDog(1, 2, 3); // 2
        walkDog(1, new int[] {4, 5}); // 2
//        walkDog(1, null); // Triggers NullPointerException in walkDog()
    }
}