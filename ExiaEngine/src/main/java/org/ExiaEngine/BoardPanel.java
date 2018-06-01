package org.ExiaEngine;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import MotionElement.*;
import MotionlessElement.*;

public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	BoardPanel() throws InterruptedException {
		super();
		this.setBackground(Color.BLACK);
		
		Enemies enemy = new Enemies(64, 64);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Pawn.getPawns().size() > 0) {
			for (Pawn i : Pawn.getPawns()) {
				if(i.isAlive())
					g.drawImage(i.getSprite(), i.getX(), i.getY(), this);
			}
		}
	}

}
