package newengine.sprite;

import java.util.HashMap;
import java.util.Map;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;

public class Sprite {
	
	public Sprite() {
		
	}
	
	private EventBus spriteBus = new BasicEventBus();
	
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		spriteBus.on(eventType, eventHandler);
	}
	
	public void emit(BusEvent event) {
		spriteBus.emit(event);
	}

	private Map<AttributeType<? extends Attribute>, Attribute> attributeMap = new HashMap<>();
	
	public void addAttribute(Attribute attribute) {
		attributeMap.put(attribute.getType(), attribute);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Attribute> T getAttribute(AttributeType<T> type) {
		return (T) attributeMap.get(type);
	}
	
	public <T extends Attribute> void removeAttribute(AttributeType<T> type) {
		attributeMap.remove(type);
	}
	
	public <T extends Attribute> boolean hasAttribute(AttributeType<T> type) {
		return attributeMap.containsKey(type);
	}
	
	private Map<ControlType<? extends Control>, Control> controlMap = new HashMap<>();
	
	public void addControl(Control control) {
		controlMap.put(control.getType(), control);
		control.onAdded(this);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Control> T getControl(ControlType<T> type) {
		return (T) controlMap.get(type);
	}
	
	public <T extends Control> void removeControl(ControlType<T> type) {
		Control control = controlMap.get(type);
		controlMap.remove(type);
		control.onRemoved();
	}
	
	public <T extends Control> boolean hasControl(ControlType<T> type) {
		return controlMap.containsKey(type);
	}
	
	public void update(double dt) {
		for (Control control: controlMap.values()) {
			control.onUpdated(dt);
		}
	}
	
}
