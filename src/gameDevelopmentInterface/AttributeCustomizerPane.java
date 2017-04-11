package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;

import data.AttributeData;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AttributeCustomizerPane extends ScrollPane {
	private AttributeData myAttribute;
	private List<AttributeCustomizerPane> subPanes;
	private VariableSetter variableSetter;
	private RestrictedFunctionSetter functionSetter;
	private boolean customizableVariables;
	private NameSetter nameSetter;
	private final double prefHeight = 500;
	private final double prefWidth = 500;
	private final double maxHeight = 600;
	private final double maxWidth = 600;


	public AttributeCustomizerPane(AttributeData attributeData, boolean customizableVariables) {
		this.customizableVariables=customizableVariables;
		myAttribute = attributeData;
		this.setPrefHeight(prefHeight);
		this.setPrefWidth(prefWidth);
		this.setMaxHeight(maxHeight);
		this.setMaxWidth(maxWidth);
		instantiate();
	}
	
	public AttributeCustomizerPane(AttributeData attributeData) {
		this(attributeData,false);
	}
	

	public AttributeData getAttribute() {
		updateAttribute();
		return myAttribute;
	}

	public void updateAttribute() {
		myAttribute.setScripts(functionSetter.getFunctions());
		myAttribute.setVariables(variableSetter.getVariables());
		myAttribute.setName(nameSetter.getName());
		subPanes.forEach((subPane) -> {
			subPane.updateAttribute();
		});

	}

	private void instantiate() {
		VBox myContents = new VBox();
		subPanes = new ArrayList<>();
		variableSetter = new VariableSetter(myAttribute.getVariables(),customizableVariables);
		functionSetter = new RestrictedFunctionSetter(myAttribute.getScripts());
		nameSetter = new NameSetter(myAttribute.getName(), myAttribute.nameModifiable());
		Button refresher = new Button("Refresh");
		refresher.setOnAction((clickEvent) -> {
			instantiate();
		});
		myContents.getChildren().addAll(nameSetter, variableSetter, functionSetter);
		myAttribute.getAttributes().forEach((subAttribute) -> {
			AttributeCustomizerPane subPane = new AttributeCustomizerPane(subAttribute);
			myContents.getChildren().add(subPane);
			subPanes.add(subPane);
		});
		myContents.getChildren().add(refresher);
		this.setContent(myContents);
	}

}