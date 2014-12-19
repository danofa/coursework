package org.damcode.udemy.threading;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L8App {

    public static void main(String[] args) throws InterruptedException {
        final L8Processor processor = new L8Processor();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L8App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException ex) {
                    Logger.getLogger(L8App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class L8Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running");
            wait();
            System.out.println("producer resumed running ( recved notify )");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("waiting for return key");
            scanner.nextLine();
            System.out.println("return key pressed");
            notify();
            Thread.sleep(5000);
        }
    }

}
