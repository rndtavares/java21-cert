package dev.ronaldotavares.java21._14_IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class _14_2_OperatingOnFileAndPath {
    public static void main(String[] args) {
        System.out.println("Operating on File and Path");

        var operatingOnFileAndPath = new _14_2_OperatingOnFileAndPath();

        operatingOnFileAndPath.usingSharedFunctionality();
    }

    void usingSharedFunctionality(){
        System.out.println("Using Shared Functionality");

//        io();
//        nio();
    }

    void io(File file) {
        if (file.exists()) {
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());
            if (file.isFile()) {
                System.out.println("Size: " + file.length());
                System.out.println("Last Modified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    System.out.println("   " + subfile.getName());
                }
            }
        }
    }

    void nio(Path path) throws IOException {
        if (Files.exists(path)) {
            System.out.println("Absolute Path: " + path.toAbsolutePath());
            System.out.println("Is Directory: " + Files.isDirectory(path));
            System.out.println("Parent Path: " + path.getParent());
            if (Files.isRegularFile(path)) {
                System.out.println("Size: " + Files.size(path));
                System.out.println("Last Modified: " + Files.getLastModifiedTime(path));
            } else {
                try (Stream<Path> stream = Files.list(path)) {
                    stream.forEach(p -> System.out.println("   " + p.getFileName()));
                }
            }
        }
    }
}
