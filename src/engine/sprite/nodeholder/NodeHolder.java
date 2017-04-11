package engine.sprite.nodeholder;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.images.LtubImage;
import javafx.scene.Node;


public class NodeHolder implements Attribute{
	private Node myNode; 
	private AttributeData myData;
	
	public NodeHolder(AttributeData data){
		myData = data; 
	}
}
