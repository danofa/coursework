package org.damcode.udemy.threading;

import javax.swing.SwingUtilities;

/**
 *
 * @author dm
 */
public class L15App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){ 

            @Override
            public void run() {
                new L15MainFrame("MAINy!");
            }
        });
    }
}
