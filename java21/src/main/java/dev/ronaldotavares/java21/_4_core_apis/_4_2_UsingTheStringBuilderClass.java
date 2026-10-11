package dev.ronaldotavares.java21._4_core_apis;

public class _4_2_UsingTheStringBuilderClass {
    public static void main(String[] args) {
        stringObjects();
        stringBuilder();
        mutabilityAndChaining();
        creatingStringBuilder();
        stringBuilderMethods();
    }

    private static void stringObjects() {
        String alpha = "";
        for(char current = 'a'; current <= 'z'; current++)
             alpha += current;
        System.out.println(alpha);
    }
    
    private static void stringBuilder(){
        StringBuilder alpha = new StringBuilder();
        for(char current = 'a'; current <= 'z'; current++)
             alpha.append(current);
        System.out.println(alpha);
    }

    private static void mutabilityAndChaining() {
        StringBuilder sb = new StringBuilder("start");
        sb.append("+middle");                        // sb = "start+middle"
        StringBuilder same = sb.append("+end");      // "start+middle+end"
        System.out.println(same);

        StringBuilder a = new StringBuilder("abc");
        StringBuilder b = a.append("de");
        b = b.append("f").append("g");
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

    private static void creatingStringBuilder() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder("animal");
        StringBuilder sb3 = new StringBuilder(10);

        var sb = new StringBuilder("animals");
        String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));
        int len = sb.length();
        char ch = sb.charAt(6);
        System.out.println(sub + " " + len + " " + ch);
    }

    private static void stringBuilderMethods(){
        append();
        codePoints();
        insert();
        delete();
        replace();
        reverse();
        stringBuilderToString();
    }

    private static void append() {
        var sb = new StringBuilder().append(1).append('c');
        sb.append("-").append(true);
        System.out.println(sb);         // 1c-true
    }

    private static void codePoints() {
        var sb = new StringBuilder()      
            .appendCodePoint(87).append(',')
            .append((char)87).append(',')
            .append(87).append(',')
            .appendCodePoint(8217);
        System.out.println(sb);   // W,W,87,â€™
    }
    
    private static void insert() {
        var sb = new StringBuilder("animals");
        sb.insert(7, "-");                            // sb = animals-
        sb.insert(0, "-");                            // sb = -animals-
        sb.insert(4, "-");                            // sb = -ani-mals-
        System.out.println(sb);
    }

    private static void delete() {
        var sb = new StringBuilder("abcdef");
        sb.delete(1, 3);                               // sb = adef
//        sb.deleteCharAt(5);                      // exception
        System.out.println(sb);

        var sb1 = new StringBuilder("abcdef");
        sb1.delete(1, 100);                           // sb = a
        System.out.println(sb1);
    }

    private static void replace(){
        var builder = new StringBuilder("pigeon dirty");
        builder.replace(3, 6, "sty");
        System.out.println(builder);   // pigsty dirty

        var builder1 = new StringBuilder("pigeon dirty");
        builder1.replace(3, 100, "");
        System.out.println(builder1);
    }

    private static void reverse() {
        var sb = new StringBuilder("ABC");
        sb.reverse();
        System.out.println(sb);
    }

    private static void stringBuilderToString(){
        var sb = new StringBuilder("ABC");
        String s = sb.toString();
        System.out.println(s);
    }
    
}
