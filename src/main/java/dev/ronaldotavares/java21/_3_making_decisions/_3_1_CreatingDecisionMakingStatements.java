package dev.ronaldotavares.java21._3_making_decisions;

public class _3_1_CreatingDecisionMakingStatements {
	public static void main(String[] args) {
		statementsAndBlocks();
		ifStatement();
		elseStatement();
		patternMatching();
	}

	private static void statementsAndBlocks() {
		var patrons = 0;
		
		// Single statement 
		patrons++;
		
		// Statement inside a block 
		{
			patrons++; 
		}
		
		var ticketsTaken = 2;
		
		// Single statement 
		if (ticketsTaken> 1)
		patrons++;
		
		// Statement inside a block 
		if (ticketsTaken> 1) {
			patrons++; 
		}
	}
	
	private static void ifStatement() {
		var hourOfDay = 10;
		
		if (hourOfDay < 11)    
		System.out.println("Good Morning");
		
		
		var morningGreetingCount = 0;
		if (hourOfDay < 11) {
			System.out.println("Good Morning");
			morningGreetingCount++; 
		}	

		// if (hourOfDay) {  // DOES NOT COMPILE    
			//… 
		// }
	}

	private static void elseStatement() {
		var hourOfDay = 12;
		if (hourOfDay < 11) {    
			System.out.println("Good Morning"); 
		} 
		if (hourOfDay>= 11) {    
			System.out.println("Good Afternoon"); 
		}

		if (hourOfDay < 11) {
			System.out.println("Good Morning"); 
		} else System.out.println("Good Afternoon");

		if (hourOfDay < 11) {    
			System.out.println("Good Morning");
		} else if (hourOfDay < 15) {
		    System.out.println("Good Afternoon"); 
		} else {   
			System.out.println("Good Evening"); 
		}
	}

	private static void patternMatching() {
		compareIntegers(5);
		reassigningPatternVariables(8);
		printIntegersGreaterThan5(10);
		patternMatchingWithNull();
		flowScoping();
		
	}

	private static void supportedTypes() {
		 Number bearHeight = Integer.valueOf(123); 
		 
		if (bearHeight instanceof Integer i) {} 
		if (bearHeight instanceof Number n) {} 
		// if (bearHeight instanceof String s) {} // DOES NOT COMPILE 
		if (bearHeight instanceof Object o) {}
	}

	private static void compareIntegers(Number number) {
		if (number instanceof Integer) {
			Integer data = (Integer)number;
			System.out.println(data.compareTo(5));
		}

		if (number instanceof Integer data) {
			System.out.println(data.compareTo(5));
		}
	}

	private static void reassigningPatternVariables(Number number) {
		if (number instanceof Integer data) {    
			data = 10; 
		}

		if (number instanceof final Integer data) {    
			// data = 10;  // DOES NOT COMPILE 
		}
	}

	private static void printIntegersGreaterThan5(Number number) {    
		if (number instanceof Integer data && data.compareTo(5) > 0)
			System.out.println(data); 
	}
	
	private static void patternMatchingWithNull() {
		String noObjectHere = null;   

		if(noObjectHere instanceof String)    
				System.out.println("Not printed");   
				
		if(noObjectHere instanceof String s)    
				System.out.println("Still not printed");   
				
		if(noObjectHere instanceof String s && s.length() > -1)    
				System.out.println("Nope, not this one either");
	}

	private static void flowScoping() {
		printIntegersOrNumbersGreaterThan5(5);
		printIntegerTwice(10);
		printOnlyIntegers(15);
	}

	private static void printIntegersOrNumbersGreaterThan5(Number number) {    
		// if (number instanceof Integer data || data.compareTo(5) > 0)       
				// System.out.print(data); 	
	}

	private static void printIntegerTwice(Number number) {
		if (number instanceof Integer data)
			System.out.println(data.intValue());    
		// System.out.print(data.intValue());  // DOES NOT COMPILE 
	}

	private static void printOnlyIntegers(Number number) {    
		if (!(number instanceof Integer data))       
			return;    
		System.out.println(data.intValue()); 

		if (!(number instanceof Integer data1))       
			return;    
		else       
			System.out.println(data1.intValue()); 

		if (number instanceof Integer data2)       
			System.out.println(data2.intValue());    
		else       
			return; 
	}
}
