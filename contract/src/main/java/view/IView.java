package view;

import org.ExiaEngine.BoardFrame;

import controller.IController;
import model.IModel;



/**
 * <h1>The Interface IView.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IView {

	/**
	 * Display message.
	 *
	 * @param message
	 *            the message
	 */
	void displayMessage(String message);

	public BoardFrame getMainWindow();

	public void setMainWindow(BoardFrame mainWindow);

	public void initWindow(BoardFrame boardframe);

	public void chooseLevel(IModel model);

	void drawMap(int lvl, IModel model ,IController controller) throws InterruptedException;

	void resetMap(IModel model);

	void writeScore(IModel model);
}
