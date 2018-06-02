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

	/** List of all the obstacles instanciated */
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

	public Obstacle(int x, int y, Status status) {
		this.setX(x);
		this.setY(y);
		this.setStatus(status);
		if(this.getStatus() == Status.CRYSTAL)
			this.setImagePath("../sprite/crystal_ball.png");
		else if(this.getStatus() == Status.PURSE)
			this.setImagePath("../sprite/purse.png");
		else if(this.getStatus() == Status.GATE_CLOSED)
			this.setImagePath("../sprite/gate_closed.png");
		else if(this.getStatus() == Status.OBSTACLE)
			this.setImagePath("../sprite/bone.png");
		
		this.loadSprite();
		BoardPanel.addObject(this, this.getX(), this.getY());
		
	}

	public Obstacle(int x, int y, boolean isVerticalBone) {
		this.setX(x);
		this.setY(y);
		this.setStatus(Status.OBSTACLE);

		if (isVerticalBone) {
			this.setImagePath("../sprite/vertical_bone.png");
		} else {
			this.setImagePath("../sprite/horizontal_bone.png");
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
	
	/** 
	 * Opening the gate.
	 * */
	public void openGate() {
		this.setStatus(Status.GATE_OPEN);
		this.setImagePath("../sprite/gate_open.png");
		this.loadSprite();
	}

	/**
	 * Getter for the static list of the obstacles
	 * @return List<Obstacle>
	 * 		The list of all the obstacles objects.
	 */
	public static List<Obstacle> getObstacles() {
		return Obstacle.obstacles;
	}

}
