package data;

import java.util.ArrayList;
import java.util.List;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteMakerModel {
	private List<Component> myComponents;
	private List<EventHandleData> myEventHandlers;

	
	public SpriteMakerModel() {
		//myScriptMap = new HashMap<BusEvent,String>();
		myComponents = new ArrayList<Component>();
		myEventHandlers = new ArrayList<EventHandleData>();
	}
	
	public void addComponent(Component comp) {
		if (!myComponents.contains(comp)) {
			myComponents.add(comp);
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
	
	public void printComponents() {
		for (Component c : myComponents) {
			System.out.println(c.getType());
		}
	}

	
//	public Map<BusEvent,String> getScriptMap() {
//		return myScriptMap;
//	}
	
	public List<Component> getComponents() {
		return myComponents;
	}
	

	public Component getComponentByType(ComponentType<?> type) {
		for (Component c : myComponents) {
			if (c.getType().equals(type)) {
				return c;
			}
		}
		return null;
	}

}