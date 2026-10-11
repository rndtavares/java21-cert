package dev.ronaldotavares.java21.questions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.util.stream.IntStream;

public class Test9 {
    public static void main(String[] args) {
        var test9 = new Test9();
        test9._8();
        test9._19();
        test9._28();
    }

    void _8(){
        float f = Float.valueOf("0.0").floatValue();
        float f2 = Float.parseFloat("0.1");
        System.out.println(f);
        System.out.println(f2);
    }

    void _19(){
        var nums = IntStream.range(1, 10);
        System.out.println(nums.collect(() -> new StringBuilder(),
                (sb, i) -> sb.append(i).append(","),
                (sb1, sb2) -> sb1.append(sb2))
                .toString());

//        nums.mapToObj(i->i).collect(Collectors.averagingInt(i->i));

//        nums.parallel().mapToDouble(i->i).average().getAsDouble();

    }

    void _28(){

//        Given:
//        1. The time zone of America / New York is normally 5 hours behind UTC. 2. Day light saving is ON during June in
//        New York.(This means, clocks in New York are 1 hour ahead of the regular time that would have been shown when
//        day light saving is OFF.)What will the following code print when compiled and run?
        LocalDate ld = LocalDate.now().withMonth(6).withDayOfMonth(2);
        LocalTime lt = LocalTime.of(6, 0, 0);
        LocalDateTime ldt = ld.atTime(lt);
        ZoneOffset nyOffset = ZoneOffset.ofHoursMinutes(-5, 0);
        ZoneId nyZone = ZoneId.of("America/New_York");
        OffsetDateTime nyOdt = ldt.atOffset(nyOffset);
        ZonedDateTime nyZdt = ldt.atZone(nyZone);
        Duration d = Duration.between(nyOdt, nyZdt);
        System.out.println(d);
    }
}


//Consider thefollowing class:
class PortConnector {
    public PortConnector(int port) throws IOException {
//        ...lot of valid code.
    }
//    ...other valid code .
}
//You want to write another class CleanConnector that extends from PortConnector.
//Which of the following statements should hold true for CleanConnector class?
class CleanConnector extends PortConnector {
    public CleanConnector(int port) throws Exception, IOException, FileNotFoundException, InterruptedException {
        super(port);
    }
}

class AccessTest {
    static int number;
    int result = 10;

    public static void main(String[] args) {
        AccessTest at = new AccessTest();
        number = 11;
//        var number = at.addSalt(number);
        var number = at.addSalt(11);
        System.out.println(number);
    }

    int addSalt(int salt) {
        return number + result;
    }
}