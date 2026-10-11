package dev.ronaldotavares.java21._1_building_blocks;

public class _1_9_ManagingVariableScope {

    //2 vars in the method scope
    private void eat(int piecesOfCheese) {
        int bitesOfCheese = 1;
    }

    public void eatIfHungry(boolean hungry) {
        if (hungry) {
            int bitesOfCheese = 1;
        }  // bitesOfCheese goes out of scope here
//       System.out.println(bitesOfCheese);  // DOES NOT COMPILE
    }

    public void eatIfHungry2(boolean hungry) {
        if (hungry) {
            int bitesOfCheese = 1;
            {
                var teenyBit = true;
                System.out.println(bitesOfCheese);
            }
        }
//        System.out.println(teenyBit);  // DOES NOT COMPILE
     }

    public void eatMore(boolean hungry, int amountOfFood) {
        int roomInBelly = 5;
        if (hungry) {
            var timeToEat = true;
            while (amountOfFood> 0) {
                int amountEaten = 2;
                roomInBelly = roomInBelly - amountEaten;
                amountOfFood = amountOfFood - amountEaten;
            }
        }
        System.out.println(amountOfFood);
    }
}

class Mouse {
    final static int MAX_LENGTH = 5;
    int length;
    public void grow(int inches) {
        int test;
        if (length < MAX_LENGTH) {
//            int newSize = test + length + inches; //TEST NOT INITIALIZED
            int newSize = length + inches;
            length = newSize;
        }
    }
}