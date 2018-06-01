package MotionElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Enemies extends Pawn {
	/** A list containing the entire sprites.*/
	private List<String> enemySprites = new ArrayList<String>();;
	
	/** The enemy constructor
	 * @throws InterruptedException */
	public Enemies() throws InterruptedException {
		this.setStatus(Status.ENEMY);
		this.addSprite();
		this.randomSprite();
		this.loadSprite();
		this.launchCollisionDetection();
	}
	
	public Enemies(int x, int y) throws InterruptedException {
		this();
		this.setX(x);
		this.setY(y);
		
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
	
	/**Randomizer of the sprite selector
	 * 
	 */
	public void randomSprite() {
		Random randomizer = new Random();
		String image = enemySprites.get(randomizer.nextInt(this.enemySprites.size()));
		this.setImagePath(image);
	}
	
	/**
	 * Collision detection of the enemies
	 */
	@Override
	public void detectCollision() throws InterruptedException {
		while (true) {
			Iterator<Pawn> iter = Pawn.getPawns().iterator();
			while (iter.hasNext()) {
				Pawn i = iter.next();
				if (this.getStatus() == Status.ENEMY) {
					if (i.getStatus() == Status.SPELL && this.getX() == i.getX() && this.getY() == i.getY()) {
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
