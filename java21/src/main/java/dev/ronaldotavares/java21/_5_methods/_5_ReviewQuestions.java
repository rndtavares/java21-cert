package dev.ronaldotavares.java21._5_methods;

public class _5_ReviewQuestions {
    public static void main(String[] args) {
        _5();
        _7();
        _11();
    }
    private static void _5(){
        final int song = 1;
        final Integer song1 = 1;
        final long song2 = 1;
//        final Long song3 = 1;
        final double song4 = 1;
//        final Double song5 = 1;
//        int i = 9.0f;
        double ii = 9.0f;

    }
    private static void _7(){
        System.out.println("question 7");
//        juggle();
        System.out.println(juggle(true));
        System.out.println(juggle(true, true));
        System.out.println(juggle(true, true, true));
//        juggle(true, {true, true});
        System.out.println(juggle(true, new boolean[2]));
    }
    private static int juggle(boolean b, boolean... b2) {
        return b2.length;
    }

    private static void _11(){
        System.out.println("question 11");
        Rope.main(null);
    }
}

class Rope {
   public static void swing() {
      System.out.print("swing");
   }
   public void climb() {
      System.out.println("climb");
   }
   public static void play() {
      swing();
//      climb();
   }
   public static void main(String[] args) {
      Rope rope = new Rope();
      rope.play();
      Rope rope2 = null;
      System.out.print("-");
      rope2.play();
   }
}

