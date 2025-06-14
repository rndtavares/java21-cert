package dev.ronaldotavares.java21._14_IO;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class _14_6_InteractingWithUsers {
    public static void main(String[] args) {
        System.out.println("Interacting with Users");

        var interactingWithUsers = new _14_6_InteractingWithUsers();

//        interactingWithUsers.printingDataToTheUser();
//        interactingWithUsers.readingInputAsAnIOStream();
        interactingWithUsers.acquiringInputWithConsole();
        interactingWithUsers.formattingConsoleData();
        interactingWithUsers.reviewingConsoleMethods();
        interactingWithUsers.closingSystemStreams();
    }

    private void printingDataToTheUser() {
        System.out.println("Printing Data to the User");

        try (var in = new FileInputStream("zoo.txt")) {
            System.out.println("Found file!");
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            System.out.println(e);
        }

        var logger = Logger.getLogger("errors");
        logger.info("Code is running");
        logger.warning("Code shouldn't have done that");

        logger.log(Level.SEVERE, "You should worry");
    }

    private void readingInputAsAnIOStream() {
        System.out.println("Reading Input as an IO Stream");

        var reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("You entered: " + userInput);
    }

    private void acquiringInputWithConsole() {
        System.out.println("Acquiring Input with Console");

//        Console c = new Console(); // DOES NOT COMPILE

        Console console = System.console();
        if (console != null) {
            String userInput = console.readLine();
            console.writer().println("You entered: " + userInput);
            console.flush();
        } else {
            System.out.println("Console not available");
        }
    }

    private void formattingConsoleData() {
        System.out.println("Formatting Console Data");

        Console console = System.console();
        if (console == null) {
            System.out.println("Console not available");
        } else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("It has %d animals and employs %d people", 391, 25);
            console.writer().println();
            console.printf("The zoo spans %5.1f acres", 128.91);
            console.flush();
            System.out.println();
        }
    }

    private void reviewingConsoleMethods() {
        System.out.println("Reviewing Console Methods");

        Console console = System.console();
        if (console == null) {
            System.out.println("Console not available");
        } else {
            String name = console.readLine("Please enter your name:");
            console.writer().format("Hi %s", name);
            console.writer().println();

            console.format("What is your address? ");
            String address = console.readLine();

            char[] password = console.readPassword("Enter a password "
                            + "between %d and %d characters: ", 5, 10);
            char[] verify = console.readPassword("Enter the password again:");
            console.printf("Passwords "
                    + (Arrays.equals(password, verify) ? "match" : "do not match"));
            console.flush();
            System.out.println();
        }
    }

    private void closingSystemStreams() {
        System.out.println("Closing System Streams");

        try (var out = System.out) {}
        System.out.println("Hello");

        try (var err = System.err) {}
        System.err.println("Hello");

        var reader = new BufferedReader(new InputStreamReader(System.in));
        try (reader) {}
        catch (IOException e) {
            System.out.println(e);
        }

        try {
            String data = reader.readLine(); // IOException
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
