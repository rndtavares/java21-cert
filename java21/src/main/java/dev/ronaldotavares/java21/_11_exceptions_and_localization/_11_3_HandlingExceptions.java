package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.io.IOException;

public class _11_3_HandlingExceptions {
    public static void main(String[] args) {
        System.out.println("Handling Exceptions");

        var handlingExceptions = new _11_3_HandlingExceptions();
        handlingExceptions.usingTryAndCatchStatements();
        handlingExceptions.chainingCatchBlocks();
        handlingExceptions.applyingAMultiCatchBlock();
        handlingExceptions.addingAFinallyBlock();
    }

    void usingTryAndCatchStatements() {
        System.out.println("usingTryAndCatchStatements");
        explore();
    }

    void explore() {
        System.out.println("explore");
        try {
            fall();
            System.out.println("never get here");
        } catch (RuntimeException e) {
            getUp();
        }
        seeAnimal();

//        try // DOES NOT COMPILE
//            fall();
//        catch (Exception e)
//            System.out.println("get up");

//        try { // DOES NOT COMPILE
//            fall();
//        }
    }

    void fall() {
        System.out.println("fall");
        throw new RuntimeException();
    }

    void getUp(){
        System.out.println("get up");
    }
    void seeAnimal(){
        System.out.println("see animal");
    }
    void seeAnimals(){
        System.out.println("see animals");
    }
    void getHugFromDaddy(){
        System.out.println("get hug from daddy");
    }
    void seeMoreAnimals(){
        System.out.println("see more animals");
    }
    void goHome(){
        System.out.println("go home");
    }

    void chainingCatchBlocks(){
        System.out.println("chainingCatchBlocks");

        visitPorcupine();
        visitMonkeys();
        visitSnakes();
        visitManatees();
    }

    class AnimalsOutForAWalk extends RuntimeException {}
    class ExhibitClosed extends RuntimeException {}
    class ExhibitClosedForLunch extends ExhibitClosed {}

    void visitPorcupine() {
        System.out.println("visitPorcupine");
        try {
            seeAnimal();
        } catch (AnimalsOutForAWalk e) {
            System.out.println("try back later");
        } catch (ExhibitClosed e) {
            System.out.println("not today");
        }
    }

    void visitMonkeys() {
        System.out.println("visitMonkeys");
        try {
            seeAnimal();
        } catch (ExhibitClosedForLunch e) {
            System.out.println("try back later");
        } catch (ExhibitClosed e) {
            System.out.println("not today");
        }

//        try {
//            seeAnimal();
//        } catch (ExhibitClosed e) {
//            System.out.println("not today");
//        } catch (ExhibitClosedForLunch e) { // DOES NOT COMPILE
//            System.out.println("try back later");
//        }
    }

    void visitSnakes() {
        System.out.println("visitSnakes");
//        try {
//        } catch (IllegalArgumentException e) {
//        } catch (NumberFormatException e) { // NÃO COMPILA
//        }
    }

    void visitManatees() {
        System.out.println("visitManatees");
        try {
        } catch (NumberFormatException e1) {
            System.out.println(e1);
        } catch (IllegalArgumentException e2) {
//            System.out.println(e1); // DOES NOT COMPILE
        }
    }

    void applyingAMultiCatchBlock() {
        System.out.println("applyingAMultiCatchBlock");

        //before
        String[] args = new String[0];
        try {
            System.out.println(Integer.parseInt(args[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing or invalid input");
        } catch (NumberFormatException e) {
            System.out.println("Missing or invalid input");
        }

        //after
        try {
            System.out.println(Integer.parseInt(args[1]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Missing or invalid input");
        }

//        catch(Exception1 e | Exception2 e | Exception3 e) // DOES NOT COMPILE
//        catch(Exception1 e1 | Exception2 e2 | Exception3 e3) // DOES NOT COMPILE
//        catch(Exception1 | Exception2 | Exception3 e) // CORRECT SYNTAX

//        try {
//            throw new IOException();
//        } catch (FileNotFoundException | IOException p) {} // DOES NOT COMPILE

        try {
            throw new IOException();
        } catch (IOException e) {}
    }

    void addingAFinallyBlock(){
        System.out.println("addingAFinallyBlock");

        exploreWithFinally();

//        try { // DOES NOT COMPILE
//            fall();
//        } finally {
//            System.out.println("all better");
//        } catch (Exception e) {
//            System.out.println("get up");
//        }

//        try { // DOES NOT COMPILE
//            fall();
//        }

//        try {
//            fall();
//        } finally {
//            System.out.println("all better");
//        }

        order();
        goHomeInt();
        exitFinally();
    }

    void exploreWithFinally() {
        System.out.println("exploreWithFinally");
        try {
            seeAnimals();
            fall();
        } catch (Exception e) {
            getHugFromDaddy();
        } finally {
            seeMoreAnimals();
        }
        goHome();
    }

    void order(){
        System.out.println("order");

        StringBuilder sb = new StringBuilder();
        try {
            sb.append("t");
        } catch (Exception e) {
            sb.append("c");
        } finally {
            sb.append("f");
        }
        sb.append("a");
        System.out.println(sb.toString());

    }

    int goHomeInt(){
        System.out.println("goHomeInt");
        try {
            System.out.println("1");
            return -1;
        } catch (Exception e) {
            System.out.println("2");
            return -2;
        } finally {
            System.out.println("3");
//            throw new RuntimeException(); // if finally throws an exception, the execution stops here
            return -3;
        }
    }

    void exitFinally(){
        System.out.println("exitFinally");
        try {
            System.exit(0);
        } finally {
            System.out.println("Never going to get here");
        }
    }

}
