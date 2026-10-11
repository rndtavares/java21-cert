package dev.ronaldotavares.java21._2_operators;

public class _2_1_UnderstandingJavaOperators {

    public static void main(String[] args) {
        differentOrders();
        operatorPrecedence(2,3);
    }

    private static void differentOrders(){
        int cookies = 4; //left-to-right
        double reward = 3 + 2 * --cookies; //right-to-left
        System.out.println("Zoo animal receives: "+reward+" reward points");
    }

    private static void operatorPrecedence(int height, int length){
        var perimeter1 = 2 * height + 2 * length;
        var perimeter2 = ((2 * height) + (2 * length));
        System.out.println("perimeter1 " + perimeter1);
        System.out.println("perimeter2 " + perimeter2);
    }
}
