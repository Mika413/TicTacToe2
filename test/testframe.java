package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import cPlayer.VerySimplePlayer;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.PlayControl;
import cPlayer.ComputerPlayer;
import cPlayer.VerySimplePlayer;

public class TicTacToe implements GuiCallback {
    private JFrame frame;
    private PlayControl control;
    private JTextArea textArea;

    private ComputerPlayer computerPlayer;

    private int winsForYou;
    private int winsForComputer;
    private TTField[] buttons;
    public enum ZeichenTyp {
        SPIELER,
        COMPUTER
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicTacToe window = new TicTacToe();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    
    public TicTacToe() {
        control = new PlayControl();
        computerPlayer = new VerySimplePlayer(control);
        frame = new JFrame();
        frame.setBounds(100, 100, 477, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        JPanel controlPanel = new JPanel();
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        JButton btnPlay = new JButton("Neues Spiel");
        controlPanel.add(btnPlay);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPlay.setEnabled(false);
                control.reset();
                resetButtons(); // Diese Zeile war im ursprünglichen Code nicht vorhanden
                btnPlay.setEnabled(true); // Aktivieren Sie den Button für ein neues Spiel
            }
        });

        JButton btnExit = new JButton("Exit");
        controlPanel.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        textArea = new JTextArea();
        mainPanel.add(textArea, BorderLayout.CENTER);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        mainPanel.add(boardPanel, BorderLayout.SOUTH);

        buttons = new TTField[9]; // Array von TTField-Buttons erstellen
        for (int i = 0; i < 9; i++) {
            buttons[i] = new TTField(i, this); // TTField-Objekte erstellen und initialisieren
            final int index = i;
            buttons[i].setPreferredSize(new Dimension(100, 100)); // Hier setzen wir die bevorzugte Größe jedes Buttons auf 100x100 Pixel
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(index);
                }
            });
            boardPanel.add(buttons[i]); // TTField-Buttons zum Panel hinzufügen
        }
    }

    private void buttonClicked(int index) {
        if (!control.gameOver() && !control.playerHasWon() && !control.computerHasWon()) {
            if (control.fieldFree(index)) {
                control.playerSet(index); // Setze das Feld des Spielers
                buttons[index].setEnabled(false); // Deaktiviere den Button
                
                // Aktualisiere die Buttons im EDT
                SwingUtilities.invokeLater(() -> {
                    updateButtons();
                });
                
                // Lass den Computer einen Zug machen, wenn das Spiel nicht vorbei ist
                if (!control.gameOver() && !control.playerHasWon() && !control.computerHasWon()) {
                    int computerMove = computerPlayer.draw(); // Fordern Sie den Computerplayer auf, einen Zug zu zeichnen
                    control.computerSet(computerMove); // Setze das Feld des Computers
                    buttons[computerMove].setEnabled(false); // Deaktiviere den Button
                    
                    // Aktualisiere die Buttons im EDT
                    SwingUtilities.invokeLater(() -> {
                        updateButtons();
                    });
                }
            }
        }
    }


    private void resetButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
            ((TTField) button).clearSymbol();
        }
    }

    private void updateButtons() { 
        int[][] board = control.getBoard();
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            if (board[row][col] == 1) {
                ((TTField) buttons[i]).setZeichenTyp(TTField.ZeichenTyp.SPIELER); // Setze den Zeichentyp für X
            } else if (board[row][col] == 2) {
                ((TTField) buttons[i]).setZeichenTyp(TTField.ZeichenTyp.COMPUTER); // Setze den Zeichentyp für O
            }
        }
    }






    // Methode, die aufgerufen wird, wenn der Spieler ein Feld ausgewählt hat
    public void playerHasChosen(int nr) {
        control.playerSet(nr);
        updateFields();
        if (!control.gameOver() && !control.playerHasWon() && !control.computerHasWon()) {
            // Wenn das Spiel nicht vorbei ist, lässt der Computer einen Zug machen
            int computerMove = player.draw();
            control.computerSet(computerMove);
            updateFields();
        }
        // Überprüfen, ob das Spiel vorbei ist
        if (control.playerHasWon()) {
            textArea.setText("Du hast gewonnen!");
            winsForYou++;
        } else if (control.computerHasWon()) {
            textArea.setText("Der Computer hat gewonnen!");
            winsForComputer++;
        } else if (control.gameOver()) {
            textArea.setText("Unentschieden!");
        }
        // Aktualisiere den Text mit den Spielstand-Informationen
        updateGameStatus();
    }

    // Methode zum Aktualisieren der Spielfelder basierend auf dem aktuellen Zustand des Spiels
    private void updateFields() {
        int[][] board = control.getBoard();
        for (int i = 0; i < 9; i++) {
            fields[i].setType(board[i / 3][i % 3]);
        }
    }

    // Methode zum Aktualisieren des Texts mit den Spielstand-Informationen
    private void updateGameStatus() {
        textArea.append("\nSpielstand: Du " + winsForYou + " - Computer " + winsForComputer);
    }
}

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
