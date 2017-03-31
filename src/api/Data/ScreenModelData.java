package api.Data;

import java.util.Collection;
/**
 * 
 * @author Daniel, Jake
 * This class will hold the data for the screen as a collection of string, each string will
 * represent the (possibly) unique data for that particular object.
 *
 */

public interface ScreenModelData {
	/**
	 * Will put this object into the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void addObjectData(String data);
	/**
	 * Will remove this object from the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void removeObject(String data);
	/**
	 * 
	 * @return all the objects on the screen
	 */
	public Collection<String> getAllObjectsOnScreen();
}
