package org.damcode.udemy.threading;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author dm
 */
public class L15MainFrame extends JFrame {

    private JLabel count = new JLabel("0");
    private JLabel statusLabel = new JLabel("task not finished");
    private JButton btnStart = new JButton("Start");

    public L15MainFrame(String title) {
        super(title);

        JPanel mainPanel = new JPanel();
        mainPanel.add(count);
        mainPanel.add(statusLabel);
        mainPanel.add(btnStart);
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }

        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void start() {
        System.out.println("start pushed!");
        SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() throws Exception {

                for (int i = 0; i < 300; i++) {
                    Thread.sleep(100);
                    System.out.println("Working " + i);
                    
                    publish(i);
                }

                return true;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int val = chunks.get(chunks.size() -1);
                count.setText("Current val " + val);
            }

            
            
            @Override
            protected void done() {
                try {
                    System.out.println("Worker thread done!");

                    Boolean status = get();
                    statusLabel.setText("completed! status " + status);

                } catch (InterruptedException ex) {
                    Logger.getLogger(L15MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(L15MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        worker.execute();
    }

}
