package gui;

import java.awt.EventQueue;
import java.util.Random;

import javax.swing.*;

import cPlayer.ComputerPlayer;
import control.PlayControl;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class TicTacToe implements GuiCallback{

	private JFrame frame;
	private TTField fields;
	private PlayControl control;
	private ComputerPlayer player;
    private JTextArea textArea;
    private Timer timer;
    private Random rand;
	private static JButton btnPlay;
    
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
		checkGameOver();
	}

	private void initialize() {
		control = new PlayControl();
		frame = new JFrame();
		frame.setBounds(100, 100, 477, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[10%][1%][grow]", "[7%][1%][grow]"));
		
		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, "cell 0 0 3 1,grow");
		controlPanel.setLayout(new BorderLayout(0, 0));
		
		btnPlay = new JButton("Neues Spiel");
		controlPanel.add(btnPlay, BorderLayout.WEST);
		btnPlay.addActionListener(e -> {
            btnPlay.setEnabled(false);
            control.reset();
        });
		
		JButton btnExit = new JButton("Exit");
		controlPanel.add(btnExit, BorderLayout.EAST);
		btnExit.addActionListener(e -> System.exit(0));
		
		JLabel label1 = new JLabel("");
		controlPanel.add(label1, BorderLayout.CENTER);
		
		JPanel infoPanel = new JPanel();
		frame.getContentPane().add(infoPanel, "cell 0 1 1 2,grow");
		infoPanel.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea_1 = new JTextArea();
		infoPanel.add(textArea_1, BorderLayout.CENTER);
		
		JPanel tttPanel = new JPanel();
		tttPanel.setMaximumSize(new Dimension(390, 390));
		tttPanel.setMinimumSize(new Dimension(390, 390));
		frame.getContentPane().add(tttPanel, "cell 1 1 2 2,grow");
		tttPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btnFeld1 = new JButton("");
		btnFeld1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tttPanel.add(btnFeld1);
		
		JButton btnFeld2 = new JButton("");
		tttPanel.add(btnFeld2);
		
		JButton btnFeld3 = new JButton("");
		tttPanel.add(btnFeld3);
		
		JButton btnFeld4 = new JButton("");
		tttPanel.add(btnFeld4);
		
		JButton btnFeld5 = new JButton("");
		tttPanel.add(btnFeld5);
		
		JButton btnFeld6 = new JButton("");
		tttPanel.add(btnFeld6);
		
		JButton btnFeld7 = new JButton("");
		tttPanel.add(btnFeld7);
		
		JButton btnFeld8 = new JButton("");
		tttPanel.add(btnFeld8);
		
		JButton btnFeld9 = new JButton("");
		tttPanel.add(btnFeld9);
	}
	
    private void createTTFields() {

    }

    private void disablePlayer() {

    }


    private void enablePlayer() {

    }


    private void startTimerForComputer() {

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
		String message = "Du: " + winsForYou + "\nComputer: " + winsForComputer + "\nUnentschieden: " + winsForNone;
				JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    	return true;
	}


    private void computerShallPlay() {

    }

    public void playerHasChosen(int nr) {

    }

	public static void enablePlayButton() {
		btnPlay.setEnabled(true);
	}
}
