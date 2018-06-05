package controller;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.BoardPanel;

import MotionElement.Enemies;
import MotionElement.Obstacle;
import MotionElement.Player;
import MotionElement.Status;

/**
 * <h1>The Interface IController.</h1>
 * 
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IController {
	/**
	 * Instantiate a vertical or horizontal bone
	 * 
	 * @param x
	 * @param y
	 * @param isVertical
	 */
	public void instanciateObject(int x, int y, boolean isVertical);
	/**
	 * Instantiate a purse or a gate or a crystal
	 * 
	 * @param x
	 * @param y
	 * @param status
	 */
	public void instanciateObject(int x, int y, Status status);

	/**
	 * Instantiate the player
	 * 
	 * @param x
	 * @param y
	 * @param boardframe
	 * @throws InterruptedException
	 */
	public void instanciateObject(int x, int y, BoardFrame boardframe) throws InterruptedException;
	/**
	 * Instantiate the enemy
	 * 
	 * @param x
	 * @param y
	 * @param FOV
	 */
	public void instanciateObject(int x, int y, int FOV);

}
