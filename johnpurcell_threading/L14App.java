package org.damcode.udemy.threading;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dm
 */
public class L14App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("starting thread");
        Thread t1 = new  Thread(){

            @Override
            public void run() {
                Random rand = new Random();
                
                for (int i = 0; i < 1E8; i++) {
                    
                    try {
                        //                    if(Thread.currentThread().isInterrupted()){
//                        System.out.println("interrupted!");
//                        break;
//                    }
                        
                        Thread.sleep(1);
                        
                        Math.sin(rand.nextDouble());
                    } catch (InterruptedException ex) {
                        System.out.println("interrupted!");
                        break;
                    }
                }
            }
            
        };
        t1.start();
        
        Thread.sleep(500);
        
        t1.interrupt();
        
        t1.join();
        System.out.println("thread finished"); 
       
    }
}
