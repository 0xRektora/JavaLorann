import java.awt.Graphics;
import java.lang.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Joueur extends MotionElement {
	
	Direction direction;
	private boolean isAlive;
	public BufferedImage image;
	public int x;
	public int y;
	
	public Joueur(int x, int y, boolean isAlive) {
		this.x = x;
		this.y = y;
		this.isAlive = true;
	}
	
	public  int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	public void setDirection(Direction direction) {
	this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
    
	public boolean setDead() {
		return this.isAlive = false;
	}
}
