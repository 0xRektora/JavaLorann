package org.ExiaEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import MotionElement.Pawn;
import MotionElement.Player;

public class KeyboardControl implements KeyListener {

	/** The pawn we'll move. */
	private Pawn pawn;

	/**
	 * The constructor
	 * 
	 * @param pawn
	 *            take the pawn we wanna move.
	 */
	public KeyboardControl(Pawn pawn) {
		this.pawn = pawn;
	}

	/**
	 * Function to listen the pawn controller and move it around the map.
	 * 
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (this.pawn.isAlive()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				this.pawn.move_right();
				break;
			case KeyEvent.VK_LEFT:
				this.pawn.move_left();
				break;
			case KeyEvent.VK_UP:
				this.pawn.move_up();
				break;
			case KeyEvent.VK_DOWN:
				this.pawn.move_down();
				break;
			case KeyEvent.VK_SPACE:
				this.pawn.shoot();
				break;
			case KeyEvent.VK_ENTER:
				System.out.println("enter");
				Player player = new Player();
				break;
			default:
				break;

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
