package newengine.events.input;

import bus.BusEvent;
import bus.BusEventType;
import commons.point.GamePoint;

public class MouseEvent extends BusEvent {	
	
	public static final BusEventType<MouseEvent> LEFT = new BusEventType<>("LEFT_CLICK");
	public static final BusEventType<MouseEvent> RIGHT = new BusEventType<>("RIGHT_CLICK");
	public static final BusEventType<MouseEvent> DOUBLE_LEFT = new BusEventType<>("DOUBLE_LEFT_CLICK");
	public static final BusEventType<MouseEvent> DOUBLE_RIGHT = new BusEventType<>("DOUBLE_RIGHT_CLICK");
	public static final BusEventType<MouseEvent> MIDDLE = new BusEventType<>("MIDDLE_CLICK");
	public static final BusEventType<MouseEvent> SCROLL =  new BusEventType<>("MIDDL_SCROLL"); // TODO related to camera
	
	private GamePoint pos;
	
	public MouseEvent(BusEventType<? extends BusEvent> busEventType, GamePoint pos) {
		super(busEventType);
		this.pos = pos;
	}
	
	public GamePoint getPos() {
		return pos;
	}

}
