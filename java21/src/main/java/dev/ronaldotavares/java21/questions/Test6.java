package dev.ronaldotavares.java21.questions;

import java.util.ArrayList;
import java.util.Date;

public class Test6 {

    public static void main(String[] args) {
        var testClass = new Test6();
        testClass._25();
        testClass._27();
        testClass._28();
        testClass._43();
        testClass._48();
    }

    void _25() {
        System.out.println("25");
        B.main(null);
    }

    void _27() {
        System.out.println("27");
        SortTest.main(null);
    }

    void _28(){
        System.out.println("28");
        TestClasss testClasss = new TestClasss();
        testClasss.useClasses();
    }

    void _43(){
        System.out.println("43");
        Test.main(null);
    }

    void _48(){
        System.out.println("48");
        ArrayTest.main(null);
    }
}


class A {
    A() {
        print();
    }

    void print() {
        System.out.print("A ");
    }
}

class B extends A {
    int i = 4;

    //    final int i =   4;
    public static void main(String[] args) {
        A a = new B();
        a.print();
    }

    void print() {
        System.out.print(i + " ");
    }
}


class Person {
    String name;
    String dob;

    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}

class MySorter {
    public int compare(Person p1, Person p2) {
        return p1.dob.compareTo(p2.dob);
    }
}

class SortTest {
    public static int diff(Person p1, Person p2) {
        return p1.dob.compareTo(p2.dob);
    }

    public static int diff(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    public static void main(String[] args) {
        ArrayList<Person> al = new ArrayList<>();
        al.add(new Person("Paul", "01012000"));
        al.add(new Person("Peter", "01011990"));
        al.add(new Person("Patrick", "01012002"));
        // INSERT CODE HERE

        java.util.Collections.sort(al, (p1, p2) -> p1.dob.compareTo(p2.dob)); //I
        System.out.println("I: " + al);
        java.util.Collections.sort(al, SortTest::diff); //II
        System.out.println("II: " + al);
        java.util.Collections.sort(al, new MySorter()::compare); //III
        System.out.println("III: " + al);
//        java.util.Arrays.sort(al, SortTest::diff); //IV
    }
}

class TestClasss {
    public int i=0;
    public class A {
    }

    public static class B {
    }

    class C {
    }

    protected class D {
    }

    private class E {
    }

    static class F {
    }

    protected static class G {
    }

    private static class H {
    }

    public void useClasses() {
        //1
        new TestClasss().new A();
        new TestClasss.B();
        new A();
        new TestClasss.A();

        //public class
        new A();
        new TestClasss.A();
        new TestClasss().new A();

        //public static class
        new B();
        new TestClasss.B();
//        new TestClasss().new B();

        //package private class
        new C();
        new TestClasss.C();
        new TestClasss().new C();

        //protected class
        new D();
        new TestClasss.D();
        new TestClasss().new D();

        //private class
        new E();
        new TestClasss.E();
        new TestClasss().new E();

        //package private static class
        new F();
        new TestClasss.F();
//        new TestClasss().new F();

        //protected static class
        new G();
        new TestClasss.G();
//        new TestClasss().new G();

        //private static class
        new H();
        new TestClasss.H();
//        new TestClasss().new H();
    }

    public static void staticUseClasses(){
        //2
        new TestClasss().new A();
        new TestClasss.B();
//        new A();
//        new TestClasss.A();

        //public class
//        new A();
//        new TestClasss.A();
        new TestClasss().new A();

        //public static class
        new B();
        new TestClasss.B();
//        new TestClasss().new B();

        //package private class
//        new C();
//        new TestClasss.C();
        new TestClasss().new C();

        //protected class
//        new D();
//        new TestClasss.D();
        new TestClasss().new D();

        //private class
//        new E();
//        new TestClasss.E();
        new TestClasss().new E();

        //package private static class
        new F();
        new TestClasss.F();
//        new TestClasss().new F();

        //protected static class
        new G();
        new TestClasss.G();
//        new TestClasss().new G();

        //private static class
        new H();
        new TestClasss.H();
//        new TestClasss().new H();
    }
}

class Test {
    public static void main(String[] args) {
        int k = 1;
        int[] a = {1};
        k += (k = 4) * (k + 2);
        a[0] += (a[0] = 4) * (a[0] + 2);
        System.out.println(k + " , " + a[0]);
    }
}

class ArrayTest{
    public static void main(String[] args) {
        double daaa[][][] = new double[3][][];
        var d = 100.0;
        double[][] daa = new double[1][1];
        double[] newd = daa[0].clone();

//        FYI:
//        Actually, the clone method is defined in Object class but it has protected access. All array classes override this method and make it public.
//        The clone method returns a shallow clone of an array. For example, if you have,
//        Student[] sa1 = new Student[]{ new Student(), new Student()};// assuming a Student class exists,
//        you can do:
//        Student[] sa2 = sa1.clone();
//        sa2 will now point to a new array of Student objects. But the elements of this new array will point to the same Student objects that were there in the original array.
//        In other words, when you clone an array, the array is cloned but the elements of the array are not cloned.
//        Thus, sa1 == sa2 will be false, but sa1[0] == sa2[0] will be true.

    }
}

