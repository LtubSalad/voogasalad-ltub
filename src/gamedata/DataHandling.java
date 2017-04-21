package gamedata;

import java.util.List;

import data.SpriteMakerModel;
import newengine.sprite.Sprite;

/**
 * @author tahiaemran
 *general interface for translating data back and forth from the authoring environment to the game environment
 *
 */
public interface DataHandling {
	/**
	 * method that translates authoring environment data into data usable by the game builder 
	 */
	public void toSprites(List<SpriteMakerModel> data); 
	/**
	 * method that translates game data into authoring environment data
	 */
	public void toModel(Sprite sprite); 
}
