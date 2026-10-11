package dev.ronaldotavares.java21._3_making_decisions;

public class _3_2_BuildingSwitchStatementsAndExpressions {
    public static void main(String[] args) {
        System.out.println(getAnimalBad(0));
        System.out.println(getAnimalBetter(2));
        System.out.println(getAnimalBest(4));
        definingSwitch();
        acceptableValues();
        workingWithSwitchStatements();
        workingWithSwitchExpressions();
        exaustiveSwitchStatements();
        handlingANullCase();
    }

    private static String getAnimalBad(int type) {
        String animal;
        if(type == 0)         
            animal = "Lion";    
        else if(type == 1)         
            animal = "Elephant";    
        else if(type == 2 || type == 3)         
            animal = "Alligator";    
        else if(type == 4)         
            animal = "Crane";    
        else         
            animal = "Unknown";    
        return animal; 
    }

    private static String getAnimalBetter(int type) {      
		String animal;      
		switch (type) {           
            case  0:               
                animal = "Lion";               
                break;           
            case  1:               
                animal = "Elephant";               
                break;           
            case  2, 3:               
                animal = "Alligator";               
                break;           
            case  4:               
                animal = "Crane";               
                break;           
            default:               
                animal = "Unknown";      
        }      
        return animal; 
    }

    private static String getAnimalBest(int type) {      
		return switch (type)  {           
            case  0      -> "Lion";           
            case  1      -> "Elephant";           
            case  2, 3   -> "Alligator";           
            case  4      -> "Crane";           
            default      -> "Unknown";      
		}; 
    }

    private static void definingSwitch() {
        String name = "123";

        switch (name) {                    // Switch statement    
            case "Sancha":        
                System.out.println(1);   
                break;    
            case "Jacob", "Jake": 
                System.out.println(2);   
                break;    
            default:              
                System.out.println(999); 
                break; 
        }   
                
        System.out.println(switch (name) { // Switch expression    
            case "Sancha"        -> 1;    
            case "Jacob", "Jake" -> 2;    
            default              -> 999; 
        });

        var type = 2;
        switch (type) {    
            case 0 :  System.out.println("Lion");    
            // case 1 -> System.out.println("Elephant");  //DO NOT COMPILE
        }

        // var result = switch (bear) {    
            // case 30 -> "Grizzly"    
            // default -> "Panda" 
        // } //DO NOT COMPILE

        var bear = 50;
        var result = switch (bear) {    
            case 30 -> "Grizzly";    
            default -> "Panda"; 
        };

        int food = 5, month = 4, weather = 2, day = 0, time = 4;   

        // String meal = switch food { // #1    DOES NOT COMPILE
        //         case 1  -> "Dessert"    
        //         default -> "Porridge" 
        // };   
        
        // switch (month) // #2    DOES NOT COMPILE
        //         case 4: System.out.println("January");   
                
        switch (weather) { // #3    
                case 2: System.out.println("Rainy");
                case 5: {       
                        System.out.println("Sunny");    
                } 
        }   
        
        // switch (day) { // #4    DOES NOT COMPILE
        //         case 1: 13: System.out.println("January");    
        //         default     System.out.println("July"); 
        // }   
        
        // String description = switch (time) { // #5    DOES NOT COMPILE
        //         case 10 -> "Morning";    
        //         default -> "Late"; 
        // }        

        switch (month) {}
    }

    private static void acceptableValues() {
        feedAnimals();
        System.out.println(cleanFishTank(1));
        System.out.println(shouldGetACoat(Season.SPRING));
    }

    private static final int getCookies() { return 4; } 

    private static void feedAnimals() {    
        final int bananas = 1;    
        int apples = 2;    
        int numberOfAnimals = 3;    
        final int cookies = getCookies();    
        switch (numberOfAnimals) {       
            case bananas:
            // case apples:        // DOES NOT COMPILE       
            // case getCookies():  // DOES NOT COMPILE       
            // case cookies :      // DOES NOT COMPILE       
            case 3 * 5 :    
        } 
    }

    private static String cleanFishTank(int dirty) {    
		return switch (dirty) {       
			// case "Very" -> "1 hour";  // DOES NOT COMPILE       
			default     -> "45 minute";    
		}; 
    }

    private static boolean shouldGetACoat(Season s) {    
		return switch (s) {       
				case SPRING -> false;       
				case Season.SUMMER -> false;       
				case FALL -> true;       
				case Season.WINTER -> true;    
		}; 
    }

    private static void workingWithSwitchStatements() {
        printSeasonForMonth(4);
        printSeasonForMonthWithBreaks(4);
        printSeasonForMonthWithSwitchExpression(4);
        printWeather(2);
    }

    private static void printSeasonForMonth(int month) {    
		switch (month) {       
            case 1, 2, 3:    System.out.println("Winter-");       
            case 4, 5, 6:    System.out.println("Spring-");       
            default:         System.out.println("Unknown-");       
            case 7, 8, 9:    System.out.println("Summer-");       
            case 10, 11, 12: System.out.println("Fall-");    
		} 
    }

    private static void printSeasonForMonthWithBreaks(int month) {    
		switch (month) {       
            case 1, 2, 3:    System.out.println("Winter-");  break;       
            case 4, 5, 6:    System.out.println("Spring-");  break;       
            default:         System.out.println("Unknown-"); break;       
            case 7, 8, 9:    System.out.println("Summer-");  break;       
            case 10, 11, 12: System.out.println("Fall-");    break;    
		} 
    }

    private static void printSeasonForMonthWithSwitchExpression(int month) {    
		String value = switch (month) {       
            case 1, 2, 3    -> "Winter-";       
            case 4, 5, 6    -> "Spring-";       
            default         -> "Unknown-";       
            case 7, 8, 9    -> "Summer-";       
            case 10, 11, 12 -> "Fall-";    
		};    
		System.out.println(value); 
    }

    private static void printWeather(int rain) {    
		switch (rain) {       
            case 0 -> System.out.println("Dry");       
            case 1 -> System.out.println("Wet");       
            case 2 -> System.out.println("Storm");    
		} 
    }
    
    private static void workingWithSwitchExpressions() {
        int measurement = 10; 
        int size = switch (measurement) {    
            case 5  -> Integer.valueOf(1);    
            case 10 -> (short)2;    
            default -> 3;    
            // case 20 -> "4";   // DOES NOT COMPILE    
            // case 40 -> 5L;    // DOES NOT COMPILE    
            // case 50 -> null;  // DOES NOT COMPILE 
        };

        identifyType("");
        getWeatherCoveredAll(Season.SPRING);
        usingYieldStatement();
        usingPatternMatching();
        incorrectType();
        newPrintDetails(10);
    }

    private static void identifyType(String type) {    
		// Integer reptile = switch (type) { // DOES NOT COMPILE       
				// case "Snake"  -> 1;       
				// case "Turtle" -> 2;    
		// }; 
    }

    // private static String getWeatherMissingOne(Season value) {    
		// return switch (value) {  // DOES NOT COMPILE       
            // case WINTER -> "Cold";       
            // case SPRING -> "Rainy";       
            // case SUMMER -> "Hot";    
		// }; 
    // }

    private static String getWeatherCoveredAll(Season value) {    
		return switch (value) {       
            case WINTER -> "Cold";       
            case SPRING -> "Rainy";
            case SUMMER -> "Hot";       
            case FALL   -> "Warm";       
            default     -> throw new RuntimeException("Unsupported Season");    
		}; 
    }

    private static void usingYieldStatement() {
        int fish = 5; 
        int length = 12; 
        var name = switch (fish) {    
            case 1 -> "Goldfish";    
            case 2 -> { yield "Trout"; }    
            case 3 -> {       
                    if (length> 10) yield "Blobfish";       
                    else yield "Green";
            }    
            case 4 -> {       
                    throw new RuntimeException("Unsupported value");    
            }    
            default -> "Swordfish"; 
        };
    }

    private static void semicolonsInSwitchExpression(){
        // int fish = 1; 
        // var name = switch (fish) {    
            // case 1  -> "Goldfish"           // DOES NOT COMPILE (missing semicolon)    
            // case 2  -> { yield "Trout"; };  // DOES NOT COMPILE (extra semicolon)    
            // default -> "Shark"; }  // DOES NOT COMPILE (missing semicolon)
        // }
    }

    private static void usingPatternMatching() {
        printDetails(2);
        getTrainer(8);
    }

    private static void printDetails(Number height) {    
        String message = switch (height) {               
                case Integer i -> "Rounded: " + i;       
                case Double d  -> "Precise: " + d;       
                case Number n  -> "Unknown: " + n;    
        };    
        System.out.println(message);
    }

    private static String getTrainer(Number height) {    
		return switch (height) {               
            case Integer i when i > 10 -> "Joseph";       
            case Integer i -> "Daniel";       
            case Double num when num <= 15.5 -> "Peter";       
            case Double num -> "Kelly";       
            case Number num -> "Ralph";    
		}; 
    }

    private static void incorrectType() {
        Number fish = 10; 
        String name = switch (fish) {
                case Integer freshWater -> "Bass";    
                case Number saltWater   -> "ClownFish";    
                // case String s           -> "Shark";  // DOES NOT COMPILE 
        };
    }

    private static void newPrintDetails(Number height) {    
		String message = switch (height) {               
				case Number n  -> "Unknown: " + n;       
				// case Integer i -> "Rounded: " + i;       
				// case Double d  -> "Precise: " + d;    
		};
		System.out.println(message); 
    }

    // private static String newGetTrainer(Number animal) {    
		// return switch (animal) {               
            // case Integer i             -> "Daniel";       
            // case Integer i when i > 10 -> "Joseph"; // DOES NOT COMPILE       
            // …    
		// }; 
    // }

    private static void exaustiveSwitchStatements() {
        // Number zooPatrons = Integer.valueOf(1_000); 
        // switch (zooPatrons) {    
            // case Integer count -> System.out.println("Welcome: " + count); 
        // }

        Integer zooPatrons = Integer.valueOf(1_000); 
        switch (zooPatrons) {    
		    case Integer count -> System.out.println("Welcome: " + count); 
        }

        Number zooPatrons1 = Integer.valueOf(1_000); 
        switch (zooPatrons1) {    
		    case Integer count -> System.out.println("Welcome: " + count);    
		    case Number count  -> System.out.println("Too many people at the zoo!"); 
        }

        Number zooPatrons2 = Integer.valueOf(1_000); 
        switch (zooPatrons2) {    
		    case Integer count -> System.out.println("Welcome: " + count);    
		    case Number count  -> System.out.println("Too many people at the zoo!");    
		    // default            -> System.out.println("The zoo is closed"); 
        }
    }

    private static void handlingANullCase() {
        String fish = null; 
        // System.out.println(switch (fish) {    
            // case "ClownFish" -> "Hello!";    
            // case "BlueTang"  -> "Hello again!";    
            // default          -> "Goodbye"; 
        // }); // NullPointerException

        String fish1 = null; 
        if (fish1 == null) {    
            System.out.println("What type of fish are you?"); 
        } else {    
            System.out.println(switch (fish) {       
                case "ClownFish" -> "Hello!";       
                case "BlueTang"  -> "Hello again!";
                default          -> "Goodbye";    
            }); 
        }

        String fish2 = null; 
        System.out.println(switch (fish2) {    
            case "ClownFish" -> "Hello!";    
            case "BlueTang"  -> "Hello again!";    
            case null        -> "What type of fish are you?";    
            default          -> "Goodbye"; 
        });

        // String fish3 = null; 
        // switch (fish3) {  // DOES NOT COMPILE    
		    // case "ClownFish": System.out.println("Hello!");
		    // case "BlueTang":  System.out.println("Hello again!");    
		    // case null:        System.out.println("What type of fish are you?"); 
        // }

        System.out.println(switch (fish) {    
            case String s when "ClownFish".equals(s) -> "Hello!";    
            case null -> "No good"; 
            case String s when "BlueTang".equals(s) -> "Hello again!";    
            default -> "Goodbye";
        });

        // System.out.println(switch (fish) {    
            // case String s when "ClownFish".equals(s) -> "Hello!";    
            // case String s when "BlueTang".equals(s) -> "Hello again!";    
            // default -> "Goodbye";
        //    // case null -> "No good";  // DOES NOT COMPILE 
        // });
    }

}

enum Season { SPRING, SUMMER, FALL, WINTER }

enum DayOfWeek {    
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY 
}