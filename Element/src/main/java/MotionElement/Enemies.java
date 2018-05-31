package MotionElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies extends Pawn {
	/** A list containing the entire sprites.*/
	private List<String> enemySprites = new ArrayList<String>();;
	
	/** The enemy constructor*/
	public Enemies() {
		this.setStatus(Status.ENEMY);
		this.addSprite();
		this.randomSprite();
		this.loadSprite();
	}
	
	public Enemies(int x, int y) {
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
		System.out.println(this.getImagePath());
	}
	
	

}
