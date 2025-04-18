package dev.ronaldotavares.java21._5_methods;

import dev.ronaldotavares.java21._5_methods.pond.duck.*;
import dev.ronaldotavares.java21._5_methods.pond.goose.Goose;
import dev.ronaldotavares.java21._5_methods.pond.goose.Gosling;
import dev.ronaldotavares.java21._5_methods.pond.goose.LostDuckling;
import dev.ronaldotavares.java21._5_methods.pond.inland.BirdWatcherFromAfar;
import dev.ronaldotavares.java21._5_methods.pond.shore.BirdWatcher;
import dev.ronaldotavares.java21._5_methods.pond.swan.BadCygnet;
import dev.ronaldotavares.java21._5_methods.pond.swan.Swan;

public class _5_4_ApplyingAccessModifiers {
    public static void main(String[] args) {

//        var fatherDuck = new FatherDuck();

        var badDuckling = new BadDuckling();
        badDuckling.makeNoise();

//        var motherDuck = new MotherDuck();

        var goodDuckling = new GoodDuckling();
        goodDuckling.makeNoise();

        var badCygnet = new BadCygnet();
        badCygnet.makeNoise();

//        var bird = new Bird();

        var gosling = new Gosling();
        gosling.swim();

        var birdWatcher = new BirdWatcher();
        birdWatcher.watchBird();

        var birdWatcherFromAfar = new BirdWatcherFromAfar();
        birdWatcherFromAfar.watchBird();

        var swan = new Swan();
        swan.helpOtherSwanSwim();
        swan.helpOtherBirdSwim();

        var goose = new Goose();
        goose.helpGooseSwim();
        goose.helpOtherGooseSwim();

        var gooseWatcher = new GooseWatcher();
        gooseWatcher.watch();

        var duckTeacher = new DuckTeacher();
        duckTeacher.swim();

        var lostDuckling = new LostDuckling();
        lostDuckling.swim();
    }
}

class Fish {}

class ClownFish extends Fish {}
