package org.damcode.udemy.threading;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L10App {

    public static void main(String[] args) throws InterruptedException {
        final L10Runner runner = new L10Runner();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L10App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L10App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }
}

class L10Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    
    private void incremenet() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        
        System.out.println("waiting");
        cond.await();
        
        System.out.println("woken up");
        
        try {
            incremenet();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        
        Thread.sleep(2000);
        lock.lock();
        
        System.out.println("Press return");
        new Scanner(System.in).nextLine();
        System.out.println("got return");
        
        cond.signal();

        try {
            incremenet();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("countis : " + count);
    }

}
