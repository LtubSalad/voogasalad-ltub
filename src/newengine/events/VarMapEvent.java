package newengine.events;

import bus.BusEvent;
import bus.BusEventType;
import newengine.utils.variable.VarKey;
import newengine.utils.variable.VarValue;

public class VarMapEvent extends BusEvent{

	public static final BusEventType<VarMapEvent> ADD = new BusEventType<>(
			VarMapEvent.class.getName() + "ADD");
	public static final BusEventType<VarMapEvent> REMOVE = new BusEventType<>(
			VarMapEvent.class.getName() + "REMOVE");
	
	private VarKey key; 
	private VarValue value;	
	
	public VarMapEvent(BusEventType<? extends BusEvent> busEventType, VarKey key, VarValue value) {
		super(busEventType);
		this.key = key;
		this.value = value;
	}

	public VarKey getKey() {
		return key;
	}
	
	public VarValue getValue() {
		return value;
	}
	
}
