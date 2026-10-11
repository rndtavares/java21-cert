package dev.ronaldotavares.java21._14_IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static dev.ronaldotavares.java21._14_IO._14_2_OperatingOnFileAndPath.DEFAULT_PATH;

public class _14_5_SerializingData {
    public static void main(String[] args) {
        System.out.println("Serializing Data");

        var serializingData = new _14_5_SerializingData();

        serializingData.applyingTheSerializableInterface();
        serializingData.ensuringThatAClassIsSerializable();
        serializingData.storingDataWithObjectOutputStreamAndObjectInputStream();
        serializingData.understandingTheDeserializationCreationProcess();

    }

    private void applyingTheSerializableInterface() {
        System.out.println("Applying the Serializable Interface");

        var gorilla = new Gorilla("king kong", 10, false);
    }

    private void ensuringThatAClassIsSerializable() {
        System.out.println("Ensuring that a Class is Serializable");

        var cat = new Cat();
        var record = new Record("Record");
        var record1 = new Record1("Record 1");
    }

    private void storingDataWithObjectOutputStreamAndObjectInputStream() {
        System.out.println("Storing Data with ObjectOutputStream and ObjectInputStream");

        var gorillas = new ArrayList<Gorilla>();
        gorillas.add(new Gorilla("Grodd", 5, false));
        gorillas.add(new Gorilla("Ishmael", 8, true));
        File dataFile = new File(DEFAULT_PATH.concat("gorilla.ser"));

        try {
            saveToFile(dataFile, gorillas);
        } catch (IOException e) {
            System.out.println(e);
        }

        List<Gorilla> gorillasFromDisk = null;
        try {
            gorillasFromDisk = readFromFile(dataFile);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        System.out.println(gorillasFromDisk);
    }

    List<Gorilla> readFromFile(File dataFile) throws IOException, ClassNotFoundException {
        var gorillas = new ArrayList<Gorilla>();
        try (var in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Gorilla g)
                    gorillas.add(g);
            }
        } catch (EOFException e) {
            System.out.println(e);
        }
        return gorillas;
    }

    private void understandingTheDeserializationCreationProcess() {
        System.out.println("Understanding the Deserialization Creation Process");

        var chimpanzees = List.of(new Chimpanzee("Ham", 2,'A'),
                new Chimpanzee("Enos", 3,'B'));
        File dataFile = new File(DEFAULT_PATH.concat("chimpanzee.ser"));
        System.out.println("Original chimpanzees: " + chimpanzees);

        try {
            saveToFile(dataFile, chimpanzees);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Chimpanzee> chimpanzeesFromDisk = null;
        try {
            chimpanzeesFromDisk = readChimpanzeesFromFile(dataFile);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        System.out.println("Chimpanzees From Disk: " + chimpanzeesFromDisk);


        var chimpanzeesBabies = List.of(new BabyChimpanzee("Little Ham",'A'),
                new BabyChimpanzee("Little Enos",'B'));
        File dataFileBabyChimpanzee = new File(DEFAULT_PATH.concat("chimpanzeesBabies.ser"));
        System.out.println("Original chimpanzeesBabies: " + chimpanzeesBabies);

        try {
            saveToFile(dataFileBabyChimpanzee, chimpanzeesBabies);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Chimpanzee> chimpanzeesBabiesFromDisk = null;
        try {
            chimpanzeesBabiesFromDisk = readChimpanzeesFromFile(dataFileBabyChimpanzee);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        System.out.println("Chimpanzees Babies From Disk: " + chimpanzeesBabiesFromDisk);

        var fishes = List.of(new Fish(), new Fish());
        File dataFileFish = new File(DEFAULT_PATH.concat("fish.ser"));
        System.out.println("Original fishes: " + fishes);

        try {
            saveToFile(dataFileFish, fishes);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Fish> fishesFromDisk = null;
        try {
            fishesFromDisk = readFishesFromFile(dataFileFish);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        System.out.println("Fishes From Disk: " + fishesFromDisk);

    }

    List<Chimpanzee> readChimpanzeesFromFile(File dataFile) throws IOException, ClassNotFoundException {
        var chimpanzees = new ArrayList<Chimpanzee>();
        try (var in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Chimpanzee c)
                    chimpanzees.add(c);
            }
        } catch (EOFException e) {
            System.out.println(e);
        }
        return chimpanzees;
    }

    void saveToFile(File dataFile, List<?> objects) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (Object object : objects)
                out.writeObject(object);
        }
    }

    List<Fish> readFishesFromFile(File dataFile) throws IOException, ClassNotFoundException {
        var fishes = new ArrayList<Fish>();
        try (var in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Fish f)
                    fishes.add(f);
            }
        } catch (EOFException e) {
            System.out.println(e);
        }
        return fishes;
    }
}

class Gorilla implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Boolean friendly;
    private transient String favoriteFood;

    public Gorilla(String name, int age, Boolean friendly) {
        this.name = name;
        this.age = age;
        this.friendly = friendly;
    }

    @Override
    public String toString() {
        return "Gorilla{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendly=" + friendly +
                ", favoriteFood='" + favoriteFood + '\'' +
                '}';
    }
}

class Cat implements Serializable {
    private Tail tail = new Tail();
}

class Tail implements Serializable {
//    private Fur fur = new Fur();
    private transient Fur fur = new Fur();
}

class Fur /* implements Serializable */ {}

record Record(String name) {}

record Record1(String name) implements Serializable {}

class Mammal {
    private int id;
    public Mammal() {
        this.id = 4;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "id=" + id +
                '}';
    }
}

class Chimpanzee extends Mammal implements Serializable {
    private static final long serialVersionUID = 2L;
    private transient String name;
    private transient int age = 10;
    private static char type = 'C';
    { this.age = 14; }
    public Chimpanzee() {
        this("Unknown", 12, 'Q');
    }
    public Chimpanzee(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
        setId(9);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static char getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Chimpanzee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", id=" + getId() +
                '}';
    }
}

class BabyChimpanzee extends Chimpanzee {
    private static final long serialVersionUID = 3L;
    private String mother = "Mom";
    public BabyChimpanzee() { super(); }
    public BabyChimpanzee(String name, char type) {
        super(name, 0, type);
    }

    @Override
    public String toString() {
        return "BabyChimpanzee{" +
                "mother='" + mother + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", type='" + getType() + '\'' +
                ", id=" + getId() +
                '}';
    }
}

class Fish implements Serializable {
    private String name;
    private transient int fins;

    private void readObject(ObjectInputStream in)
            throws ClassNotFoundException, IOException {
        in.defaultReadObject();

        this.fins = 10;
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {

        this.name = "Nemo";
        out.defaultWriteObject();
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", fins=" + fins +
                '}';
    }
}