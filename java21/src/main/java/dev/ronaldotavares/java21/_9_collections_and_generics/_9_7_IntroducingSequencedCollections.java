package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.*;

public class _9_7_IntroducingSequencedCollections {
    public static void main(String[] args) {
        var visitArrayList = new ArrayList<String>(List.of("Huey", "Dewey", "Louie"));
        var visitLinkedList = new LinkedList<String>(List.of("Moe", "Larry", "Shemp"));
        var visitTreeSet = new TreeSet<String>(Set.of("Alvin", "Simon", "Theodore"));

        welcomeNext(visitArrayList);  // Huey
        welcomeNext(visitLinkedList); // Moe
        welcomeNext(visitTreeSet);    // Alvin

        welcomeNext(visitArrayList);  // Huey
        welcomeNext(visitLinkedList); // Moe
        welcomeNext(visitTreeSet);    // Alvin

        var visitArrayList1 = new ArrayList<String>(List.of("Bluey", "Bingo", "Socks"));
        var visitLinkedList1 = new LinkedList<String>(List.of("Garfield", "Odie"));
        var visitTreeSet1 = new TreeSet<String>(Set.of("Tom", "Jerry"));

        moveToEnd(visitArrayList1);
        welcomeNext(visitArrayList1); // Welcome to the Zoo! Bingo
        moveToEnd(visitLinkedList1);
        welcomeNext(visitLinkedList1); // Welcome to the Zoo! Odie
//        moveToEnd(visitTreeSet1); // java.lang.UnsupportedOperationException
        welcomeNext(visitTreeSet1);

        //sequencedMap
//        var visitHashMap = new HashMap<String,String>( //DOES NOT COMPILE - HASH MAP IS NOT A SEQUENCED MAP
//                Map.of("1", "Yakko", "2", "Wakko", "3", "Dot"));
        var visitTreeMap = new TreeMap<String,String>(
                Map.of("Pink", "Blossom", "Green", "Buttercup", "Blue", "Bubbles"));
        welcomeNext(visitTreeMap);
    }

    private static void welcomeNext(SequencedCollection<String> visitors) {
        System.out.println("Welcome to the Zoo! " + visitors.getFirst());
        visitors.removeFirst();
    }

    private static void moveToEnd(SequencedCollection<String> visitors) {
        visitors.addLast(visitors.removeFirst());
    }

    private static void welcomeNext(SequencedMap<String, String> visitors) {
        System.out.println("Welcome to the Zoo! " + visitors.pollFirstEntry());
    }
}
