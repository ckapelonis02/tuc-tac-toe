package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;

import model.GameModel;
import model.Hal;
import model.Move;
import model.MrBean;
import view.MainAreaPanel;
import view.MainWindow;

public class GameController extends WindowAdapter {
	GameModel model;
	MainWindow view;
	public Timer t;
	Hal hal = new Hal("enas");
	MrBean mrbean = new MrBean("allos");
	public boolean b = false;
	
	public void start() {
		this.t = new Timer(500,
				(e)->{if (model.mover) {
					if (model.gamePlayers[0].amogus == -1) {
						botMove(mrbean.move(model.board));
					}
					else if (model.gamePlayers[0].amogus == 1){
						botMove(hal.bestMove(model.board));
					}
				}
				else {
					if (model.gamePlayers[1].amogus == -1) {
						botMove(mrbean.move(model.board));
					}
					else if (model.gamePlayers[1].amogus == 1){
						botMove(hal.bestMove(model.board));
					}
				}
			}
		);
		this.model = new GameModel(this);
		this.model.deserialize();
		this.view= new MainWindow(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);
		this.view.getMainPanel().hallOfFame.setText(this.model.getBestPlayers());
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	public void newGame() {
		this.model.startGame();
		this.view.getTopPanel().getStartBtn().setEnabled(false);
		this.view.getMainPanel().showCard(MainAreaPanel.BOARD);
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getTopPanel().getAddPlayerBtn().setEnabled(model.NoPlay());
		this.t.setRepeats(false);
		
		this.game(model.gamemode);
	}
	
	//case -1 bot vs bot, case 1 bot vs human
	public void game(int gameMode) {
		switch(gameMode) {
			case 0: t.setRepeats(true); t.start(); break;
			case 1: b = true; t.start(); break;
			default: break;
		}
	}
	
	public void botMove(Move move) {
		model.makeMove(move.row, move.col);
		System.out.println("Bot played on cell (" + move.row + ", " + move.col + ")");
		model.board.checkResult();
		if (model.board.result != 2) {
			endGame();
			t.stop();
		}
	}
	
	public void makeMouseMove(int row, int column, boolean bot) {
		model.makeMove(row, column);
		model.board.checkResult();
		if (model.board.result != 2) {
			endGame();
			return;
		}
		if (bot) {
			t.start();
		}
	}

	public void endGame() {
		this.model.saveResult();
		this.model.getRoster().roster.sort(null);
		this.view.getMainPanel().hallOfFame.setResult(model.board.result);
		this.view.getMainPanel().showCard(MainAreaPanel.HOF);
		this.view.getTopPanel().getDoneBtn().setEnabled(true);
		this.view.getTopPanel().getAddPlayerBtn().setEnabled(true);
	}
	
	public void selectPlayer(String name, int pos) {
		this.model.selectPlayer(name, pos);
		this.view.getTopPanel().getStartBtn().setEnabled(model.ready());
		System.out.println("Player " + name + " set to " + pos);
	}
	
	public void addPlayer(String name) {
		if (name.trim() == null || name.equals("")) {
			System.out.println("Player not added.");
			return;
		}
		this.model.addPlayer(name.trim());
		this.view.getMainPanel().hallOfFame.setText(this.model.getBestPlayers());
		System.out.println("Player " + name.trim() + " successfully added");
	}
	
	public void clear() {
		this.model.clear();
		this.view.getMainPanel().hallOfFame.setText(this.model.getBestPlayers());
		this.view.getMainPanel().showCard(MainAreaPanel.HOF);
		this.view.getLeftPanel().clear();
		this.view.getRightPanel().clear();
		this.view.getTopPanel().getDoneBtn().setEnabled(false);
		this.view.getMainPanel().setEnabled(true);
	}

	public void quit() {
		this.model.serialize();
		System.out.println("telos kalo ola kala");		
		System.exit(0);
	}

	public GameModel getGameModel() {
		return model;
	}

	public MainWindow getView() {
		return view;
	}
	
}