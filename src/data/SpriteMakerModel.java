package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bus.BusEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import newengine.events.sprite.SpriteKilledEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteMakerModel {
	// Key Event.TYPE Value Action.TYPE
	private Map<String, String> myCustomEventHandlers;
	private ObservableMap<ComponentType<?>,Component> myComponents;
	private ObservableMap<BusEvent, String> myScriptMap;
	
	public SpriteMakerModel() {
		Map<ComponentType<?>, Component >componentMap=new HashMap<>();
		myComponents=FXCollections.observableMap(componentMap);
		Map<BusEvent, String> handlers=new HashMap<>();
		myScriptMap=FXCollections.observableMap(handlers);
	}
	
	public void addComponent(Component comp) {
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
	

	public Component getComponentByType(ComponentType<?> type) {
		for (Component c : myComponents.values()) {
			if (c.getType().equals(type)) {
				return c;
			}
		}
		return null;
	}
	
	public Sprite produceSprite(){
		return null;
	}

}