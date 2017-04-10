package sprite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable; 

public class CompositionMap extends Observable {
	
	private HashMap<String, SpriteAttribute> namesToAttributes; 
	
	public CompositionMap(){
		namesToAttributes = new HashMap<String, SpriteAttribute>();
	}
	
	public CompositionMap(List<String> attributes){
		configureMap(attributes); 
	}
	
	public CompositionMap(Sprite sprite) {
		// TODO Auto-generated constructor stub
	}

	private void configureMap(List<String> attributes) {
		for (String name : attributes){
			if (!(namesToAttributes.keySet().contains(name))){
				namesToAttributes.put(name, null);
			}
		}
		
	}

	public void setAttribute(String name, SpriteAttribute attribute){
		namesToAttributes.put(name, attribute);
		setChanged(); 
		notifyObservers();
	}
	
	public Map<String, SpriteAttribute> getMap(){
		return namesToAttributes; 
	}
	
	
}
