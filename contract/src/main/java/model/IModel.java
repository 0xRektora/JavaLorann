package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ExiaEngine.BoardFrame;

import MotionElement.Enemies;
import MotionElement.Player;

/**
 * <h1>The Interface IModel.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IModel {

	/** Char set of the vertical bone */
	public static final String VERTICALBONE = "I";

	/** Char set of the horizontal bone */
	public static final String HORIZONTALBONE = "-";

	public static final String ROUNDOBSTACLE = "B";

	public static final String PLAYER = "P";

	public static final String MONSTER = "M";

	public static final String PURSE = "D";

	public static final String CRYSTAL = "C";

	public static final String VOID = "V";

	public static final String GATE = "G";

	public static final int FOV = 3;

	/** Number of tiles in the X axis */
	public static final int caseX = 20;

	/** Number of tiles in the Y axis */
	public static final int caseY = 12;

	/**
	 * Gets the example by id.
	 *
	 * @param id
	 *            the id
	 * @return the example by id
	 * @throws SQLException
	 *             the SQL exception
	 */
	ArrayList<Example> getMapByLvl(int id1, int id2) throws SQLException;

	/**
	 * Gets the example by name.
	 *
	 * @param name
	 *            the name
	 * @return the example by name
	 * @throws SQLException
	 *             the SQL exception
	 */
	Example getExampleByName(String name) throws SQLException;

	/**
	 * Gets the all examples.
	 *
	 * @return the all examples
	 * @throws SQLException
	 *             the SQL exception
	 */
	List<Example> getAllmaps() throws SQLException;

	public Player getPlayer();

	public void setPlayer(Player player);

	public BoardFrame getBoardframe();

	public void setBoardframe(BoardFrame boardframe);

	public void addEnemy(Enemies enemy);

	public void removeEnemy(Enemies enemy);

	public List<Enemies> getEnemies(Enemies enemy);

	void setLvl(int checkSelection);

	int getLvl();

	public List<String> getMap();

	public void setMap(List<String> map);

}
