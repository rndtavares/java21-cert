package dev.ronaldotavares.java21._1_building_blocks;

public class _1_8_InitializingVariables {

    private void finalVariables() {
        final int y = 10;
        int x = 20;
//        y = x + 10;  // DOES NOT COMPILE
    }

    private void finalArray() {
        final int[] favoriteNumbers = new int[10];
        favoriteNumbers[0] = 10;
        favoriteNumbers[1] = 20;
//        favoriteNumbers = null;  // DOES NOT COMPILE
    }

    private void UninitializedNotValid() {
       int y = 10;
       int x;
//       int reply = x + y;  // DOES NOT COMPILE
    }

    private int validInitialization() {
        int y = 10;
        int x;  // x is declared here
        x = 3;  // x is initialized here
        int z;  // z is declared here but never initialized or used
        int reply = x + y;
        return reply;
    }

    private void findAnswer(boolean check) {
        int answer;
        int otherAnswer;
        int onlyOneBranch;
        if (check) {
            onlyOneBranch = 1;
            answer = 1;
        } else {
            answer = 2;
        }
        System.out.println(answer);
//        System.out.println(onlyOneBranch);  // DOES NOT COMPILE
    }

    private void checkAnswer() {
        boolean value;
//        findAnswer(value);  // DOES NOT COMPILE
    }

    private void localVariableTypeInference(){
        var name = "Hello";
        var size = 7;
    }

//    var tricky = "Hello"; // DOES NOT COMPILE

    private void reassignment() {
       var number = 7;
       number = 4;
//       number = "five";  // DOES NOT COMPILE
//        int number = "five" // DOES NOT COMPILE;
    }

    private void breakingDeclaration() {
       var silly
          = 1;
    }

    private void doesThisCompile(boolean check) {
//        var question;
//        question = 1;
//        var answer;
//        if (check) {
//            answer = 2;
//        } else {
//            answer = 3;
//        }
//        System.out.println(answer);
    }

    public void twoTypes() {
//       int a, var b = 3;  // DOES NOT COMPILE
//       var a, b = 3;      // DOES NOT COMPILE
//       var n = null;      // DOES NOT COMPILE
    }

//    public int addition(var a, var b) {  // DOES NOT COMPILE
//        return a + b;
//    }

    class Var {
        public void var() {
            var var = "var";
        }
        public void Var() {
            Var var = new Var();
        }
        public void VAr() {
            Var var = new Var();
        }
        public void VAR() {
            Var var = new Var();
        }
    }

//    class var { // DOES NOT COMPILE
//    }

    private void goodUseOfVar(){
//        PileOfPapersToFileInFilingCabinet pileOfPapersToFile = new PileOfPapersToFileInFilingCabinet();
//        var pileOfPapersToFile = new PileOfPapersToFileInFilingCabinet();
    }

}
