package MotionElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Pawn {
	/** The X position of the pawn*/
	private int x;
	
	/** The Y position of the pawn*/
	private int y;
	
	/** The image path of the sprite */
	private String imagePath;
	
	/** The sprite object */
	private BufferedImage sprite;

	/** The pawn constructor*/
	Pawn() {
		this.setX(0);
		this.setY(0);
	}

	/**
	 * Setter for the X position of the pawn. 
	 * @param x
	 * 		the X position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setter for the Y position of the pawn.
	 * @param y
	 * 		The Y position.
	 */		
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter for the X position of the pawn.
	 * @return int
	 * 		The X position.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Getter for the Y position of the pawn.
	 * @return int
	 * 		The Y position.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Set the image path of the pawn.
	 * @param imagePath
	 * 		path of the image to be choosen.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * The sprite loader function
	 */
	public void loadSprite() {
		try {
			this.sprite = ImageIO.read(new File(this.imagePath));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "Error while loading the sprite.");

		}

	}

}
