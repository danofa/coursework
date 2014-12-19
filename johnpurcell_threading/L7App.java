package org.damcode.udemy.threading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L7App {

    private static BlockingQueue<Integer> bQueue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread() {
            public void run() {
                try {
                    producer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L7App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
  

        Thread t2 = new Thread() {
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L7App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
    }

    private static void producer() throws InterruptedException {
        Random rand = new Random();

        while (true) {
            int value = rand.nextInt(100);
            System.out.println("dropping value into queue: " + value);
            bQueue.put(value);
        }
    }

    private static void consumer() throws InterruptedException {
        Random rand = new Random();

        while (true) {

            Thread.sleep(100);
            if (rand.nextInt(10) == 0) {
                Integer value;
                value = bQueue.take();
                System.out.println("taken value: " + value + " , size of array: " + bQueue.size());
            }

        }
    }

}
