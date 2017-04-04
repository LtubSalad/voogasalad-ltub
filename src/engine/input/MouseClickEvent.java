package engine.input;

import bus.BusEvent;
import bus.BusEventType;
import javafx.scene.input.MouseEvent;

public class MouseClickEvent extends BusEvent {	
	
	public static final BusEventType<MouseClickEvent> ANY = new BusEventType<>("MOUSE_CLICK_EVENT");
	
	private double x;
	private double y;

	public MouseClickEvent(MouseEvent mouseEvent) {
		this(ANY, mouseEvent);
	}
	
	public MouseClickEvent(BusEventType<? extends BusEvent> busEventType, MouseEvent mouseEvent) {
		super(busEventType);
		x = mouseEvent.getSceneX();
		y = mouseEvent.getSceneY();
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
