package view;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.ExiaEngine.BoardFrame;
import org.ExiaEngine.BoardPanel;

import MotionElement.Pawn;
import MotionElement.Player;
import MotionElement.Status;
import controller.IController;
import model.Example;
import model.IModel;

/**
 * <h1>The Class ViewFacade provides a facade of the View component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public class ViewFacade implements IView {

	private BoardFrame mainWindow;

	private Thread gameWindow;

	/**
	 * Instantiates a new view facade.
	 */
	public ViewFacade() {
		super();
		

	}

	public void initWindow(BoardFrame boardframe) {

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

	/**
	 * Function that ask the user a level to load w/ a option pan.
	 */
	public void chooseLevel(IModel model) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Levels:", new Object[] { "1", "2", "3", "4", "5" });

		JTable table = new JTable(tableModel);
		ListSelectionModel selectionModel = table.getSelectionModel();

		JPanel p = new JPanel(new BorderLayout());
		p.add(table, BorderLayout.CENTER);

		int option = JOptionPane.showConfirmDialog(null, p, "Choose a level:", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);

		if (JOptionPane.OK_OPTION == option) {
			model.setLvl(checkSelection(selectionModel, tableModel));
		} else {
			selectionModel.clearSelection();
		}

	}

	/**
	 * Function to get the selected value from the option pan.
	 * 
	 * @param selectionModel
	 * @param tableModel
	 * @return
	 */
	private static int checkSelection(ListSelectionModel selectionModel, TableModel tableModel) {
		for (int i = selectionModel.getMinSelectionIndex(); i <= selectionModel.getMaxSelectionIndex(); i++) {
			if (selectionModel.isSelectedIndex(i)) {
				Object selectedValue = tableModel.getValueAt(i, 0);
				return Integer.parseInt((String) selectedValue);
			}
		}
		return 1;
	}

	/**
	 * Instantiate each object of the DB tile per tile like a brush into the map.
	 * 
	 * @param lvl
	 * @throws InterruptedException
	 */
	public void drawMap(int lvl, IModel model ,IController controller) throws InterruptedException {
		System.out.println("lvl: " + lvl);
		lvl = lvl - 1;

		int start = (model.caseX * model.caseY) * lvl;
		int end = start + (model.caseX * model.caseY);
		System.out.println("start : " + start + " end : " + end);
		System.out.println(start + " " + end);

		// Load all the objects of the database into the model
		try {
			for (Example i : model.getMapByLvl(start, end)) {
				model.getMap().add(i.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Load the objects into the game
		int x = 0;
		int y = 0;

		for (String i : model.getMap()) {

			if (x == 32 * 20) {
				x = 0;
				y += 32;
			}
			if (i.equals(model.VERTICALBONE) || i.equals("i")) {
				controller.instanciateObject(x, y, true);
				x += 32;
			} else if (i.equals(model.HORIZONTALBONE) || i.equals("-")) {

				controller.instanciateObject(x, y, false);
				x += 32;
			} else if (i.equals(model.ROUNDOBSTACLE) || i.equals("b")) {
				controller.instanciateObject(x, y, Status.OBSTACLE);
				x += 32;
			} else if (i.equals(model.PLAYER) || i.equals("p")) {
				controller.instanciateObject(x, y, model.getBoardframe());
				x += 32;
			} else if (i.equals(model.MONSTER) || i.equals("m")) {
				controller.instanciateObject(x, y, IModel.FOV);
				x += 32;
			} else if (i.equals(model.PURSE) || i.equals("d")) {
				controller.instanciateObject(x, y, Status.PURSE);
				x += 32;
			} else if (i.equals(model.CRYSTAL) || i.equals("c")) {
				controller.instanciateObject(x, y, Status.CRYSTAL);
				x += 32;
			} else if (i.equals(model.GATE) || i.equals("g")) {
				controller.instanciateObject(x, y, Status.GATE_CLOSED);
				x += 32;
			} else if (i.equals(model.VOID) || i.equals("v")) {
				x += 32;
			} else {
				x += 32;
			}
		}

	}
	
	@Override
	public void resetMap(IModel model) {
		Pawn.resetPawns();
		for (int x = 0; x < model.getBoardframe().getPan().getTile().getWidth(); x++) {
			for (int y = 0; y < model.getBoardframe().getPan().getTile().getHeight(); y++) {
				BoardPanel.removeObject(x * 32, y * 32);
			}
		}
		model.setMap(new ArrayList<String>());
	}

	
	@Override
	public void setMainWindow(BoardFrame mainWindow) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeScore(IModel model) {
		model.getBoardframe().getPan().writeScore(model.getPlayer().getScore());
	}


}
