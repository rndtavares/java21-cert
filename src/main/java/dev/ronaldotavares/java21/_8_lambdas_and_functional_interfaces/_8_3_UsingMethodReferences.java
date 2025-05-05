package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

public class _8_3_UsingMethodReferences {
    public static void main(String[] args) {
        // 1 - Calling static Methods
        Converter methodRef1 = Math::round;
        Converter lambda1 = x -> Math.round(x);

        System.out.println(methodRef1.round(100.1));  // 100
        System.out.println(lambda1.round(100.1));  // 100

        // 2 - Calling Instance Methods on a Particular Object
        var str = "Zoo";
        StringStart methodRef2 = str::startsWith;
        StringStart lambda2 = s -> str.startsWith(s);

        System.out.println(methodRef2.beginningCheck("A"));  // false
        System.out.println(lambda2.beginningCheck("A"));  // false

        var str2 = "";
        StringChecker methodRef22 = str2::isEmpty;
        StringChecker lambda22 = () -> str2.isEmpty();

        System.out.println(methodRef22.check());  // true
        System.out.println(lambda22.check());  // true

        StringChecker lambda = () -> str2.startsWith("Zoo");

//        StringChecker methodReference1 = str2::startsWith; // DOES NOT COMPILE
//        StringChecker methodReference2 = str2::startsWith("Zoo"); // DOES NOT COMPILE

        // 3 - Calling Instance Methods on a Parameter
        StringParameterChecker methodRef3 = String::isEmpty;
        StringParameterChecker lambda3 = s -> s.isEmpty();

        System.out.println(methodRef3.check("Zoo"));  // false
        System.out.println(lambda3.check("Zoo"));  // false

        StringTwoParameterChecker methodRef33 = String::startsWith;
        StringTwoParameterChecker lambda33 = (s, p) -> s.startsWith(p);

        System.out.println(methodRef33.check("Zoo", "A"));  // false
        System.out.println(lambda33.check("Zoo", "A"));  // false

        //4 - Calling Constructors
        EmptyStringCreator methodRef4 = String::new;
        EmptyStringCreator lambda4 = () -> new String();

        var myStringRef = methodRef4.create();
        var myStringLambda = lambda4.create();
        System.out.println(myStringRef.equals("Snake"));  // false
        System.out.println(myStringLambda.equals("Snake"));  // false

        StringCopier methodRef44 = String::new;
        StringCopier lambda44 = x -> new String(x);

        var myStringRef44 = methodRef44.copy("Zebra");
        var myStringLambda44 = lambda44.copy("Zebra");
        System.out.println(myStringRef44.equals("Zebra"));  // true
        System.out.println(myStringLambda44.equals("Zebra"));  // true
    }
}

interface LearnToSpeak {
    void speak(String sound);
}

class DuckHelper {
    public static void teacher(String name, LearnToSpeak learner) {
        // Exercise patience (omitted)
        learner.speak(name);
    }
}

class Duckling {
    public static void makeSound(String sound) {
        LearnToSpeak learner = s -> System.out.println(s);
        LearnToSpeak learner1 = System.out::println;
        DuckHelper.teacher(sound, learner);
        DuckHelper.teacher(sound, learner1);
    }
}

interface Converter {
    long round(double num);
}

interface StringStart {
    boolean beginningCheck(String prefix);
}

interface StringChecker {
    boolean check();
}

interface StringParameterChecker {
    boolean check(String text);
}

interface StringTwoParameterChecker {
    boolean check(String text, String prefix);
}

interface EmptyStringCreator {
    String create();
}

interface StringCopier {
    String copy(String value);
}
