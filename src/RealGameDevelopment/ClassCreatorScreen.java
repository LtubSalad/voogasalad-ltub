package RealGameDevelopment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import api.Data.AttributeData;
import api.Data.ClassData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * 
 * @author Jake, Daniel Takes in data concerning interface data from the
 *         interface popup box and puts it in a xStream-readable format, to
 *         produce the data for a single object type. Gives this data to a XML
 *         file producer when needed.
 */
public class ClassCreatorScreen extends BorderPane {
	// Put in attribute data from an attribute
	private Node attributePane;
	private ClassData attributeHolder;

	public ClassCreatorScreen() {
		attributePane = new AttributeSelectorPane();
		this.setRight(attributePane);
		this.setLeft(new AttributeCustomizerPane());
	}

	// Produce XML file for this class' data.
	public File createClassData() {
		return null;
	}
}
