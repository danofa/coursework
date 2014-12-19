package org.damcode.udemy.threading;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */






public class ThreadIntlv {
    
    private int count = 0;
    
    public synchronized void increment(){
        count++;
    }
    
    public static void main(String[] args) {
        ThreadIntlv app = new ThreadIntlv();
        app.doWork();
    }

    private void doWork() {
        
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 0; i < 10000; i++){
                    increment();
                }
            }
        });

           Thread t2 = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 0; i < 10000; i++){
                    increment();
                }
            }
        });

           t1.start();
           t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadIntlv.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           System.out.println("count is: " + count);
    }
}
