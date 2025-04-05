package dev.ronaldotavares.java21._2_operators;

public class _2_6_MakingDecisionsWithTheTernaryOperator {

    public static void main(String[] args) {
        ternaryOperator();
    }

    private static void ternaryOperator() {
        int owl = 5;
        int food;
        if(owl < 2) {
            food = 3;
        } else {
            food = 4;
        }
        System.out.println(food);  // 4

        int owl0 = 5;
        int food0 = owl0 < 2 ? 3 : 4;
        System.out.println(food0);  // 4

        int food1 = owl < 4 ? owl> 2 ? 3 : 4 : 5;
        int food2 = (owl < 4 ? ((owl> 2) ? 3 : 4) : 5);
        System.out.println(food1);
        System.out.println(food2);

        int stripes = 7;  
        System.out.println((stripes> 5) ? 21 : "Zebra");  
//        int animal = (stripes < 9) ? 3 : "Horse";  // DOES NOT COMPILE

        int sheep = 1;
        int zzz = 1;
        int sleep = zzz<10 ? sheep++ : zzz++;
        System.out.println(sheep + "," + zzz);  // 2,1

        int sheep1 = 1;
        int zzz1 = 1;
        int sleep1 = sheep1 >=10 ? sheep1++ : zzz1++;
        System.out.println(sheep1 + "," + zzz1);  // 1,2
    }
}
