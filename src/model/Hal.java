package model;

public class Hal extends Player {
	public Hal(String name) {
		super(name);
		super.cpu = true;
		super.amogus = 1;
	}
	
	public Move bestMove(Board board) {
		int bestScore = -50;
		Move move = new Move();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.gameBoard[i][j] == null) {
					Board referenceBoard = board.makari();
					referenceBoard.makeMove(i, j, "X");
					int score = minimax(referenceBoard, 0, false);
					if (score > bestScore) {
						bestScore = score;
						move.row = i;
						move.col = j;
					}
				}
			}
		}
		return move;
		
	}
	
	public int minimax(Board board, int depth, boolean isMaximizing) {
		board.checkResult();
		if (board.result != 2) {
			return board.result; 
		}
		
		if (isMaximizing) {
			int bestScore = -30;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board.gameBoard[i][j] == null) {
						Board referenceBoard = board.makari();
						referenceBoard.makeMove(i, j, "X");
						int score = minimax(referenceBoard, depth + 1, false);
						bestScore = Integer.max(score, bestScore);
					}
				}
			}
			return bestScore;
		}
		else {
			int bestScore = 30;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board.gameBoard[i][j] == null) {
						Board referenceBoard = board.makari();
						referenceBoard.makeMove(i, j, "O");
						int score = minimax(referenceBoard, depth + 1, true);
						bestScore = Integer.min(score, bestScore);
					}
				}
			}
			return bestScore;
		}
	}
	
	
}