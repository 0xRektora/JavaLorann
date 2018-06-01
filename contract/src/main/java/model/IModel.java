package model;

import java.sql.SQLException;
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

    /**
     * Gets the example by id.
     *
     * @param id
     *            the id
     * @return the example by id
     * @throws SQLException
     *             the SQL exception
     */
    Example getExampleById(int id) throws SQLException;

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
    List<Example> getAllExamples() throws SQLException;
    
    public Player getPlayer();
    
    public void setPlayer(Player player);
    
    public BoardFrame getBoardframe();

	public void setBoardframe(BoardFrame boardframe);
	
	public void addEnemy(Enemies enemy);
	
	public void removeEnemy(Enemies enemy);
	
	public List<Enemies> getEnemies(Enemies enemy);

}
