package MotionElement;

public class Spell extends Pawn {

	private final int speed = 300;

	public Spell() {
		// TODO Auto-generated constructor stub
	}

	public Spell(int x, int y, Direction direction) throws InterruptedException {
		super(x, y);
		this.setStatus(Status.SPELL);
		this.addAssets("../sprite/fireball_1.png");
		this.addAssets("../sprite/fireball_2.png");
		this.addAssets("../sprite/fireball_3.png");
		this.addAssets("../sprite/fireball_4.png");
		this.addAssets("../sprite/fireball_5.png");
		this.launchAnimaton();
		this.launchCollisionDetection();
		this.setDirection(direction);
		this.move();

	}

	public void move() {

		if (this.getDirection() == Direction.UP) {

			this.move_up();
		} else if (this.getDirection() == Direction.DOWN) {

			this.move_down();
		} else if (this.getDirection() == Direction.RIGHT) {

			this.move_right();
		} else if (this.getDirection() == Direction.LEFT) {

			this.move_left();
		}

	}

}
