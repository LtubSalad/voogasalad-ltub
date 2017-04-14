package gameDevelopmentInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import data.AttributeData;
import data.AttributesForScreenUse;
import gameDevelopmentInterface.attributeCreator.AttributeDataFactory;
import gameDevelopmentInterface.attributeCreator.AttributeDisplay;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class AttributeSelectorPane extends VBox {
	private final double prefWidth = 250;
	private final double prefHeight = 200;
	private final String basicAttributesFile = "data/attributeSkeletons/basicAttributes";
	private final String presetAttributesFile = "data/attributeSkeletons/presetAttributes";
	private final String userCreatedAttributesFile = "data/attributeSkeletons/userCreatedAttributes";
	private AttributeData attributeHolder;
	private AttributesForScreenUse myAttributesModel;

	public AttributeSelectorPane(AttributesForScreenUse attributesModel, AttributeData attributeHolder) {
		this.attributeHolder = attributeHolder;
		myAttributesModel = attributesModel;
		Node customAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add Custom Attributes",
				getAttributesFromFolder(new File(basicAttributesFile)),this.attributeHolder);
		Node presetAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add Preset Attributes",
				getAttributesFromFolder(new File(presetAttributesFile)),attributeHolder);
//		Node userCreatedAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add User-Created Attributes",
//				getAttributesFromFolder(new File(userCreatedAttributesFile)),attributeHolder);
		Node thisClassesAttributes = new AttributeDisplay(myAttributesModel, "Edit This Class' Attributes", attributeHolder.getAttributes(),attributeHolder);

		this.setPrefSize(prefWidth, prefHeight);
		this.getChildren().addAll(customAttributesDisplay, presetAttributesDisplay, //userCreatedAttributesDisplay,
				thisClassesAttributes);
	}
	
	private List<AttributeData> getAttributesFromFolder(File file){
		AttributeDataFactory factory = new AttributeDataFactory();
		File[] attributeFiles=file.listFiles();
		List<AttributeData> attributes=new ArrayList<>();
		for(File attributeFile:attributeFiles){
			attributes.add(factory.produceAttribute(attributeFile));
		}
		return attributes;
	}

}