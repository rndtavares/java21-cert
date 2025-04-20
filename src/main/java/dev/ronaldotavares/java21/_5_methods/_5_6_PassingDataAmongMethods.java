package dev.ronaldotavares.java21._5_methods;

public class _5_6_PassingDataAmongMethods {
    public static void main(String[] args) {
        int num = 4;
        newNumber(num);
        System.out.println(num); // 4

        passingByValueVsByReference();
        unboxingAndAutoboxing();
    }

    private static void passingByValueVsByReference() {
        int original1 = 1;
        int original2 = 2;
        swap(original1, original2);
        System.out.println(original1); // 1
        System.out.println(original2); // 2
    }

    private static void unboxingAndAutoboxing() {
        int quack = 5;
        Integer quackquack = Integer.valueOf(quack); // Convert int to Integer
        int quackquackquack = quackquack.intValue(); // Convert Integer to int

        Integer quackquack1 = quack; // Autoboxing
        int quackquackquack1 = quackquack1; // Unboxing

        Short tail = 8; // Autoboxing
        Character p = Character.valueOf('p');
        char paw = p; // Unboxing
        Boolean nose = true; // Autoboxing
        Integer e = Integer.valueOf(9);
        long ears = e; // Unboxing, then implicit casting

//        Long badGorilla = 8; // DOES NOT COMPILE

        Character elephant = null;
//        char badElephant = elephant; // NullPointerException

        Integer[] openingHours = { 9, 12 };
        Double[] temperaturesAtZoo = { 74.1, 93.2 };

//        Integer[] winterHours = { 10.5, 17.0 }; // DOES NOT COMPILE
//        Double[] summerHours = { 9, 21 }; // DOES NOT COMPILE

    }

    private static void newNumber(int num) {
        num = 8;
    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
}

class Dog {
    public static void main(String[] args) {
        String name = "Webby";
        speak(name);
        System.out.print(name);
    }
    public static void speak(String name) {
        name = "Georgette";
    }
}

class Dog1 {
    public static void main(String[] args) {
        var name = new StringBuilder("Webby");
        speak(name);
        System.out.print(name); // WebbyGeorgette
    }
    public static void speak(StringBuilder s) {
        s.append("Georgette");
    }
}

class ZooTickets {
    public static void main(String[] args) {
        int tickets = 2; // tickets = 2
        String guests = "abc"; // guests = abc
        addTickets(tickets); // tickets = 2
        guests = addGuests(guests); // guests = abcd
        System.out.println(tickets + guests); // 2abcd
    }
    public static int addTickets(int tickets) {
        tickets++;
        return tickets;
    }
    public static String addGuests(String guests) {
        guests += "d";
        return guests;
    }
}

class Chimpanzee {
    public void climb(long t) {}
    public void swing(Integer u) {}
    public void jump(int v) {}
    public static void main(String[] args) {
        var c = new Chimpanzee();
        c.climb(123);
        c.swing(123);
//        c.jump(123L); // DOES NOT COMPILE
    }
}

class Gorilla1 {
    public void rest(Long x) {
        System.out.print("long");
    }
    public static void main(String[] args) {
        var g = new Gorilla();
//        g.rest(8); // DOES NOT COMPILE
    }
}
