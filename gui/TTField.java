package gui;

import java.awt.Graphics;

public class TTField {
    private GuiCallback myParent;
    private int nr;
    private boolean myTurn;
    private int setType;

    public TTField(int nr, GuiCallback parent) {
        this.nr = nr;
        this.myParent = parent;
    }

    public void setType(int type) {

    }

    public void setItsPlayersTurn(boolean turn) {

    }

    public void setComputerHasChosen() {

    }

    public void resetField() {

    }

    
    public void paintComponent(Graphics g) {
        this.paintComponent(g);

    }
}
