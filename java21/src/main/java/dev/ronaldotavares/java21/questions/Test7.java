package dev.ronaldotavares.java21.questions;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test7 {
    public static void main(String[] args) {
        var test7 = new Test7();
        _32 q32 = test7.new _32();
        try {
            q32.main(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    class _32{
        public static void main(String[] args) throws Exception {
            try (var fis = new FileInputStream("c:\\temp\\test.txt");
                 var isr = new InputStreamReader(fis)) {
                while (isr.ready()) {
                    isr.skip(1);
                    int i = isr.read();
                    char c = (char) i;
                    System.out.print(c);
                }
            }
        }
    }
}


class TestClass1 {
    public class A {
    }

    public static class B {
    }

    public static void main(String args[]) {
        class C {
        }
        //1
        new TestClass1().new A();
//        new TestClass1.A();
        new C();
    }
}

class A1 {
}