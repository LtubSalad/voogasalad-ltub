package gameDevelopmentInterface;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.AttributeData;
import data.AttributesForScreenUse;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Jake, Daniel Takes in data concerning interface data from the
 *         interface popup box and puts it in a xStream-readable format, to
 *         produce the data for a single object type. Gives this data to a XML
 *         file producer when needed.
 */
public class AttributeHolderCreator extends BorderPane {
	// Put in attribute data from an attribute
	private AttributeSelectorPane attributeSelectorPane;
	private AttributeCustomizerPane attributeCustomizerPane;
	private AttributeData attributeHolder;
	private final String userCreatedAttributesFile = "data/attributeSkeletons/userCreatedAttributes";
	private AttributesForScreenUse myAttributesModel;

	public AttributeHolderCreator(AttributesForScreenUse attributesModel) {
		myAttributesModel = attributesModel;
		Button saveButton = new Button("Save attribute to file");
		saveButton.setOnAction((c)->createClassData());
		attributeHolder=new AttributeData("Choose attribute name",true,true,null);
		attributeSelectorPane = new AttributeSelectorPane(myAttributesModel, attributeHolder);
		attributeCustomizerPane=new AttributeCustomizerPane(attributeHolder);
		this.setRight(attributeSelectorPane);
		this.setCenter(attributeCustomizerPane);
		this.setBottom(saveButton);
	}
	// Produce XML file for this class' data.
	public void createClassData() {
		attributeCustomizerPane.updateAttribute();
		XStream xstream = new XStream(new DomDriver());
		try {
            FileOutputStream fs = new FileOutputStream(userCreatedAttributesFile+"/"+attributeHolder.getName()+".xml");
            xstream.toXML(attributeHolder, fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
	}
	
}