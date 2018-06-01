package MotionElement;

import java.util.Iterator;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.KeyboardControl;

public class Player extends Pawn {

	private KeyboardControl kbControll = new KeyboardControl(this);

	public Player(BoardFrame frame) throws InterruptedException {
		super(0, 0);
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

	}

	/**
	 * Function to move left the pawn, 32px is the size of the sprite.
	 */

	@Override
	public void move_left() {
		if (this.getX() - 32 >= 0)
			this.setX(this.getX() - 32);

		this.setImagePath("../sprite/lorann_l.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.LEFT);

	}

	/**
	 * Function to move right the pawn, 32px is the size of the sprite.
	 * 
	 */
	@Override
	public void move_right() {
		super.move_right();
		this.setImagePath("../sprite/lorann_r.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.RIGHT);

	}

	/**
	 * Function to move up the pawn, 32px is the size of the sprite.
	 */
	@Override
	public void move_up() {
		super.move_up();
		this.setImagePath("../sprite/lorann_u.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.UP);
	}

	/**
	 * Function to move down the pawn, 32px is the size of the sprite.
	 */
	@Override
	public void move_down() {
		super.move_down();
		this.setImagePath("../sprite/lorann_b.png");
		this.loadSprite();
		this.setSpriteIndex(0);
		this.setDirection(Direction.DOWN);
	}

	@Override
	public void detectCollision() throws InterruptedException {
		while (true) {
			// Moving the spell
			if (!this.hasSpell() && this.getSpell().getStatus() == Status.SPELL) {
				this.getSpell().move();

			}
			// Re get the spell
			if (!this.hasSpell() && this.getSpell().getStatus() == Status.SPELL) {
				if (this.getX() == this.getSpell().getX() && this.getY() == this.getSpell().getY()) {
					Thread.sleep(10);
					Pawn.getPawns().remove(this.getSpell());
					this.resetSpell();
					this.setHasSpell(true);
				}
			}
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (this.getStatus() == Status.PLAYER) {
					if (i.getStatus() == Status.ENEMY && this.getX() == i.getX() && this.getY() == i.getY()) {
						Pawn.getPawns().remove(this);
						this.kill();
						break;
					}
				}

			}

			try {
				Thread.sleep(this.getTime());
			} catch (InterruptedException e) {
			}
		}

	}
}