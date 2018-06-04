package view;

import org.ExiaEngine.BoardFrame;

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
}
