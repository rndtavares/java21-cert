package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.text.NumberFormat.Style;
import java.util.Locale;
import java.util.stream.Stream;

public class _11_6_SupportingInternationalizationAndLocalization {
    public static void main(String[] args) {
        System.out.println("Supporting Internationalization And Localization");

        var supportingInternationalizationAndLocalization = new _11_6_SupportingInternationalizationAndLocalization();

        supportingInternationalizationAndLocalization.pickingALocale();
        supportingInternationalizationAndLocalization.formattingNumbers();
        supportingInternationalizationAndLocalization.parsingNumbers();
        supportingInternationalizationAndLocalization.formattingWithCompactNumberFormat();
        supportingInternationalizationAndLocalization.localizingDates();
    }

    void pickingALocale() {
        System.out.println("pickingALocale");
        
        Locale locale = Locale.getDefault();
        System.out.println(locale);

        System.out.println(Locale.GERMAN); // de
        System.out.println(Locale.GERMANY); // de_DE

        System.out.println(Locale.of("fr")); // fr
        System.out.println(Locale.of("hi","IN")); // hi_IN

        Locale l1 = new Locale.Builder()
            .setLanguage("en")
            .setRegion("US")
            .build();

        Locale l2 = new Locale.Builder()
            .setRegion("US")
            .setLanguage("en")
            .build();

        System.out.println(l1); // en_US
        System.out.println(l2); // en_US

        System.out.println(Locale.getDefault()); 
        Locale locale1 = Locale.of("fr");
        Locale.setDefault(locale1);
        System.out.println(Locale.getDefault()); // fr
    } 

    void formattingNumbers() {
        System.out.println("formattingNumbers");

        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;
        var us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(attendeesPerMonth)); // 266,666
        var gr = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(gr.format(attendeesPerMonth)); // 266.666
        var ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(attendeesPerMonth)); // 266 666

        double price = 48;
        var myLocale = NumberFormat.getCurrencyInstance();

        System.out.println(myLocale.format(price));

        double successRate = 0.802;
        var us1 = NumberFormat.getPercentInstance(Locale.US);
        System.out.println(us1.format(successRate)); // 80%
        var gr1 = NumberFormat.getPercentInstance(Locale.GERMANY);
        System.out.println(gr1.format(successRate)); // 80 %
    }

    void parsingNumbers(){
        System.out.println("parsingNumbers");

        try {
            String s = "40.45";
            var en = NumberFormat.getInstance(Locale.US);
            System.out.println(en.parse(s)); // 40.45
            var fr = NumberFormat.getInstance(Locale.FRANCE);
            System.out.println(fr.parse(s)); // 40

            String income = "$92,807.99";
            var cf = NumberFormat.getCurrencyInstance();
            double value = (Double) cf.parse(income);
            System.out.println(value); // 92807.99
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    void formattingWithCompactNumberFormat(){
        System.out.println("formattingWithCompactNumberFormat");

        var formatters = Stream.of(
                NumberFormat.getCompactNumberInstance(),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), Style.LONG),
                NumberFormat.getCompactNumberInstance(Locale.GERMAN, Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.GERMAN, Style.LONG),
                NumberFormat.getNumberInstance()
            );
        formatters.map(s -> s.format(7_123_456)).forEach(System.out::println);


        var formatters1 = Stream.of(
                NumberFormat.getCompactNumberInstance(),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.getDefault(), Style.LONG),
                NumberFormat.getCompactNumberInstance(Locale.GERMAN, Style.SHORT),
                NumberFormat.getCompactNumberInstance(Locale.GERMAN, Style.LONG),
                NumberFormat.getNumberInstance()
            );
        formatters1.map(s -> s.format(314_900_000)).forEach(System.out::println);

        var locale = Locale.of("en", "US");
        var compact = NumberFormat.getCompactNumberInstance(locale, Style.SHORT);

        System.out.println(compact.format(1_000_000)); // 1M
        try{
            System.out.println(compact.parse("1M")); // 1000000
            System.out.println(compact.parse("1000000")); // 1000000
            System.out.println(compact.parse("1,000,000")); // 1
            System.out.println(compact.parse("$1000000")); // ParseException
        } catch( ParseException e) {
           System.out.println(e); 
        }
    }

    void localizingDates() {
        System.out.println("localizingDates");

        Locale.setDefault(Locale.of("en","US"));
        var italy = Locale.of("it","IT");
        var dt = LocalDateTime.of(2025, Month.OCTOBER, 20, 15, 12, 34);
        // 10/20/25 --- 20/10/25
        print(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT), dt, italy);

        // 3:12 PM --- 15:12
        print(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT), dt, italy);
        // 10/20/25, 3:12 PM --- 20/10/25, 15:12
        print(DateTimeFormatter.ofLocalizedDateTime(
        FormatStyle.SHORT, FormatStyle.SHORT), dt, italy);
    }

    public static void print(DateTimeFormatter dtf,
        LocalDateTime dateTime, Locale locale) {
        System.out.println(dtf.format(dateTime) + "---" 
        + dtf.withLocale(locale).format(dateTime));
    }
}