package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;

public class PlayerPanel extends GamePanel {
	
	JButton selectPlayerBtn;
	int pos;
	String currentPlayer;
	JTextField plName;
	JLabel plMark;
	JTextArea plStats;
	
	
	public PlayerPanel(GameController c, int pos) {
		super(c);
		this.pos=pos;		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn = new JButton("Choose Player");
		selectPlayerBtn.setPreferredSize(new Dimension(150,40));
		selectPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn.addActionListener((e)->{changePlayer();});
		this.add(selectPlayerBtn);
		
		plName = new JTextField();
		plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,40));
		plName.setMaximumSize(plName.getPreferredSize() );
		plName.setAlignmentX(CENTER_ALIGNMENT);
		plName.setHorizontalAlignment(JTextField.CENTER);
		plName.setEnabled(false);
		
		plMark = new JLabel(pos==0? "X" : "O");
		plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
		plMark.setAlignmentX(CENTER_ALIGNMENT);
		plMark.setHorizontalAlignment(JTextField.CENTER);
		plMark.setEnabled(false);
		Font markf = new Font("SansSerif", Font.BOLD,90);
		plMark.setFont(markf);
		
		plStats = new JTextArea(10,100);		
		plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
		plStats.setAlignmentX(CENTER_ALIGNMENT);
		Font statsf = new Font("SansSerif", Font.BOLD,20);
		plStats.setFont(statsf);
		plStats.setEnabled(false);		
		plStats.setMargin(new Insets(10, 10, 10, 10));
		this.add(plMark);
		this.add(plName);		
		this.add(plStats);
	}

	public void changePlayer() {
		//Get the list of all players
		String[] allPlayers = new String[getModel().getRoster().roster.size()];
		for (int i = 0; i < getModel().getRoster().roster.size(); i++) {
			allPlayers[i] = getModel().getRoster().roster.get(i).name;
		}
		Arrays.sort(allPlayers);

		//Show Player Selection Dialog
		String selPlayer = (String) JOptionPane.showInputDialog(this, 
				"Choose a Player...",
				"Player selection",
				JOptionPane.PLAIN_MESSAGE,
				null,
				allPlayers,
				currentPlayer
				);
		
		//Set the selected player
		if (selPlayer != null) {
			if (selPlayer.equals(getModel().getGamePlayers()[pos==0?1:0])) {
				JOptionPane.showMessageDialog(gc.getView(),					
						"Player already selected",
						"Ooops...",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.currentPlayer = selPlayer;
			gc.selectPlayer(selPlayer, pos);
			this.plName.setText(currentPlayer);
			this.setPlayerStats(getModel().getPlayerStats(currentPlayer));
			this.repaint();
		}
	}
	
	public void clear() {
		plStats.setText(null);
		plName.setText(null);
		this.repaint();
		selectPlayerBtn.setEnabled(true);
	}
	
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public JTextField getPlName() {
		return plName;
	}
	
	public JTextArea getPlStats() {
		return plStats;
	}
	
	public void setPlayerStats(String stats) {
		this.plStats.setText(stats);
	}

	public JButton getSelectPlayerBtn() {
		return selectPlayerBtn;
	}
}
