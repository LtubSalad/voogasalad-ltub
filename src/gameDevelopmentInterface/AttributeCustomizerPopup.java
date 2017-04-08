package gameDevelopmentInterface;

import java.util.List;
import java.util.Map;

import data.AttributeData;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AttributeCustomizerPopup extends Stage {
	public AttributeCustomizerPopup(AttributeData dataToAdd, AttributeData targetData) {
		Group root = new Group();
		root.getChildren().add(new AttributeAdderPane(dataToAdd,targetData));

		Scene myScene = new Scene(root);
		this.setTitle("Set properties of " + dataToAdd.getName());
		this.setScene(myScene);
		this.show();
	}

}
