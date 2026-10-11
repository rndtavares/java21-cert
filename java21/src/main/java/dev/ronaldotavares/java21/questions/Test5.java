package dev.ronaldotavares.java21.questions;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.*;

public class Test5 {
    public static void main(String[] args) {
        var testClass = new TestClass();
        testClass.main(null);
    }

    void properties(){
        for(var x : System.getProperties().entrySet()){
            var m = x.getKey();
        }

        for(var x : System.getProperties().keySet()){
//            System.out.println(x.length());
            System.out.println(((String)x).length());
        }
    }
}

class TestClass {
    public static void main(String[] args){
        var day = LocalDate.now().with(FRIDAY).getDayOfWeek();
        switch(day){
            case MONDAY:
                TUESDAY:
                WEDNESDAY:
                THURSDAY:
                FRIDAY:
                System.out.println("working");
            case DayOfWeek.SATURDAY:
                    SUNDAY:
                    System.out.println("off");
        }
    }
}