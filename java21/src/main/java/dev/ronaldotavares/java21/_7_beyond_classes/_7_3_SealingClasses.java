package dev.ronaldotavares.java21._7_beyond_classes;

public class _7_3_SealingClasses {
    public static void main(String[] args) {

    }
    private static String getType(Fish1 fish) {
        return switch (fish) {
            case Trout t -> "Trout!";
            case Bass b -> "Bass!";
        };
    }
}

sealed class Bear1 permits Kodiak, Panda {}

final class Kodiak extends Bear1 {}

non-sealed class Panda extends Bear1 {}

//class sealed Frog permits GlassFrog {} // DOES NOT COMPILE
//final class GlassFrog extends Frog {}

abstract sealed class Mammal permits Wolf {}
final class Wolf extends Mammal {}
//final class Tiger extends Mammal {} // DOES NOT COMPILE

//sealed class Penguin permits Emperor {}
final class Emperor {}

sealed class Antelope permits Gazelle {}
final class Gazelle extends Antelope {}

sealed class Fish permits ClownFish {}
sealed class ClownFish extends Fish permits OrangeClownFish {}
final class OrangeClownFish extends ClownFish {}

abstract sealed class Mammal1 permits Feline {}
non-sealed class Feline extends Mammal1 {}
class Tiger extends Feline {}
class BengalTiger extends Tiger {}

sealed class Snake10 permits Cobra10 {}
final class Cobra10 extends Snake10 {}

sealed class Snake11 {}
final class Cobra11 extends Snake11 {}

sealed class Snake12 {
//sealed class Snake12 permits Cobra12 { // DOES NOT COMPILE
//sealed class Snake12 permits Snake12.Cobra12 {
    final class Cobra12 extends Snake12 {}
}

sealed interface Swims permits Duck, Swan, Floats {}
// Classes permitted to implement sealed interface
final class Duck implements Swims {}
final class Swan implements Swims {}
// Interface permitted to extend sealed interface
non-sealed interface Floats extends Swims {}

abstract sealed class Fish1 permits Trout, Bass {}
final class Trout extends Fish1 {}
final class Bass extends Fish1 {}