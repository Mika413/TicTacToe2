package cPlayer;

import java.util.Random;

public class VerySimplePlayer extends ComputerPlayer{
    private Random rand;


    public VerySimplePlayer(PlayControl pc) {
        this.pc = pc;
    }

    public int draw() {
        return 0;
    }
}

