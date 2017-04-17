package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * These are the attributes that will show up on the bottom of the authoring environment
 * so that the user can click and drag them
 * @author Jake
 *
 */
public class AttributesForScreenUse {
	private ObservableList<AttributeData> allPossibleScreenAttributes = FXCollections.observableArrayList();
	
	public AttributesForScreenUse() {
		
	}
	/**
	 * 
	 * @param newData a possible new sprite the user can place on the screen
	 */
	public void addAttribute(AttributeData newData) {
		allPossibleScreenAttributes.add(newData);
	}
	/**
	 * 
	 * @return all sprites that have been placed on the bottom of the screen for clicking and dragging
	 */
	public ObservableList<AttributeData> getScreenAttributes() {
		return allPossibleScreenAttributes;
	}

}