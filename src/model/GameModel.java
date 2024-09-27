package model;

import control.GameController;

public class GameModel {
	PlayerRoster roster;
	public Player[] gamePlayers;
	public Board board;
	GameController gc;
	public Boolean mover;
	public int gamemode;
	
	public GameModel(GameController gc) {
		this.gc = gc;
		this.roster = new PlayerRoster();
		gamePlayers = new Player[2];
	}
	
	public void startGame() {
		board = new Board();
		mover = true;
		if (gamePlayers[0].cpu && gamePlayers[1].cpu) {
			gamemode = 0;
		}
		else if (!gamePlayers[0].cpu && gamePlayers[1].cpu) {
			gamemode = -1;
		}
		else if (gamePlayers[0].cpu && !gamePlayers[1].cpu) {
			gamemode = 1;
		}
		else {
			gamemode = 5;
		}
	}
	
	public void makeMove(int row, int col) {
		board.moves++;
		board.gameBoard[row][col] = getMoverMark();
		mover = !mover;
	}
	
	public void saveResult() {
		MyGameRecord gameX = new MyGameRecord(gamePlayers[0], gamePlayers[1], board.result, 1);
		MyGameRecord gameO = new MyGameRecord(gamePlayers[0], gamePlayers[1], board.result, -1);
		
		gamePlayers[0].record.addGameX(gameX);
		gamePlayers[1].record.addGameO(gameO);
	}
	
	//helpful methods
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] != null);
	}
	
	public boolean inPlay() {
		return board.gameBoard != null && board.moves < 9;
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	public String getMoverMark() {
		return mover? "X": "O";
	}
	
	public void checkDimValidity(int row, int col) {
		if (row < 0 || col < 0 || row > 2 || col > 2) {
			throw new IndexOutOfBoundsException(row + "," + col + " is not a valid board cell");
		}
	}
	
	public void clear() {
		gamePlayers[0] = null;
		gamePlayers[1] = null;
	}
	
	//get a player's stats
	public String getPlayerStats(String name) {
		StringBuilder sb = new StringBuilder("");
		Player p = roster.findPlayer(name);
		sb.append(name).append("\n\n\n");
		sb.append("Total:").append("\t").append(p.record.games).append("\n");
		sb.append("Wins:").append("\t").append(p.record.wins).append("\n");
		sb.append("Defeats:").append("\t").append(p.record.defeats).append("\n");
		sb.append("Draws:").append("\t").append(p.record.draws).append("\n\n");
		sb.append("Score:").append("\t").append(String.valueOf(p.record.score)).append("\n");
		sb.append("Best Games:").append("\t").append("\n\n");
		int counter = 0;
		char c = 0;
		for (MyGameRecord game: p.record.allGames) {
			if (game != null) {
				switch (game.isXorO*game.result)  {
					case 0: c = 'D'; break;
					case -1: c = 'L'; break;
					case 1: c = 'W'; break;
				}
				sb.append(c + "\n");
				counter++;
			}
			if (counter == 5) break;
		}
		return sb.toString();
	}
	
	//get best players
	public String getBestPlayers() {
		StringBuilder sb = new StringBuilder("");
		if (!roster.roster.isEmpty()) {
			for (int i = 0; i < roster.roster.size(); i++) {
				sb.append("   ").append(i + 1).append(") ").append(roster.roster.get(i).name).append("\t").append(roster.roster.get(i).record.score).append("\n\n");
			}
		}
		return sb.toString();
	}
	
	//select player
	public void selectPlayer(String player, int pos) {
		if (pos < 0 && pos > 1)return;
		gamePlayers[pos] = roster.findPlayer(player);
	}
	
	//add player
	public void addPlayer(String name) {
		Player p = new Human(name);
		roster.addPlayer(p);
	}
	
	//serialization methods
	public void serialize() {
		SerDeser.serialize(roster);
	}
	
	public void deserialize() {
		roster = SerDeser.deserialize();
	}
	
	//getters and setters
	public String getBoardMark(int row, int col) {
		return board.gameBoard[row][col];
	}
	
	public int getMover() {
		return mover.compareTo(false);
	}
	
	public Player[] getGamePlayers() {
		return gamePlayers;
	}
	
	public PlayerRoster getRoster() {
		return roster;
	}
	
}
