package gameDevelopmentInterface.attributeCreator;

import data.AttributeData;
import data.AttributesForScreenUse;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import utilities.XStreamHandler;

/**
 * 
 * @author Jake, Daniel Takes in data concerning interface data from the
 *         interface popup box and puts it in a xStream-readable format, to
 *         produce the data for a single object type. Gives this data to a XML
 *         file producer when needed.
 */
public class AttributeHolderCreator extends BorderPane {
	private static final String LOAD_ATTRIBUTE_FROM_FILE = "Load attribute from file";
	private static final String SAVE_ATTRIBUTE_TO_FILE = "Save attribute to file";
	private static final String CHOOSE_ATTRIBUTE_NAME = "Choose attribute name";
	private AttributeSelectorPane attributeSelectorPane;
	private AttributeCustomizerPane attributeCustomizerPane;
	private AttributeData attributeHolder;
	private XStreamHandler dataHandler;
	private AttributesForScreenUse myAttributesModel;

	public AttributeHolderCreator(AttributesForScreenUse attributesModel) {
		myAttributesModel = attributesModel;
		attributeHolder=new AttributeData("Choose attribute name",true,null);
		attributeHolder.getVariables().put("xPosition", "0");
		attributeHolder.getVariables().put("yPosition", "0");
		instantiate();
	}
	
	private void instantiate(){
		dataHandler=new XStreamHandler();
		HBox saveAndLoad=new HBox();
		Button saveButton = new Button("Save attribute to file");
		saveButton.setOnAction((c)->createClassData());
		Button loadButton= new Button(LOAD_ATTRIBUTE_FROM_FILE);
		saveAndLoad.getChildren().addAll(saveButton,loadButton);
		loadButton.setOnAction((click)->{
			attributeHolder=dataHandler.getAttributeFromFile();
			instantiate();
			//System.out.print(attributeHolder.getName());
			
		});
		attributeSelectorPane = new AttributeSelectorPane(myAttributesModel, attributeHolder);
		attributeCustomizerPane=new AttributeCustomizerPane(attributeHolder);
		this.getChildren().clear();
		this.setRight(attributeSelectorPane);
		this.setCenter(attributeCustomizerPane);
		this.setBottom(saveAndLoad);
		
	}
	
	/**
	 * Produce XML file for this class' data.
	 */
	private void createClassData() {
		attributeCustomizerPane.updateAttribute();
		dataHandler.saveToFile(attributeHolder);
	}
	
}