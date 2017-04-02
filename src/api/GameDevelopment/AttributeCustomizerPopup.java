package api.GameDevelopment;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AttributeCustomizerPopup extends Stage{
	public AttributeCustomizerPopup(String attributeName){
		Group root=new Group();
		root.getChildren().add(new AttributeCustomizerPane());
		
		Scene myScene=new Scene(root);
		this.setTitle("Set properties of "+attributeName);
		this.setScene(myScene);
		this.show();
	}

}
