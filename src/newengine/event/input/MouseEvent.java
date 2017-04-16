package newengine.event.input;

import bus.BusEvent;
import bus.BusEventType;
import newengine.utils.point.ViewPoint;

public class MouseEvent extends BusEvent {	
	
	public static final BusEventType<MouseEvent> LEFT = new BusEventType<>("LEFT_CLICK");
	public static final BusEventType<MouseEvent> RIGHT = new BusEventType<>("RIGHT_CLICK");
	public static final BusEventType<MouseEvent> DOUBLE_LEFT = new BusEventType<>("DOUBLE_LEFT_CLICK");
	public static final BusEventType<MouseEvent> DOUBLE_RIGHT = new BusEventType<>("DOUBLE_RIGHT_CLICK");
	public static final BusEventType<MouseEvent> MIDDLE = new BusEventType<>("MIDDLE_CLICK");
	public static final BusEventType<MouseEvent> SCROLL =  new BusEventType<>("MIDDL_SCROLL"); // TODO related to camera
	
	private ViewPoint pos;
	
	public MouseEvent(BusEventType<? extends BusEvent> busEventType, ViewPoint pos) {
		super(busEventType);
		this.pos = pos;
	}
	
	public ViewPoint getPos() {
		return pos;
	}

}
