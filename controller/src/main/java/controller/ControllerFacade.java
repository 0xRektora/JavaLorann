package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.BoardPanel;

import MotionElement.Enemies;
import MotionElement.Obstacle;
import MotionElement.Player;
import MotionElement.Status;
import model.Example;
import model.IModel;
import view.IView;

/**
 * <h1>The Class ControllerFacade provides a facade of the Controller
 * component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public class ControllerFacade implements IController {

	/** The view. */
	private final IView view;

	/** The model. */
	private final IModel model;

	private List<String> map = new ArrayList<String>();

	/**
	 * Instantiates a new controller facade.
	 *
	 * @param view
	 *            the view
	 * @param model
	 *            the model
	 */
	public ControllerFacade(final IView view, final IModel model) {
		super();
		this.model = model;
		this.view = view;

	}

	/**
	 * Start.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 * @throws InterruptedException
	 */
	public void start() throws SQLException, InterruptedException {
		this.model.setBoardframe(new BoardFrame("Lorann"));
		this.view.initWindow(this.model.getBoardframe());
		// this.drawMap();
		//this.model.setPlayer(new Player(0, 0, this.model.getBoardframe()));
		// JOptionPane.showMessageDialog(new JFrame("Error"), );
		this.drawMap(1);

	}

	public void drawMap(int lvl) throws InterruptedException {
		lvl = lvl - 1;
		int start = (20 * 12) * lvl;
		int end = start + (20 * 12);

		// Load all the objects of the database into the model
		try {
			for (Example i : this.getModel().getMapByLvl(start, end)) {
				this.map.add(i.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Load the objects into the game
		int x = 0;
		int y = 0;

		for (String i : this.map) {

			if (x == 32 * 20) {
				x = 0;
				y += 32;
			}
			if (i.equalsIgnoreCase(this.getModel().VERTICALBONE)) {
				this.instanciateObject(x, y, true);
				x += 32;
			}
			if (i.equals(this.getModel().HORIZONTALBONE)) {

				this.instanciateObject(x, y, false);
				x += 32;
			}
			if (i.equals(this.getModel().ROUNDOBSTACLE)) {
				this.instanciateObject(x, y, Status.OBSTACLE);
				x += 32;
			}
			if (i.equals(this.getModel().PLAYER)) {
				this.instanciateObject(x, y, this.model.getBoardframe());
				x += 32;
			}
			if (i.equals(this.getModel().MONSTER)) {
				this.instanciateObject(x, y, IModel.FOV);
				x += 32;
			}
			if (i.equals(this.getModel().PURSE)) {
				this.instanciateObject(x, y, Status.PURSE);
				x += 32;
			}
			if (i.equals(this.getModel().CRYSTAL)) {
				this.instanciateObject(x, y, Status.CRYSTAL);
				x += 32;
			}
			if (i.equals(this.getModel().GATE)) {
				this.instanciateObject(x, y, Status.GATE_CLOSED);
				x += 32;
			}
			if (i.equals(this.getModel().VOID)) {
				x += 32;
			}
		}

	}

	public void resetMap() {
		for (int x = 0; x < this.getModel().getBoardframe().getPan().getTile().getWidth(); x++) {
			for (int y = 0; y < this.getModel().getBoardframe().getPan().getTile().getHeight(); y++) {
				BoardPanel.removeObject(x * 32, y * 32);
			}
		}
		this.getModel().getPlayer().kill(false);
	}

	/**
	 * Instantiate a vertical or horizontal bone
	 * 
	 * @param x
	 * @param y
	 * @param isVertical
	 */
	public void instanciateObject(int x, int y, boolean isVertical) {
		BoardPanel.addObject(new Obstacle(x, y, isVertical), x, y);
	}

	/**
	 * Instantiate a purse or a gate or a crystal
	 * 
	 * @param x
	 * @param y
	 * @param status
	 */
	public void instanciateObject(int x, int y, Status status) {
		BoardPanel.addObject(new Obstacle(x, y, status), x, y);
	}

	/**
	 * Instantiate the player
	 * 
	 * @param x
	 * @param y
	 * @param boardframe
	 * @throws InterruptedException 
	 */
	public void instanciateObject(int x, int y, BoardFrame boardframe) throws InterruptedException {
		this.model.setPlayer(new Player(x, y, boardframe));

	}

	/**
	 * Instantiate the enemy
	 * 
	 * @param x
	 * @param y
	 * @param FOV
	 */
	public void instanciateObject(int x, int y, int FOV) {
		try {
			new Enemies(x, y, FOV);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public IView getView() {
		return this.view;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public IModel getModel() {
		return this.model;
	}
}
