package gameDevelopmentInterface;

import java.util.List;
import java.util.Map;

import data.AttributeData;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AttributeCustomizerPopup extends Stage {
	public AttributeCustomizerPopup(AttributeData data) {
		Group root = new Group();
		root.getChildren().add(new AttributeCustomizerPane(data));

		Scene myScene = new Scene(root);
		this.setTitle("Set properties of " + data.getName());
		this.setScene(myScene);
		this.show();
	}

}
