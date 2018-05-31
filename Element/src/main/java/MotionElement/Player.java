package MotionElement;

import java.util.Iterator;



public class Player extends Pawn {
	
	
	
	public Player() {
		super();
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
		this.setCanShoot(true);
		
		
	}


	
	
	/**
	 * Function to move left the pawn, 32px is the size of the sprite.
	 */

	@Override
	public void move_left() {
		if(this.getX() - 32  >= 0)
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
	public void collision() throws InterruptedException {
		super.collision();
		Iterator<Pawn> iter = Pawn.getPawns().iterator();
		while (iter.hasNext()) {
			Pawn i = iter.next();
			if(this.getStatus() == Status.PLAYER) {
				if(this.getX() == i.getX() && this.getY() == i.getY() && i.getStatus() == Status.ENEMY) {
					Pawn.getPawns().remove(this);
					this.kill();
					System.out.println("Game Over");
					break;
				}
			}

		}
	}

}
