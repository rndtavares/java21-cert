package dev.ronaldotavares.java21._1_building_blocks;

public class _1_10_DestroyingObjects {

    private void suggestJVMToCallGarbageCollector(){
        System.gc();
    }

    private void gcElegible(){
        String one, two;
        one = new String("a");
        two = new String("b");
        one = two;
        String three = one;
        one = null;
    }
}
