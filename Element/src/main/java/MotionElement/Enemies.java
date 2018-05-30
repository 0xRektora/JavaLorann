package MotionElement;

import java.util.List;
import java.util.Random;

public class Enemies extends Pawn {
	/** A list containing the entire sprites.*/
	private List<String> enemySprites;
	
	/** The enemy constructor*/
	public Enemies() {
		this.randomSprite();
		this.loadSprite();
	}
	
	/**
	 * Adding the sprites to the list.
	 */
	public void addSprite() {
		this.enemySprites.add("monster_1.png");
		this.enemySprites.add("monster_2.png");
		this.enemySprites.add("monster_3.png");
		this.enemySprites.add("monster_4.png");
		this.enemySprites.add("monster_5.png");
	}
	
	/**Randomizer of the sprite selector
	 * 
	 */
	public void randomSprite() {
		Random randomizer = new Random();
		String image = enemySprites.get(randomizer.nextInt(this.enemySprites.size()));
		this.setImagePath(image);
	}

}
