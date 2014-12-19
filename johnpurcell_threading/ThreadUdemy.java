package org.damcode.udemy.threading;

import java.util.Scanner;

/**
 *
 * @author dm
 */
class Processor extends Thread {
    private volatile boolean running = true;

    public void run() {
        while(running){
            try {
                System.out.println("helloo");
                
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void shutdown(){
        running = false;
    }
}

public class ThreadUdemy {
    public static void main(String[] args) {
        Processor p1 = new Processor();
        p1.start();
        
        System.out.println("press return to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        
        p1.shutdown();
    }
}
