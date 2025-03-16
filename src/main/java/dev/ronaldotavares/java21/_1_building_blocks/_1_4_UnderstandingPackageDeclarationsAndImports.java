package dev.ronaldotavares.java21._1_building_blocks;

// single class import
//import java.util.Random;

// wildcard import all files in java.util package (not the subpackages files)
import dev.ronaldotavares.java21._1_building_blocks.packageb.ClassA;

import java.util.*;

// redundant imports because java.lang is always imported by default
import java.lang.System;
import java.lang.*;

//import all files in java.nio.file, option to the single file import
//import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.*;            // NO GOOD - a wildcard only matches
// class names, not "file.Files"

//import java.nio.*.*;          // NO GOOD - you can only have one wildcard
// and it must be at the end

import java.nio.file.Paths.*; // NO GOOD - you cannot import methods
// only class names

public class _1_4_UnderstandingPackageDeclarationsAndImports {

    private void javaCommands(){
//        from the project path /java21-cert
//        compile two files
//        javac ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/_1_4_UnderstandingPackageDeclarationsAndImports.java ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/packageb/ClassA.java

//        compile with wildcards
//        javac ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/*.java ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/packageb/*.java

//        run ClassB
//        java -classpath ./src/main/java dev.ronaldotavares.java21._1_building_blocks.ClassB

//        compile with -d option to specify the output directory
//        javac -d classes ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/_1_4_UnderstandingPackageDeclarationsAndImports.java ./src/main/java/dev/ronaldotavares/java21/_1_building_blocks/packageb/ClassA.java

//        all classpath options
//        java -cp classes dev.ronaldotavares.java21._1_building_blocks.ClassB
//        java -classpath classes dev.ronaldotavares.java21._1_building_blocks.ClassB
//        java --class-path classes dev.ronaldotavares.java21._1_building_blocks.ClassB
    }

    public static void main(String[] args) {
        Random r = new Random();   // DOES NOT COMPILE WITHOUT IMPORT
        System.out.println(r.nextInt(10)); // DOES NOT COMPILE WITHOUT IMPORT
    }
}

class InputImports {
    public void read(Files files) {
        Paths.get("name");
    }
}

class Conflicts {
    Date date;
    java.util.Date utilDate;
    java.sql.Date sqlDate;
    // some more code
}

class ClassB {
    public static void main(String[] args) {
        ClassA a;
        System.out.println("Got it");
    }
}

//Class elements order example
/*
package structure;      // package must be first non-comment
import java.util.*;     // import must come after package
public class Meerkat {  // then comes the class
    double weight;       // fields and methods can go in either order
    public double getWeight() {
        return weight;
    }
    double height;    // another field - they don't need to be together
}
*/

//Incorrect order of elements example
/*
import java.util.*;
package structure;       // DOES NOT COMPILE
String name;             // DOES NOT COMPILE
public class Meerkat { } // DOES NOT COMPILE
*/

