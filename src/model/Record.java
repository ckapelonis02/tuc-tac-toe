package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Serializable {
	int games, wins, defeats, draws; //wins + defeats + draws = games
	float score;
	Player player;
	ArrayList<MyGameRecord> allGames = new ArrayList<MyGameRecord>();
	ArrayList<MyGameRecord> lastGames = new ArrayList<MyGameRecord>();
	
	public Record(Player player) {
		this.games = 0;
		this.wins = 0;
		this.defeats = 0;
		this.draws = 0;
		this.score = 0;
		this.player = player;
		for (int i = 0; i < 5; i++) {
			allGames.add(null);
		}
	}
	
	public void addGameX(MyGameRecord newGame) {
		games++;
		switch(newGame.result) {
			case -1: defeats++; break;
			case 0: draws++; break;
			case 1: wins++; break;
			default: break;
		}
		
		updateScore();
		
		lastGames.add(newGame);
		//iterate bestGames and call method newGame.compareTo(bestGames[i]), STARTING FROM FIRST COLLECTION OBJECT
		for (int i = 0; i < 10; i++) {
			if (allGames.get(i) == null || allGames.get(i).compareToX(newGame) > 0) {
				allGames.add(i, newGame);
				break;
			}
		}
	}
	
	public void addGameO(MyGameRecord newGame) {
		games++;
		switch(-newGame.result) {
			case -1: defeats++; break;
			case 0: draws++; break;
			case 1: wins++; break;
			default: break;
		}
		
		updateScore();
		
		lastGames.add(newGame);
		//iterate bestGames and call method newGame.compareTo(bestGames[i]), STARTING FROM FIRST COLLECTION OBJECT
		for (int i = 0; i < 10; i++) {
			if (allGames.get(i) == null || allGames.get(i).compareToO(newGame) > 0) {
				allGames.add(i, newGame);
				break;
			}
		}
	}
	
	public void sysout() {
		System.out.println("games: " + games);
		System.out.println("wins: " + wins);
		System.out.println("defeats: " + defeats);
		System.out.println("draws: " + draws);
		System.out.println("score: " + score);
		for (int i = 0; i < 10; i++) {
			System.out.println("best games " + i + ":" + allGames.get(i).result + " " + allGames.get(i).scoreO + " " + allGames.get(i).dateAndTime.toString());
		}
		for (int i = 0; i < lastGames.size(); i++) {
			System.out.println("last games " + i + ":" + lastGames.get(i).result);
		}
		System.out.println("telos");
	}
	
	public void updateScore() {
		this.score = 50*(2*wins + draws)/games;
	}
	
	
}
