package gameDevelopmentInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import data.AttributeData;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class AttributeSelectorPane extends VBox {
	private final double prefWidth = 250;
	private final double prefHeight = 200;
	private final String basicAttributesFile = "data/attributeSkeletons/basicAttributes";
	private final String presetAttributesFile = "data/attributeSkeletons/presetAttributes";
	private final String userCreatedAttributesFile = "data/attributeSkeletons/userCreatedAttributes";
	private AttributeData attributeHolder;

	public AttributeSelectorPane(AttributeData attributeHolder) {
		this.attributeHolder = attributeHolder;
		Node customAttributesDisplay = new AttributeDisplay("Add Custom Attributes",
				getAttributesFromFolder(new File(basicAttributesFile)),this.attributeHolder);
		Node presetAttributesDisplay = new AttributeDisplay("Add Preset Attributes",
				getAttributesFromFolder(new File(presetAttributesFile)),attributeHolder);
//		Node userCreatedAttributesDisplay = new AttributeDisplay("Add User-Created Attributes",
//				getAttributesFromFolder(new File(userCreatedAttributesFile)),attributeHolder);
		Node thisClassesAttributes = new AttributeDisplay("Edit This Class' Attributes", attributeHolder.getAttributes(),attributeHolder);

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