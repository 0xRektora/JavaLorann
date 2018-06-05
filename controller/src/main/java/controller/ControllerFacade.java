package controller;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.BoardPanel;
import org.ExiaEngine.ThreadsHandler;

import MotionElement.Enemies;
import MotionElement.Obstacle;
import MotionElement.Pawn;
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
	private IView view;

	/** The model. */
	private IModel model;

	private ThreadsHandler gameState = new ThreadsHandler(this) {
		@Override
		public void launchJob() {
			((ControllerFacade) gameState.gettClass()).checkPlayerState();
		}
	};

	/**
	 * Instantiates a new controller facade.
	 *
	 * @param view
	 *            the view
	 * @param model
	 *            the model
	 * @throws InterruptedException
	 */
	public ControllerFacade(final IView view, final IModel model) throws InterruptedException {
		super();
		this.model = model;
		this.view = view;
		this.model.setBoardframe(new BoardFrame("Lorann"));
		this.gameState.start();
		BoardFrame.playSound(this.getModel().mainSound, 0.15);
		
		

	}

	/**
	 * Start.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 * @throws InterruptedException
	 */
	public void start() throws SQLException, InterruptedException {
		this.level();

	}

	public void level() throws InterruptedException {
		this.getView().chooseLevel(this.getModel());
		this.getView().drawMap(this.getModel().getLvl(), this.getModel(), this);
	}

	
	public void checkPlayerStateRedo(String message) {
		this.getView().resetMap(this.getModel());
		JOptionPane.showMessageDialog(new JFrame("Error"), message + this.getModel().getPlayer().getScore());
		

		try {
			this.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void checkPlayerState() {
		while (true) {
			try {
				this.getView().writeScore(this.getModel());

				if (!this.getModel().getPlayer().isAlive() && !this.getModel().getPlayer().getHasCrystal()) {
					this.checkPlayerStateRedo("Game Over !\nYour Score : ");
				} else if (!this.getModel().getPlayer().isAlive() && this.getModel().getPlayer().getHasCrystal()
						&& this.getModel().getPlayer().getX() == BoardPanel.getGate()[0]
						&& this.getModel().getPlayer().getY() == BoardPanel.getGate()[1]) {
					this.checkPlayerStateRedo("You won !\nYour Score : ");
				} else if (!this.getModel().getPlayer().isAlive()) {
					this.checkPlayerStateRedo("Game Over !\nYour Score : ");
				} else {

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
