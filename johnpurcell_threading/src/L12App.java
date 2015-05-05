package org.damcode.udemy.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * semaphores
 *
 * @author dm
 */
public class L12App {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            Logger.getLogger(L12App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

class Connection {

    private int connections = 0;
    private static Connection instance = new Connection();
    private Semaphore sem = new Semaphore(10, true);

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            doConnect();
        } finally {
            sem.release();
        }

    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("current connections " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        synchronized (this) {
            connections--;
        }

    }

}
