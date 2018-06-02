package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ExiaEngine.BoardFrame;

import MotionElement.Enemies;
import MotionElement.Obstacle;
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
	
	private Obstacle gate;
	
	
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
    public ArrayList<Example> getMapByLvl(final int id1, final int id2) throws SQLException {
        return ExampleDAO.getMapByLvl(id1, id2);
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
    public List<Example> getAllmaps() throws SQLException {
        return ExampleDAO.getAllmaps();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEnemy(Enemies enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enemies> getEnemies(Enemies enemy) {
		// TODO Auto-generated method stub
		return null;
	}

	public Obstacle getGate() {
		return gate;
	}

	public void setGate(Obstacle gate) {
		this.gate = gate;
	}


}
