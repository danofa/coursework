package org.damcode.udemy.threading;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L9App {

    public static void main(String[] args) throws InterruptedException {
        final L9Processor processor = new L9Processor();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L9App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L9App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}

class L9Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;

    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {

        Random rand = new Random();

        while (true) {
            synchronized (lock) {

                while (list.size() == 0) {
                    lock.wait();
                }

                System.out.print("list size : " + list.size());
                int value = list.removeFirst();
                System.out.println(" , value is : " + value);
                lock.notify();
            }
            Thread.sleep(rand.nextInt(1000));
        }

    }

}
