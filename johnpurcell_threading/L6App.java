package org.damcode.udemy.threading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */


class L6Processor implements Runnable {

    private CountDownLatch latch;

    public L6Processor(CountDownLatch latch) {
        this.latch = latch;
    }
    
    @Override
    public void run() {
        System.out.println("started");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(L6Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("finished");
        latch.countDown();

    }
}


public class L6App {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executor.submit(new L6Processor(latch));
        }
        
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(L6App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("all threads have returned");
    }
}
