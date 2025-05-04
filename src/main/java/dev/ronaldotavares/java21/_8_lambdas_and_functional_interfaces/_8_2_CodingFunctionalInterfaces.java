package dev.ronaldotavares.java21._8_lambdas_and_functional_interfaces;

public class _8_2_CodingFunctionalInterfaces {
}

@FunctionalInterface
interface Sprint {
    public void sprint(int speed);
}

class Tiger implements Sprint {
    public void sprint(int speed) {
        System.out.println("Animal is sprinting fast! " + speed);
    }
}

//@FunctionalInterface  // DOES NOT COMPILE
interface Dance {
    void move();
    void rest();
}

//functional interface
interface Dash extends Sprint {}

//non functional interface
interface Skip extends Sprint {
    void skip();
}

//non functional interface
interface Sleep {
    private void snore() {}
    default int getZzz() { return 1; }
}

//functional interface
interface Climb {
    void reach();
    default void fall() {}
    static int getBackUp() { return 100; }
    private static boolean checkHeight() { return true; }
}

//non functional interface
interface Soar {
    abstract String toString();
}

//functional interface
interface Dive {
    String toString();
    public boolean equals(Object o);
    public abstract int hashCode();
    public void dive();
}

//non functional interface
interface Hibernate {
    String toString();
    public boolean equals(Hibernate o);
    public abstract int hashCode();
    public void rest();
}