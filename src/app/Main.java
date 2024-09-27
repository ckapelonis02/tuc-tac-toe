package app;

import control.GameController;
import model.SerDeser;

public class Main {

	public static void main(String[] args) {
		GameController gc = new GameController();
		SerDeser.createFile();
		gc.start();
	}
}
