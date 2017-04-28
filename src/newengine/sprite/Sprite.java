package newengine.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import helperAnnotations.DeveloperMethod;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Sprite {
	private EventBus spriteBus = new BasicEventBus();
	private SpriteID spriteID = IDGenerator.generateID();
	private Map<ComponentType<? extends Component>, Component> components = new HashMap<>();
	// TODO: is dis ok for serialization 
	private final ScriptEngine scriptHandler = new ScriptEngineManager().getEngineByName("groovy");

	public EventBus getSpriteBus() {
		return spriteBus;
	}

	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		spriteBus.on(eventType, eventHandler);
	}
	
	@DeveloperMethod
	public void emit(BusEvent event) {
		spriteBus.emit(event);
	}

	public SpriteID getID() {
		return spriteID;
	}

	public void addComponent(Component component) {
		if (component == null) {
			System.out.println("component is null");
		}
		components.put(component.getType(), component);
		component.onAdded(this);
	}

	@SuppressWarnings("unchecked")
	public <T extends Component> Optional<T> getComponent(ComponentType<T> type) {
		return Optional.ofNullable((T) components.get(type));
	}

	public <T extends Component> void removeComponent(ComponentType<T> type) {
		Component component = components.get(type);
		component.beforeRemoved();
		components.remove(type);
	}

	public <T extends Component> boolean hasComponent(ComponentType<T> type) {
		return components.containsKey(type);
	}	
	
	/**
	 * To be called in each frame.
	 * 
	 * @param dt
	 */
	@DeveloperMethod
	public void update(double dt) {
		for (Component component : components.values()) {
			component.onUpdated(dt);
		}
	}

	@Override
	public Sprite clone() {
		Sprite newSprite = new Sprite();
		for (Component component : new ArrayList<Component>(components.values())) {
			Component newComponent = component.clone();
			newSprite.addComponent(newComponent);
		}
		return newSprite;
	}

	@Override
	public String toString() {
		return "sprite(" + spriteID + ")";
	}

//	public <T extends BusEvent> void produceHandler(BusEventType<T> eventType, String script) {
//		BusEventHandler<T> newHandler;
//		
//		newHandler = (onFired) -> {
//			try { 
//				scriptHandler.eval(script);
//			} catch (ScriptException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		};
//		on(eventType, newHandler);
//	}
	
	public ScriptEngine getScriptHandler(){
		return scriptHandler; 
	}

}
