package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class SpriteMakerModel {
	private Map<String,List<String>> myComponents;
	private List<EventHandleData> myEventHandlers;

	
	public SpriteMakerModel() {
		myComponents = new HashMap<String, List<String>>();
		myEventHandlers = new ArrayList<EventHandleData>();
	}
	
	public void addComponent(String componentName, List<String> params) {
		if (!myComponents.keySet().contains(componentName)) {
			myComponents.put(componentName, params);
		}
	}
	
	public void addEventHandler(EventHandleData eventHandler) {
		myEventHandlers.add(eventHandler);
	}
//	
//	public List<Component> getComponents() {
//		return myComponents;
//	}
	
	public List<EventHandleData> getEventHandlers() {
		return myEventHandlers;
	}
	
//	public void printComponents() {
//		for (Component c : myComponents) {
//			System.out.println(c.getType());
//		}
//	}

	
//	public Map<BusEvent,String> getScriptMap() {
//		return myScriptMap;
//	}
	
	public Map<String,List<String>> getComponents() {
		return myComponents;
	}
	

	public Pair<String, List<String>> getComponentByType(String componentName) {
		for (String c : myComponents.keySet()) {
			if (c.equals(componentName)) {
				return new Pair<String, List<String>>(c, myComponents.get(c));
			}
		}
		return null;
	}

}