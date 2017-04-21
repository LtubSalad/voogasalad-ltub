package data;

import java.util.ArrayList;
import java.util.List;
import newengine.sprite.component.Component;

public class SpriteMakerModel {
	private List<Component> myComponents;
	private List<EventHandleData> myEventHandlers;
	
	
	public SpriteMakerModel() {
		myEventHandlers = new ArrayList<EventHandleData>();
		myComponents = new ArrayList<Component>();
	}
	
	public void addComponent(Component comp) {
		if (!myComponents.contains(comp)) {
			myComponents.add(comp);
		}
	}
	
	public void addEventHandler(EventHandleData eventHandler) {
		myEventHandlers.add(eventHandler);
	}
	
	public List<Component> getComponents() {
		return myComponents;
	}
	
	public List<EventHandleData> getEventHandlers() {
		return myEventHandlers;
	}
	
	public void printComponents() {
		for (Component c : myComponents) {
			System.out.println(c.getType());
		}
	}

}