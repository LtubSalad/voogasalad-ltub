/**
 * 
 */
package newengine.view;

import javafx.scene.Scene;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;

/**
 * @author Zhiyong
 * 
 * This is the interface to create the view for games.
 * In our game authoring environment, we support two
 * kinds of games: TD(tower defense) and RTS(real time 
 * strategy). Different kinds of games need to implement
 * the basic interface for view
 *
 */
public interface IView{
	
	/**
	 * @param spriteModel
	 * 
	 * render the SpriteModel for the game
	 */
	public void render(SpriteModel spriteModel);
	
	/**
	 * @param playerStatsModel
	 * render the playerStatsModel for the game
	 */
	public void render(PlayerStatsModel playerStatsModel);
	
	/**
	 * @param selectionModel
	 * render the selectionModel for the game
	 */
	public void render(SelectionModel selectionModel);
	
	/**
	 * @return Scene
	 * return the scene to the game
	 */
	public Scene getScene();

}
