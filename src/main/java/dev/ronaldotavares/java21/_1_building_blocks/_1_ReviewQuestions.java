package dev.ronaldotavares.java21._1_building_blocks;

public class _1_ReviewQuestions {
    public static void main(String[] args) {
        System.out.println("Review Questions for Building Blocks");

        var reviewQuestions = new _1_ReviewQuestions();
        reviewQuestions._5();
        reviewQuestions._19();
        reviewQuestions._21();
    }

    void _5(){
        Bear.main(null);
    }

    void _19(){
        System.out.println("19 - Which are true about the following code? (Choose all that apply.)");

        var num1 = Integer.parseInt("11");
        var num2 = Integer.valueOf("B", 16);
        System.out.println(Integer.max(num1, num2));

//        int i = 0xB;
        int j = 0b011;
        int k = 8;

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
      System.out.print(s.count+"-");
        var var = 4;
   }

}


class Buddy {
    Buddy upper;
    String name;

    public Buddy() {
    }

    public Buddy(String name) {
        this.name = name;
    }

    public Buddy(String name, Buddy upper) {
        this.name = name;
        Buddy b = new Buddy("C");//1
        upper.name = "Z";
        upper = b; //2
    }
    public static void main(String[] args) {
        Buddy b1 = new Buddy("A");     //3
        Buddy b2 = new Buddy("B", b1); //4
        System.out.println(b1); //5
    }
}

class Bear {
    private Bear pandaBear;
    private String id;

    public Bear(String id) {
        this.id = id;
    }

    private void roar(Bear b) {
        System.out.println("Roar!");
        pandaBear = b;
    }

    public static void main(String[] args) {
        Bear brownBear = new Bear("b1");
        Bear polarBear = new Bear("b2");
        brownBear.roar(polarBear);
        polarBear = null;
        brownBear = null;
        System.gc();
    }
}