package dev.ronaldotavares.java21._4_core_apis;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class _4_6_WorkingWithDatesAndTimes {
    public static void main(String[] args) {
        creatingDatesAndTimes();
        manipulationDatesAndTimes();
        workingWithPeriods();
        workingWithDurations();
        periodVsDuration();
        workingWithInstants();
        accountingForDaylightSavingTime();
    }

    private static void creatingDatesAndTimes() {
        System.out.println("Creating Dates and Times");
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());

        var date1 = LocalDate.of(2025, Month.JANUARY, 20);
        var date2 = LocalDate.of(2025, 1, 20);

        var time1 = LocalTime.of(6, 15);                      // hour and minute 
        var time2 = LocalTime.of(6, 15, 30);                // + seconds 
        var time3 = LocalTime.of(6, 15, 30, 200);         // + nanoseconds

        var dateTime1 = LocalDateTime.of(2025, Month.JANUARY, 20, 6, 15, 30);
        var dateTime2 = LocalDateTime.of(date1, time2);

        var zone = ZoneId.of("US/Eastern");
        var zoned1 = ZonedDateTime.of(2025, 1, 20, 6, 15, 30, 200, zone);
        var zoned2 = ZonedDateTime.of(date1, time1, zone);
        var zoned3 = ZonedDateTime.of(dateTime1, zone);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
        System.out.println(dateTime1);
        System.out.println(dateTime2);
        System.out.println(zoned1);
        System.out.println(zoned2);
        System.out.println(zoned3);

//        var d = new LocalDate(); // DOES NOT COMPILE

//        var d = LocalDate.of(2025, Month.JANUARY, 32); // DateTimeException
    }

    private static void manipulationDatesAndTimes() {
        System.out.println("Manipulation Dates and Times");

        var date = LocalDate.of(2025, Month.JANUARY, 20);
        System.out.println(date);      // 2025–01–20
        date = date.plusDays(2);
        System.out.println(date);      // 2025–01–22
        date = date.plusWeeks(1);
        System.out.println(date);      // 2025–01–29
        date = date.plusMonths(1);
        System.out.println(date);      // 2025–02–28
        date = date.plusYears(5);
        System.out.println(date);      // 2030–02–28

        var date0 = LocalDate.of(2025, Month.JANUARY, 20);
        var time = LocalTime.of(5, 15);
        var dateTime = LocalDateTime.of(date0, time);
        System.out.println(dateTime);          // 2025–01–20T05:15
        dateTime = dateTime.minusDays(1);
        System.out.println(dateTime);          // 2025–01–19T05:15
        dateTime = dateTime.minusHours(10);
        System.out.println(dateTime);          // 2025–01–18T19:15
        dateTime = dateTime.minusSeconds(30);
        System.out.println(dateTime);          // 2025–01–18T19:14:30

        var date1 = LocalDate.of(2025, Month.JANUARY, 20);
        var time1 = LocalTime.of(5, 15);
        var dateTime1 = LocalDateTime.of(date1, time1)
                .minusDays(1).minusHours(10).minusSeconds(30);
        System.out.println(dateTime1);

        var date2 = LocalDate.of(2025, Month.JANUARY, 20);
        date2.plusDays(10);
        System.out.println(date2);

        var date3 = LocalDate.of(2025, Month.JANUARY, 20);
//        date3 = date3.plusMinutes(1);          // DOES NOT COMPILE

        var date4 = LocalDate.of(2025, Month.FEBRUARY, 20);   // 2025-02-20
        var differentDay = date4.withDayOfMonth(15);             // 2025-02-15
        var differentMonth = date4.withDayOfYear(3);             // 2025-01-03
        var allChanged = date4.withYear(2026).withMonth(4)
                .withDayOfMonth(10);// 2026-04-10
        System.out.println(differentDay);
        System.out.println(differentMonth);
        System.out.println(allChanged);
        System.out.println(date4);

        var date5 = LocalDate.of(2025, Month.MARCH, 3);
        var withTime = date5.atTime(5, 30);   // 2025-03-03T05:30
        var start = date5.atStartOfDay();      // 2025-03-03T00:00
        System.out.println(date5);
        System.out.println(withTime);
        System.out.println(start);
    }

    private static void workingWithPeriods() {
        System.out.println("Working with Periods");
        
        var start = LocalDate.of(2025, Month.JANUARY, 1);
        var end = LocalDate.of(2025, Month.MARCH, 30);
        performAnimalEnrichment(start, end);

        var period = Period.ofMonths(1);      // create a period
        performAnimalEnrichment(start, end, period);

        var annually = Period.ofYears(1);                  // every 1 year 
        var quarterly = Period.ofMonths(3);               // every 3 months 
        var everyThreeWeeks = Period.ofWeeks(-3);      // every 3 weeks going backwards 
        var everyOtherDay = Period.ofDays(2);            // every 2 days 
        var everyYearAndAWeek = Period.of(1, 0, 7);   // every year plus 1 week

        System.out.println(annually);
        System.out.println(quarterly);
        System.out.println(everyThreeWeeks);
        System.out.println(everyOtherDay);
        System.out.println(everyYearAndAWeek);

        var wrong = Period.ofYears(1).ofWeeks(1); // every week
        System.out.println(wrong); // every week

        var wrong1 = Period.ofYears(1);
        wrong1 = Period.ofWeeks(1);
        System.out.println(wrong1);

        System.out.println(Period.ofMonths(3));

        var xmas = LocalDate.of(2025, Month.DECEMBER, 25);
        var newYears = LocalDate.of(2026, Month.JANUARY, 1);   
        System.out.println(Period.between(xmas, newYears));   // P7D 
        System.out.println(Period.between(newYears, xmas));   // P-7D

        var date = LocalDate.of(2025, 3, 20);
        var time = LocalTime.of(6, 15);
        var dateTime = LocalDateTime.of(date, time);
        var period1 = Period.ofMonths(-1);
        System.out.println(date.plus(period1));       // 2025–02–20
        System.out.println(dateTime.plus(period1)); // 2025–02–20T06:15
//        System.out.println(time.plus(period1));       // Exception
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
	    var upTo = start;     
        while (upTo.isBefore(end)) {   // check if still before end           
            System.out.println("give new toy: " + upTo);           
            upTo = upTo.plusMonths(1); // add a month 
        }
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period  period) { // uses the generic period
        var upTo = start;     
        while (upTo.isBefore(end)) {           
            System.out.println("give new toy: " + upTo);           
            upTo = upTo.plus(period); // adds the period 
        }
    }

    private static void workingWithDurations() {
        System.out.println("Working with Durations");
        var daily = Duration.ofDays(1);               // PT24H 
        var hourly = Duration.ofHours(1);             // PT1H 
        var everyMinute = Duration.ofMinutes(1);      // PT1M 
        var everyTenSeconds = Duration.ofSeconds(10); // PT10S 
        var everyMilli = Duration.ofMillis(1);        // PT0.001S 
        var everyNano = Duration.ofNanos(1);          // PT0.000000001S

        var daily1 = Duration.of(1, ChronoUnit.DAYS);
        var hourly1 = Duration.of(1, ChronoUnit.HOURS);
        var everyMinute1 = Duration.of(1, ChronoUnit.MINUTES);
        var everyTenSeconds1 = Duration.of(10, ChronoUnit.SECONDS);
        var everyMilli1 = Duration.of(1, ChronoUnit.MILLIS);
        var everyNano1 = Duration.of(1, ChronoUnit.NANOS);

        var date = LocalDate.of(2025, 1, 20);
        var time = LocalTime.of(6, 15);
        var dateTime = LocalDateTime.of(date, time);
        var duration = Duration.ofHours(6);

        System.out.println(dateTime.plus(duration));  // 2025–01–20T12:15
        System.out.println(time.plus(duration));      // 12:15
//        System.out.println(date.plus(duration));  // UnsupportedTemporalTypeException

        var duration1 = Duration.ofHours(23);
        System.out.println(dateTime.plus(duration1));  // 2025–01–21T05:15
        System.out.println(time.plus(duration1));      // 05:15
//        System.out.println(date.plus(duration));  // UnsupportedTemporalTypeException

        var one = LocalTime.of(5, 15);
        var two = LocalTime.of(6, 55);
        System.out.println(ChronoUnit.HOURS.between(one, two));     // 1 
        System.out.println(ChronoUnit.MINUTES.between(one, two));   // 100 
//        System.out.println(ChronoUnit.MINUTES.between(one, date));  // DateTimeException

        LocalTime time1 = LocalTime.of(3,12,45);
        System.out.println(time1);      // 03:12:45
        LocalTime truncated = time1.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(truncated); // 03:12
    }
    
    private static void periodVsDuration() {
        System.out.println("Period vs Duration");
        var date = LocalDate.of(2025, 5, 25);
        var period = Period.ofDays(1);
        var days = Duration.ofDays(1);

        System.out.println(date.plus(period));   // 2025–05–26 
//        System.out.println(date.plus(days));     // Unsupported unit: Seconds
    }

    private static void workingWithInstants() {
        System.out.println("Working with Instants");
        var now = Instant.now();
        // Do something time consuming
        for(int i = 0; i < 1000000; i++) {}
        var later = Instant.now();  

        var duration = Duration.between(now, later);
        System.out.println(duration.toMillis());  // Returns number milliseconds

        var date = LocalDate.of(2025, 5, 25);
        var time = LocalTime.of(11, 55, 00);
        var zone = ZoneId.of("US/Eastern");
        var zonedDateTime = ZonedDateTime.of(date, time, zone);

        var instant = zonedDateTime.toInstant(); // 2025–05–25T15:55:00Z
        System.out.println(zonedDateTime); // 2025–05–25T11:55–04:00[US/Eastern]
        System.out.println(instant);       // 2025–05–25T15:55:00Z
    }

    private static void accountingForDaylightSavingTime() {
        System.out.println("Accounting for Daylight Saving Time");
        var date = LocalDate.of(2025, Month.MARCH, 9);
        var time = LocalTime.of(1, 30);
        var zone = ZoneId.of("US/Eastern");
        var dateTime = ZonedDateTime.of(date, time, zone);  

        System.out.println(dateTime);  // 2025–03-09T01:30-05:00[US/Eastern]
        System.out.println(dateTime.getHour());   // 1
        System.out.println(dateTime.getOffset()); // -05:00  

        dateTime = dateTime.plusHours(1);
        System.out.println(dateTime);  // 2025–03-09T03:30-04:00[US/Eastern]
        System.out.println(dateTime.getHour());   // 3
        System.out.println(dateTime.getOffset()); // -04:00

        var date1 = LocalDate.of(2025, Month.NOVEMBER, 2);
        var time1 = LocalTime.of(1, 30);
        var zone1 = ZoneId.of("US/Eastern");
        var dateTime1 = ZonedDateTime.of(date1, time1, zone1);
        System.out.println(dateTime1); // 2025-11-02T01:30-04:00[US/Eastern]

        dateTime = dateTime1.plusHours(1);
        System.out.println(dateTime); // 2025-11-02T01:30-05:00[US/Eastern]  

        dateTime1 = dateTime1.plusHours(1);
        System.out.println(dateTime); // 2025-11-02T02:30-05:00[US/Eastern]

        var date2 = LocalDate.of(2025, Month.MARCH, 9);
        var time2 = LocalTime.of(2, 30);
        var zone2 = ZoneId.of("US/Eastern");
        var dateTime2 = ZonedDateTime.of(date2, time2, zone2);
        System.out.println(dateTime2);    // 2025–03–09T03:30–04:00[US/Eastern]
    }
}
