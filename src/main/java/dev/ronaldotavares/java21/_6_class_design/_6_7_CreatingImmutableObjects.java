package dev.ronaldotavares.java21._6_class_design;

import java.util.ArrayList;
import java.util.List;

public class _6_7_CreatingImmutableObjects {
    public static void main(String[] args) {
        var zebra = new Animal_6_7();
        System.out.println(zebra.getFavoriteFoods());  // [Apples]

        zebra.getFavoriteFoods().clear();
        zebra.getFavoriteFoods().add("Chocolate Chip Cookies");
        System.out.println(zebra.getFavoriteFoods());  // [Chocolate Chip Cookies]

        var favorites = new ArrayList<String>();
        favorites.add("Apples");
        var zebra1 = new Animal_6_7_v2(favorites); // Caller still has access to favorites
        System.out.println(zebra1.getFavoriteFoodsItem(0)); // [Apples]
        favorites.clear();
        favorites.add("Chocolate Chip Cookies");
        System.out.println(zebra1.getFavoriteFoodsItem(0)); // [Chocolate Chip Cookies]

        favorites.clear();
        favorites.add("Apples");
        var zebra2 = new Animal_6_7_v3(favorites); // Caller still has access to favorites
        System.out.println(zebra2.getFavoriteFoodsItem(0)); // [Apples]
        favorites.clear();
        favorites.add("Chocolate Chip Cookies");
        System.out.println(zebra2.getFavoriteFoodsItem(0)); // [Apples]
    }
}

class Animal_6_7 {  // Not an immutable object declaration
    private final ArrayList<String> favoriteFoods;

    public Animal_6_7() {
        this.favoriteFoods = new ArrayList<String>();
        this.favoriteFoods.add("Apples");
    }

    public List<String> getFavoriteFoods() {
        return favoriteFoods;
    }
}

final class Animal_6_7_v1 {  // An immutable object declaration
    private final List<String> favoriteFoods;

    public Animal_6_7_v1() {
        this.favoriteFoods = new ArrayList<String>();
        this.favoriteFoods.add("Apples");
    }

    public int getFavoriteFoodsCount() {
        return favoriteFoods.size();
    }

    public String getFavoriteFoodsItem(int index) {
        return favoriteFoods.get(index);
    }

    public ArrayList<String> getFavoriteFoods() {
        return new ArrayList<String>(this.favoriteFoods);
    }
}

final class Animal_6_7_v2 { // Not an immutable object declaration
    private final ArrayList<String> favoriteFoods;
    public Animal_6_7_v2(ArrayList<String> favoriteFoods) {
        if (favoriteFoods == null || favoriteFoods.size() == 0)
            throw new RuntimeException("favoriteFoods is required");
        this.favoriteFoods = favoriteFoods;
    }
    public int getFavoriteFoodsCount() {
        return favoriteFoods.size();
    }
    public String getFavoriteFoodsItem(int index) {
        return favoriteFoods.get(index);
    }
}

final class Animal_6_7_v3 {
    private final ArrayList<String> favoriteFoods;
    public Animal_6_7_v3(ArrayList<String> favoriteFoods) {
        if (favoriteFoods == null || favoriteFoods.size() == 0)
            throw new RuntimeException("favoriteFoods is required");
        //this.favoriteFoods = favoriteFoods;
        this.favoriteFoods = new ArrayList<String>(favoriteFoods);
    }
    public int getFavoriteFoodsCount() {
        return favoriteFoods.size();
    }
    public String getFavoriteFoodsItem(int index) {
        return favoriteFoods.get(index);
    }
}
