package dev.ronaldotavares.java21._14_IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static dev.ronaldotavares.java21._14_IO._14_2_OperatingOnFileAndPath.DEFAULT_PATH;

public class _14_4_ReadingAndWritingFiles {
    public static void main(String[] args) {
        System.out.println("Reading and Writing Files");

        var readingAndWritingFiles = new _14_4_ReadingAndWritingFiles();

        readingAndWritingFiles.usingIOStreams();
        readingAndWritingFiles.enhancingWithFiles();
        readingAndWritingFiles.combiningWithNewBuﬀeredReaderAndNewBuﬀeredWriter();
    }

    void usingIOStreams() {
        System.out.println("Using IO Streams");

        System.out.print(System.getProperty("line.separator")); // the output is a line break
        System.out.print(System.lineSeparator()); // the output is a line break
    }

    void copyStream(Reader in, Writer out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
    }

    void copyStreamV1(InputStream in, OutputStream out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
    }

    void copyStreamV2(InputStream in, OutputStream out) throws IOException {
        int batchSize = 1024;
        var buffer = new byte[batchSize];
        int lengthRead;
        while ((lengthRead = in.read(buffer, 0, batchSize)) > 0) {
            out.write(buffer, 0, lengthRead);
            out.flush();
        }
    }

    void copyTextFileV1(File src, File dest) throws IOException {
        try (var reader = new BufferedReader(new FileReader(src));
             var writer = new BufferedWriter(new FileWriter(dest))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    void copyTextFileV2(File src, File dest) throws IOException {
        try (var reader = new BufferedReader(new FileReader(src));
             var writer = new PrintWriter(new FileWriter(dest))) {
            String line = null;
            while ((line = reader.readLine()) != null)
                writer.println(line);
        }
    }

    void enhancingWithFiles() {
        System.out.println("Enhancing with Files");

        try {
            readLazily(Path.of(DEFAULT_PATH, "sharks.log"));
        }catch (IOException e) {
            System.out.println(e);
        }

        try {
            Files.readAllLines(Path.of(DEFAULT_PATH,"birds.txt")).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            Files.lines(Path.of(DEFAULT_PATH,"birds.txt")).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }

//        try {
//            Files.readAllLines(Path.of("birds.txt"))
//                    .filter(s -> s.length()> 2) //DOES NOT COMPILES - DO NOT RETURN A STREAM
//                    .forEach(System.out::println);  //DOES NOT COMPILES - DO NOT RETURN A STREAM
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void copyPathAsString(Path input, Path output) throws IOException {
        String string = Files.readString(input);
        Files.writeString(output, string);
    }
    private void copyPathAsBytes(Path input, Path output) throws IOException {
        byte[] bytes = Files.readAllBytes(input);
        Files.write(output, bytes);
    }

    private void copyPathAsLines(Path input, Path output) throws IOException {
        List<String> lines = Files.readAllLines(input);
        Files.write(output, lines);
    }

    private void readLazily(Path path) throws IOException {
        try (Stream<String> s = Files.lines(path)) {
            s.forEach(System.out::println);
        }
        System.out.println();
        //with filter
        try (var s = Files.lines(path)) {
            s.filter(f -> f.startsWith("WARN:"))
                    .map(f -> f.substring(5))
                    .forEach(System.out::println);
        }
    }


    void combiningWithNewBuﬀeredReaderAndNewBuﬀeredWriter() {
        System.out.println("Combining with new BufferedReader and new BufferedWriter");

        try {
            copyPath(Path.of(DEFAULT_PATH, "sharks.log"), Path.of(DEFAULT_PATH, "sharks-copy.log"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void copyPath(Path input, Path output) throws IOException {
        try (var reader = Files.newBufferedReader(input);
             var writer = Files.newBufferedWriter(output)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
