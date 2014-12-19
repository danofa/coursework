package org.damcode.udemy.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    public void stageOne() {
        synchronized (lock1) {
            list1.add(random.nextInt(9999));
        }
    }

    public void stageTwo() {
        synchronized(lock2) {
            list2.add(random.nextInt(9999));
        }
    }

    public void process() {

        for (int i = 0; i < 100000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Hello World");
        System.out.println("start");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                process();
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                process();
            }
        };
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

        long end = System.currentTimeMillis();

        System.out.println("time taken: " + (end - start));
        System.out.println("List1 : " + list1.size() + ", List2: " + list2.size());
    }
}
