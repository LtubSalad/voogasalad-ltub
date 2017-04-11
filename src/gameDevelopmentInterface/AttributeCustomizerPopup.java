package gameDevelopmentInterface;

import data.AttributeData;
import data.AttributesForScreenUse;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AttributeCustomizerPopup extends Stage {
	public AttributeCustomizerPopup(AttributesForScreenUse attributesModel, AttributeData dataToAdd, AttributeData targetData) {
		Group root = new Group();
		VBox box=new VBox();
		AttributeCustomizerPane customizer=new AttributeCustomizerPane(dataToAdd);
		Button saveButton= new Button("Add Attribute");
		saveButton.setOnAction((c)->{
			targetData.addAttributeData(customizer.getAttribute());
			attributesModel.addAttribute(customizer.getAttribute());
		});
		box.getChildren().addAll(customizer,saveButton);
		root.getChildren().add(box);

		Scene myScene = new Scene(root);
		this.setTitle("Set properties of " + dataToAdd.getName());
		this.setScene(myScene);
		this.show();
	}

}
