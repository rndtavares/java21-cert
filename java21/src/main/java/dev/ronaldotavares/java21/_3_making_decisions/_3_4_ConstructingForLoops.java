package dev.ronaldotavares.java21._3_making_decisions;

import java.util.List;

public class _3_4_ConstructingForLoops {
    public static void main(String[] args) {
        invalidVariableScope();
        validVariableScope();
        forLoop();
        reverseForLoop();
        loopVariations();
        forEachLoop();
    }

    private static void invalidVariableScope() {
        for (int i = 0; i < 10; i++)    
		    System.out.println("Value is: "+i); 
        // System.out.println(i);  // DOES NOT COMPILE 
    }

    private static void validVariableScope() {
        int i; 
        for (i = 0; i < 10; i++)    
            System.out.println("Value is: "+i); 
        System.out.println(i);
    }

    private static void forLoop() {
        for (int i = 0; i < 5; i++) {    
            System.out.print(i + " "); 
        }
        System.out.println("");
    }
    
    private static void reverseForLoop() {
        for (var counter = 5; counter > 0; counter--) {    
            System.out.print(counter + " "); 
        }
        System.out.println("");

        for (var counter = 4; counter> 0; counter--) {    
            System.out.print(counter + " "); 
        }
        System.out.println("");

        for (var counter = 4; counter >= 0; counter--) {    
            System.out.print(counter + " "); 
        }
        System.out.println("");
    }

    private static void loopVariations() {
        //1. infinite loop
        // for ( ; ; )    
            // System.out.println("Hello World");

        //2. Adding Multiple Terms to the for Statement
        int x = 0; 
        for (long y = 0, z = 4; x < 5 && y < 10; x++, y++) {    
                System.out.print(y + " "); } 
        System.out.print(x + " ");

        //3. Redeclaring a Variable in the Initialization Block
        // int x1 = 0; 
        // for (int x1 = 4; x < 5; x1++)   // DOES NOT COMPILE    
	        // System.out.print(x1 + " ");
        // }

        int x1 = 0; 
        for ( ; x1 < 5; x1++)    
        	System.out.print(x1 + " ");

        //4. Using Incompatible Data Types in the Initialization Block
        // int x2 = 0; 
        // for (long y = 0, int z = 4; x2 < 5; x2++)  // DOES NOT COMPILE    
		    // System.out.print(y + " ");

        //5. 
        for (long y = 0, x2 = 4; x < 5 && y < 10; x++, y++)    
	        System.out.print(y + " "); 
        // System.out.print(x2);  // DOES NOT COMPILE

        System.out.println("");
    }

    private static void forEachLoop() {
        String[] names = {"Ronaldo", "Tavares", "Java 21"};
        printNamesFor(names);
        printNamesForEach(names);
        printNamesForEachIterable(List.of(names));
    }

    private static void printNamesFor(String[] names) {    
        for (int counter = 0; counter < names.length; counter++)       
                System.out.println(names[counter]); 
    }   

    private static void printNamesForEach(String[] names) {    
        for (var name : names)       
                System.out.println(name); 
    }

    private static void printNamesForEachIterable(List<String> names) {    
		for (var name : names)       
            System.out.println(name); 
    }

    private static void invalidForEach(){
        String birds = "Jay"; 
        // for (String bird : birds)  // DOES NOT COMPILE    
		    // System.out.print(bird + " ");   
		
        String[] sloths = new String[3]; 
        // for (int sloth : sloths)    // DOES NOT COMPILE    
            // System.out.print(sloth + " ");
    }
}
