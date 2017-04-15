package newSprite;

import java.util.Collection;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventType;

public abstract class ComponentSprite {
	private final BusEventType<RemoveEvent> JUST_BEFORE_REMOVAL=new BusEventType<>("REMOVED");
	private final BusEventType<InitializationEvent> JUST_AFTER_INITIALIZATION=new BusEventType<>("ADDED");
	
	private int ID;
	private String type;
	private BasicEventBus myBus;
	private BasicEventBus environmentBus;
	private Collection<Component> myComponents;
	
	public ComponentSprite(){
		myBus=new BasicEventBus();
		myBus.emit(new InitializationEvent(JUST_AFTER_INITIALIZATION));
	}
	
	public String getType(){
		return type;
	}
	
	public int getID(){
		return ID;
	}
	
	public void addComponent(Component component){
		myComponents.add(component);
	}
	
	public BasicEventBus getMyBus(){
		return myBus;
	}
	
	public void removeMe(){
		//TODO: tell environment bus to remove me
		myBus.emit(new RemoveEvent(JUST_BEFORE_REMOVAL));
	}
	
	public class InitializationEvent extends BusEvent {
		public InitializationEvent(BusEventType<? extends BusEvent> busEventType) {
			super(busEventType);
		}
	}
	
	public class RemoveEvent extends BusEvent {
		public RemoveEvent(BusEventType<? extends BusEvent> busEventType) {
			super(busEventType);
		}
	}
}
