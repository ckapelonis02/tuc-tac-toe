package model;

import java.util.concurrent.ThreadLocalRandom;

public class MrBean extends Player {

	public MrBean(String name) {
		super(name);
		super.cpu = true;
		super.amogus = -1;
	}
	
	public Move move(Board board) {
		Move move = new Move();
		move.row = 0;
		move.col = 0;
		do {
			move.row = ThreadLocalRandom.current().nextInt(0, 3);
			move.col = ThreadLocalRandom.current().nextInt(0, 3);
		} while (board.gameBoard[move.row][move.col] != null);
		return move;
	}

}
