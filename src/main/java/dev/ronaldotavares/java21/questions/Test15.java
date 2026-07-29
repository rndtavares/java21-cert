package dev.ronaldotavares.java21.questions;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test15 {
    public static void main(String[] args) {
        var test15 = new Test15();
        test15._4();
        test15._12();
        test15._18();
        test15._20();
        test15._22();
        test15._26();
        test15._28();
        test15._37();
        test15._45();
    }

    private void _4() {
        System.out.println("question 4");

//        What will be the result of compilation and execution of the following code ?
        IntStream is1 = IntStream.range(0, 5); //1
        OptionalDouble x = is1.average(); //2
        System.out.println(x); //3
    }

    private void _12() {
        System.out.println("question 12");
        Helper.main(null);
    }

    private void _18() {
        System.out.println("question 18");
        ScopeTest.main(null);
    }

    private void _20() {
        System.out.println("question 20");
        // What will the following code print?
        var a = new int[]{ 1, 2, 3, 4, 5};
        var b = new int[]{ 1, 2, 3, 4, 5, 3};
        var c = new int[]{ 1, 2, 3, 4, 5, 6};
        int x = Arrays.compare(a, c);
        int y = Arrays.compare(b, c);
        System.out.println(x+" "+y);

        var d = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int z = Arrays.compare(a, d);
        int z1 = Arrays.compare(d, a);
        var e = new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int w = Arrays.compare(d, e);
        System.out.println(z+" "+z1+" "+w);

        var f = new int[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int v = Arrays.compare(a, f);
        System.out.println(v);
    }

    private void _22() {
        System.out.println("question 22");

        List<String> l1 = Arrays.asList("a", "b");
        List<String> l2 = Arrays.asList("1", "2");
        //Which of the following lines of code will print the following output?
        // a
        // b
        // 1
        // 2

        System.out.println("A");
        Stream.of(l1, l2).forEach((x)->System.out.println(x));
        System.out.println("B");
        Stream.of(l1, l2).flatMap((x)->Stream.of(x)).forEach((x)->System.out.println(x));
        System.out.println("C");
        Stream.of(l1, l2).flatMap((x)->x.stream()).forEach((x)->System.out.println(x));

//        Stream.of(l1, l2).flatMap((x)->x.iterator()).forEach((x)->System.out.println(x));
    }

    private void _26() {
        System.out.println("question 26");
//        public class TestClass{    public static void main(String args[]){
        CC cc = new CC();
//        System.out.println(cc.i); //2
//        System.out.println(cc.j); //3       System.out.println(c.k);
        // } }
    }

    private void _28() {
        System.out.println("question 28");
        Locale.setDefault(Locale.FRANCE);
//        ResourceBundle msg = ResourceBundle.getBundle("messages");
//        String message = MessageFormat.format(msg.getString("message"), "Amy", "Adams");
//        System.out.println(message);


//        ResourceBundle msg1 = ResourceBundle.getBundle("messages", new Locale("fr", "FR"));
        Object[] names = {"Amy", "Adams"};
//        String message1 = MessageFormat.format(msg1.getString("message"), names);
//        System.out.println(message1);

        System.out.println(Locale.FRANCE);
        System.out.println(Locale.FRENCH);
    }

    private void _37() {
        System.out.println("question 37");
//        Given:
        Map hm = new ConcurrentHashMap();
        try {
            hm.put(null, "asdf");  //1
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            hm.put("aaa", null);  //2
        }catch (Exception e) {
            System.out.println(e);
        }

        hm = new HashMap();
        hm.put(null, "asdf");  //3
        hm.put("aaa", null);  //4

        List list = new ArrayList();
        list.add(null); //5
        list.add(null); //6

        list = new CopyOnWriteArrayList();
        list.add(null); //7
        // Which of the above lines will throw NullPointerException?
        // (Assume that each of the put and add calls are executed as if they are wrapped inside a try/catch block i.e. an exception thrown at //1 will not prevent the execution of //2.)
    }

    private void _45() {
        System.out.println("question 45");
        OuterWorld.main(null);
    }
}


//Identify the correct statements about the following code:
//        import java.util.*;
class Personal {
    private String name;

    public Personal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

class Helper {
    public void helpPeople(Queue people, Queue helped) {
        do {
            Personal p = (Personal) people.poll();
            System.out.println("Helped : " + p + " ");
            helped.offer(p.getName());
        } while (!people.isEmpty());
    }

    public static void main(String[] args) {
        Queue<Personal> q = new LinkedList<Personal>();
        q.offer(new Personal("Pope"));
        q.offer(new Personal("John"));
        Queue<Personal> helpedQ = new LinkedList<Personal>();
        Helper h = new Helper();
        h.helpPeople(q, helpedQ);
    }
}

//What will be the output when the following class is compiled and run?
class ScopeTest {
    static int x = 5;

    public static void main(String[] args) {
        int x = (x = 3) * 4;  // 1
        System.out.println(x);
    }
}


//Which statement regarding the following code is correct?
class AA {
    public int i = 10;
    private int j = 20;
}

class BB extends AA {
    private int i = 30; //1
    public int k = 40;
}

class CC extends BB {
}


//Consider the following code:
class OuterWorld {
    public InnerPeace i = new InnerPeace("none"); //1

    class InnerPeace {
        private String reason = "none";

        InnerPeace(String reason) {
            this.reason = reason;
        }
    }

    public static void main(String[] args) {
//        var ip = new InnerPeace("yoga"); //2
        var out = new OuterWorld();
        System.out.println(out.i.reason); //3
    }
}
//Identify correct statements about the above code.