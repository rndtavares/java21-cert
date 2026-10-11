package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.io.IOException;

public class _11_1_UnderstandingExceptions {
    public static void main(String[] args) throws Exception {
        System.out.println("Understanding Exceptions");
        returnCode();

        var understandingExceptions = new _11_1_UnderstandingExceptions();
        understandingExceptions.fall(1);
        understandingExceptions.fallHandle(1);
        understandingExceptions.fall("test");
        understandingExceptions.throwingExceptions();
        understandingExceptions.callingMethodsThatThrowExceptions();
        understandingExceptions.overridingMethodsWithExceptions();
        understandingExceptions.printingAnException();
    }

    private static void returnCode(){
        System.out.println("Return Code");
        int i = indexOf(new String[]{"Ronaldo", "Tavares"}, "da Silva");
        System.out.println(i); // -1
    }

    private static int indexOf(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) { return i; }
        }
        return -1;
    }

    void fall(int distance) throws IOException {
        if(distance> 10) {
            throw new IOException();
        }
    }

    void fallHandle(int distance) {
        try {
            if(distance > 10) {
                throw new IOException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fall(String input) {
        System.out.println(input.toLowerCase());
    }

    void throwingExceptions() throws Exception {
        System.out.println("Throwing Exceptions");
        String[] animals = new String[0];
//        System.out.println(animals[0]); // ArrayIndexOutOfBoundsException

//        throw new Exception();
//        throw new Exception("Ow! I fell.");

//        throw new RuntimeException();
//        throw new RuntimeException("Ow! I fell.");

//        var e = new RuntimeException();
//        throw e;

//        throw RuntimeException(); // DOES NOT COMPILE

        try {
            throw new RuntimeException();
//            throw new ArrayIndexOutOfBoundsException();  // NÃO COMPILA
        } catch (Exception e) {}
    }

    void callingMethodsThatThrowExceptions(){
        System.out.println("Calling Methods That Throw Exceptions");

        var bunny = new Bunny();
        bunny.hopAround();
    }

    class NoMoreCarrotsException extends Exception {}

    public class Bunny {
        private void eatCarrot() {}
        private void eatCarrotException() throws NoMoreCarrotsException {}
        public void hopAround() /* throws NoMoreCarrotsException */ {
            try {
                eatCarrotException(); // DOES NOT COMPILE
            } catch (NoMoreCarrotsException e) {
                System.out.print("sad rabbit");
            }
        }
        public void bad() {
//            try {
                eatCarrot();
//            } catch (NoMoreCarrotsException e) { // DOES NOT COMPILE
//                System.out.print("sad rabbit");
//            }
        }
    }

    void overridingMethodsWithExceptions(){
        System.out.println("Overriding Methods With Exceptions");

        new Hopper().hop();
        new Bunny1().hop();
    }

    class CanNotHopException extends Exception {}

    class Hopper {
        public void hop() {}
    }

//    public class Bunny extends Hopper {
//        public void hop() throws CanNotHopException {} // DOES NOT COMPILE
//    }

    class Hopper1 {
        public void hop() throws CanNotHopException {}
    }

    public class Bunny1 extends Hopper1 {
        public void hop() {} // OK
    }

    private void printingAnException(){
        System.out.println("Printing An Exception");

        try {
            hop();
        } catch (Exception e) {
            System.out.println(e + "\n");
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void hop() {
        throw new RuntimeException("cannot hop");
    }
}
