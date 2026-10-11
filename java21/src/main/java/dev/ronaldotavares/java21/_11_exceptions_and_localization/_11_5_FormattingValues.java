package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class _11_5_FormattingValues {
    public static void main(String[] args) {
        System.out.println("Formatting Values");

        var formattingValues = new _11_5_FormattingValues();

        formattingValues.formattingNumbers();
        formattingValues.formattingDatesAndTimes();
        formattingValues.customizingTheDateTimeFormat();
        formattingValues.selectingAFormatMethod();
        formattingValues.addingCustomTextValues();
    }

    void formattingNumbers(){
        System.out.println("formattingNumbers");

        double d = 1234.567;
        NumberFormat f1 = new DecimalFormat("###,###,###.0");
        System.out.println(f1.format(d));  // 1,234.6

        NumberFormat f2 = new DecimalFormat("000,000,000.00000");
        System.out.println(f2.format(d));  // 000,001,234.56700

        NumberFormat f3 = new DecimalFormat("Your Balance $#,###,###.##");
        System.out.println(f3.format(d));  // Your Balance $1,234.57
    }

    void formattingDatesAndTimes(){
        System.out.println("formattingDatesAndTimes");

        LocalDate date = LocalDate.of(2025, Month.OCTOBER, 20);
        System.out.println(date.getDayOfWeek()); // MONDAY
        System.out.println(date.getMonth()); // OCTOBER
        System.out.println(date.getYear()); // 2025
        System.out.println(date.getDayOfYear()); // 293

        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dt = LocalDateTime.of(date, time);
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        try {
            date.format(DateTimeFormatter.ISO_LOCAL_TIME); // RuntimeException
        } catch (RuntimeException e){
            System.out.println(e);
        }

        try {
            time.format(DateTimeFormatter.ISO_LOCAL_DATE); // RuntimeException
        } catch (RuntimeException e){
            System.out.println(e);
        }

        var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(dt.format(f)); // October 20, 2025 at 11:12
    }

    void customizingTheDateTimeFormat(){
        System.out.println("customizingTheDateTimeFormat");

        var dt = LocalDateTime.of(2025, Month.OCTOBER, 20, 6, 15, 30);

        var formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        System.out.println(dt.format(formatter1));  // 10/20/2025 06:15:30

        var formatter2 = DateTimeFormatter.ofPattern("MM_yyyy_-_dd");
        System.out.println(dt.format(formatter2));  // 10_2025_-_20

        try {
            var formatter3 = DateTimeFormatter.ofPattern("hh:mm:z");
            System.out.println(dt.format(formatter3));  // DateTimeException
        } catch (DateTimeException e){
            System.out.println(e);
        }
    }

    void selectingAFormatMethod() {
        System.out.println("selectingAFormatMethod");

        var dateTime = LocalDateTime.of(2025, Month.OCTOBER, 20, 6, 15, 30);
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");

        System.out.println(dateTime.format(formatter));  // 10/20/2025 06:15:30
        System.out.println(formatter.format(dateTime));  // 10/20/2025 06:15:30
    }

    void addingCustomTextValues() {
        System.out.println("addingCustomTextValues");

        var dt = LocalDateTime.of(2025, Month.OCTOBER, 20, 6, 15, 30);
        var f1 = DateTimeFormatter.ofPattern("MMMM dd, yyyy ");
        var f2 = DateTimeFormatter.ofPattern(" hh:mm");
        System.out.println(dt.format(f1) + "at" + dt.format(f2));

        var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(dt.format(f)); // October 20, 2025 at 06:15

        var g1 = DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm");
        System.out.println(dt.format(g1)); // October 20, Party's at 06:15
        var g2 = DateTimeFormatter.ofPattern("'System format, hh:mm' hh:mm");
        System.out.println(dt.format(g2)); // System format, hh:mm: 06:15'hh:mm");
        var g3 = DateTimeFormatter.ofPattern("'NEW! 'yyyy', yay!'");
        System.out.println(dt.format(g3)); // NEW! 2025, yay!

        try{
            DateTimeFormatter.ofPattern("The time is hh:mm"); 
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }
        try{
            DateTimeFormatter.ofPattern("'Time is: hh:mm:"); 
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }
}
