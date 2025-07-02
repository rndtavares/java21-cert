package dev.ronaldotavares.java21._1_building_blocks;

public class _1_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Building Blocks");

        var reviewQuestions = new _1_ReviewQuestions();
        reviewQuestions._19();
        reviewQuestions._21();
    }

    void _19(){
        System.out.println("19 - Which are true about the following code? (Choose all that apply.)");

        var num1 = Integer.parseInt("11");
        var num2 = Integer.valueOf("B", 16);
        System.out.println(Integer.max(num1, num2));
    }

    void _21(){
        System.out.println("21 - What is the output of executing the following class?");

        Salmon.main(null);
    }
}

class Salmon {
   int count;
   { System.out.print(count+"-"); }
   { count++; }
   public Salmon() {
      count = 4;
      System.out.print(2+"-");
   }
   public static void main(String[] args) {
      System.out.print(7+"-");
      var s = new Salmon();
      System.out.print(s.count+"-"); }
}
