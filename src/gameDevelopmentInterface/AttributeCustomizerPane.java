package gameDevelopmentInterface;

import data.AttributeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AttributeCustomizerPane extends ScrollPane {
	private AttributeData myAttribute;
	private ObservableList<AttributeCustomizerPane> subPanes;
	private VariableSetter variableSetter;
	private FunctionSetter functionSetter;
	private final double prefHeight=500;
	private final double prefWidth=500;
	private final double maxHeight=600;
	private final double maxWidth=600;

	public AttributeCustomizerPane(AttributeData attributeData) {
		myAttribute = attributeData;
		this.setPrefHeight(prefHeight);
		this.setPrefWidth(prefWidth);
		this.setMaxHeight(maxHeight);
		this.setMaxWidth(maxWidth);
		instantiate();
	}

	public AttributeData getAttribute() {
		updateAttribute();
		return myAttribute;
	}
	
	public void updateAttribute(){
		myAttribute.setScripts(functionSetter.getFunctions());
		myAttribute.setVariables(variableSetter.getVariables());
		subPanes.forEach((subPane)->{
			subPane.updateAttribute();
		});
		
	}

	private void instantiate() {
		VBox myContents=new VBox();
		subPanes= FXCollections.observableArrayList();
		variableSetter = new VariableSetter(myAttribute.getVariables());
		functionSetter = new FunctionSetter(myAttribute.getScripts());
		Button refresher = new Button("Refresh");
		refresher.setOnAction((clickEvent) -> {
			instantiate();
		});
		myContents.getChildren().addAll(new Label("Attribute Name: " + myAttribute.getName()), variableSetter,
				functionSetter);
		myAttribute.getAttributes().forEach((subAttribute) -> {
			AttributeCustomizerPane subPane=new AttributeCustomizerPane(subAttribute);
			myContents.getChildren().add(subPane);
			subPanes.add(subPane);
		});
		myContents.getChildren().add(refresher);
		this.setContent(myContents);
	}
}
