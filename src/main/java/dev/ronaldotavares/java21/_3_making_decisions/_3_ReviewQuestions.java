package dev.ronaldotavares.java21._3_making_decisions;

import java.util.ArrayList;
import java.util.List;

public class _3_ReviewQuestions {
    public static void main(String[] args) {
//        _1();
//        _3();
//        _5_printReptile(6);
//        _6();
//        _7(new int[]{1, 2});
//        _8_printType(11);
        _9();
//        new _11_Zoo().printLocation(_11_Zoo.Animal.MAMMAL);
//        _12();
//        _13_getHatSize(9f);
//        _14();
//        _15();
    }

    private static void _1(){
//        System.out.println("question 1");
//        var a = 1;
//        var b = 2;
//        Object skips = 10;
//        switch (skips) {
//           case a when a ˂ 10  -> System.out.print(2);
//           case b when b ˃= 10 -> System.out.print(4);
//           case null -> System.out.print(6);
//           default   -> System.out.print(8);
//        }
    }

    private static void _3() {
        System.out.println("question 3");
        int temperature = 4;
        long humidity = -temperature + temperature * 3;
        if (temperature>=4)
        if (humidity < 6) System.out.println("Too Low");
        else System.out.println("Just Right");
        else System.out.println("Too High");
    }

    private static void _5_printReptile(int category) {
//        System.out.println("question 5");
//        var type = switch (category) {
//            case 1,2 -> "Snake";
//            case 3,4 -> "Lizard";
//            case 5,6 -> "Turtle";
//            case 7,8 -> "Alligator";
//        };
//        System.out.print(type);
    }

    private static void _6(){
//        List<Integer> myFavoriteNumbers = new ArrayList<>();
//        myFavoriteNumbers.add(10);
//        myFavoriteNumbers.add(14);
//        for (var a : myFavoriteNumbers) {
//            System.out.print(a + ", ");
//            break;
//        }
//
//        for (int b : myFavoriteNumbers) {
//            continue;
//            System.out.print(b + ", ");
//        }
//
//        for (Object c : myFavoriteNumbers)
//            System.out.print(c + ", ");
    }

    private static void _7(int[] weather) {
        System.out.println("question 7");
//            for (__________________) {
//        for(int i=weather.length; i>0; i--)
//            System.out.println(weather[i]);

        for(int i=0; i<=weather.length-1; ++i) // ok
//        for(var w : weather) // nok
//        for(int i=weather.length-1; i>=0; i--) // ok
//        for(int i=0, int j=3; i<weather.length; ++i) // nok
//        for(int i=0; ++i<10 && i<weather.length;) // nok
            System.out.println(weather[i]);
    }

    private static void _8_printType(Object o) {
//       if (o instanceof Integer bat) {
//          System.out.print("int");
//       } else if (o instanceof Integer bat && bat < 10) {
//          System.out.print("small int");
//       } else if (o instanceof Long bat || bat <= 20) {
//          System.out.print("long");
//       } default {
//          System.out.print("unknown");
//       }
    }

    private static void _9() {
        // executions:
        // (0 + 1)
        // (1 + 1) % 2 = 0
        // (2 + 1)
        // (0 + 2) % 2 = 0
        // (1 + 2)
        // (2 + 2) % 2 = 0
        // (0 + 3)
        // (1 + 3) % 2 = 0
        // (2 + 3)
        int count = 0;
        BUNNY: for (int row = 1; row <=3; row++)
        RABBIT: for (int col = 0; col <3 ; col++) {
            if ((col + row) % 2 == 0)
//                break BUNNY;          // Opção A - nok - sai do for externo no primeiro match do if (1 + 1)
//                break RABBIT;         // Opção B - ok  - sai do for interno a cada match do if
//                continue BUNNY;       // Opção C - ok  - vai para a próxima iteração do for externo. É equivalente a opção B que sai do for interno a cada match do if
//                continue RABBIT;      // Opção D - nok - vai para a próxima iteração do for interno a cada match
//                break;                // Opção E - ok  - equivalente a opção B - sai do for interno a cada match do if
//                continue;               // Opção F - nok - equivalente a opção D - vai para a próxima iteração do for interno a cada match
            count++;
        }
        System.out.println("question 9");
        System.out.println(count);
    }

    private static void _12() {
        int sing = 8, squawk = 2, notes = 0;
        while (sing > squawk) {
           sing--;
           squawk += 2;
           notes += sing + squawk;
        }
        System.out.println("question 12");
        System.out.println(notes);
    }

    private static void _13_getHatSize(Number measurement) {
//       return switch (measurement) {
//          case Double d -> 1 + d.intValue();
//          case null     -> 11;
//          case !(Number n) -> 3 + n.intValue();
//          case Float f when f < 10 -> 4 + f.intValue();
//       };
    }

    private static void _14() {
//        boolean keepGoing = true;
//        int result = 15, meters = 10;
//        do {
//           meters--;
//           if (meters==8) keepGoing = false;
//           result -= 2;
//        } while keepGoing;
//        System.out.println(result);
    }

    private static void _15() {
        System.out.println("question 15");
        for (var penguin : new int[2])
            System.out.println(penguin);

        var ostrich = new Character[3];
        for (var emu : ostrich)
            System.out.println(emu);

        List<Integer> parrots = new ArrayList<Integer>();
        for (var macaw  : parrots)
            System.out.println(macaw);
    }
}

enum _10_DayOfWeek {
   SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
   private DayOfWeek getWeekDay(int day, final int thursday) {
      int otherDay = day;
      int Sunday = 0;
      switch (otherDay) {
         default:
//         case 1: continue;
//         case thursday: return DayOfWeek.THURSDAY;
         case 2,10: break;
//         case Sunday: return DayOfWeek.SUNDAY;
//         case DayOfWeek.MONDAY: return DayOfWeek.MONDAY;
      }
      return DayOfWeek.FRIDAY;
   } }

class _11_Zoo {
   enum Animal {BIRD, FISH, MAMMAL}
   void printLocation(Animal a) {
      long type = switch (a) {
         case BIRD -> 1;
         case FISH -> 2;
         case MAMMAL -> 3;
         default -> 4;
      };
      System.out.println("question 11");
      System.out.println(type);
   } }
