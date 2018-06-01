package model;

import java.sql.SQLException;
import java.util.List;

import org.ExiaEngine.BoardFrame;

import MotionElement.Enemies;
import MotionElement.Player;
import model.dao.ExampleDAO;
/**
 * <h1>The Class ModelFacade provides a facade of the Model component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public final class ModelFacade implements IModel {

	/** Current instantance of the player*/
	private Player player;
	
	/** Current instance of the window*/
	private BoardFrame boardframe;
	
	/** Current instances of the enemies*/
	private List<Enemies> enemies;
	
	
	
    /**
     * Instantiates a new model facade.
     */
    public ModelFacade() {
        super();

        
    }

    /*
     * (non-Javadoc)
     * @see model.IModel#getExampleById(int)
     */
    @Override
    public Example getExampleById(final int id) throws SQLException {
        return ExampleDAO.getExampleById(id);
    }

    /*
     * (non-Javadoc)
     * @see model.IModel#getExampleByName(java.lang.String)
     */
    @Override
    public Example getExampleByName(final String name) throws SQLException {
        return ExampleDAO.getExampleByName(name);
    }

    /*
     * (non-Javadoc)
     * @see model.IModel#getAllExamples()
     */
    @Override
    public List<Example> getAllExamples() throws SQLException {
        return ExampleDAO.getAllExamples();
    }
    
    @Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public BoardFrame getBoardframe() {
		return boardframe;
	}

	@Override
	public void setBoardframe(BoardFrame boardframe) {
		this.boardframe = boardframe;
	}

	@Override
	public void addEnemy(Enemies enemy) {
		this.enemies.add(enemy);
		
	}

	@Override
	public void removeEnemy(Enemies enemy) {
		this.enemies.remove(enemy);
		
	}

	@Override
	public List<Enemies> getEnemies(Enemies enemy) {
		return this.enemies;
	}

}
