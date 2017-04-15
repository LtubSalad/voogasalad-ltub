package newengine.event;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

public class UpdateEvent extends BusEvent{


	public static final BusEventType<UpdateEvent> TYPE = new BusEventType<>();
	
	private Sprite sprite;
	private double dt;

	public UpdateEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, double dt) {
		super(busEventType);
		this.sprite = sprite;
		this.dt = dt;
	}	
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public double getDT() {
		return dt;
	}
}
