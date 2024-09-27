package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerRoster implements Serializable {
	public ArrayList<Player> roster; //sorted by player score
	
	//constructor
	public PlayerRoster() {
		 this.roster = new ArrayList<>();
	}
	
	//adds a player in the collections
	public void addPlayer(Player p) {
		roster.add(p);
	}
	
	//returns sublist with best n players
	public ArrayList<Player> findHallOfFame(int n) {
		return (ArrayList<Player>)roster.subList(roster.size() - n, roster.size());
	}
	
	//method that finds a player by their name
	public Player findPlayer(String name) {
		for (Player p: roster) {
			if (p.name.equals(name)) {
				return p;
			}
		}
		return null;
	}
}
