package dev.ronaldotavares.java21._14_IO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static dev.ronaldotavares.java21._14_IO._14_2_OperatingOnFileAndPath.DEFAULT_PATH;

public class _14_7_WorkingWithAdvancedAPIs {
    public static void main(String[] args) {
        System.out.println("Working with Advanced APIs");

        var workingWithAdvancedAPIs = new _14_7_WorkingWithAdvancedAPIs();
        workingWithAdvancedAPIs.markingData();
        workingWithAdvancedAPIs.skippingData();
        workingWithAdvancedAPIs.discoveringFileAttributes();
    }

    private void markingData() {
        System.out.println("Marking Data");

        try {
            InputStream is = new ByteArrayInputStream("LION".getBytes());
            readData(is);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(InputStream is) throws IOException {
        System.out.print((char) is.read()); // L
        if (is.markSupported()) {
            is.mark(100); // Marks up to 100 bytes
            System.out.print((char) is.read()); // I
            System.out.print((char) is.read()); // O
            is.reset(); // Resets stream to position before I
        }
        System.out.print((char) is.read()); // I
        System.out.print((char) is.read()); // O
        System.out.print((char) is.read()); // N
    }

    private void skippingData() {
        System.out.println("Skipping Data");

        try {
            InputStream is = new ByteArrayInputStream("TIGERS".getBytes());
            skipData(is);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void skipData(InputStream is) throws IOException {
        System.out.print ((char)is.read()); // T
        is.skip(2); // Skips I and G
        is.read(); // Reads E but doesn't output it
        System.out.print((char)is.read()); // R
        System.out.print((char)is.read()); // S
    }

    private void discoveringFileAttributes() {
        System.out.println("Discovering File Attributes");

        checkingForSymbolicLinks();
        checkingFileAccessibility();
    }

    private void checkingForSymbolicLinks() {
        System.out.println("Checking for Symbolic Links");

        System.out.println(Files.isDirectory(Path.of(DEFAULT_PATH,"/canine/fur.jpg")));
        System.out.println(Files.isSymbolicLink(Path.of(DEFAULT_PATH,"/canine/coyote")));
        System.out.println(Files.isRegularFile(Path.of(DEFAULT_PATH,"/canine/types.txt")));
    }

    private void checkingFileAccessibility() {
        System.out.println("Checking File Accessibility");

        try {
            System.out.println(Files.isHidden(Path.of(DEFAULT_PATH,"/walrus.txt")));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(Files.isReadable(Path.of(DEFAULT_PATH,"/seal/baby.png")));
        System.out.println(Files.isWritable(Path.of(DEFAULT_PATH,"dolphin.txt")));
        System.out.println(Files.isExecutable(Path.of(DEFAULT_PATH,"whale.png")));
    }
}
