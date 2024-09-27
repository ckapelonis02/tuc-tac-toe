package model;

import java.io.Serializable;

public class MyGameRecord extends GameRecord implements Serializable{
	int isXorO;
	
	public MyGameRecord(Player playerX, Player playerO, int result, int isXorO) {
		super(playerX, playerO, result);
		this.isXorO = isXorO;
	}

	//compare this obj with a game where the player was x
	public int compareToX(MyGameRecord gameToCompare) {
		//returns 1 if gameToCompare has better result
		int x = Integer.compare(gameToCompare.result, isXorO*this.result);
		
		if (x != 0) {
			return x;
		}
		else {
			int y = 0;
			if (isXorO == 1) {
				y = Float.compare(gameToCompare.scoreO, this.scoreO);
			}
			else if (isXorO == -1) {
				y = Float.compare(gameToCompare.scoreO, this.scoreX);
			}
			if (y != 0) {
				return y;
			}
			else {
				return gameToCompare.dateAndTime.compareTo(this.dateAndTime);
			}
		}
	}
	
	//compare this obj with a game where the player was o
	public int compareToO(MyGameRecord gameToCompare) {
		int x = Integer.compare(-gameToCompare.result, isXorO*this.result);
		
		if (x != 0) {
			return x;
		}
		else {
			int y = 0;
			if (isXorO == 1) {
				y = Float.compare(gameToCompare.scoreX, this.scoreO);
			}
			else if (isXorO == -1) {
				y = Float.compare(gameToCompare.scoreX, this.scoreX);
			}
			if (y != 0) {
				return y;
			}
			else {
				return gameToCompare.dateAndTime.compareTo(this.dateAndTime);
			}
		}
	}
}
