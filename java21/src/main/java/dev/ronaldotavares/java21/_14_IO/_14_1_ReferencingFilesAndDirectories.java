package dev.ronaldotavares.java21._14_IO;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _14_1_ReferencingFilesAndDirectories {
    public static void main(String[] args) {
        System.out.println("Referencing Files and Directories");

        var referencingFilesAndDirectories = new _14_1_ReferencingFilesAndDirectories();

        referencingFilesAndDirectories.conceptualizingTheFileSystem();
        referencingFilesAndDirectories.creatingAFileOrPath();
        referencingFilesAndDirectories.switchingBetweenFileAndPath();
    }

    void conceptualizingTheFileSystem(){
        System.out.println("Conceptualizing the File System");

        System.out.print(System.getProperty("file.separator"));
    }

    void creatingAFileOrPath(){
        System.out.println("Creating a File or Path");

        creatingAFile();
        creatingAPath();
    }

    void creatingAFile(){
        System.out.println("Creating a File");

        File zooFile1 = new File("/home/tiger/data/stripes.txt");
        File zooFile2 = new File("/home/tiger", "data/stripes.txt");

        File parent = new File("/home/tiger");
        File zooFile3 = new File(parent, "data/stripes.txt");

        System.out.println(zooFile1.exists());
    }

    void creatingAPath(){
        System.out.println("Creating a Path");

        Path zooPath1 = Path.of("/home/tiger/data/stripes.txt");
        Path zooPath2 = Path.of("/home", "tiger", "data", "stripes.txt");

        System.out.println(Files.exists(zooPath1));

        //other ways to create a Path
        Path zooPath3 = Paths.get("/home", "tiger", "data", "stripes.txt");
        Path zooPath4 = FileSystems.getDefault().getPath("/home/tiger/data/stripes.txt");
        try {
            Path zooPath5 = Path.of(URI.create("https://www.selikoff.net"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void switchingBetweenFileAndPath(){
        System.out.println("Switching Between File and Path");

        File file = new File("rabbit");
        Path newPath = file.toPath();

        File backToFile = newPath.toFile();
    }
}
