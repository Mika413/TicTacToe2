package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.*;

import cPlayer.VerySimplePlayer;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.PlayControl;
import net.miginfocom.swing.MigLayout;
import cPlayer.ComputerPlayer;
import cPlayer.VerySimplePlayer;
import java.util.Timer;
import java.util.TimerTask;

public class TicTacToe implements GuiCallback{

	private JFrame frame;
    private PlayControl control;
    private ComputerPlayer computerPlayer;
    private TTField[] buttons;
    private JPanel boardPanel;
    private JButton btnPlay;

    public enum ZeichenTyp {
        SPIELER,
        COMPUTER;
    }

    private int winsForYou;
    private int winsForComputer;
    private int winsForNone;

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
		initialize();
	}

	private void initialize() {
		control = new PlayControl();
        computerPlayer = new VerySimplePlayer(control);
		frame = new JFrame();
		frame.setBounds(100, 100, 443, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[10%][1%][grow]", "[7%][grow][1%][grow]"));
        
        JPanel controlPanel = new JPanel();
        frame.getContentPane().add(controlPanel, "cell 0 0 3 2,grow");
        controlPanel.setLayout(new BorderLayout(0, 0));
        
        btnPlay = new JButton("Neues Spiel");
        controlPanel.add(btnPlay, BorderLayout.WEST);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPlay.setEnabled(false);
                control.reset();
                resetButtons(); 
                btnPlay.setEnabled(true); 
            }
        });
        
        JButton btnExit = new JButton("Exit");
        controlPanel.add(btnExit, BorderLayout.EAST);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JLabel label1 = new JLabel("");
        controlPanel.add(label1, BorderLayout.CENTER);
        
        JPanel infoPanel = new JPanel();
		frame.getContentPane().add(infoPanel, "cell 0 2 1 2,grow");
		infoPanel.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea_1 = new JTextArea();
		infoPanel.add(textArea_1, BorderLayout.CENTER);

        boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setMaximumSize(new Dimension(390, 390));
        boardPanel.setMinimumSize(new Dimension(390, 390));
		frame.getContentPane().add(boardPanel, "cell 1 2 2 2,grow");
		boardPanel.setLayout(new GridLayout(3, 3, 0, 0));

        createTTFields();
        
	}
	
    private void createTTFields() {
		buttons = new TTField[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new TTField(i, this); 
            final int index = i;
            buttons[i].setPreferredSize(new Dimension(100, 100)); 
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	playerHasChosen(index);
                }
            });
            boardPanel.add(buttons[i]); 
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
                ((TTField) buttons[i]).setZeichenTyp(TTField.ZeichenTyp.SPIELER);
            } else if (board[row][col] == 2) {
                ((TTField) buttons[i]).setZeichenTyp(TTField.ZeichenTyp.COMPUTER);
            }
        }
    }
    

    private void disablePlayer() {
    	for (int i = 0; i < 9; i++) {
    		buttons[i].setEnabled(false);
    	}
    }


    private void enablePlayer() {
    	for (int i = 0; i < 9; i++) {
    		buttons[i].setEnabled(true);
    	}
    }


    private void startTimerForComputer() {
    	Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               
            	computerShallPlay();
            	enablePlayer();
                timer.cancel(); 
            }
        }, 3000);
    }

 
    private boolean checkGameOver() {
        String title;
        if (control.playerHasWon()) {
            winsForYou++;
            title = "Du hast gewonnen!";
        } else if (control.computerHasWon()) {
            winsForComputer++;
            title = "Computer hat gewonnen!";
        } else if (control.gameOver()) {
            winsForNone++;
            title = "Unentschieden!";
        } else return false;
        enablePlayButton();
        JOptionPane.showMessageDialog(frame, "", title, JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public void enablePlayButton() {
        btnPlay.setEnabled(true);
    }


    private void computerShallPlay() {
    	if (!checkGameOver()) {
            int computerMove = computerPlayer.draw(); 
            control.computerSet(computerMove); 
            buttons[computerMove].setEnabled(false); 
            
            SwingUtilities.invokeLater(() -> {
                updateButtons();
            });
        }
    }


    public void playerHasChosen(int index) {
        if (!checkGameOver()) {
            if (control.fieldFree(index)) {
                control.playerSet(index); 
                buttons[index].setEnabled(false); 
                
                SwingUtilities.invokeLater(() -> {
                    updateButtons();
                });
                disablePlayer();
                startTimerForComputer();
                
            }
        }
    }
	public void Punktestandanzeigen() {
        String stats = "Du: " + winsForYou + "\nComputer: " + winsForComputer + "\nUnentschieden: " + winsForNone;
        textArea_1.setText("Spieler:" +winsForYou);
    }
    
}
	
