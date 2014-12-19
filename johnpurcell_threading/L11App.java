package org.damcode.udemy.threading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L11App {

    public static void main(String[] args) throws InterruptedException {
        final L11Runner runner = new L11Runner();

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

class L11Runner {

    L11Account acc1 = new L11Account();
    L11Account acc2 = new L11Account();
    
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            lock1.lock();
            lock2.lock();
            try {
            L11Account.transfer(acc1, acc2, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }

    }

    public void secondThread() throws InterruptedException {
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            lock1.lock();
            lock2.lock();
            try {
            L11Account.transfer(acc2, acc1, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }

    }

    public void finished() {
        System.out.println("Account 1 bal : " + acc1.getBalance());
        System.out.println("Account 2 bal : " + acc2.getBalance());
        System.out.println("Total balance : " + (acc1.getBalance() + acc2.getBalance()));
    }

}

class L11Account {

    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(L11Account acc1, L11Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }

}
