package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bus.BusEvent;
import gamecreation.DataWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.util.Pair;
import newengine.events.sprite.SpriteKilledEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteMakerModel {
	// Key Event.TYPE Value Action.TYPE
	private Map<String, String> myCustomEventHandlers;
	private ObservableMap<ComponentType<?>,Component> myComponents;
	private ObservableMap<BusEvent, String> myScriptMap;
	
	
	// Jake and Tahia's DO NOT TOUCH
	private Map<String, List<String>> componentsForTransfer; 
	private List<Component> actualComponents; 
	private List<EventHandleData> myEventHandlers; 
	
	
	
	
	public SpriteMakerModel() {
		Map<ComponentType<?>, Component >componentMap=new HashMap<>();
		myComponents=FXCollections.observableMap(componentMap);
		Map<BusEvent, String> handlers=new HashMap<>();
		myScriptMap=FXCollections.observableMap(handlers);
		//components = new HashMap<String, Map<String, DataWrapper>>();
		actualComponents = new ArrayList<Component>(); 
		
	}

	
	public void addComponent(String componentName, List<String> params) {
		//TODO: modify this method call to pass data correct data structures (Map<String, DataWrapper>) 
		if (!componentsForTransfer.keySet().contains(componentName)) {
			componentsForTransfer.put(componentName, params);
		}
		
	}
	
	
	/**
	 * @param componentName
	 * @param parameters
	 * 
	 * USE THIS ONE 
	 */
	public void addActualComponent(Component c){
		actualComponents.add(c);
	}
	
	
	public List<Component> getActualComponents(){
		return actualComponents; 
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
	
	public Map<String,List<String>> getTransferComponents() {
		return componentsForTransfer; 
	}
	
	/**
	 * This code means that the sprite only has one component for each type... though
	 * you can possibly have two components of the same class with different "type".
	 * @param comp
	 */
	public void addComponent(Component comp) {
		myComponents.forEach((type,component)->{
			if(comp.getType().equals(type)){
				myComponents.remove(type,component);
			}
		});
		myComponents.put(comp.getType(), comp);
	}
	
	public void addScript(BusEvent event, String script) {
		myScriptMap.put(event, script);
	}
	
	public Map<BusEvent,String> getScriptMap() {
		return myScriptMap;
	}
	
	public ObservableMap<ComponentType<?>,Component> getComponents() {
		return myComponents;
	}
	
	public Collection<BusEvent> getListenedEvents(){
		List<BusEvent> dummyList=new ArrayList<>();
		dummyList.add(new SpriteKilledEvent());
		return dummyList;
	}
	

	public Pair<String, List<String>> getComponentByType(String componentName) {
		for (String c : componentsForTransfer.keySet()) {
			if (c.equals(componentName)) {
				return new Pair<String, List<String>>(c, componentsForTransfer.get(c));
			}
		}
		return null; 
	}
	
	public Component getComponentByType(ComponentType<?> type) {
		for (Component c : myComponents.values()) {
			if (c.getType().equals(type)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Call this AFTER unserialization to avoid the bus issues
	 * @return
	 */
	public Sprite produceSprite(){
		Sprite sprite=new Sprite();
		myComponents.forEach((componentType, component)->{
			sprite.addComponent(component);
		});
//		myScriptMap.forEach((event, script)->{
//			sprite.produceHandler(event.getEventType(), script);
//		});
		return sprite;
	}

}
