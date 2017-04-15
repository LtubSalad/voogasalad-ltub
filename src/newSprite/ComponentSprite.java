package newSprite;

import java.util.Collection;
import java.util.Map;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;

/**
 * 
 * @author Daniel
 * A new version of the Sprite (still in progress). Stores a bunch of components, (which are similar to attributes
 * but for their ability to handle and fire events). Every ComponentSprite also handles events concerning 
 * instantiation and removal.
 * Note the nested classes. The ComponentSprite stores nested classes for events, but also the abstract Component class.
 * Hence every component has access to the 
 *
 */
public abstract class ComponentSprite {
	private final BusEventType<RemoveEvent> JUST_BEFORE_REMOVAL=new BusEventType<>("REMOVED");
	private final BusEventType<InitializationEvent> JUST_AFTER_INITIALIZATION=new BusEventType<>("ADDED");
	
	private int ID; //I'm keeping a distinction between ID and type,
	private String type;
	private BasicEventBus myBus;
	private BasicEventBus environmentBus;
	private Collection<Component<? extends BusEvent>> myComponents;
	
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
	
	public void addComponent(Component<? extends BusEvent> component){
		myComponents.add(component);
	}
	
	public BasicEventBus getMyBus(){
		return myBus;
	}
	
	public void removeMe(){
		myBus.emit(new RemoveEvent(JUST_BEFORE_REMOVAL));
		//TODO: tell environment bus to remove me
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
	
	public abstract class Component<T extends BusEvent> {
		private BasicEventBus myBus;
		
		public ComponentSprite getSprite(){
			return ComponentSprite.this;
		}
		
		public abstract Map<T,BusEventHandler<T>> getHandlers();
		
		protected BasicEventBus getBus(){
			return myBus;
		}		
	}
}
