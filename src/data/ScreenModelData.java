package data;

import java.util.ArrayList;
import java.util.List;
import engine.sprite.Sprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Daniel, Jake
 * This class will hold the data for the screen as a collection of string, each string will
 * represent the (possibly) unique data for that particular object.
 *
 */

public class ScreenModelData {
	private ObservableList<Sprite> myScreenData = FXCollections.observableArrayList();
	/**
	 * Will put this object into the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void addObjectData(Sprite newSprite) {
		myScreenData.add(newSprite);
	}
	/**
	 * Will remove this object from the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void removeObject(Sprite spriteToRemove) {
		myScreenData.remove(spriteToRemove);
	}
	/**
	 * 
	 * @return all the objects on the screen
	 */
	public ObservableList<Sprite> getAllObjectsOnScreen() {
		return myScreenData;
		
	}
}
