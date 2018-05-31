package org.ExiaEngine;

import java.awt.Dimension;

import javax.swing.JFrame;

public class BoardFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The frame dimensions */
	public Dimension frameDimensions = new Dimension(650, 384);

	/** The panel component. */
	public BoardPanel panel;

	/**
	 * Constructor of the BoardFrame class.
	 * 
	 * @param title
	 *            the title of the window.
	 */
	public BoardFrame(String title) {
		super();
		this.setTitle(title);
		this.setSize(this.frameDimensions.width, this.frameDimensions.height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPan(new BoardPanel());
		this.setContentPane(this.panel);
		this.setResizable(false);
		this.setVisible(true);
		this.run();
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

	@Override
	public void run() {
		while (true) {
			this.panel.repaint();


		}

	}

}
