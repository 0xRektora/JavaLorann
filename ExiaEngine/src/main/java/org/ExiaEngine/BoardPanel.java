package org.ExiaEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import MotionElement.Enemies;
import MotionElement.Obstacle;
import MotionElement.Pawn;
import MotionElement.Status;

public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The tile map 20x12 */
	public static Object[][] map;

	/** The map dimensions */
	private Dimension tiles = new Dimension(BoardFrame.CASE20X / 32, BoardFrame.CASE12Y / 32);

	BoardPanel() throws InterruptedException {
		super();
		this.setBackground(Color.BLACK);
		BoardPanel.map = new Object[(int) this.tiles.getWidth()][(int) this.tiles.getHeight()];
		

	}
	
	/**
	 * Getter for the tiles dimensions
	 * @return Dimension
	 */
	public Dimension getTile() {
		return this.tiles;
	}

	/** Return the map arrays */
	public static Object[][] getMap() {
		return BoardPanel.map;
	}

	public static Object getObject(int x, int y) {
		return BoardPanel.getMap()[x / 32][y / 32];
	}

	public static void addObject(Object obj, int x, int y) {
		BoardPanel.map[x / 32][y / 32] = obj;

	}
	
	public static void removeObject(int x, int y) {
		BoardPanel.map[x / 32][y / 32] = null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		try {
			// Painting the map components
			for (int x = 0; x < (int) this.tiles.getWidth(); x++) {
				for (int y = 0; y < (int) this.tiles.getHeight(); y++) {
					Obstacle temp = (Obstacle) BoardPanel.getMap()[x][y];
					if (temp != null)
						g.drawImage(temp.getSprite(), temp.getX(), temp.getY(), this);
				}
			}
			
			//Painting the pawns
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (i.isAlive())
					g.drawImage(i.getSprite(), i.getX(), i.getY(), this);

			}
		} catch (Exception e) {

		}

	}

}
