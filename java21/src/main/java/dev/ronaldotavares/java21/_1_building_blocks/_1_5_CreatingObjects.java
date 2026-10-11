package dev.ronaldotavares.java21._1_building_blocks;

public class _1_5_CreatingObjects {
    private void creatingObjects() {
        Chick myChick = new Chick();
    }
}

class Chick {
//    { System.out.println(name); }  // DOES NOT COMPILE
    private String name = "Fluffy";

    { System.out.println("setting field"); }

    public Chick() {
        name = "Tiny";
        System.out.println("setting constructor");
    }

    public void Chick() { }  // NOT A CONSTRUCTOR

    public static void main(String[] args) {
        Chick chick = new Chick();
        System.out.println(chick.name);
    }
}

class Chicken {
    int numEggs = 12;  // initialize on line
    String name;
    public Chicken() {
        name = "Duke";  // initialize in constructor
    }
}

class Swan {
    int numberEggs;                            // instance variable
    public static void main(String[] args) {
        Swan mother = new Swan();
        mother.numberEggs = 1;                  // set variable
        System.out.println(mother.numberEggs);  // read variable
    }
}

class Name {
   String first = "Theodore";
   String last = "Moose";
   String full = first + last;
}

class Bird {
   public static void main(String[] args) {
       { System.out.println("Feathers"); }
   }
   { System.out.println("Snowy"); }
}

class Egg {
    public Egg() {
        number = 5;
    }
    public static void main(String[] args) {
        Egg egg = new Egg();
        System.out.println(egg.number);
    }
    private int number = 3;
    { number = 4; }
}