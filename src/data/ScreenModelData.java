package data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private ObservableList<AttributeData> myScreenData = FXCollections.observableArrayList();
	private Map<AttributeData, Boolean> onScreenOrNot = new HashMap<AttributeData, Boolean>();
	public ScreenModelData() {
		
	}	
	/**
	 * Will put this object into the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void addObjectData(AttributeData newSprite) {
		onScreenOrNot.put(newSprite, false);
		myScreenData.add(newSprite);
	}
//	/**
//	 * Will remove this object from the collection of objects for the screen
//	 * @param data the possibly unique representation of the object
//	 */
//	public void removeObject(Sprite spriteToRemove) {
//		myScreenData.remove(spriteToRemove);
//	}
	/**
	 * 
	 * @return all the objects on the screen
	 */
	public ObservableList<AttributeData> getAllObjectsOnScreen() {
		return myScreenData;
	}
	public Map<AttributeData, Boolean> getIfOnScreen() {
		return onScreenOrNot;
	}
	/**
	 * 
	 * @return a list version of the observable list we have been using to store screen sprites
	 */
	public List<AttributeData> getDataToSave() {
		List<AttributeData> toReturn = new ArrayList<AttributeData>();
		myScreenData.forEach(Attr -> {
			toReturn.add(Attr);
		});
		return toReturn;
	}
	/**
	 * 
	 * @param datas are from a file that contains a preset map
	 */
	public void setObjectsOnScreen(List<AttributeData> datas){
		onScreenOrNot.clear();
		datas.forEach(d -> onScreenOrNot.put(d, false));
		myScreenData.clear();
		myScreenData.addAll(datas);
	}
	
}