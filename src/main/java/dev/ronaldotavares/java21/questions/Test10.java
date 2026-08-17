package dev.ronaldotavares.java21.questions;

import java.time.Duration;
import java.time.Period;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Test10 {
    public static void main(String[] args) {
        var test10 = new Test10();
        test10._1();
        test10._12();
        test10._17();
        test10._18();
        test10._20();
        test10._49();
        test10._4();
        test10._30();
    }

    void _1(){
        // What is the result of executing the following fragment of code:
        boolean b1 = false;
        boolean b2 = false;
//        if (b2 != b1 = !b2) {
//        if (false = !b2) {
        if (b2 != b1 == !b2) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
//        false = true;
    }

    void _12(){
        // What will the following code print when compiled and run?
//        var numA = new Integer[]{1, null, 3}; //1
        var numA = new Integer[]{1, 20, 3}; //1
        var list1 = List.of(numA); //2
        var list2 = Collections.unmodifiableList(list1); //3
        var list3 = List.copyOf(Arrays.asList(numA));
        numA[1] = 2; //4
        System.out.println(list1+" "+list2+" "+list3+" "+Arrays.toString(numA));
//        list2.stream().fla
    }

    void _17(){
        // What will the following code print?
        Duration d = Duration.ofHours(25);
        System.out.println(d);
        Period p = Period.ofDays(1);
        System.out.println(p);

        Duration d2 = Duration.ofDays(365);
        System.out.println(d2);
    }

    void _18(){
        Thread t = Thread.ofVirtual().unstarted(() -> System.out.println("Hello from virtual thread!"));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    void _20() {
        var testClass = new TestClass();
        System.out.println(testClass.process(100, 10));
    }
    // What changes, when applied independent of each other, will enable the following code to compile?
    // assume appropriate import statements
    class TestClass {
        public double process(double payment, int rate) {
            double defaultrate = 0.10;        //1
            if(rate>10) defaultrate = rate;  //2
            class Implement{
                public int apply(double data){
//                    Function<Integer, Integer> f = x->x+(int)(x*defaultrate);  //3

//                    Change //3 to:
                    Function<Integer, Integer> f = x->x+(int)(x*rate);

//                    BiFunction<Integer, Double, Integer> f1 = (m, n)->m+(int)(n*m);
//                    return f.apply((int)data, defaultrate);

                    return f.apply((int)data); //4
                }
            }
//            rate++;
            Implement i = new Implement();
            return i.apply(payment);
        }
    }

    void _49(){
//        What will the following code print?
        List<Integer> names = Arrays.asList(1, 2, 3); //1
         names.forEach(x->x=x+1); //2
         names.forEach(System.out::println); //3

        List<Student> students = Arrays.asList(new Student(1), new Student(2), new Student(3));
        students.forEach(x->x.setId(x.getId()+1));
//        students.forEach(x-> x = new Student(x.getId()+1));
        students.forEach(System.out::println);
    }

    void _4(){

//        Given:  enum Card
//         What will the following line of code print?
        Arrays.stream(Card.values()).takeWhile(c->c.isRed()).forEach(System.out::print);
        System.out.println();
        Arrays.stream(Card.values()).dropWhile(c->c.isRed()).forEach(System.out::print);
    }

    void _30(){
        Onion.main(null);
    }
}

class Student{
    private int id;
    public Student(int id) {
        this.id = id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String toString() {
        return "Student{id=" + id + "}";
    }
}

enum Card {
    HEART, CLUB, SPADE, DIAMOND;

    public boolean isRed() {
        return switch (this) {
            case HEART, DIAMOND -> true;
            default -> false;
        };
    }
}


//What will the following code print when compiled and run?
class Onion {
    private String data = "skin";

    private class Layer extends Onion {
        String data = "thegoodpart";

        public String getData() {
            return data;
        }
    }

    public String getData() {
        return new Layer().getData();
    }

    public static void main(String[] args) {
        var o = new Onion();
        System.out.println(o.getData());
    }
}