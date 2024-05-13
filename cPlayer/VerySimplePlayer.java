package cPlayer;

import control.PlayControl;

import java.util.Random;

public class VerySimplePlayer extends ComputerPlayer {
    private Random rand;

    public VerySimplePlayer(PlayControl pc) {
        super(pc);
        rand = new Random();
    }

    public int draw() {
        int whaelen;
        do {
        	whaelen = rand.nextInt(9);
        } while (!control.fieldFree(whaelen));
        return whaelen;
    }
}
