package MotionElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.ExiaEngine.BoardPanel;

public class Obstacle {

	/** List of all the obstackes instanciated */
	public static List<Obstacle> obstacles = new ArrayList<Obstacle>();

	/** The X position of the obstacle */
	private int x;

	/** The Y position of the obstacle */
	private int y;

	/** The image path of the sprite */
	private String imagePath;

	/** The sprite object */
	private BufferedImage sprite;

	/** The status of this object, in this case obstacle */
	private Status status;

	public Obstacle(int x, int y, boolean isVerticalBone) {
		this.setX(x);
		this.setY(y);
		this.setStatus(Status.OBSTACLE);

		if (isVerticalBone) {
			this.setImagePath("../sprite/vertical_bone.png");
		} else {
			this.setImagePath("../sprite/bone.png");
		}
		this.loadSprite();

		BoardPanel.addObject(this, this.getX(), this.getY());
	}

	private void loadSprite() {
		try {
			this.sprite = ImageIO.read(new File(this.imagePath));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "Error while loading the sprite.\n" + ex);

		}

		Obstacle.obstacles.add(this);

	}

	/** Getter for the X position */
	public int getX() {
		return x;
	}

	/** Setter for the X position */
	public void setX(int x) {
		this.x = x;
	}

	/** Getter for the Y position */
	public int getY() {
		return y;
	}

	/** Setter for the Y position */
	public void setY(int y) {
		this.y = y;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public static List<Obstacle> getObstacles() {
		return Obstacle.obstacles;
	}

}
