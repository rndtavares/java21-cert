package dev.ronaldotavares.java21._4_core_apis;

public class _4_1_CreatingAndManipulatingStrings {

    public static void main(String[] args) {
        stringSamples();
        concatenation();
        stringMethods();
        methodChaining();
    }

    private static void stringSamples() {
        String name = "Fluffy";
        String newName = new String("Fluffy");
        String textBlockName = """
                                Fluffy""";
    }
    
    private static void concatenation() {
        System.out.println(1 + 2); // 3 
        System.out.println("a" + "b"); // ab 
        System.out.println("a" + "b" + 3); // ab3 
        System.out.println(1 + 2 + "c"); // 3c 
        System.out.println("c" + 1 + 2); // c12 
        System.out.println("c" + null); // cnull

        int three = 3;
        String four = "4";
        System.out.println(1 + 2 + three + four);

        var s = "1";                // s currently holds "1"
        s += "2";                   // s currently holds "12"
        s += 3;                     // s currently holds "123"
        System.out.println(s);      // 123
    }

    private static void stringMethods() {
        length();
        chatAt();
        codePoint();
        substring();
        indexOf();
        adjustingCase();
        equality();
        searchingSubstrings();
        replace();
        removingWhitespace();
        indentation();
        blankAndEmpty();
        formatValues();
    }

    private static void length(){
        var name = "animals";
        System.out.println(name.length()); // 7
    }
    
    private static void chatAt() {
        var name1 = "animals";
        System.out.println(name1.charAt(0)); // a
        System.out.println(name1.charAt(6)); // s
//        System.out.println(name1.charAt(7)); // exception
    }

    private static void codePoint() {
        var s = "Weâ€™re done feeding the animals";
        System.out.println(s.charAt(0) + " " + s.codePointAt(0));  // W 87 
        System.out.println(s.charAt(2) + " " + s.codePointAt(2));  // â 226
        System.out.println(s.codePointBefore(3));                  // 226
        System.out.println(s.codePointCount(0, 4));                       // 4
    }
    
    private static void substring() {
        var name = "animals";
        System.out.println(name.substring(3));         // mals
        System.out.println(name.substring(name.indexOf('m')));   // mals 
        System.out.println(name.substring(3, 4));                // m 
        System.out.println(name.substring(3, 7));                // mals

        System.out.println(name.substring(3, 3)); // empty string
//        System.out.println(name.substring(3, 2)); // exception
//        System.out.println(name.substring(3, 8)); // exception
    }

    private static void indexOf(){
        var name = "animals";
        System.out.println(name.indexOf('a'));                                    // 0
        System.out.println(name.indexOf("al"));                                   // 4
        System.out.println(name.indexOf('a', 4));                   // 4
        System.out.println(name.indexOf("al", 5));                  // -1
        System.out.println(name.indexOf('a', 2, 4));      // -1
        System.out.println(name.indexOf("al", 2, 6));     // 4
    }
    
    private static void adjustingCase(){
        var name = "animals";
        System.out.println(name.toUpperCase());    // ANIMALS 
        System.out.println("Abc123".toLowerCase());  // abc123
    }

    private static void equality(){
        System.out.println("abc".equals("ABC"));            // false 
        System.out.println("ABC".equals("ABC"));            // true 
        System.out.println("ABC".equals(6));                // false 
        System.out.println("abc".equalsIgnoreCase("ABC"));  // true
    }

    private static void searchingSubstrings() {
        System.out.println("abc".startsWith("a")); // true 
        System.out.println("abc".startsWith("A")); // false   

        System.out.println("abc".startsWith("b", 1)); // true 
        System.out.println("abc".startsWith("b", 2)); // false

        System.out.println("abc".endsWith("c"));   // true 
        System.out.println("abc".endsWith("a"));   // false   

        System.out.println("abc".contains("b"));   // true 
        System.out.println("abc".contains("B"));   // false
    }

    private static void replace() {
        System.out.println("abcabc".replace('a', 'A')); // AbcAbc
        System.out.println("abcabc".replace("a", "A")); // AbcAbc
    }

    private static void removingWhitespace() {
        System.out.println("abc".strip());                 // abc 
        System.out.println("\t   a b c\n".strip());        // a b c   

        String text = " abc\t ";
        System.out.println(text.trim().length());          // 3 
        System.out.println(text.strip().length());         // 3

        System.out.println(text.stripLeading().length());  // 5 
        System.out.println(text.stripTrailing().length()); // 4
    }
    
    private static void indentation() {
        var block = """ 
                    a 
                     b 
                    c""";
        var concat = " a\n"
                   + "  b\n"
                   + " c";
        System.out.println(block.length());                 // 6
        System.out.println(concat.length());                // 9
        System.out.println(block.indent(1).length());    // 10
        System.out.println(concat.indent(-1).length());  // 7
        System.out.println(concat.indent(-4).length());  // 6
        System.out.println(concat.stripIndent().length());  // 6
    }

    private static void blankAndEmpty(){
        System.out.println(" ".isEmpty()); // false
        System.out.println("".isEmpty()); // true
        System.out.println(" ".isBlank()); // true
        System.out.println("".isBlank()); // true
    }
    
    private static void formatValues(){
        var name = "Kate";
        var orderId = 5;  

        // All print: Hello Kate, order 5 is ready
        System.out.println("Hello "+name+", order "+orderId+" is ready");
        System.out.println(String.format("Hello %s, order %d is ready", name, orderId));
        System.out.println("Hello %s, order %d is ready".formatted(name, orderId));

        var name1 = "James";
        var score = 90.25;
        var total = 100;
        System.out.println("%s:%n   Score: %f out of %d".formatted(name1, score, total));

        var pi = 3.14159265359;     
        System.out.format("[%f]",pi);      // [3.141593]     
        System.out.format("[%12.8f]",pi);  // [  3.14159265]     
        System.out.format("[%012f]",pi);   // [00003.141593]     
        System.out.format("[%12.2f]",pi);  // [        3.14]     
        System.out.format("[%.3f]",pi);    // [3.142]
    }
    
    private static void methodChaining() {
        var start = "AniMaL   ";
        var trimmed = start.trim();                 // "AniMaL" 
        var lowercase = trimmed.toLowerCase();      // "animal" 
        var result = lowercase.replace('a', 'A');   // "AnimAl" 
        System.out.println("\n" + result);

        String result1 = "AniMaL   ".trim().toLowerCase().replace('a', 'A');
        System.out.println(result1);

        String a = "abc";
        String b = a.toUpperCase();
        b = b.replace("B", "2").replace('C', '3');
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
}
