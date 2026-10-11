package dev.ronaldotavares.java21._11_exceptions_and_localization;

public class _11_2_RecognizingExceptionClasses {
    public static void main(String[] args) {
        System.out.println("Recognizing Exception Classes");

        var recognizingExceptionClasses = new _11_2_RecognizingExceptionClasses();
        recognizingExceptionClasses.runtimeExceptionClasses();
    }

    void runtimeExceptionClasses(){
        System.out.println("Runtime Exception Classes");

        arithmeticException();
        arrayIndexOutOfBoundsException();
        classCastException();
        nullPointerException();
        illegalArgumentException();
        numberFormatException();
    }

    void arithmeticException(){
        System.out.println("Arithmetic Exception");

        try {
            int answer = 11 / 0;
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }

    void arrayIndexOutOfBoundsException(){
        System.out.println("Array Index Out Of Bounds Exception");

        try {
            int[] countsOfMoose = new int[3];
            System.out.println(countsOfMoose[-1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    void classCastException(){
        System.out.println("Class Cast Exception");

        try{
            String type = "moose";

            //Integer number = (Integer) type; // DOES NOT COMPILE
            Object obj = type;

            Integer number = (Integer) obj;  // ClassCastException
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }

    void nullPointerException(){
        System.out.println("Null Pointer Exception");

        try {
            FrogStaticNPE.main(null);
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        try {
            FrogLocalNPE.main(null);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    class FrogStaticNPE {
        static String name;
        public void hop() {
            System.out.print(name.toLowerCase() + " is hopping");
        }
        public static void main(String[] args) {
            new _11_2_RecognizingExceptionClasses().new FrogStaticNPE().hop();
        }
    }

    class FrogLocalNPE {
        public void hop(String name) {
            System.out.print(name.toLowerCase() + " is hopping");
        }
        public static void main(String[] args) {
            new _11_2_RecognizingExceptionClasses().new FrogLocalNPE().hop(null);
        }
    }

    void illegalArgumentException(){
        System.out.println("Illegal Argument Exception");

        try {
            setNumberEggs(-2);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    int numberEggs;
    void setNumberEggs(int numberEggs) {
        if (numberEggs < 0)
            throw new IllegalArgumentException("# eggs must not be negative");
        this.numberEggs = numberEggs;
    }

    void numberFormatException() {
        System.out.println("Number Format Exception");

        try{
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}
