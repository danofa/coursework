package org.damcode.udemy.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */


class MyProcessor implements Runnable {
    private int id;
    
    public MyProcessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("starting : " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("stopping : " + id);
    }
}

public class ThreadPoolApp {
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        for(int i = 0; i < 5; i++){
            executor.submit(new MyProcessor(i));
        }
        
        executor.shutdown();
        
        System.out.println("All tasks submitted");
        
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPoolApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
