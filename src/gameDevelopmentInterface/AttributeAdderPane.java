package gameDevelopmentInterface;

import data.AttributeData;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
/**
 * A GUI component allowing users to add attributes to a target attribute.
 * @author Daniel
 *
 */
public class AttributeAdderPane extends VBox{
	public AttributeAdderPane(AttributeData addedAttribute, AttributeData targetAttribute){
		AttributeCustomizerPane customizer=new AttributeCustomizerPane(addedAttribute);
		Button saveButton= new Button("Add Attribute");
		saveButton.setOnAction((c)->{
			targetAttribute.addAttributeData(customizer.getAttribute());
		});
		this.getChildren().addAll(customizer,saveButton);
	}
}
