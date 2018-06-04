package MotionElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.BoardPanel;
import org.ExiaEngine.ThreadsHandler;

public abstract class Pawn {

	/** The list of all pawns */
	private static List<Pawn> pawns = new ArrayList<Pawn>();

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

	/** Index of the sprite */
	private int spriteIndex = 0;

	private Spell spell;

	private Direction pdirection = Direction.DOWN;

	private boolean canShoot = false;

	private boolean haveSpell = true;

	private Status status;

	/** Thread of the animation */
	private ThreadsHandler animaton = new ThreadsHandler(this) {
		@Override
		public void launchJob() {
			((Pawn) animaton.gettClass()).animate();

		}
	};

	private ThreadsHandler collision = new ThreadsHandler(this) {
		@Override
		public void launchJob() {
			try {
				((Pawn) collision.gettClass()).detectCollision();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	/** The pawn constructor */
	Pawn() {
		this.setX(0);
		this.setY(0);
		this.isAlive = true;
		this.addPawn();

	}

	Pawn(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.isAlive = true;
		this.addPawn();

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

	public void resetSpell() {
		this.spell = null;
	}

	public void setDirection(Direction i) {
		if (this.hasSpell())
			this.pdirection = i;
	}

	public Direction getDirection() {
		return this.pdirection;
	}

	public void shoot() throws InterruptedException {
		if (this.getCanShoot() && this.hasSpell()) {

			switch (this.getDirection()) {
			case UP:
				this.spell = new Spell(this.getX(), this.getY(), Direction.DOWN);
				break;
			case DOWN:
				this.spell = new Spell(this.getX(), this.getY(), Direction.UP);
				break;
			case LEFT:
				this.spell = new Spell(this.getX(), this.getY(), Direction.RIGHT);
				break;
			case RIGHT:
				this.spell = new Spell(this.getX(), this.getY(), Direction.LEFT);
				break;
			default:
				break;
			}
			this.haveSpell = false;
			this.setCanShoot(false);

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

	public void launchCollisionDetection() {
		this.collision.start();
	}

	public Thread getAnimaton() {
		return this.animaton;
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
		Object tile = null;
		boolean noEnemy = true;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX() - 32, this.getY()));
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (i.getStatus() == Status.ENEMY && this.getX() - 32 == i.getX() && this.getY() == i.getY())
					noEnemy = false;
				break;
			}

			if (((Obstacle) tile).getStatus() == Status.GATE_CLOSED && this.getStatus() == Status.PLAYER) {
				this.kill();
			}
		} catch (Exception e) {

		}
		if (tile != null) {
			if (((Obstacle) tile).getStatus() != Status.OBSTACLE) {
				if (this.getStatus() == Status.ENEMY && ((Obstacle) tile).getStatus() != Status.PURSE
						&& ((Obstacle) tile).getStatus() != Status.CRYSTAL && noEnemy == true) {
					this.setX(this.getX() - 32);
					this.setDirection(Direction.LEFT);
				} else if (this.getStatus() == Status.PLAYER) {
					this.setX(this.getX() - 32);
					this.setDirection(Direction.LEFT);
				}
			} else {
				if (this.getStatus() == Status.SPELL)
					this.setDirection(Direction.RIGHT);
			}
			if (((Obstacle) tile).getClass() == Obstacle.class && this.getStatus() == Status.SPELL) { // collision
																										// between the
																										// spell and a
																										// purse/gate
				this.setDirection(Direction.RIGHT);
			}
		} else if (this.getX() - 32 >= 0) {
			if (this.getStatus() == Status.ENEMY && noEnemy == true) {
				this.setX(this.getX() - 32);
				this.setDirection(Direction.LEFT);
			} else if (this.getStatus() != Status.ENEMY) {
				this.setX(this.getX() - 32);
				this.setDirection(Direction.LEFT);
			}
		} else {
			this.setDirection(Direction.RIGHT);

		}

	}

	/**
	 * Function to move right the pawn, 32px is the size of the sprite.
	 */
	public void move_right() {
		Object tile = null;
		boolean noEnemy = true;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX() + 32, this.getY()));
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (i.getStatus() == Status.ENEMY && this.getX() + 32 == i.getX() && this.getY() == i.getY())
					noEnemy = false;
				break;
			}
			if (((Obstacle) tile).getStatus() == Status.GATE_CLOSED && this.getStatus() == Status.PLAYER) {
				this.kill();
			}
		} catch (Exception e) {

		}
		if (tile != null) {
			if (((Obstacle) tile).getStatus() != Status.OBSTACLE) {
				if (this.getStatus() == Status.ENEMY && ((Obstacle) tile).getStatus() != Status.PURSE
						&& ((Obstacle) tile).getStatus() != Status.CRYSTAL && noEnemy == true) {
					this.setX(this.getX() + 32);
					this.setDirection(Direction.RIGHT);
				} else if (this.getStatus() == Status.PLAYER) {
					this.setX(this.getX() + 32);
					this.setDirection(Direction.RIGHT);
				}
			} else {
				if (this.getStatus() == Status.SPELL)
					this.setDirection(Direction.LEFT);
			}
			if (((Obstacle) tile).getClass() == Obstacle.class && this.getStatus() == Status.SPELL) { // collision
																										// between the
																										// spell and a
																										// purse/gate
				this.setDirection(Direction.LEFT);
			}
		} else if (this.getX() <= BoardFrame.CASE20X - 64) {
			if (this.getStatus() == Status.ENEMY && noEnemy == true) {
				this.setX(this.getX() + 32);
				this.setDirection(Direction.RIGHT);
			} else if (this.getStatus() != Status.ENEMY) {
				this.setX(this.getX() + 32);
				this.setDirection(Direction.RIGHT);
			}
			// System.out.println(" This position X : " + this.getX() + "This position Y
			// :"+this.getY() );
		} else {
			this.setDirection(Direction.LEFT);
		}

	}

	/**
	 * Function to move up the pawn, 32px is the size of the sprite.
	 */
	public void move_up() {

		Object tile = null;
		boolean noEnemy = true;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX(), this.getY() - 32));
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (i.getStatus() == Status.ENEMY && this.getX() == i.getX() && this.getY() - 32 == i.getY()) {
					noEnemy = false;
					break;
				}

			}

			if (((Obstacle) tile).getStatus() == Status.GATE_CLOSED && this.getStatus() == Status.PLAYER) {
				this.kill();
			}
		} catch (Exception e) {
		}

		if (tile != null) {
			if (((Obstacle) tile).getStatus() != Status.OBSTACLE) {
				if (this.getStatus() == Status.ENEMY && ((Obstacle) tile).getStatus() != Status.PURSE
						&& ((Obstacle) tile).getStatus() != Status.CRYSTAL && noEnemy == true) {
					this.setY(this.getY() - 32);
					this.setDirection(Direction.UP);
				} else if (this.getStatus() == Status.PLAYER) {
					this.setY(this.getY() - 32);
					this.setDirection(Direction.UP);
				}
			} else {
				if (this.getStatus() == Status.SPELL)
					this.setDirection(Direction.DOWN);
			}
			if (((Obstacle) tile).getClass() == Obstacle.class && this.getStatus() == Status.SPELL) { // collision
																										// between the
																										// spell and a
																										// purse/gate
				this.setDirection(Direction.DOWN);
			}
		} else if (this.getY() - 32 >= 0) {

			if (this.getStatus() == Status.ENEMY && noEnemy == true) {
				this.setY(this.getY() - 32);
				this.setDirection(Direction.UP);
			} else if (this.getStatus() != Status.ENEMY) {
				this.setY(this.getY() - 32);
				this.setDirection(Direction.UP);
			}
		} else {
			this.setDirection(Direction.DOWN);
		}
	}

	/**
	 * Function to move down the pawn, 32px is the size of the sprite.
	 */
	public void move_down() {
		Object tile = null;
		boolean noEnemy = true;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX(), this.getY() + 32));
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (i.getStatus() == Status.ENEMY && this.getX() == i.getX() && this.getY() + 32 == i.getY())
					noEnemy = false;
				break;
			}
			if (((Obstacle) tile).getStatus() == Status.GATE_CLOSED && this.getStatus() == Status.PLAYER) { // Collision
																											// between
																											// the
																											// player
																											// and the
																											// closed
																											// gate
				this.kill();
			}
		} catch (Exception e) {

		}

		if (tile != null) {
			if (((Obstacle) tile).getStatus() != Status.OBSTACLE) { // collision between the spell and an obstacle
				if (this.getStatus() == Status.ENEMY && ((Obstacle) tile).getStatus() != Status.PURSE
						&& ((Obstacle) tile).getStatus() != Status.CRYSTAL && noEnemy == true) {
					this.setY(this.getY() + 32);
					this.setDirection(Direction.DOWN);
				} else if (this.getStatus() == Status.PLAYER) {
					this.setY(this.getY() + 32);
					this.setDirection(Direction.DOWN);
				}
			} else {
				if (this.getStatus() == Status.SPELL)
					this.setDirection(Direction.UP);
			}
			if (((Obstacle) tile).getClass() == Obstacle.class && this.getStatus() == Status.SPELL) { // collision
																										// between the
																										// spell and a
																										// purse/gate
				this.setDirection(Direction.UP);
			}
		} else if (this.getY() <= BoardFrame.CASE12Y - 96) { // collision between the spell or the player between the
																// border of the map
			if (this.getStatus() == Status.ENEMY && noEnemy == true) {
				this.setY(this.getY() + 32);
				this.setDirection(Direction.DOWN);
			} else if (this.getStatus() != Status.ENEMY) {
				this.setY(this.getY() + 32);
				this.setDirection(Direction.DOWN);
			}
		} else {
			this.setDirection(Direction.UP);

		}

	}

	/**
	 * Function to move up right the pawn, 32px is the size of the sprite.
	 */
	public void move_up_right() {
		this.move_up();
		this.move_right();

	}

	/**
	 * Function to move down right the pawn, 32px is the size of the sprite.
	 */
	public void move_down_right() {
		this.move_down();
		this.move_right();

	}

	/**
	 * Function to move up left the pawn, 32px is the size of the sprite.
	 */
	public void move_up_left() {
		this.move_up();
		this.move_left();
	}

	/**
	 * Function to move down left the pawn, 32px is the size of the sprite.
	 */
	public void move_down_left() {
		this.move_down();
		this.move_left();
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

	public void setisAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * Function to kill the pawn
	 */
	public void kill() {
		System.out.println("Pawn : " + this.getStatus() + " killed");
		if (this.getStatus() == Status.PLAYER && !this.isAlive()) { // remove the spell if it's running and the player
																	// died
			try {
				this.spell.setisAlive(false);
				this.setHasSpell(false);
				Pawn.getPawns().remove(this.spell);
				this.spell = null;
			} catch (Exception e) {

			}
		}
		ThreadsHandler.removeThread(this.collision);
		ThreadsHandler.removeThread(this.animaton);
		if (this.getStatus() != Status.PLAYER) {
			this.setX(-32);
			this.setY(-32);
		}

		this.setisAlive(false);
		Pawn.getPawns().remove(this);
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

	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * The sprite loader function
	 */

	/**
	 * Return the image of the pawn.
	 * 
	 * @return BufferedImage the current sprite.
	 */
	public BufferedImage getSprite() {
		return this.sprite;
	}

	/**
	 * Sprite loading function
	 */
	public void loadSprite() {
		try {
			this.sprite = ImageIO.read(new File(this.imagePath));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame("Error"), "Error while loading the sprite.\n" + ex);

		}

	}

	/*
	 * The animation function of each pawn.
	 */
	public void animate() {
		while (this.isAlive()) {
			if (this.spriteIndex >= this.assets.size())
				this.spriteIndex = 0;

			this.setImagePath(this.assets.get(this.spriteIndex));
			this.loadSprite();
			this.spriteIndex++;

			try {
				Thread.sleep(this.getTime());
			} catch (InterruptedException e) {
			}

		}
	}

	public void detectCollision() throws InterruptedException {
		while (true) {

			try {
				Thread.sleep(this.getTime());
			} catch (InterruptedException e) {
			}

		}

	}

	/**
	 * Getter for the canShoot attribute.
	 * 
	 * @return boolean if the pawn can shoot.
	 */
	public Boolean getCanShoot() {
		return canShoot;
	}

	/**
	 * Setter for the canShoot attribute.
	 * 
	 * @param canShoot
	 *            value of canShoot
	 */
	public void setCanShoot(Boolean canShoot) {
		this.canShoot = canShoot;
	}

	/**
	 * Getter for the Status attribute.
	 * 
	 * @return Status value of status.
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Setter for the Status attribute.
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public static List<Pawn> getPawns() {
		return pawns;
	}

	public void addPawn() {
		Pawn.getPawns().add(this);
	}

	public static void resetPawns() {
		Iterator<Pawn> iter = Pawn.getPawns().iterator();
		while (iter.hasNext()) {
			Pawn i = iter.next();
			i.kill();
			Pawn.getPawns().remove(i);
		}
		Pawn.pawns = new ArrayList<Pawn>();
		ThreadsHandler.reset();
	}

	public int getTime() {
		return time;
	}

	public static Pawn getPlayer() {
		Iterator<Pawn> iter = Pawn.getPawns().iterator();
		Pawn player = null;
		while (iter.hasNext()) {
			Pawn i = iter.next();
			if (i.getStatus() == Status.PLAYER)
				player = i;
		}
		return player;

	}

}
