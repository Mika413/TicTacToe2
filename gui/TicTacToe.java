package gui;

import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import cPlayer.ComputerPlayer;
import control.PlayControl;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

public class TicTacToe {

	private JFrame frame;
	private TTField fields;
	private PlayControl control;
	private ComputerPlayer player;
    private JTextArea textArea;
    private Timer timer;
    private Random rand;
    
    private int winsForYou;
    private int winsForComputer;


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
		frame = new JFrame();
		frame.setBounds(100, 100, 477, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[10%][1%][grow]", "[7%][1%][grow]"));
		
		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, "cell 0 0 3 1,grow");
		controlPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnPlay = new JButton("Neues Spiel");
		controlPanel.add(btnPlay, BorderLayout.WEST);
		btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestartActionPerformed(evt);
            }
        });
		
		JButton btnExit = new JButton("Exit");
		controlPanel.add(btnExit, BorderLayout.EAST);
		
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

        return false; 
    }


    private void computerShallPlay() {

    }

    public void playerHasChosen(int nr) {

    }
	private void RestartActionPerformed(java.awt.event.ActionEvent evt){
		for(i=0; i<=8; i++){
			i=0;
		}
	}
