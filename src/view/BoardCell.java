package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.sound.sampled.Line;
import javax.swing.border.LineBorder;

import control.GameController;

@SuppressWarnings("serial")
public class BoardCell extends GamePanel implements MouseListener {

	public static final int CELL_PADDING = 10;

	int row, col;	

	public boolean highlighted;

	public BoardCell(GameController gc, int row, int col) {
		super(gc);
		this.setBackground(Color.WHITE);
		this.row = row;
		this.col = col;
		this.addMouseListener(this);
		this.highlighted = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("Mouse entered cell " + this);
		this.highlight();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("Mouse exited on cell " + this);
		this.unHighlight();
	}

	private void highlight() {
		if (!highlighted && getModel().inPlay()) {
			highlighted = true;
			repaint();
		}
	}

	private void unHighlight() {
		if (highlighted && getModel().inPlay()) {
			highlighted = false;
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBorder(new LineBorder(Color.DARK_GRAY, 1));

		String mark = getModel().getBoardMark(this.row, this.col);
		Graphics2D g2d = (Graphics2D) g;
		int size = this.getSize().width - 2 * CELL_PADDING;
		g2d.setStroke(new BasicStroke(6));
		if (mark == null) {
			if (highlighted) {
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(CELL_PADDING, CELL_PADDING, size, size);
			}
			return;
		} else if (mark.equals("X")) {
			g2d.setColor(Color.RED);
			g2d.drawLine(CELL_PADDING, CELL_PADDING, CELL_PADDING + size, CELL_PADDING + size);
			g2d.drawLine(CELL_PADDING + size, CELL_PADDING, CELL_PADDING, CELL_PADDING + size);

		} else {
			g2d.setColor(Color.BLUE);
			g2d.drawOval(CELL_PADDING, CELL_PADDING, size, size);
		}

	}

	@Override
	public String toString() {
		return "(" + this.row + ", " + this.col + ")";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked on cell " + this);
		if (getModel().inPlay()) {
			gc.makeMouseMove(row, col, true);
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
}
