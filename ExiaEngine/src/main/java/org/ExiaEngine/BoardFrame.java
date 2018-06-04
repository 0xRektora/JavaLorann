package org.ExiaEngine;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class BoardFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int CASE20X = 640;

	public static final int CASE12Y = 384;

	/** The frame dimensions */
	private Dimension frameDimensions = new Dimension(CASE20X + 12, CASE12Y + 128);

	/** The panel component. */
	private BoardPanel panel;

	/** FPS counter */
	private FPSCounter fpscounter;

	/** Repaint the panel */
	private Thread paintWindow = new Thread() {
		@Override
		public void run() {
			while (true)
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
		this.fpscounter = new FPSCounter();
		this.fpscounter.start();

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

	/**
	 * Getter for the frame panel.
	 * 
	 * @return BoardPanel The panel currently used by the BoardFrame.
	 */
	public BoardPanel getPan() {
		return this.panel;
	}

	public Dimension getDimensions() {
		return this.frameDimensions;
	}
	
	public static void playSound(String soundName, int volume) 
	{    
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
