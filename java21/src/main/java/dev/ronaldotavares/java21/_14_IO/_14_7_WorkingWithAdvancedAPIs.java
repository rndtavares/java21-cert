package dev.ronaldotavares.java21._14_IO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static dev.ronaldotavares.java21._14_IO._14_2_OperatingOnFileAndPath.DEFAULT_PATH;

public class _14_7_WorkingWithAdvancedAPIs {
    public static void main(String[] args) {
        System.out.println("Working with Advanced APIs");

        var workingWithAdvancedAPIs = new _14_7_WorkingWithAdvancedAPIs();
        workingWithAdvancedAPIs.markingData();
        workingWithAdvancedAPIs.skippingData();
        workingWithAdvancedAPIs.discoveringFileAttributes();
        workingWithAdvancedAPIs.traversingADirectoryTree();
        workingWithAdvancedAPIs.searchingADirectory();
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
        retrievingAttributes();
        modifyingAttributes();
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

    private void retrievingAttributes() {
        System.out.println("Retrieving Attributes");

        var path = Path.of(DEFAULT_PATH,"/turtles/sea.txt");
        BasicFileAttributes data = null;
        try {
            data = Files.readAttributes(path,
                    BasicFileAttributes.class);
            System.out.println("Is a directory? " + data.isDirectory());
            System.out.println("Is a regular file? " + data.isRegularFile());
            System.out.println("Is a symbolic link? " + data.isSymbolicLink());
            System.out.println("Size (in bytes):" + data.size());
            System.out.println("Last modified:" + data.lastModifiedTime());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void modifyingAttributes() {
        System.out.println("Modifying Attributes");

        // Read file attributes
        var path = Path.of(DEFAULT_PATH,"/turtles/sea.txt");
        try {
            BasicFileAttributeView view = Files.getFileAttributeView(path,
                    BasicFileAttributeView.class);
            BasicFileAttributes attributes = view.readAttributes();

            // Modify file last modified time
            System.out.println("Last modified:" + attributes.lastModifiedTime());
            FileTime lastModifiedTime = FileTime.fromMillis(
                    attributes.lastModifiedTime().toMillis() + 10_000);
            view.setTimes(lastModifiedTime, null, null);
            System.out.println("Last modified:" + lastModifiedTime);
        }catch (IOException e) {
            System.out.println(e);
        }

    }

    private void traversingADirectoryTree(){
        System.out.println("Traversing a Directory Tree");
        walkingADirectory();
        applyingADepthLimit();
        avoidingCircularPaths();
    }

    private void walkingADirectory() {
        System.out.println("Walking a Directory");
        long size = 0;
        try {
            size = getPathSize(Path.of("src"));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.format("Total Size: %.2f megabytes", (size/1000000.0));
        System.out.println();
    }

    private long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public long getPathSize(Path source) throws IOException {
        try (var s = Files.walk(source)) {
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

    public long getPathSizeWithLimit(Path source) throws IOException {
        try (var s = Files.walk(source, 7)) { // 7 is the depth limit
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

    private void applyingADepthLimit() {
        System.out.println("Applying a Depth Limit");

        long size = 0;
        try {
            size = getPathSizeWithLimit(Path.of("src"));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.format("Total Size: %.2f megabytes", (size/1000000.0));
        System.out.println();
    }

    public long getPathSizeFollowingSymbolicLinks(Path source) throws IOException {
        try (var s = Files.walk(source, FileVisitOption.FOLLOW_LINKS)) {
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

    private void avoidingCircularPaths() {
        System.out.println("Avoiding Circular Paths");

        long size = 0;
        try {
            size = getPathSizeFollowingSymbolicLinks(Path.of("src"));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.format("Total Size: %.2f megabytes", (size/1000000.0));
        System.out.println();
    }

    private void searchingADirectory() {
        System.out.println("Searching a Directory");

        var path = Path.of("src");
        long minSize = 1_000; //bytes
        try (var s = Files.find(path, 10,
                (p, a) -> a.isRegularFile()
                          && p.toString().endsWith(".java")
                          && a.size() > minSize)) {
            s.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }

        usingDirectoryStream();
    }

    private void usingDirectoryStream() {
        System.out.println("Using Directory Stream");
        var path = Path.of("src");
        try (DirectoryStream<Path> dirStream = Files
                .newDirectoryStream(path, "*")) {
            for (Path entry : dirStream)
                System.out.println(entry);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
