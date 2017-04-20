package gameDevelopmentInterface.spriteCreator;


import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;

public class ComponentCustomizerPopup extends Stage {
	/**
	 * Basically just a popup with an AttributeCustomizerPane, allowing you to set the variables of an attribute.
	 * 
	 * @param attributesModel
	 * @param dataToAdd
	 * @param targetData
	 * @throws UnsupportedTypeException 
	 */
	public ComponentCustomizerPopup(Class<? extends Component> clazz, SpriteMakerModel targetSprite) {
		Group root = new Group();
		VBox box=new VBox();
		ComponentSetter<? extends Component> customizer;
		try {
			customizer = new ComponentSetter<>(clazz);
			Button saveButton= new Button("Add Component");
			saveButton.setOnAction((c)->{
				try {
					targetSprite.addComponent(customizer.makeComponent());
				} catch (UnsupportedTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			box.getChildren().addAll(customizer,saveButton);
			root.getChildren().add(box);
	
			Scene myScene = new Scene(root);
			this.setTitle("Set properties of new sprite");
			this.setScene(myScene);
			this.show();
		}
		catch(UnsupportedTypeException e){
			
		}
	}

}
