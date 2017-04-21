package data;

import java.util.ArrayList;
import java.util.List;
import newengine.sprite.component.Component;

public class SpriteMakerModel {
	private List<Component> myComponents;
	private List<EventHandleData> myEventHandlers;
	
	public SpriteMakerModel() {
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
	
	public List<Component> getComponents() {
		return myComponents;
	}
	
	public List<EventHandleData> getEventHandlers() {
		return myEventHandlers;
	}

}