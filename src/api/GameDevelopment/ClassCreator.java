package api.GameDevelopment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import api.Data.InterfaceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Jake, Daniel Takes in data concerning interface data from the
 *         interface popup box and puts it in a xStream-readable format, to
 *         produce the data for a single object type. Gives this data to a XML
 *         file producer when needed.
 */
public class ClassCreator extends BorderPane {
	// Put in attribute data from an attribute
	private ComboBox<String> attributeComboBox;

	public ClassCreator() {
		attributeComboBox = createAttributeSelector();
		this.setRight(attributeComboBox);
		this.setLeft(new AttributeCustomizerPane());
	}

	public void putInterfaceData(InterfaceData data) {

	}

	// Produce XML file for this class' data.
	public File createClassData() {
		return null;
	}

	private ComboBox<String> createAttributeSelector() {
		List<String> attributeNames = new ArrayList<String>();
		attributeNames.add("Actor");
		attributeNames.add("Sprite");
		attributeNames.add("EntityFinder");
		attributeNames.add("ObjectCreator");
		attributeNames.add("SoundPlayer");
		attributeNames.add("Specific- Tower");
		attributeNames.add("Specific- Monster");
		ObservableList<String> observableAttributeNames = FXCollections.observableList(attributeNames);

		ComboBox<String> attributesBox = new ComboBox<String>();
		attributesBox.setItems(observableAttributeNames);
		attributesBox.setPromptText("Add Attribute");
		return attributesBox;
	}
}
