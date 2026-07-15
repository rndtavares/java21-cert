package dev.ronaldotavares.java21.questions;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class Test4 {

    public static void main(String[] args) {
        System.out.println("Test 4");
        var test4 = new Test4();
        test4.daylightSavingTime();
    }

    private static String getAnimalBetter(Integer type) {
        String animal;
        switch (type) {
            case  0:
                animal = "Lion";
                break;
            case  1:
                animal = "Elephant";
                break;
            case  2, 3:
                animal = "Alligator";
                break;
            case  4&4:
            {
                animal = "Crane";
            }
            {
                break;
            }
            case null:
                animal = "null";
            default:
                animal = "Unknowns";
        }
        switch (type) {
            case 0 -> {
                animal = "Lion";
                break;
            }
            case 1 -> animal = "Elephant";
            case 2, 3 -> animal = "Alligator";
            case 4&4 -> animal = "Crane";
//            default -> animal = "Unknown";
        }
        return animal;
    }

    private static String getAnimalBest(int type) {
        var animal = "";
        animal = switch (type)  {
            case  0      -> "Lion";
            case  1      -> "Elephant";
            case  2, 3   -> "Alligator";
            case  4      -> "Crane";
            default      -> "Unknown";
        };
        animal = switch (type)  {
            case  0      : yield "Lion";
            case  1      : {
                "Elephant".substring(3);
                "Elephant".substring(2); //break;
//                yield "Elephant";
            }
            {
                "Elephant".substring(1);
                "Elephant".substring(0); //break;
                yield "Elephant";
            }
            case  2, 3   : yield "Alligator";
            case  4      : yield "Crane";
            default      : yield "Unknown";
        };
        return animal;
    }

    void switches(){
        Integer zooPatrons = Integer.valueOf(1_000);
        switch (zooPatrons) {
            case Integer count when count < 500 : System.out.println("Welcome: " + count); break;
            case Integer count : System.out.println("Welcome: " + count);
        }

    }

    void collections() {
        class Request {
        }
        class RequestCollector {
            //1 : Insert declaration here
//        Queue<Request> container = new LinkedList<Request>();
            Queue<Request> container = new PriorityQueue<Request>();

            //        List<Request> container = new LinkedList<Request>();
            public synchronized void addRequest(Request r) {
                container.add(r);
            }

            public synchronized Request getRequestToProcess() {
                return container.poll();
            }
        }
        //What can be inserted at //1?
    }

    void daylightSavingTime(){

//        Given that Daylight Savings Time ends on Nov 1 at 2 AM in US/Eastern time zone, what will the following code print -
        LocalDateTime ld = LocalDateTime.of(2025, 11, 2, 1, 0);
        ZonedDateTime date = ZonedDateTime.of(ld, ZoneId.of("US/Eastern"));
        date = date.plus(Duration.ofDays(1)); System.out.println(date);
        date = ZonedDateTime.of(ld, ZoneId.of("US/Eastern"));
        date = date.plus(Period.ofDays(1)); System.out.println(date);

//        Important thing to remember here is that Period is used to manipulate dates in terms of days, months, and years,
//        while Duration is used to manipulate dates in terms of hours, minutes, and seconds.
//        Therefore, Period doesn't mess with the time component of the date while
//        Duration may change the time component if the date is close to the DST  boundary.
    }
    
    void streams(){
        var doubles = List.of(1.2, 2.3, 3.4);
        Double collect = doubles.stream().collect(Collectors.averagingDouble(a -> a));
    }

    void arrays(){
        var a = new Object[]{ "aaa", new Object(), new ArrayList(), 10};
//        var b = new Object[]{ "aaa", new Object(), new ArrayList(), {} };
        var c = new Object[]{ "aaa", new Object(), new ArrayList(), new String[]{""} };
//        var d = new Object[1]{ new Object() };

    }

    void randomAccessFile(){
        try {
            RandomAccessFile raf = new RandomAccessFile("file.txt", "a");

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    void runnable(){

        Runnable rA = ()->System.out.println("Hello");
//        Runnable rB = { System.out.println("Hello");}
//        Runnable rC = () -> System.out::println();
//        Runnable rD = (a)->System.out.println(a);
//        Runnable rE = -> System.out.println();
        Runnable rF = ()-> { someMethod(); };
        Runnable rF2 = ()-> { someMethod(); return; };
        Runnable rG = ()-> someMethod();
        Runnable rG2 = this::someMethod;

    }

    int someMethod(){
        return 1;
    }
}
