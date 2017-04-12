package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AttributesForScreenUse {
	private ObservableList<AttributeData> allPossibleScreenAttributes = FXCollections.observableArrayList();
	
	public AttributesForScreenUse() {
		
	}
	
	public void addAttribute(AttributeData newData) {
		allPossibleScreenAttributes.add(newData);
	}
	
	public ObservableList<AttributeData> getScreenAttributes() {
		return allPossibleScreenAttributes;
	}

}