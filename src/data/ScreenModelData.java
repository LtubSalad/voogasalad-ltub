package data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.Translatable;
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
	private ObservableList<SpriteMakerModel> myScreenData = FXCollections.observableArrayList();
	private Map<SpriteMakerModel, Boolean> onScreenOrNot = new HashMap<SpriteMakerModel, Boolean>();
	
	public ScreenModelData() {
		
	}
//	public void setNumCols(int numCols) {
//		this.numCols = numCols;
//	}
//	public void setNumRows(int numRows) {
//		this.numRows = numRows;
//	}
//	public int getNumCols() {
//		return numCols;
//	}
//	public int getNumRows() {
//		return numRows;
//	}
	
	/**
	 * Will put this object into the collection of objects for the screen
	 * @param data the possibly unique representation of the object
	 */
	public void addObjectData(SpriteMakerModel anActualPlacedScreenObject) {
		onScreenOrNot.put(anActualPlacedScreenObject, false);
		myScreenData.add(anActualPlacedScreenObject);
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
	public ObservableList<SpriteMakerModel> getAllObjectsOnScreen() {
		return myScreenData;
	}
	public Map<SpriteMakerModel, Boolean> getIfOnScreen() {
		return onScreenOrNot;
	}
	/**
	 * 
	 * @return a list version of the observable list we have been using to store screen sprites
	 */
	public List<SpriteMakerModel> getDataToSave() {
		List<SpriteMakerModel> toReturn = new ArrayList<SpriteMakerModel>();
		myScreenData.forEach(Attr -> {
			toReturn.add(Attr);
		});
		return toReturn;
	}
	/**
	 * 
	 * @param datas are from a file that contains a preset map
	 */
	public void setObjectsOnScreen(List<SpriteMakerModel> datas){
		onScreenOrNot.clear();
		datas.forEach(d -> onScreenOrNot.put(d, false));
		myScreenData.clear();
		myScreenData.addAll(datas);
	}
	// TODO: GET RID OF DUPLICATE
	//@Override
	public List<SpriteMakerModel> getData() {
		List<SpriteMakerModel> toReturn = new ArrayList<SpriteMakerModel>();
		myScreenData.forEach(Attr -> {
			toReturn.add(Attr);
		});
		return toReturn;
	}
	
}