package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

import java.util.List;
import java.util.function.Function;

public class _8_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Lambdas and Functional Interfaces");
        var reviewQuestions = new _8_ReviewQuestions();
        reviewQuestions._12();
        reviewQuestions._26();
    }

    void _12(){
        System.out.println("12 - What does the following code output?");
        Function<Integer, Integer> s = a -> a + 4;
        Function<Integer, Integer> t = a -> a * 3;
        Function<Integer, Integer> c = s.compose(t);
        System.out.print(c.apply(1));
        var var = 1;
    }

    void _26(){
        System.out.println("26 - What is the output of the following program?");
        Animals.main(null);
    }


}

enum Animals {
   MAMMAL(List.of(2,4)),
   INVERTEBRATE(List.of(2, 4, 6, 8, 100)),
   BIRD(null) {
      public int stand() {
         return legs.get(0) + 4;
      }
   };
   List<Integer> legs;
   Animals(List<Integer> legs) {
      this.legs = legs;
   }
   public int stand() { return legs.get(0); }
   public static void main(String[] a) {
      Animals.BIRD.legs = List.of(-1);
      System.out.println(Animals.BIRD.stand());
   }
}

