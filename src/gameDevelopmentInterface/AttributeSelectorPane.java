package gameDevelopmentInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import data.AttributeData;
import data.SpritesForScreenUse;
import gameDevelopmentInterface.attributeCreator.AttributeDataFactory;
import gameDevelopmentInterface.attributeCreator.AttributeDisplay;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class AttributeSelectorPane extends VBox {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private final double prefWidth = 250;
	private final double prefHeight = 200;
	private final String basicAttributesFile = "basicAttributesFile";
	private final String presetAttributesFile = "presetAttributesFile";
	private final String userCreatedAttributesFile = "userCreatedAttributesFile";
	private AttributeData attributeHolder;
	private SpritesForScreenUse myAttributesModel;

	public AttributeSelectorPane(SpritesForScreenUse attributesModel, AttributeData attributeHolder) {
		this.attributeHolder = attributeHolder;
		myAttributesModel = attributesModel;
		Node customAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add Custom Attributes",
				getAttributesFromFolder(new File(myResources.getString(basicAttributesFile))), this.attributeHolder);
		Node presetAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add Preset Attributes",
				getAttributesFromFolder(new File(myResources.getString(presetAttributesFile))), attributeHolder);
		Node userCreatedAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add User-Created Attributes",
				getAttributesFromFolder(new File(myResources.getString(userCreatedAttributesFile))), attributeHolder);
		Node thisClassesAttributes = new AttributeDisplay(myAttributesModel, "Edit This Class' Attributes",
				attributeHolder.getAttributes(), attributeHolder);
		this.setPrefSize(prefWidth, prefHeight);
		this.getChildren().addAll(customAttributesDisplay, presetAttributesDisplay, userCreatedAttributesDisplay,
				thisClassesAttributes);
	}

	private List<AttributeData> getAttributesFromFolder(File file) {
		AttributeDataFactory factory = new AttributeDataFactory();
		File[] attributeFiles = file.listFiles();
		List<AttributeData> attributes = new ArrayList<>();
		for (File attributeFile : attributeFiles) {
			attributes.add(factory.produceAttribute(attributeFile));
		}
		return attributes;
	}
}