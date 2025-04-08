package dev.ronaldotavares.java21._3_making_decisions;

public class _3_5_ControllingFlowWithBranching {
    public static void main(String[] args) {
        nestedLoops();
        labels();
        returnStatements();
        unreachableCode();
    }

    private static void nestedLoops() {
        int[][] myComplexArray = {{5,2,1,3}, {3,9,8,9}, {5,7,12,7}};  

        for (int[] mySimpleArray : myComplexArray) {   
            for (int i = 0; i < mySimpleArray.length; i++) {       
                System.out.print(mySimpleArray[i]+"\t");   
            }   
            System.out.println();
        }

        int hungryHippopotamus = 8;
        while (hungryHippopotamus > 0) {   
            do {       
                hungryHippopotamus -= 2;   
            } while (hungryHippopotamus>5);   
            hungryHippopotamus--;   
            System.out.print(hungryHippopotamus+", ");
        }
    }

    private static void labels() {
        int[][] myComplexArray = {{5,2,1,3}, {3,9,8,9}, {5,7,12,7}};
        
        OUTER_LOOP:  for (int[] mySimpleArray : myComplexArray) {
            INNER_LOOP:  for (int i = 0; i < mySimpleArray.length; i++) {
                System.out.print(mySimpleArray[i]+"\t");
            }
            System.out.println();
        }
    }
    
    private static void returnStatements(){
        int[][] list = {{1,13}, {5,2}, {2,2}};
        FindInMatrixUsingReturn.searchForValue(list, 2);
    }

    private static void unreachableCode(){
        int checkDate = 0;
        while (checkDate<10) {   
            checkDate++;   
            if (checkDate>100) {       
                break;       
//                checkDate++;  // DOES NOT COMPILE
            }
        }

        int minute = 1;
        WATCH: while (minute>2) {   
            if (minute++>2) {       
                continue WATCH;       
//                System.out.print(minute);  // DOES NOT COMPILE
            }
        }  

        int hour = 2;
        switch (hour) {   
//            case 1: return; hour++;  // DOES NOT COMPILE
            case 2:
        }
    }
}

class FindInMatrixOuterBreak {
    public static void main(String[] args) {
        int[][] list = {{1,13}, {5,2}, {2,2}};
        int searchValue = 2;
        int positionX = -1;
        int positionY = -1;

        PARENT_LOOP: for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j] == searchValue) {
                    positionX = i;
                    positionY = j;
                    break PARENT_LOOP;
                }
            }
       }
       if (positionX == -1 || positionY == -1) {
            System.out.print("Value "+searchValue+" not found");
       } else {
            System.out.print("Value "+searchValue+" found at: " +
                "("+positionX+","+positionY+")");
       }
    } }

class FindInMatrixInnerBreak {
    public static void main(String[] args) {
        int[][] list = {{1,13}, {5,2}, {2,2}};
        int searchValue = 2;
        int positionX = -1;
        int positionY = -1;

        PARENT_LOOP: for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j]==searchValue) {
                    positionX = i;
                    positionY = j;
                    break;
                }
            }
        }
        if (positionX == -1 || positionY == -1) {
            System.out.print("Value "+searchValue+" not found");
        } else {
            System.out.print("Value "+searchValue+" found at: " +
                    "("+positionX+","+positionY+")");
        }
    } }

class FindInMatrixNoBreak {
    public static void main(String[] args) {
        int[][] list = {{1,13}, {5,2}, {2,2}};
        int searchValue = 2;
        int positionX = -1;
        int positionY = -1;

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j]==searchValue) {
                    positionX = i;
                    positionY = j;
                }
            }
        }
        if (positionX == -1 || positionY == -1) {
            System.out.print("Value "+searchValue+" not found");
        } else {
            System.out.print("Value "+searchValue+" found at: " +
                    "("+positionX+","+positionY+")");
        }
    } }

class CleaningScheduleOuterContinue {
    public static void main(String[] args) {
        CLEANING: for (char stables = 'a'; stables<='d'; stables++) {
            for (int leopard = 1; leopard <= 3; leopard++) {
                if (stables=='b' || leopard==2) {
                    continue CLEANING;
                }
                System.out.println("Cleaning: "+stables+","+leopard);
 } } } }

class CleaningScheduleInnerContinue {
    public static void main(String[] args) {
        CLEANING: for (char stables = 'a'; stables<='d'; stables++) {
            for (int leopard = 1; leopard <= 3; leopard++) {
                if (stables=='b' || leopard==2) {
                    continue;
                }
                System.out.println("Cleaning: "+stables+","+leopard);
            } } } }

class CleaningScheduleNoContinue {
    public static void main(String[] args) {
        CLEANING: for (char stables = 'a'; stables<='d'; stables++) {
            for (int leopard = 1; leopard <= 3; leopard++) {
                System.out.println("Cleaning: "+stables+","+leopard);
            } } } }

class FindInMatrixUsingReturn {
    public static int[] searchForValue(int[][] list, int v) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j] == v) {
                    System.out.println("Value "+v+" found at: ("+i+","+j+")");
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}