package MotionElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.ExiaEngine.BoardPanel;

public class Enemies extends Pawn {
	/** A list containing the entire sprites. */
	private List<String> enemySprites = new ArrayList<String>();;

	/** The X spawn point of the enemy */
	private int spawnPointX;

	/** The Y spawn point of the enemy */
	private int spawnPointY;

	/** The X destinaton of the pawn for the path */
	private int destX = 320;

	/** The X destinaton of the pawn for the path */
	private int destY = 0;

	/** Temporary var for the switching path */
	private int tempGoalX;

	/** Temporary var for the switching path */
	private int tempGoalY;

	/**
	 * Field of view of the enemy, if fov = 2 the enemy will look up for 2 case away
	 */
	private int fieldOfView;

	/** Objects RayCasted by the enemy. */
	private List<Pawn> raycast = new ArrayList<Pawn>();

	/** The thread for making the enemy follow a path */
	private Thread chase = new Thread() {
		@Override
		public void run() {
			movingToDest();
		}
	};

	/**
	 * The enemy constructor
	 * 
	 * @throws InterruptedException
	 */
	public Enemies() throws InterruptedException {
		this.setStatus(Status.ENEMY);
		this.addSprite();
		this.randomSprite();
		this.loadSprite();
		this.launchCollisionDetection();
	}

	public Enemies(int x, int y, int fieldOfView) throws InterruptedException {
		this();
		this.setX(x);
		this.setY(y);
		this.tempGoalX = this.getX();
		this.tempGoalY = this.getY();
		this.setFieldOfView(fieldOfView);
		this.chase.start();

	}

	/**
	 * Adding the sprites to the list.
	 */
	public void addSprite() {
		this.enemySprites.add("../sprite/monster_1.png");
		this.enemySprites.add("../sprite/monster_2.png");
		this.enemySprites.add("../sprite/monster_3.png");
		this.enemySprites.add("../sprite/monster_4.png");

	}

	/**
	 * Randomizer of the sprite selector
	 * 
	 */
	public void randomSprite() {
		Random randomizer = new Random();
		String image = enemySprites.get(randomizer.nextInt(this.enemySprites.size()));
		this.setImagePath(image);
	}

	/**
	 * The pathfinding algorithm of the pawn
	 */
	public void path() {

		if (this.getX() - this.destX > 0 && this.getY() - this.destY > 0)
			this.move_up_left();
		else if (this.getX() - this.destX < 0 && this.getY() - this.destY > 0)
			this.move_up_right();
		else if (this.getX() - this.destX < 0 && this.getY() - this.destY < 0)
			this.move_down_right();
		else if (this.getX() - this.destX > 0 && this.getY() - this.destY < 0)
			this.move_down_left();
		else if (this.getX() - this.destX > 0)
			this.move_left();
		else if (this.getX() - this.destX < 0)
			this.move_right();
		else if (this.getY() - this.destY < 0)
			this.move_down();
		else if (this.getY() - this.destY > 0)
			this.move_up();

	}

	/**
	 * Moving the pawn toward a point
	 */
	public void movingToDest() {
		while (true) {

			this.playerDetect();
			// Random movement
			Random randomizer = new Random();
			int i = randomizer.nextInt(4);
			if (i == 1) {

				switch (this.getDirection()) {
				case LEFT:
					this.move_down_left();
					// this.setDirection(Direction.DOWN);
					break;
				case RIGHT:
					this.move_up_right();
					// this.setDirection(Direction.DOWN);
					break;
				default:
					break;
				}

			} else {
				// Trace the path
				this.path();
			}

			// Reset the path if the goal is reached
			if (this.getX() == this.destX && this.getY() == this.destY) {

				this.setDest(this.tempGoalX, this.tempGoalY);
				this.tempGoalX = this.getX();
				this.tempGoalY = this.getY();

			}

			// Follow the time
			try {
				Thread.sleep(this.getTime() + 150);
			} catch (InterruptedException e) {
			}
		}

	}

	public void setDest(int x, int y) {
		this.destX = x;
		this.destY = y;
	}

	/**
	 * Collision detection of the enemies with the spell
	 */
	@Override
	public void detectCollision() throws InterruptedException {
		while (true) {
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (this.getStatus() == Status.ENEMY) {
					if (i.getStatus() == Status.PLAYER && !i.hasSpell() && this.getX() == i.getSpell().getX() && this.getY() == i.getSpell().getY()) {
						this.kill();
						Pawn.getPawns().remove(this);
						break;
					}
				}

			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}

	}

	/** Field of view of the enemy */
	public void playerDetect() {
		for (int x = (this.getX() - (32 * this.getFieldOfView())); x <= this.getX()
				+ (this.getFieldOfView() * 32); x += 32) {
			for (int y = (this.getY() - (32 * this.getFieldOfView())); y <= this.getY()
					+ (this.getFieldOfView() * 32); y += 32) {
				this.scanArea(x, y);

			}
		}

		Iterator<Pawn> iter = Pawn.getPawns().iterator();
		while (iter.hasNext()) {
			Pawn i = iter.next();
			if (this.raycast.contains(i) && i.getStatus() == Status.PLAYER) {

				this.destX = Pawn.getPawns().get(Pawn.getPawns().indexOf(i)).getX();
				this.destY = Pawn.getPawns().get(Pawn.getPawns().indexOf(i)).getY();

			}

		}

	}

	/** Method to scan a tile and check if there's an obstacle or a player */
	private void scanArea(int x, int y) {
		Iterator<Pawn> iter = Pawn.getPawns().iterator();
		while (iter.hasNext()) {
			Pawn i = iter.next();
			if (i.getX() == x && i.getY() == y && !this.raycast.contains(i) && i != this) {
				this.raycast.add(i);
				if (i.getStatus() == Status.PLAYER) {
					this.destX = Pawn.getPawns().get(Pawn.getPawns().indexOf(i)).getX();
					this.destY = Pawn.getPawns().get(Pawn.getPawns().indexOf(i)).getX();
				}
			}

		}
	}

	/** FoV getter */
	public int getFieldOfView() {
		return fieldOfView;
	}

	/** FoV setter */
	public void setFieldOfView(int fieldOfView) {
		this.fieldOfView = fieldOfView;
	}

}
