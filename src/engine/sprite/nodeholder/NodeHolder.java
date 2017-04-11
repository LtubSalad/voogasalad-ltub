package engine.sprite.nodeholder;

import data.AttributeData;
import engine.sprite.images.LtubImage;
import javafx.scene.Node;
import sprite.Attribute;

public class NodeHolder extends Attribute{
	private Node myNode; 
	private AttributeData myData;
	
	public NodeHolder(AttributeData data){
		myData = data; 
	}
}
