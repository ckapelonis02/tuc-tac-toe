package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GameRecord implements Serializable {
	Player playerX, playerO;
	float scoreX, scoreO;
	LocalDateTime dateAndTime;
	int result; //-1 o lost, 0 draw, +1 x won
	
	//constructor
	public GameRecord(Player playerX, Player playerO, int result) {
		this.playerX = playerX;
		this.playerO = playerO;
		this.scoreX = playerX.record.score;
		this.scoreO = playerO.record.score;
		this.dateAndTime = LocalDateTime.now();
		this.result = result;
	}

	
}
