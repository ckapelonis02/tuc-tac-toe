package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import control.GameController;

public class TopPanel extends GamePanel {	
	JButton quitBtn;
	JButton startGameBtn;
	JButton doneBtn;
	JButton addPlayerBtn;
	JButton bot;
	
	public TopPanel(GameController gc) {
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		quitBtn = new JButton("Quit App");	
		quitBtn.setPreferredSize(new Dimension(100, 40));
		quitBtn.addActionListener((e)->{this.gc.quit();});		
		
		startGameBtn = new JButton("Start Game");
		startGameBtn.setPreferredSize(new Dimension(100, 40));
		startGameBtn.setEnabled(false);
		startGameBtn.addActionListener((e)->{this.gc.newGame();});
		
		doneBtn = new JButton("Done");		
		doneBtn.setPreferredSize(new Dimension(100, 40));		
		doneBtn.setEnabled(false);
		doneBtn.addActionListener((e)->{this.gc.clear();
				System.out.println("Done pressed");});

		addPlayerBtn = new JButton("New Player");		
		addPlayerBtn.setPreferredSize(new Dimension(100, 40));		
		addPlayerBtn.setEnabled(true);
		addPlayerBtn.addActionListener((e)->{String name = (String) JOptionPane.showInputDialog(this,
				"Enter name",
				"Add new Player...",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
				);
			this.gc.addPlayer(name);});
		
		bot = new JButton("Bot");		
		bot.setPreferredSize(new Dimension(100, 40));		
		bot.setEnabled(true);
		bot.addActionListener((e)->{});
		
		add(startGameBtn);
		add(doneBtn);
		add(quitBtn);
		add(addPlayerBtn);
		add(bot);
	}

	public JButton getQuitBtn() {
		return quitBtn;
	}

	public JButton getStartBtn() {
		return startGameBtn;
	}
	
	public JButton getDoneBtn() {
		return doneBtn;
	}	
	
	public JButton getAddPlayerBtn() {
		return addPlayerBtn;
	}	
	
}
