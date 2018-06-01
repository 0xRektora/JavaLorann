package org.ExiaEngine;

import java.awt.Dimension;

import javax.swing.JFrame;

import MotionElement.Player;

public class BoardFrame extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The frame dimensions */
	private Dimension frameDimensions = new Dimension(650, 384);

	/** The panel component. */
	private BoardPanel panel;
	
	private Thread paintWindow = new Thread() {
		@Override
		public void run() {
			
			while(true)
				panel.repaint();
		}
	};
	
	
	
	/**
	 * Constructor of the BoardFrame class.
	 * 
	 * @param title
	 *            the title of the window.
	 * @throws InterruptedException 
	 */
	public BoardFrame(String title) throws InterruptedException {
		super();
		this.setTitle(title);
		this.setSize(this.frameDimensions.width, this.frameDimensions.height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPan(new BoardPanel());
		this.setContentPane(this.panel);
		this.setResizable(false);
		this.setVisible(true);
		this.paintWindow.start();
		
		
		
	}

	/**
	 * Setter for the frame panel.
	 * 
	 * @param panel
	 *            the panel we'll bind to the frame.
	 */
	public void setPan(BoardPanel panel) {
		this.panel = panel;
	}

	public Dimension getDimensions() {
		return this.frameDimensions;
	}

	

}
