package model;

import java.io.Serializable;

public abstract class Player implements Serializable, Comparable<Player> {
	public String name; //not greater than 20 characters
	public Record record;
	public boolean cpu;
	public int amogus;
	
	public Player(String name) {
		this.name = name;
		this.record = new Record(this);
	}
	
	public int compareTo(Player p) {
		return Float.compare(p.record.score ,this.record.score);
	}
}