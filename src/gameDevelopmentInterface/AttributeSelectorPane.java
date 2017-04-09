package gameDevelopmentInterface;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.AttributeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class AttributeSelectorPane extends VBox {
	private final double prefWidth = 150;
	private final double prefHeight = 200;
	private final String basicAttributesFile = "data/attributeSkeletons/basicAttributes";
	private final String presetAttributesFile = "data/attributeSkeletons/presetAttributes";
	private final String userCreatedAttributesFile = "data/attributeSkeletons/userCreatedAttributes";
	private AttributeData attributeHolder;

	public AttributeSelectorPane(AttributeData attributeHolder) {
		
		this.attributeHolder = attributeHolder;
		Node customAttributesDisplay = new AttributeDisplay("Add Custom Attributes",
				getAttributesFromFolder(new File(basicAttributesFile)),attributeHolder);
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