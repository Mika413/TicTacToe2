/*package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class testframe extends JFrame {

    private CustomButton btnNewButton;
    private boolean playerClicked = false;

    public testframe() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Test Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        btnNewButton = new CustomButton();
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerHasChosen();
            }
        });
        panel.add(btnNewButton, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); 
    }

    
    private void playerHasChosen() {
        if (!playerClicked) {
            playerClicked = true;
            btnNewButton.setEnabled(false); 
            btnNewButton.setPlayerSymbol();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    clearPlayerSymbol();
                    setComputerHasChosen();
                    timer.cancel(); 
                }
            }, 3000);
        }
    }

    private void setComputerHasChosen() {
        btnNewButton.setComputerSymbol();
    }

    private void clearPlayerSymbol() {
        btnNewButton.clearPlayerSymbol();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	testframe frame = new testframe();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class CustomButton extends JButton {

    private boolean playerSymbol = false;
    private boolean computerSymbol = false;

    public CustomButton() {
        super();
        setBackground(Color.darkGray);
    }

    public void setPlayerSymbol() {
        playerSymbol = true;
        repaint(); 
    }

    public void setComputerSymbol() {
        computerSymbol = true;
        repaint(); 
    }

    public void clearPlayerSymbol() {
        playerSymbol = false;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.lightGray);

        if (playerSymbol) {
            drawPlayerSymbol(g2d);
        } else if (computerSymbol) {
            drawComputerSymbol(g2d);
        }

        g2d.dispose();
    }

    private void drawPlayerSymbol(Graphics2D g2d) {
        g2d.drawLine(30, 30, getWidth() - 30, getHeight() - 30);
        g2d.drawLine(30, getHeight() - 30, getWidth() - 30, 30);
    }

    private void drawComputerSymbol(Graphics2D g2d) {
        g2d.drawOval(30, 30, getWidth() - 60, getHeight() - 60);
    }
}
