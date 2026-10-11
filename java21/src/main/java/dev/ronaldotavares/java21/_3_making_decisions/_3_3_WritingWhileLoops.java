package dev.ronaldotavares.java21._3_making_decisions;

public class _3_3_WritingWhileLoops {
    public static void main(String[] args) {
        whileLoop();
        eatCheese(10);
        whileLoopWithCondition();
        doWhileLoop();
        infiniteLoop();
    }

    private static void whileLoop() {
        int counter = 0; 
        while (counter < 10) {    
            double price = counter * 10;    
            System.out.println(price);    
            counter++; 
        }
    }
    
    static int roomInBelly = 5; 
    private static void eatCheese(int bitesOfCheese) {    
        while (bitesOfCheese > 0 && roomInBelly > 0) {       
            bitesOfCheese--;       
            roomInBelly--;    
        }    
        System.out.println(bitesOfCheese+" pieces of cheese left"); 
    }

    private static void whileLoopWithCondition() {
        int full = 5; 
        while (full < 5) {    
		    System.out.println("Not full!");    
		    full++; 
        }
    }

    private static void doWhileLoop() { 
        int lizard = 0; 
        do {    
		    lizard++;
        } while (false); 
        System.out.println(lizard);		
    }

    private static void infiniteLoop() {
        int pen = 2; 
        int pigs = 5; 
        // while (pen < 10)    
            // pigs++;
    }
}
