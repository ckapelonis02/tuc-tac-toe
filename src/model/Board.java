package model;

public class Board {
	public boolean isFinal;
	public int result; //-1 O wins, 0 draw, 1 X wins
	public int moves;
	public String[][] gameBoard;
	
	
	//constructor
	public Board() {
		this.gameBoard = new String[3][3];
		this.isFinal = false;
		this.result = 2;
	}
	
	
	public void checkEnded() {
		if (moves == 9) {
			result = 0;
			isFinal = true;
		}
	}
	
	public void checkResult() {
		checkEnded();
		checkWinner();
	}
	
	public Board makari() {
		Board b = new Board();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				b.gameBoard[i][j] = this.gameBoard[i][j];
			}
		}
		return b;
	}
	
	public void makeMove(int row, int col, String marker) {
		moves++;
		gameBoard[row][col] = marker;
	}
	
	public void checkWinner() {
		for (int i = 0; i < 3; i++) {
			if (gameBoard[i][0] == "X" && gameBoard[i][1] == "X" && gameBoard[i][2] == "X") {
				result = 1;
			}
			if (gameBoard[0][i] == "X" && gameBoard[1][i] == "X" && gameBoard[2][i] == "X") {
				result = 1;
			}
		}
		if (gameBoard[0][0] == "X" && gameBoard[1][1] == "X" && gameBoard[2][2] == "X")
			result = 1;
		if (gameBoard[0][2] == "X" && gameBoard[1][1] == "X" && gameBoard[2][0] == "X")
			result = 1;
		
		for (int i = 0; i < 3; i++) {
			if (gameBoard[i][0] == "O" && gameBoard[i][1] == "O" && gameBoard[i][2] == "O") {
				result = -1;
			}
			if (gameBoard[0][i] == "O" && gameBoard[1][i] == "O" && gameBoard[2][i] == "O") {
				result = -1;
			}
		}
		if (gameBoard[0][0] == "O" && gameBoard[1][1] == "O" && gameBoard[2][2] == "O")
			result = -1;
		if (gameBoard[0][2] == "O" && gameBoard[1][1] == "O" && gameBoard[2][0] == "O")
			result = -1;
	}

}
