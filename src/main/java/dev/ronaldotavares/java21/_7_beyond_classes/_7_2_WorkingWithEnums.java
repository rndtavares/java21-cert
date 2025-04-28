package dev.ronaldotavares.java21._7_beyond_classes;

public class _7_2_WorkingWithEnums {
    public static void main(String[] args) {
        var s = Season.SUMMER;
        System.out.println(Season.SUMMER); // SUMMER
        System.out.println(s == Season.SUMMER); // true

        for(var season: Season.values()) {
            System.out.println(season.name() + " " + season.ordinal());
        }

//        if (Season.SUMMER == 2) {} // DOES NOT COMPILE

        Season season = Season.valueOf("SUMMER"); // SUMMER
//        Season t = Season.valueOf("summer"); // IllegalArgumentException

        System.out.println(getWeather(Season.SUMMER)); // Too hot
        System.out.println(getWeather(Season.WINTER)); // Too cold
        System.out.println(getWeather(Season.SPRING)); // Just right
        System.out.println(getWeather(Season.FALL)); // Just right

        workingWithComplexEnums();
    }

    private static String getWeather(Season value) {
        return switch (value) {
            case SUMMER -> "Too hot";
            case Season.WINTER -> "Too cold";
//            case 0 -> "Too cold"; // DOES NOT COMPILE
            case SPRING, FALL -> "Just right";
        };
    }

    private static void workingWithComplexEnums() {
        System.out.print("begin, ");
        var firstCall = SeasonWithVisitors.SUMMER; // Prints 4 times
        System.out.print("middle, ");
        var secondCall = SeasonWithVisitors.SUMMER; // Doesn't print anything
        System.out.println("end");

        SeasonWithVisitors.SUMMER.printVisitors();

        System.out.println(SeasonWithTimes.WINTER.getHours());
        System.out.println(SeasonWithTimes.FALL.getHours());
        System.out.println(SeasonWithTimes.SPRING.getHours());
        System.out.println(SeasonWithTimes.SUMMER.getHours());

        System.out.println(SeasonWithTimes1.WINTER.getHours());
        System.out.println(SeasonWithTimes1.FALL.getHours());
        System.out.println(SeasonWithTimes1.SPRING.getHours());
        System.out.println(SeasonWithTimes1.SUMMER.getHours());
    }
}

enum Season {
    WINTER, SPRING, SUMMER, FALL;
}

//enum ExtendedSeason extends Season {} // DOES NOT COMPILE

interface Visitors { void printVisitors(); }

enum SeasonWithVisitors implements Visitors {
    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");

    private final String visitors;
    public static final String DESCRIPTION = "Weather enum";

//    public SeasonWithVisitors(String visitors) { // DOES NOT COMPILE
    private SeasonWithVisitors(String visitors) {
        System.out.print("constructing, ");
        this.visitors = visitors;
    }

    @Override public void printVisitors() {
        System.out.println(visitors);
    }

}

enum SeasonWithTimes {
    WINTER {
        public String getHours() { return "10am-3pm"; }
    },
    SPRING {
        public String getHours() { return "9am-5pm"; }
    },
    SUMMER {
        public String getHours() { return "9am-7pm"; }
    },
    FALL {
        public String getHours() { return "9am-5pm"; }
    };
    public abstract String getHours();
}

enum SeasonWithTimes1 {
    WINTER {
        public String getHours() { return "10am-3pm"; }
    },
    SUMMER {
        public String getHours() { return "9am-7pm"; }
    },
    SPRING, FALL;
    public String getHours() { return "9am-5pm"; }
}