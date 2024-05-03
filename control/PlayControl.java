package control;

public class PlayControl {
    private int[][] pField = new int[3][3];
    private final int player = 1;
    private final int computer = 2;
    private final int none = 0;

    

    public void reset() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
			    pField[i][j] = none;
			}
		}
    }

    private void setPoint(int pos, int type) {
		int row = pos / 3;
        int col = pos % 3;
        pField[row][col] = type;
    }

    public void playerSet(int nr) {
		setPoint(nr, player);
    }

    public void computerSet(int nr) {
		setPoint(nr, computer);
    }

    public boolean fieldFree(int nr) {
    	int row = nr / 3;
        int col = nr % 3;
        if (pField[row][col] == none) {
			return true;
        }
		else {
			return false; 
		}
    }

    private int winnerIs() {
		for (int i = 0; i < 3; i++) {
            if (pField[i][0] == pField[i][1] && pField[i][1] == pField[i][2] && pField[i][0] != none) {
                return pField[i][0];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (pField[0][i] == pField[1][i] && pField[1][i] == pField[2][i] && pField[0][i] != none) {
                return pField[0][i];
            }
        }

        if (pField[0][0] == pField[1][1] && pField[1][1] == pField[2][2] && pField[0][0] != none) {
            return pField[0][0];
        }
        if (pField[0][2] == pField[1][1] && pField[1][1] == pField[2][0] && pField[0][2] != none) {
            return pField[0][2];
        }
        return none;
    }

    public boolean gameOver() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if( pField[i][j] == none) {
                    return false;
                }
			}
		}
        return true;
    }

    public boolean playerHasWon() {
		if (winnerIs() == player){
			return true;
		}
		else{
			return false;
		}
    }

    public boolean computerHasWon() {
		if (winnerIs() == computer){
			return true;
		}
		else{
			return false;
		}
    }

    public int[][] getBoard() {
    	return pField;
    }
}
