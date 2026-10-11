package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class _11_4_AutomatingResourceManagement {
    public static void main(String[] args) {
        System.out.println("Automating Resource Management");

        var automatingResourceManagement = new _11_4_AutomatingResourceManagement();

        String file = _11_4_AutomatingResourceManagement.class.getClassLoader().getResource("test.txt").getPath();

        automatingResourceManagement.beforeTryWithResources(file);
        automatingResourceManagement.afterTryWithResources(file);
        automatingResourceManagement.constructingTryWithResourcesStatements();
        automatingResourceManagement.declaringResources();
        automatingResourceManagement.scopeOfTryWithResources();
        automatingResourceManagement.followingOrderOfOperations();
        automatingResourceManagement.applyingEffectivelyFinal();
        automatingResourceManagement.understandingSuppressedExceptions();
    }

    void beforeTryWithResources(String file){
        System.out.println("beforeTryWithResources");
        readFile(file);
    }

    void readFile(String file) {
        System.out.println("readFile");
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            // Read file data
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    void afterTryWithResources(String file){
        System.out.println("afterTryWithResources");
        readFileWithTryWithResources(file);
        try {
            readFileWithoutCatch(file);
        } catch (IOException e){
            System.out.println(e);
        }
    }

    void readFileWithTryWithResources(String file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            // Read file data
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFileWithoutCatch(String file) throws IOException {
        try (FileInputStream is = new FileInputStream(file)) {
            // Read file data
        }
    }

    void constructingTryWithResourcesStatements(){
        System.out.println("constructingTryWithResourcesStatements");

//        try (String reptile = "lizard") {} // DOES NOT COMPILE, STRING IS NOT AUTOCLOSEABLE
    }

    void declaringResources(){
        System.out.println("declaringResources");
        try (MyFileClass bookReader = new MyFileClass(1);
             MyFileClass movieReader = new MyFileClass(2)) {
            System.out.println("Try Block");
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("Catch Block");
        } finally {
            System.out.println("Finally Block");
        }
    }

    void scopeOfTryWithResources(){
        System.out.println("scopeOfTryWithResources");
        try (Scanner s = new Scanner(System.in)) {
//            s.nextLine(); //COMMENTING TO NOT PAUSE THE EXECUTION ASKING FOR TERMINAL INPUT
        } catch(Exception e) {
//            s.nextInt(); // DOES NOT COMPILE
        } finally {
//            s.nextInt(); // DOES NOT COMPILE
        }
    }

    void followingOrderOfOperations(){
        System.out.println("Following Order of Operations");

        try (MyFileClass bookReader = new MyFileClass(1);
             MyFileClass movieReader = new MyFileClass(2)) {
            System.out.println("Try Block");
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("Catch Block");
        } finally {
            System.out.println("Finally Block");
        }
    }

    void applyingEffectivelyFinal() {
        System.out.println("applyingEffectivelyFinal");

        final var bookReader = new MyFileClass(4);
        MyFileClass movieReader = new MyFileClass(5);
        try (bookReader;
             var tvReader = new MyFileClass(6);
             movieReader) {
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }

        Path path = Path.of("test.txt");
        try {
            var writer = Files.newBufferedWriter(path);
//            try (writer) { // DOES NOT COMPILE
//                writer.append("Welcome to the zoo!");
//            }
//            writer = null;
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            var writer1 = Files.newBufferedWriter(path);
            writer1.append("This write is permitted but a really bad idea!");
            try (writer1) {
                writer1.append("Welcome to the zoo!");
            }
            writer1.append("This write will fail!"); // IOException
        }catch (IOException e){
            System.out.println(e);
        }
    }

    void understandingSuppressedExceptions(){
        System.out.println("understandingSuppressedExceptions");

        JammedTurkeyCage.main(null);

        try{
            uncaughtAndSuppressed();
        } catch (RuntimeException e){
            System.out.println("caught on the caller: " + e);
        }

        try {
            lostException();
        } catch (RuntimeException e){
            System.out.println(e);
        }
    }

    void uncaughtAndSuppressed(){
        System.out.println("uncaughtAndSuppressed");

        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            throw new RuntimeException("Turkeys ran off");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
        }
    }

    void lostException(){
        System.out.println("lostException");

        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            throw new IllegalStateException("Turkeys ran off");
        } finally {
            throw new RuntimeException("and we couldn't find them");
        }
    }
}

class MyFileClass implements AutoCloseable {
    private final int num;
    public MyFileClass(int num) { this.num = num; }

    @Override
    public void close() {
        System.out.println("Closing: " + num);
    }
}

class TurkeyCage implements AutoCloseable {
    public void close() {
        System.out.println("Close gate");
    }
    public static void main(String[] args) {
        try (var t = new TurkeyCage()) {
            System.out.println("Put turkeys in");
        }
    }
}

class JammedTurkeyCage implements AutoCloseable {
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door does not close");
    }

    public static void main(String[] args) {
        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            System.out.println("Put turkeys in");
            throw new IllegalStateException("Turkeys ran off");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t: e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        }
    }
}
