package engine.input;

import bus.BusEvent;
import bus.BusEventType;
import commons.Point;

public class MouseEvent extends BusEvent {	
	
	public static final BusEventType<MouseEvent> LEFT = new BusEventType<>("LEFT_CLICK");
	public static final BusEventType<MouseEvent> RIGHT = new BusEventType<>("RIGHT_CLICK");
	public static final BusEventType<MouseEvent> DOUBLE_LEFT = new BusEventType<>("DOUBLE_LEFT_CLICK");
	public static final BusEventType<MouseEvent> DOUBLE_RIGHT = new BusEventType<>("DOUBLE_RIGHT_CLICK");
	
	private Point pos;
	
	public MouseEvent(BusEventType<? extends BusEvent> busEventType, Point pos) {
		super(busEventType);
		this.pos = pos;
	}
	
	public Point getPos() {
		return pos;
	}

}
