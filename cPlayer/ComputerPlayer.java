package cPlayer;

import control.PlayControl;

public abstract class ComputerPlayer {
    protected PlayControl control;
    protected final int ME = 0;
    protected final int OPP = 2;
    protected final int EQ = 1;
    protected final int UNDEF = -1;
    protected final int ROWS = 3;
    protected final int COLS = 3;
    protected int[][] cells;

    public ComputerPlayer(PlayControl pc) {
        this.control = pc;
    }

    public void update(int[][] board) {

    }
    
    public abstract int draw();
}
