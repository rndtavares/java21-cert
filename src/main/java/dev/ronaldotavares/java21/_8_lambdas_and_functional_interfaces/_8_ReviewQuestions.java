package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class _8_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Lambdas and Functional Interfaces");
        var reviewQuestions = new _8_ReviewQuestions();
        reviewQuestions._12();
        reviewQuestions._26();
        reviewQuestions._assessment_21();

        var practiceExam1 = reviewQuestions.new PracticeExam1();
        practiceExam1._4();
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

    void _assessment_21() {
        System.out.println("Which of the following lines can fill in the blank to print true? (Choose all that apply.)");

//        System.out.println(test(____________________________));
//        System.out.println(test(i::equals(5))); //A
//        System.out.println(test(i -> {i == 5;})); //B
        System.out.println(test((i) -> i == 5)); //C
//        System.out.println(test((int i) -> i == 5)); //D
//        System.out.println(test((int i) -> {return i == 5;})); //E
        System.out.println(test((i) -> {return i == 5;})); //F
    }

    private boolean test(Function<Integer, Boolean> b) {
        return b.apply(5);
    }

    class PracticeExam1{
        public void _4(){
            System.out.println("4 - Which line has the first compiler error?");

            int length = 3;

            for (int i = 0; i<3; i++) {
                if (i%2 == 0) {
                    Supplier<Integer> supplier = () -> length; // A
                    System.out.println(supplier.get());        // B
                } else {
//                    Supplier<Integer> supplier = () -> i;      // C
//                    System.out.println(supplier.get());        // D
                }
            }
        }
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

