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
	
	/** The keyboard controller */
	private KeyboardControl kbControll;
	
	Player lorann = new Player();
	
	BoardPanel(){
		super();
		this.setFocusable(true);
	    this.requestFocusInWindow();
		this.setBackground(Color.BLACK);
		this.kbControll = new KeyboardControl(this.lorann);
		this.addKeyListener(kbControll);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
    	 	super.paintComponent(g);
    	    g.drawImage(this.lorann.getSprite(), this.lorann.getX(), this.lorann.getY(), this);
    	    if(!this.lorann.hasSpell()) {
    	    	g.drawImage(this.lorann.getSpell().getSprite(), this.lorann.getSpell().getX(), this.lorann.getSpell().getY(), this);
    	    }
    	  }
	
}
