package dev.ronaldotavares.java21._14_IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class _14_2_OperatingOnFileAndPath {

    public static final String DEFAULT_PATH = "src/main/resources/io/";

    public static void main(String[] args) {
        System.out.println("Operating on File and Path");

        var operatingOnFileAndPath = new _14_2_OperatingOnFileAndPath();
        operatingOnFileAndPath.usingSharedFunctionality();
        operatingOnFileAndPath.providingNIO2OptionalParameters();
        operatingOnFileAndPath.interactingWithNIO2Paths();
    }

    void usingSharedFunctionality() {
        System.out.println("Using Shared Functionality");

        String relativePath = "src/main/java/dev/ronaldotavares/java21/_14_IO/_14_2_OperatingOnFileAndPath.java";
        io(new File(relativePath));
        try {
            nio(Path.of(relativePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String relativeDirectoryPath = "src/main/java/dev/ronaldotavares/java21/_14_IO";
        io(new File(relativeDirectoryPath));
        try {
            nio(Path.of(relativeDirectoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void io(File file) {
        System.out.println("Using IO with File: " + file.getPath());
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
        System.out.println("Using NIO with Path: " + path.toString());
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

    void providingNIO2OptionalParameters() {
        System.out.println("Providing NIO2 Optional Parameters");

        Path path = Path.of("schedule.xml");
        boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println("File exists: " + exists);

        Path testPath = Path.of("src/main/resources/io/test");
        Path newTestPath = Path.of("src/main/resources/io/land/test");
        try {
            // Move testPath to newTestPath if it exists, otherwise move it back from newTestPath to testPath
            if (Files.exists(testPath)) {
                move(testPath, newTestPath);
            } else if (Files.exists(newTestPath)) {
                move(newTestPath, testPath);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void move(Path source, Path target) throws IOException {
        Files.move(source, target,
                LinkOption.NOFOLLOW_LINKS,
                StandardCopyOption.ATOMIC_MOVE);
    }

    void interactingWithNIO2Paths() {
        System.out.println("Interacting with NIO2 Paths");

        System.out.println("imutability of Path");
        Path p = Path.of("whale");
        p.resolve("krill");
        System.out.println(p);  // whale

        System.out.println("Changing Path");
        Path.of("/zoo/../home").getParent().normalize().toAbsolutePath();

        viewingThePath();
        creatingPartOfThePath();
        accessingPathElements();
        resolvingPaths();
        relativizingAPath();
        normalizingAPath();
        retrievingTheRealFileSystemPath();
        creatingMovingAndDeletingFilesAndDirectories();
    }

    void viewingThePath() {
        System.out.println("Viewing the Path");

        Path path = Path.of("land/hippo/harry.happy");
        System.out.println("The Path is: " + path);
        for (int i = 0; i < path.getNameCount(); i++)
            System.out.println("   Element " + i + " is: " + path.getName(i));

        System.out.println("root is not included in the name count");
        var path1 = Path.of("/");
        System.out.println(path1.getNameCount()); // 0
        try {
            System.out.println(path1.getName(0)); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    void creatingPartOfThePath() {
        System.out.println("Creating Part of the Path");

        var p = Path.of("/mammal/omnivore/raccoon.image");
        System.out.println("Path is: " + p);
        for (int i = 0; i < p.getNameCount(); i++) {
            System.out.println(" Element " + i + " is: " + p.getName(i));
        }
        System.out.println();
        System.out.println("subpath(0,3):" + p.subpath(0, 3));
        System.out.println("subpath(1,2):" + p.subpath(1, 2));
        System.out.println("subpath(1,3): " + p.subpath(1, 3));

        try {
            var q = p.subpath(1, 4); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            var x = p.subpath(1, 1); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    void accessingPathElements() {
        System.out.println("Accessing Path Elements");

        printPathInformation(Path.of("zoo"));
        printPathInformation(Path.of("/zoo/armadillo/shells.txt"));
        printPathInformation(Path.of(" ./armadillo/../shells.txt"));
    }

    void printPathInformation(Path path) {
        System.out.println("Filename is: " + path.getFileName());
        System.out.println(" Root is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null)
            System.out.println(" Current parent is: " + currentParent);
        System.out.println();
    }

    void resolvingPaths() {
        System.out.println("Resolving Paths");

        Path path1 = Path.of("/cats/../panther");
        Path path2 = Path.of("food");
        System.out.println(path1.resolve(path2));

        Path path3 = Path.of("/turkey/food");
        System.out.println(path3.resolve("/tiger/cage"));
    }

    void relativizingAPath() {
        System.out.println("Relativizing a Path");

        var path1 = Path.of("fish.txt");
        var path2 = Path.of("friendly/birds.txt");
        System.out.println(path1.relativize(path2));
        System.out.println(path2.relativize(path1));

        var path3 = Path.of("E:\\habitat");
        var path4 = Path.of("E:\\sanctuary\\raven\\poe.txt");
        System.out.println(path3.relativize(path4));
        System.out.println(path4.relativize(path3));

        try {
            var path5 = Path.of("/primate/chimpanzee");
            var path6 = Path.of("bananas.txt");

            System.out.println(path5.relativize(path6)); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        try {
            var path7 = Path.of("C:\\primate\\chimpanzee");
            var path8 = Path.of("D:\\storage\\bananas.txt");

            System.out.println(path7.relativize(path8)); // IllegalArgumentException in windows
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    void normalizingAPath() {
        System.out.println("Normalizing a Path");

        var p1 = Path.of(" ./armadillo/../shells.txt");
        System.out.println(p1.normalize()); // shells.txt

        var p2 = Path.of("/cats/../panther/food");
        System.out.println(p2.normalize()); // /panther/food

        var p3 = Path.of(" ../../fish.txt");
        System.out.println(p3.normalize()); // ../../fish.txt

        var p4 = Path.of("/pony/../weather.txt");
        var p5 = Path.of("/weather.txt");
        System.out.println(p4.equals(p5)); // false
        System.out.println(p4.normalize().equals(p5.normalize())); // true
    }

    void retrievingTheRealFileSystemPath() {
        System.out.println("Retrieving the Real File System Path");

        try {
            System.out.println(Path.of(DEFAULT_PATH, "/zebra/food.txt").toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println(Path.of("../java21-cert", DEFAULT_PATH, "zebra/./food.txt").toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println(Path.of(".").toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void creatingMovingAndDeletingFilesAndDirectories() {
        System.out.println("Creating, Moving and Deleting Files and Directories");

        makingDirectories();
        copyingFiles();
        copyingAndReplacingFiles();
        copyingFilesWithIOStreams();
        copyingFilesIntoADirectory();
        movingOrRenamingPathsWithMove();
        performingAnAtomicMove();
        deletingAFileWithDeleteAndDeleteIfExists();
        comparingFilesWithIsSameFileAndMismatch();
    }

    private void makingDirectories() {
        System.out.println("Making Directories");
        try {
            Files.createDirectory(Path.of(DEFAULT_PATH, "/bison/field"));
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            Files.createDirectories(Path.of(DEFAULT_PATH, "/bison/field/pasture/green"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void copyingFiles() {
        System.out.println("Copying Files");

        try {
            Files.copy(Path.of(DEFAULT_PATH, "/panda/bamboo.txt"), Path.of(DEFAULT_PATH, "/panda-save/bamboo.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            Files.copy(Path.of(DEFAULT_PATH, "/turtle"), Path.of(DEFAULT_PATH, "/turtleCopy"));
        } catch (IOException e) {
            System.out.println(e);
        }

        copyPath(Path.of(DEFAULT_PATH, "/zebra"), Path.of(DEFAULT_PATH, "/zebraCopy"));
    }

    void copyPath(Path source, Path target) {
        try {
            Files.copy(source, target);
            if (Files.isDirectory(source))
                try (Stream<Path> s = Files.list(source)) {
                    s.forEach(p -> copyPath(p, target.resolve(p.getFileName())));
                }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void copyingAndReplacingFiles() {
        System.out.println("Copying and Replacing Files");
        try {
            Files.copy(Path.of(DEFAULT_PATH, "book.txt"), Path.of(DEFAULT_PATH, "movie.txt"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void copyingFilesWithIOStreams() {
        System.out.println("Copying Files with IO Streams");

        try (var is = new FileInputStream(DEFAULT_PATH + "source-data.txt")) {
            Files.copy(is, Path.of(DEFAULT_PATH, "/mammals/wolf.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            Files.copy(Path.of(DEFAULT_PATH, "/fish/clown.xsl"), System.out);
            System.out.println();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void copyingFilesIntoADirectory() {
        System.out.println("Copying Files into a Directory");

        var file = Path.of(DEFAULT_PATH, "food.txt");
        try {
            var directory = Path.of(DEFAULT_PATH, "/enclosure");
            Files.copy(file, directory); // ERRO se /enclosure existir
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            var directory = Path.of(DEFAULT_PATH, "/enclosure/food.txt");
            Files.copy(file, directory);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void movingOrRenamingPathsWithMove() {
        System.out.println("Moving or Renaming Paths with Move");

        try {
            Files.move(Path.of(DEFAULT_PATH, "zoo"), Path.of(DEFAULT_PATH, "zoo-new"));
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            Files.move(Path.of(DEFAULT_PATH, "user/addresses.txt"),
                    Path.of(DEFAULT_PATH, "zoo-new/addresses2.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void performingAnAtomicMove() {
        System.out.println("Performing an Atomic Move");
        try {
            Files.move(Path.of(DEFAULT_PATH, "mouse.txt"), Path.of(DEFAULT_PATH, "gerbil.txt"),
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void deletingAFileWithDeleteAndDeleteIfExists() {
        System.out.println("Deleting a File with Delete and Delete If Exists");

        try {
            Files.delete(Path.of(DEFAULT_PATH, "/vulture/feathers.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            boolean b = Files.deleteIfExists(Path.of(DEFAULT_PATH, "/pigeon"));
            System.out.println("deleted=" + b);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void comparingFilesWithIsSameFileAndMismatch() {
        System.out.println("comparing Files with Is Same File and Mismatch");

        try {
            System.out.println(Files.isSameFile(
                    Path.of(DEFAULT_PATH, "/animals/cobra"),
                    Path.of(DEFAULT_PATH, "/animals/snake")));
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println(Files.isSameFile(
                    Path.of(DEFAULT_PATH, "/animals/monkey/ears.png"),
                    Path.of(DEFAULT_PATH, "/animals/wolf/ears.png"))); // false
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println(Files.mismatch(
                    Path.of(DEFAULT_PATH, "/animals/monkey.txt"),
                    Path.of(DEFAULT_PATH, "/animals/wolf.txt"))); // 1
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println(Files.mismatch(
                    Path.of(DEFAULT_PATH, "/animals/wolf.txt"),
                    Path.of(DEFAULT_PATH, "/animals/monkey.txt"))); // 1
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}