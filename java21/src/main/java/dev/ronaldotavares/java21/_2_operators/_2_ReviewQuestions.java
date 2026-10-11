package dev.ronaldotavares.java21._2_operators;

public class _2_ReviewQuestions {
    public static void main(String[] args) {
        _15();
        _17();
        _19();
    }
    private static void _15() {
//        int note = 1 * 2 + (long)3;
//        short melody = (byte)(double)(note *= 2);
//        double song = melody;
//        float symphony = (float)((song == 1_000f) ? song * 2L : song);
    }

    private static void _17(){
        System.out.println("question 17:");
        int ticketsTaken = 1;
        int ticketsSold = 3;
        ticketsSold += 1 + ticketsTaken++;
        ticketsTaken *= 2;
        ticketsSold += (long)1;
        System.out.println(ticketsTaken);
        System.out.println(ticketsSold);
    }

    private static void _19() {
        System.out.println("question 19:");
        int start = 7;
        int end = 4;
        end += ++start;
        start = (byte)(Byte.MAX_VALUE + 1);
        System.out.println(start);
        System.out.println(end);
    }
}
