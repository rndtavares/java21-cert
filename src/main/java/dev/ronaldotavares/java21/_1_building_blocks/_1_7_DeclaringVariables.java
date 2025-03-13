package dev.ronaldotavares.java21._1_building_blocks;

public class _1_7_DeclaringVariables {

    private void legalVariablesDeclaration() {
        long okidentifier;
        float $OK2Identifier;
        boolean _alsoOK1d3ntifi3r;
        char __SStillOkbutKnotsonice$;
    }

    private void illegalVariablesDeclaration() {
//        int 3DPointClass;    // identifiers cannot begin with a number
//        byte hollywood@vine; // @ is not a letter, digit, $ or _
//        String *$coffee;     // first character * is not a letter, $ or _
//        double public;       // public is a reserved word
//        short _;             // a single underscore is not allowed
    }

    private void multipleVariablesDeclaration() {
        String s1, s2;
        String s3 = "yes", s4 = "no";
//        int num, String value; // DOES NOT COMPILE
    }

    private void multipleVariablesDeclarationInitialization() {
        int i1, i2, i3 = 0;
    }

    private void whichDeclarationisLegal(){
//        boolean b1, b2;
//        String s1 = "1", s2;
//        double d1, double d2;
//        int i1; int i2;
//        int i3; i4;
    }
}
