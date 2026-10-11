package dev.ronaldotavares.java21._14_IO;

import java.io.*;
import java.nio.charset.Charset;

import static dev.ronaldotavares.java21._14_IO._14_2_OperatingOnFileAndPath.DEFAULT_PATH;

public class _14_3_IntroducingIOStreams {
    public static void main(String[] args) {
        System.out.println("Introducing IO Streams");

        var introducingIOStreams = new _14_3_IntroducingIOStreams();
        introducingIOStreams.characterEncodingInJava();
        introducingIOStreams.lowLevelVSHighLevelStreams();
        introducingIOStreams.streamBaseClasses();
    }

    void characterEncodingInJava(){
        System.out.println("Character Encoding in Java");

        Charset usAsciiCharset = Charset.forName("US-ASCII");
        Charset utf8Charset = Charset.forName("UTF-8");
        Charset utf16Charset = Charset.forName("UTF-16");
    }

    void lowLevelVSHighLevelStreams(){
        System.out.println("Low-Level vs. High-Level Streams");

        try (var br = new BufferedReader(new FileReader(DEFAULT_PATH.concat("zoo-data.txt")))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            System.out.println(e);
        }

        try (var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(DEFAULT_PATH.concat("zoo-data.ser"))))) {
            System.out.print(ois.readObject());
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    void streamBaseClasses(){
        System.out.println("The 4 Stream Base Classes are: InputStream, OutputStream, Reader, Writer");

//        new BufferedInputStream(new FileReader("z.txt")); // DOES NOT COMPILE
//        new BufferedWriter(new FileOutputStream("z.txt")); // DOES NOT COMPILE
//        new ObjectInputStream(
//                new FileOutputStream("z.txt")); // DOES NOT COMPILE
//        new BufferedInputStream(new InputStream()); // DOES NOT COMPILE

    }
}
