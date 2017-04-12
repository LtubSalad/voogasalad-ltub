package sprite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable; 

public class CompositionMap extends Observable {
	
	// string to attribute = attribute map 
	
	private Sprite mySprite; 
	
	private HashMap<String, Attribute> namesToAttributes; 
	
	public CompositionMap(){
		namesToAttributes = new HashMap<String, Attribute>();
	}
	
	public CompositionMap(List<String> attributes){
		configureMap(attributes); 
	}
	
	public CompositionMap(Sprite sprite) {
		this.mySprite = sprite; 
	}

	public CompositionMap(Map<String, Attribute> attributeMap) {
		this.namesToAttributes = (HashMap<String, Attribute>) attributeMap; // get rid of this cast 
	}

	private void configureMap(List<String> attributes) {
		for (String name : attributes){
			if (!(namesToAttributes.keySet().contains(name))){
				namesToAttributes.put(name, null);
			}
		}
		
	}

	public void setAttribute(String name, Attribute attribute){
		namesToAttributes.put(name, attribute);
		setChanged(); 
		notifyObservers();
	}
	
	public Map<String, Attribute> getMap(){
		return namesToAttributes; 
	}
	
	
}
