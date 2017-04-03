package data;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Daniel, Jake
 * This class will hold the data for the screen as a collection of string, each string will
 * represent the (possibly) unique data for that particular object.
 *
 */

public class ScreenModelData {
	private List<String> myScreenData = new ArrayList<String>();
	/**
	 * Will put this object into the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void addObjectData(String data) {
		myScreenData.add(data);
	}
	/**
	 * Will remove this object from the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void removeObject(String data) {
		myScreenData.remove(data);
	}
	/**
	 * 
	 * @return all the objects on the screen
	 */
	public List<String> getAllObjectsOnScreen() {
		return myScreenData;
		
	}
}
