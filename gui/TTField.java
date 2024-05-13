package gui;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class TTField extends JButton {

    private boolean myTurn;
    private boolean computerTurn;
    public enum ZeichenTyp {
        SPIELER,
        COMPUTER
    }
    private ZeichenTyp zeichenTyp;

    
    public TTField(int nr, GuiCallback callback) {
        myTurn = false;
        computerTurn = false;
        zeichenTyp = null;
        addActionListener(e -> {
            if (myTurn) {
                setPlayerHasChosen();
            }
        });
    }

    public void setType(int type) {

    }

    public void setItsPlayersTurn(boolean turn) {
        myTurn = turn;
    }

    public void setComputerHasChosen() {
        computerTurn = true;
        repaint();
    }

    public void resetField() {
        myTurn = false;
        computerTurn = false;
        repaint();
    }
    public void setZeichenTyp(ZeichenTyp typ) {
        this.zeichenTyp = typ;
        repaint(); 
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        setBackground(Color.darkGray);
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.lightGray);

        if (zeichenTyp == ZeichenTyp.SPIELER) {
            drawPlayerSymbol(g2d);
        } else if (zeichenTyp == ZeichenTyp.COMPUTER) {
            drawComputerSymbol(g2d);
        }

        g2d.dispose();
    }

    private void drawPlayerSymbol(Graphics2D g2d) {
        g2d.drawOval(30, 30, getWidth() - 60, getHeight() - 60);
    }

    private void drawComputerSymbol(Graphics2D g2d) {
        g2d.drawLine(30, 30, getWidth() - 30, getHeight() - 30);
        g2d.drawLine(30, getHeight() - 30, getWidth() - 30, 30);
    }
    public void clearSymbol() {
        zeichenTyp = null; 
        repaint(); 
    }

    private void setPlayerHasChosen() {
        myTurn = false;
        repaint();
    }
}