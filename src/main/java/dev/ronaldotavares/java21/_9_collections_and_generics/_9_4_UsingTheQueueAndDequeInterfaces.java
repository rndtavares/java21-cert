package dev.ronaldotavares.java21._9_collections_and_generics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class _9_4_UsingTheQueueAndDequeInterfaces {
    public static void main(String[] args) {
        queue();
        deque();
    }

    private static void queue(){
        System.out.println("queue");

        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(4);
        System.out.println(queue.remove());   // 10
        System.out.println(queue.peek());     // 4
        System.out.println(queue.remove());   // 4
        System.out.println(queue.poll());     // null
        System.out.println(queue.peek());   // null
//        System.out.println(queue.element());   // Exception
//        System.out.println(queue.remove());   // Exception
    }

    private static void deque(){
        System.out.println("deque LinkedList");
        Deque<Integer> deque = new LinkedList<>();
        System.out.println(deque.offerFirst(10));    // true
        System.out.println(deque.offerLast(4));      // true
        System.out.println(deque.peekFirst());          // 10
        System.out.println(deque.pollFirst());          // 10
        System.out.println(deque.pollLast());           // 4
        System.out.println(deque.pollFirst());          // null
        System.out.println(deque.peekFirst());          // null
//        System.out.println(deque.getFirst());           // Exception
//        System.out.println(deque.getLast());            // Exception
//        System.out.println(deque.removeFirst());        // Exception
//        System.out.println(deque.removeLast());         // Exception

        //stack (LIFO)
        System.out.println("deque stack LIFO ArrayDeque");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(4);
        System.out.println(stack.peek());  // 4
        System.out.println(stack.pop());   // 4
        System.out.println(stack.pop());   // 10
        System.out.println(stack.peek());  // null
//        System.out.println(stack.pop());  // Exception
    }


}
