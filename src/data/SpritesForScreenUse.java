package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * These are the attributes that will show up on the bottom of the authoring environment
 * so that the user can click and drag them
 * @author Jake
 *
 */
public class SpritesForScreenUse {
	private ObservableList<SpriteMakerModel> allPossibleScreenAttributes = FXCollections.observableArrayList();
	
	public SpritesForScreenUse() {
		
	}
	/**
	 * 
	 * @param newData a possible new sprite the user can place on the screen
	 */
	public void addPossibleSprite(SpriteMakerModel newSprite) {
		allPossibleScreenAttributes.add(newSprite);
	}
	/**
	 * 
	 * @return all sprites that have been placed on the bottom of the screen for clicking and dragging
	 */
	public ObservableList<SpriteMakerModel> getScreenAttributes() {
		return allPossibleScreenAttributes;
	}

}