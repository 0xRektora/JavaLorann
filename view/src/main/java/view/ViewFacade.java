package view;

import javax.swing.JOptionPane;

import org.ExiaEngine.BoardFrame;

import MotionElement.Player;

/**
 * <h1>The Class ViewFacade provides a facade of the View component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public class ViewFacade implements IView {

	private  BoardFrame mainWindow;

	private Thread gameWindow;

	/**
	 * Instantiates a new view facade.
	 */
	public ViewFacade() {
		super();
		
		
	}
	
	 
	public void initWindow(BoardFrame boardframe) {
		this.gameWindow = new Thread() {
			@Override
			public void run() {
				mainWindow = boardframe;
			}
		};
		this.gameWindow.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IView#displayMessage(java.lang.String)
	 */
	@Override
	public final void displayMessage(final String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public BoardFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(BoardFrame mainWindow) {
		this.mainWindow = mainWindow;
	}

}
