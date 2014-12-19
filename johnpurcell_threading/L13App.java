package org.damcode.udemy.threading;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L13App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        Future<Integer> future = exec.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                
                int duration = new Random().nextInt(4000);
        
                if(duration > 2000){
                    throw new IOException("Thread is sleeping too long eh bro");
                }
                
                System.out.println("Starting thread");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {
                    Logger.getLogger(L13App.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("finished");
                return duration;
            }
        });

        
        exec.shutdown();
        
        try {
            System.out.println("return result: " + future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(L13App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            System.out.println("Exception exec : " + ex.getCause().getMessage());
        }
    }
}
