package gameDevelopmentInterface.spriteCreator;

import data.SpriteMakerModel;
import gameDevelopmentInterface.spriteCreator.variableSetters.SimpleVariableSetter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.AlertHandler;
/**
 * Allows the user to view and  set the name and description of a SpriteMakerModel graphically.
 * @author Daniel
 *
 */
public class SpriteDescriptorPane extends HBox{
	private SimpleVariableSetter<String> nameSetter;
	private SimpleVariableSetter<String> descriptionSetter;
	
	public SpriteDescriptorPane(SpriteMakerModel sprite){
		try{
			nameSetter=new SimpleVariableSetter<String>(String.class, "Sprite name:");
			descriptionSetter= new SimpleVariableSetter<String>(String.class, "Description:");
			nameSetter.setField(sprite.getName());
			descriptionSetter.setField(sprite.getDescription());
			HBox box=new HBox();
			box.getChildren().addAll(nameSetter, descriptionSetter);
			this.getChildren().add(box);
			box.setAlignment(Pos.CENTER);
		}
		catch(Exception e){
			AlertHandler.showError(e.getMessage());
		}
	}
	
	public void updateSpriteData(SpriteMakerModel spriteData) throws Exception{
		spriteData.setName(nameSetter.getValue());
		spriteData.setDescription(descriptionSetter.getValue());
	}
}