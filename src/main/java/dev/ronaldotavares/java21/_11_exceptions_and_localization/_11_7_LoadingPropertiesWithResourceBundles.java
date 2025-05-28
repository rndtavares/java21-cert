package dev.ronaldotavares.java21._11_exceptions_and_localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class _11_7_LoadingPropertiesWithResourceBundles {
    public static void main(String[] args) {
        System.out.println("Loading Properties With Resource Bundles");

        var loadingPropertiesWithResourceBundles = new _11_7_LoadingPropertiesWithResourceBundles();

        loadingPropertiesWithResourceBundles.creatingAResourceBundle();
        loadingPropertiesWithResourceBundles.selectingResourceBundleValues();
        loadingPropertiesWithResourceBundles.formattingMessages();
        loadingPropertiesWithResourceBundles.usingThePropertiesClass();
    }

    void creatingAResourceBundle(){
        System.out.println("creatingAResourceBundle");

        var us = Locale.of("en", "US");
        var france = Locale.of("fr", "FR");
        printWelcomeMessage(us);     // Hello, The zoo is open
        printWelcomeMessage(france); // Bonjour, Le zoo est ouvert

        iterating(us);
        iterating(france);
    }

    public static void iterating(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
        rb.keySet().stream()
                .map(k -> k + ": " + rb.getString(k))
                .forEach(System.out::println);
    }

    public static void printWelcomeMessage(Locale locale) {
        var rb = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(rb.getString("hello")
                + ", " + rb.getString("open"));
    }

    void selectingResourceBundleValues(){
        System.out.println("selectingResourceBundleValues");

        Locale.setDefault(Locale.of("en", "US"));
        var locale = Locale.of("en", "CA");
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);

        System.out.print(rb.getString("hello"));
        System.out.print(". ");
        System.out.print(rb.getString("name"));
        System.out.print(" ");
        System.out.print(rb.getString("open"));
        System.out.print(" ");
        System.out.print(rb.getString("visitors"));
        System.out.println();

        try{
            System.out.print(rb.getString("close")); // MissingResourceException
        } catch (Exception e){
            System.out.println(e);
        }
    }

    void formattingMessages(){
        System.out.println("formattingMessages");

        ResourceBundle rb = ResourceBundle.getBundle("Format");

        String greeting = rb.getString("helloGreeting");
        System.out.print(MessageFormat.format(greeting, "Tammy", "Henry"));
        System.out.println();
    }

    void usingThePropertiesClass(){
        System.out.println("usingThePropertiesClass");

        ZooOptions.main(null);
    }

    class ZooOptions {
        public static void main(String[] args) {
            var props = new Properties();
            props.setProperty("name", "Our zoo");
            props.setProperty("open", "10am");

            System.out.println(props.getProperty("camel")); // null
            System.out.println(props.getProperty("camel", "Bob")); // Bob

            System.out.println(props.get("open")); // 10am
//            props.get("open", "The zoo will be open soon"); // DOES NOT COMPILE
        }
    }

}
