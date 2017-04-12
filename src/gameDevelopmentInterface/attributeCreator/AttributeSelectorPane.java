package gameDevelopmentInterface.attributeCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import data.AttributeData;
import data.AttributesForScreenUse;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import utilities.XStreamHandler;

public class AttributeSelectorPane extends VBox {
	private final double prefWidth = 250;
	private final double prefHeight = 200;
	private final String basicAttributesFile = "data/fundamentalAttributes";
	//private final String presetAttributesFile = "data/attributeSkeletons/presetAttributes";
	private final String userCreatedAttributesFile = "data/userCreatedAttributes";
	private AttributeData attributeHolder;
	private AttributesForScreenUse myAttributesModel;

	public AttributeSelectorPane(AttributesForScreenUse attributesModel, AttributeData attributeHolder) {
		this.attributeHolder = attributeHolder;
		myAttributesModel = attributesModel;
		Node customAttributesDisplay = new AttributeDisplay(myAttributesModel, "Add Custom Attributes",
				getAttributesFromFolder(new File(basicAttributesFile)),this.attributeHolder);
		Node userAttributesDisplay=new AttributeDisplay(myAttributesModel, "Add Your Homemade Attributes",
				getAttributesFromFolder(new File(userCreatedAttributesFile)),this.attributeHolder);
	
		this.setPrefSize(prefWidth, prefHeight);
		this.getChildren().addAll(customAttributesDisplay,userAttributesDisplay);
	}
	
	private List<AttributeData> getAttributesFromFolder(File file){
		XStreamHandler handler= new XStreamHandler();
		File[] attributeFiles=file.listFiles();
		List<AttributeData> attributes=new ArrayList<>();
		for(File attributeFile:attributeFiles){
			attributes.add(handler.getObjectFromFile(new AttributeData("tets").getClass(),attributeFile));
		}
		return attributes;
	}

}