package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bus.BusEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteMakerModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<BusEvent,String> myScriptMap;
	private List<Component> myComponents;
	
	public SpriteMakerModel() {
		myScriptMap = new HashMap<BusEvent,String>();
		myComponents = new ArrayList<Component>();
	}
	
	public void addComponent(Component comp) {
		if (!myComponents.contains(comp)) {
			myComponents.add(comp);
		}
	}
	
	public void addScript(BusEvent event, String script) {
		myScriptMap.put(event, script);
	}
	
	public Map<BusEvent,String> getScriptMap() {
		return myScriptMap;
	}
	
	public List<Component> getComponents() {
		return myComponents;
	}
	
	public Component getComponentByType(ComponentType ct) {
		for (Component c : myComponents) {
			if (c.getType().equals(ct)) {
				return c;
			}
		}
		return null;
	}

}