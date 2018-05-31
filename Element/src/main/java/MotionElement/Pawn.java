package MotionElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Pawn {
	/** The X position of the pawn */
	private int x;

	/** The Y position of the pawn */
	private int y;

	/** The state of the pawn */
	private Boolean isAlive;

	/** The image path of the sprite */
	private String imagePath;

	/** The sprite object */
	private BufferedImage sprite;

	/** The speed of the animation */
	private final int time = 100;

	/** Sprite assets */
	private List<String> assets = new ArrayList<String>();

	/** Current time */
	private long timer;

	/** Last time */
	private long lastTimer;

	/** Index of the sprite */
	private int spriteIndex = 0;

	private boolean isAnimating;

	private Spell spell;

	private Direction pdirection = Direction.DOWN;

	private Boolean canShoot = false;
	
	private boolean haveSpell = true;
	/** Thread of the animation */
	private Thread animaton = new Thread() {
		@Override
		public void run() {
			animate();

		}
	};

	/** The pawn constructor */
	Pawn() {
		this.setX(0);
		this.setY(0);
		this.isAlive = true;
	}

	Pawn(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.isAlive = true;
		this.lastTimer = System.currentTimeMillis();

	}

	public Boolean hasSpell() {
		return this.haveSpell;
	}
	
	public void setHasSpell(Boolean i) {
		this.haveSpell = i;
	}

	public Spell getSpell() {
		return this.spell;
	}

	public void setDirection(Direction i) {
		if(this.hasSpell())
			this.pdirection = i;
	}

	public Direction getDirection() {
		return this.pdirection;
	}

	public void shoot() {
		if (this.getCanShoot() && this.hasSpell()) {

			this.spell = new Spell(this.getX(), this.getY(), this.getDirection());
			this.haveSpell = false;

		}

	}

	/**
	 * Adding the image to the assets
	 * 
	 * @param path
	 *            Path of the sprite
	 */
	public void addAssets(String path) {

		if (this.assets != null)
			this.assets.add(path);

	}

	/**
	 * Start the animation.
	 */
	public void launchAnimaton() {
		this.animaton.start();
	}

	/**
	 * Setter for the X position of the pawn.
	 * 
	 * @param x0
	 *            the X position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setter for the Y position of the pawn.
	 * 
	 * @param y
	 *            The Y position.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter for the X position of the pawn.
	 * 
	 * @return int The X position.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Getter for the Y position of the pawn.
	 * 
	 * @return int The Y position.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Return if the pawn is alive
	 *  
	 * @return Boolean state of the pawn
	 */

	/**
	 * Function to move left the pawn, 32px is the size of the sprite.
	 */
	public void move_left() {
		if(this.getX() - 32  >= 0)
			this.setX(this.getX() - 32);
		else{
			this.setDirection(Direction.RIGHT);
			
		}
		
		
	}

	/**
	 * Function to move right the pawn, 32px is the size of the sprite.
	 */
	public void move_right() {
		if(this.getX() <= 640 - 64)
			this.setX(this.getX() + 32);
		else{
			this.setDirection(Direction.LEFT);
			
		}
		
	}

	/**
	 * Function to move up the pawn, 32px is the size of the sprite.
	 */
	public void move_up() {
		if(this.getY() - 32 >= 0)
			this.setY(this.getY() - 32);
		else{
			this.setDirection(Direction.DOWN);
			
		}
	}

	/**
	 * Function to move down the pawn, 32px is the size of the sprite.
	 */
	public void move_down() {
		if(this.getY()  <= 384 - 96)
			this.setY(this.getY() + 32);
		else{
			this.setDirection(Direction.UP	);
			
		}
		
	}

	/**
	 * Function to move up right the pawn, 32px is the size of the sprite.
	 */
	public void move_up_right() {
		this.setX(this.getX() + 32);
		this.setY(this.getY() - 32);

	}

	/**
	 * Function to move down right the pawn, 32px is the size of the sprite.
	 */
	public void move_down_right() {
		this.setX(this.getX() + 32);
		this.setY(this.getY() + 32);

	}

	/**
	 * Function to move up left the pawn, 32px is the size of the sprite.
	 */
	public void move_up_left() {
		this.setX(this.getX() - 32);
		this.setY(this.getY() - 32);
	}

	/**
	 * Function to move down left the pawn, 32px is the size of the sprite.
	 */
	public void move_down_left() {
		this.setX(this.getX() - 32);
		this.setY(this.getY() + 32);
	}

	public void setSpriteIndex(int i) {
		this.spriteIndex = i;
	}

	/**
	 * Return a bool if the pawn is alive or not.
	 * 
	 * @return Boolean The state of the pawn.
	 */

	public Boolean isAlive() {
		return this.isAlive;
	}

	/**
	 * Function to kill the pawn
	 */
	public void kill() {
		this.isAlive = false;
	}

	/**
	 * Set the image path of the pawn.
	 * 
	 * @param imagePath
	 *            path of the image to be choosen.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * The sprite loader function
	 */

	/**
	 * Return the image of the pawn.
	 * 
	 * @return
	 */
	public BufferedImage getSprite() {
		return this.sprite;
	}

	public void loadSprite() {
		try {
			this.sprite = ImageIO.read(new File(this.imagePath));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "Error while loading the sprite.");

		}

	}

	public void animate() {
		while (true) {
			if (this.spriteIndex >= this.assets.size())
				this.spriteIndex = 0;

			this.setImagePath(this.assets.get(this.spriteIndex));
			this.loadSprite();
			this.spriteIndex++;
			if (!this.hasSpell()) {
				this.spell.move();
				if(!this.hasSpell() && this.getX() == this.getSpell().getX() && this.getY() == this.getSpell().getY()) {
					this.spell = null;
					this.haveSpell = true;
				}
			}
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
			}

		}
	}

	public Boolean getCanShoot() {
		return canShoot;
	}

	public void setCanShoot(Boolean canShoot) {
		this.canShoot = canShoot;
	}
}
