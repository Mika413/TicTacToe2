package control;

public class PlayControl {
    private int[][] pField = new int[3][3];
    private final int player = 1;
    private final int computer = 2;
    private final int none = 0;

    

    public void reset() {

    }

    private void setPoint(int pos, int type) {
      
    }

    public void playerSet(int nr) {
  
    }

    public void computerSet(int nr) {
       
    }

    public boolean fieldFree(int nr) {
    	return true;
    }

    private int winnerIs() {

        return none;
    }

    public boolean gameOver() {
   
        return false;
    }

    public boolean playerHasWon() {

        return false;
    }

    public boolean computerHasWon() {

        return false;
    }

    public int[][] getBoard() {
        
    	return new int[0][0];
    }
}
