package dev.ronaldotavares.java21._1_building_blocks;

public class _1_3_WritingAMainMethod {

    private void javaCommands(){
//        from the project path /java21-cert
//        compile a file
//        javac ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/_1_3_WritingAMainMethod.java

//        run a file with classpath, package and parameters
//        java -classpath ./src/main/java dev.ronaldotavares.java21._1_building_blocks._1_3_WritingAMainMethod Bronx Zoo

//        run a file with classpath, package and parameters with spaces
//        java -classpath ./src/main/java dev.ronaldotavares.java21._1_building_blocks._1_3_WritingAMainMethod "San Diego" Zoo

//        run a file with classpath, package and not enough parameters that throws: Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
//        java -classpath ./src/main/java dev.ronaldotavares.java21._1_building_blocks._1_3_WritingAMainMethod Zoo

//        single command for compile and run a file with classpath, package and parameters
//        java -classpath ./src/main/java dev.ronaldotavares.java21._1_building_blocks._1_3_WritingAMainMethod Bronx Zoo
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(args[0]);
        System.out.println(args[1]);
    }
}
class alterateMainParameter {
    public static void main(String... options) {
        System.out.println("Hello World");
    }
}
class anotherAlternateMainParameter {
    public static void main(String friends[]) {
        System.out.println("Hello World");
    }
}

class finalMainMethod {
    public static final void main(String[] args) {
        System.out.println("Hello World");
    }
}

class finalMainParameter {
    public static void main(final String[] args) {
        System.out.println("Hello World");
    }
}

class finalMainMethodAndParameter {
    public static final void main(final String[] args) {
        System.out.println("Hello World");
    }
}
