package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.GameController;

public class HallOfFame extends GamePanel {
	private GameController gc;
	public String text;
	JTextArea bestPlayers;
	
	public HallOfFame(GameController gc) {
		super(gc);
		this.gc=gc;
		this.text = "\tHall Of Fame:\n\n";
		this.bestPlayers = new JTextArea();
		this.setBestPlayers();
		this.add(bestPlayers);
	}
	
	@Override
	public void paintComponent(Graphics g) {  
		super.paintComponent(g);
	    int x = this.getWidth()/2 - 50;
		int y = this.getHeight()/2;	
		g.drawString(text, x, y);
	}
	
	public void setBestPlayers() {
		bestPlayers.setPreferredSize(new Dimension(MainWindow.WIDTH - 2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT));
		Font statsf = new Font("SansSerif", Font.PLAIN, 25);
		bestPlayers.setFont(statsf);
		bestPlayers.setText(text);
		bestPlayers.setBackground(Color.ORANGE);
		bestPlayers.setForeground(Color.BLACK);
	}
	
	public void setText(String text) {
		this.text = text;
		bestPlayers.setText(this.text);
		bestPlayers.setBackground(Color.ORANGE);
	}
	
	public void setResult(int result) {
		switch (result) {
		case 0: this.text = "\tDRAW"; break;
		case 1: this.text = "\tX WINS!"; break;
		case -1: this.text = "\tO WINS"; break;
		}
		bestPlayers.setText(this.text);
		bestPlayers.setBackground(Color.PINK);
	}
	

}
