package newSprite;

import java.util.List;

import bus.BasicEventBus;
import bus.BusEvent;
/**
 * 
 * @author Daniel
 * 
 */

public abstract class Component {
	private String myString;
	private List<BusEvent> myEvents;
	private BasicEventBus myBus;
	private ComponentSprite mySprite;
	
	public Component(ComponentSprite sprite){
		mySprite=sprite;
		myBus=new BasicEventBus();
	}
	
	public ComponentSprite getSprite(){
		return mySprite;
	}
	
	public String getType(){
		return myString;
	}
	
	/**
	 * Use an enum to replace this later, but I don't know how
	 * @param eventNum
	 * @param m
	 */
	public BasicEventBus getBus(){
		return myBus;
	}
	
}
