package MotionElement;

import java.util.Iterator;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.BoardPanel;
import org.ExiaEngine.KeyboardControl;
import org.ExiaEngine.ThreadsHandler;

public class Player extends Pawn {

	/** Keyboard controller of the player */
	private KeyboardControl kbControll = new KeyboardControl(this);

	private final int pointPerPurse = 200;

	private boolean hasCrystal = false;

	/** The score of the player */
	private int score = 0;

	public boolean getHasCrystal() {
		return hasCrystal;
	}

	public void setHasCrystal(boolean hasCrystal) {
		this.hasCrystal = hasCrystal;
	}

	/** Thread of the method for the spell collision detection */
	private ThreadsHandler spellChecker = new ThreadsHandler(this) {
		@Override
		public void launchJob() {
			((Player) spellChecker.gettClass()).moveSpell();
		}
	};

	public Player(int x, int y, BoardFrame frame) {
		super(x, y);
		this.setStatus(Status.PLAYER);
		this.addAssets("../sprite/lorann_b.png");
		this.addAssets("../sprite/lorann_bl.png");
		this.addAssets("../sprite/lorann_l.png");
		this.addAssets("../sprite/lorann_ul.png");
		this.addAssets("../sprite/lorann_u.png");
		this.addAssets("../sprite/lorann_ur.png");
		this.addAssets("../sprite/lorann_r.png");
		this.addAssets("../sprite/lorann_br.png");
		this.launchAnimaton();
		this.launchCollisionDetection();
		frame.addKeyListener(this.kbControll);
		this.setCanShoot(true);
		this.spellChecker.start();

	}

	/**
	 * Check if the player is on a purse to mark points.
	 */
	public void checkPurse() {
		Object tile = null;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX(), this.getY()));
			if (((Obstacle) tile).getStatus() == Status.PURSE) {
				this.markPoint();
				Obstacle.getObstacles().remove(tile);
				BoardPanel.removeObject(this.getX(), this.getY());
				BoardFrame.playSound("../data/purse.wav", 5);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Check if the player got the crystal
	 */
	public void checkCrystal() {
		Object tile = null;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX(), this.getY()));
			if (((Obstacle) tile).getStatus() == Status.CRYSTAL && !this.hasCrystal) {
				this.markPoint();
				Obstacle.getObstacles().remove(tile);
				BoardPanel.removeObject(this.getX(), this.getY());
				this.hasCrystal = true;

				((Obstacle) BoardPanel.getObject(BoardPanel.gate[0], BoardPanel.gate[1])).openGate();
				BoardFrame.playSound("../data/gate.wav", 5);

			}
		} catch (Exception e) {
		}
	}

	public void checkEndGame() {
		Object tile = null;
		try {
			tile = ((Obstacle) BoardPanel.getObject(this.getX(), this.getY()));
			if (((Obstacle) tile).getStatus() == Status.GATE_OPEN) {
				this.kill();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Function to move left the pawn, 32px is the size of the sprite.
	 */

	@Override
	public void move_left() {
		super.move_left();
		this.checkPurse();
		this.checkCrystal();
		this.setImagePath("../sprite/lorann_l.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.LEFT);
		this.checkEndGame();

	}

	/**
	 * Function to move right the pawn, 32px is the size of the sprite.
	 * 
	 */
	@Override
	public void move_right() {
		super.move_right();
		this.checkPurse();
		this.checkCrystal();
		this.setImagePath("../sprite/lorann_r.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.RIGHT);
		this.checkEndGame();

	}

	/**
	 * Function to move up the pawn, 32px is the size of the sprite.
	 */
	@Override
	public void move_up() {
		super.move_up();
		this.checkPurse();
		this.checkCrystal();
		this.setImagePath("../sprite/lorann_u.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.UP);
		this.checkEndGame();
	}

	/**
	 * Function to move down the pawn, 32px is the size of the sprite.
	 */
	@Override
	public void move_down() {
		super.move_down();
		this.checkPurse();
		this.checkCrystal();
		this.setImagePath("../sprite/lorann_b.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.DOWN);
		this.checkEndGame();
	}

	/**
	 * Mover of the spell.
	 */
	public void moveSpell() {
		while (true) {

			// Moving the spell
			try {
				if (!this.hasSpell() && this.getSpell().getStatus() == Status.SPELL) {
					this.getSpell().move();
					this.setCanShoot(false);
				}
			} catch (Exception e) {

			}

			try {
				Thread.sleep(this.getTime());
			} catch (InterruptedException e) {
			}

		}

	}

	/**
	 * Detect the collision between the spell and the enemy , the spell and the
	 * player.
	 * 
	 */
	@Override
	public void detectCollision() throws InterruptedException {
		while (this.isAlive()) {
			try {
				if (!this.hasSpell()) {
					if (this.getX() == this.getSpell().getX() && this.getY() == this.getSpell().getY()) {
						Pawn.getPawns().remove(this.getSpell());
						this.resetSpell();
						this.setHasSpell(true);
						this.setCanShoot(true);
					}
				}
			} catch (Exception e) {

			}

			// If an enemy touches the player
			try {
				Iterator<Pawn> iter = Pawn.getPawns().iterator();
				while (iter.hasNext()) {
					Pawn i = iter.next();
					if (this.getStatus() == Status.PLAYER) {
						if (i.getStatus() == Status.ENEMY && this.getX() == i.getX() && this.getY() == i.getY()) {
							this.kill();
							break;
						}
					}

				}
			} catch (Exception e) {

			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void kill() {
		super.kill();
		ThreadsHandler.removeThread(this.spellChecker);
	}

	public void markPoint() {
		this.setScore(this.getScore() + this.pointPerPurse);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}