package newSprite;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
/**
 * 
 * @author Daniel
 * 
 */

public abstract class Component<T extends BusEvent> {
	private BasicEventBus myBus;
	private ComponentSprite mySprite;
	
	
	public Component(){
		myBus=new BasicEventBus();
	}
	
	public Component(ComponentSprite sprite){
		this();
		mySprite=sprite;
	}
	
	public ComponentSprite getSprite(){
		return mySprite;
	}
	
	public abstract Map<T,BusEventHandler<T>> getHandlers();
	
	protected BasicEventBus getBus(){
		return myBus;
	}
	
}
