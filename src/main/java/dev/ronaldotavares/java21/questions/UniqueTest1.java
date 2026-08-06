package dev.ronaldotavares.java21.questions;

import java.time.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UniqueTest1 {
    public static void main(String[] args) {
        var uniqueTest1 = new UniqueTest1();
        uniqueTest1._7();
        uniqueTest1._10();
        uniqueTest1._17();
        uniqueTest1._22();
        uniqueTest1._24();
        uniqueTest1._51();
    }

    private void _7() {
        System.out.println("question 7");

    }

    private void _10() {
        System.out.println("question 10");
        Powwow.main(null);
    }

    private void _17() {
        System.out.println("question 17");
        ArrayList<Double> al = new ArrayList<>();
//        al.add(111);
        System.out.println(al.indexOf(1.0));
        System.out.println(al.indexOf("string"));
        System.out.println(al.contains("string"));
//        Double d = al.get(al.length);
    }

    private void _22() {
        System.out.println("question 22");
//        Given that Daylight Savings Time ends on Nov 6th 2022 at 2 AM in US/Eastern time zone.
//        (As a result, 2 AM becomes 1 AM. In other words, one minute after 1.59 AM, the clock shows 1 AM instead of 2 AM.),
//        what will the following code print?
        LocalDateTime ld = LocalDateTime.of(2022, Month.NOVEMBER, 6, 1, 30);
        ZonedDateTime date1 = ZonedDateTime.of(ld, ZoneId.of("US/Eastern"));
        ZonedDateTime date2 = date1.plusHours(1);
        System.out.println(date1.getOffset());
        System.out.println(date2.getOffset());
        System.out.println(date1.getOffset().compareTo(date2.getOffset()));
        System.out.println(Duration.ofSeconds(date1.getOffset().compareTo(date2.getOffset())));
        System.out.println(Duration.ofSeconds(date1.getOffset().compareTo(date2.getOffset())).toHours());
    }

    private void _24() {
        System.out.println("question 24");
    }

    private void _51() {
        System.out.println("question 51");

//        List.of(Coffee.values()).stream().forEach(e->{System.out.print(e+":"+e.value()+", ");});
        List.of(Coffee.values()).stream().forEach(e->{System.out.print(e.name()+":"+e+", ");});
//        Coffee.values().forEach(e->{System.out.print(e.name()+":"+e+", ");});
        System.out.println();
        List.of(Coffee.values()).forEach(e->{System.out.print(e+":"+e.name()+", ");});
//        Coffee.forEach(e->{System.out.print(e.strength+":"+e.name()+", ");});

    }
}


//Given:
class Booby{ }
class Dooby extends Booby{ }
class Tooby extends Dooby{ }
class TestClassU1 {
    Booby b = new Booby();
    Tooby t = new Tooby();
    public void do1(List<? super Dooby> dataList){
//        1 INSERT CODE HERE
//       b = dataList.get(0); //1
//       t = dataList.get(0); //2
//       dataList.add(b); //3
       dataList.add(t); //4
    }
    public void do2(List<? extends Dooby> dataList){
       b = dataList.get(0); //1
//       t = dataList.get(0); //2
//       dataList.add(b); //3
//        dataList.add(t); //4
    }
}
//and the following four statements:
//1.   b = dataList.get(0);
//2.   t = dataList.get(0);
//3.   dataList.add(b);
//4.   dataList.add(t);
//What can be inserted in the above code?


//What will the following code print when compiled and run?
interface Pow {
    static void wow() {
        System.out.println("In Pow.wow");
    }
}

abstract class Wow {
    static void wow() {  // LINE 9
        System.out.println("In Wow.wow");
    }
}

class Powwow extends Wow implements Pow {
    public static void main(String[] args) {
        Powwow f = new Powwow();
        f.wow();

//        Pow powwow = new Powwow();
//        powwow.wow();
    }
}

//Given:
class BaseU1 {
    public <T> List<T> transform(List<T> list) {
        return new ArrayList<T>();
    }
}

class DerivedU1 extends BaseU1 {
    /*INSERT CODE HERE*/

    public <Number> ArrayList<Number> transform(List<Number> list) {
        return new ArrayList<Number>();
    }

//    public ArrayList<Object> transform(List<Object> list) {
//        return new ArrayList<Object>();
//    }
//
//    public <T> ArrayList<T> transform(List<T> list) {
//        return new ArrayList<T>();
//    }
//
//    public <T> Collection<T> transform(List<T> list) {
//        return new ArrayList<T>();
//    }
//
//    public <T> Collection<T> transform(Collection<T> list) {
//        return new HashSet<T>();
//    }
}
//What can be inserted in the above code?


enum Coffee {
    ESPRESSO("Very Strong"), MOCHA("Bold"), LATTE("Mild");
    public String strength;

    Coffee(String strength) {
        this.strength = strength;
    }

    public String toString() {
        return strength;
    }
}
//Which of the given code snippets will produce the following output: ESPRESSO:Very Strong, MOCHA:Bold, LATTE:Mild,

class SuperClass {}
class SubClass extends SuperClass {}

class GenericsUse{
    public static void main(String[] args) {
        List<? super SuperClass> lista = new LinkedList<>();
        lista.add(new SuperClass());
        lista.add(new SubClass());
        lista.add(null);

        Object object = lista.get(0);

        List<? extends SuperClass> listaEx = new LinkedList<>();
//        List<? extends SuperClass> listaEx = List.of(new SuperClass(), new SubClass());
//        listaEx.add(new SubClass());
//        listaEx.add(new SuperClass());
//        listaEx.add(new Object());
        listaEx.add(null);

        SuperClass superClass = listaEx.get(0);
//        SubClass subClass = listaEx.get(0);
    }
}