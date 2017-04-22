package newengine.events.camera;

import bus.BusEvent;
import bus.BusEventType;

public class CameraEvent extends BusEvent{

	public static final BusEventType<CameraEvent> ZOOM = new BusEventType<>(
			CameraEvent.class.getName() + "ZOOM");
	public static final BusEventType<CameraEvent> MOVE = new BusEventType<>(
			CameraEvent.class.getName() + "MOVE");
	
	private double scaleFactor;
	private double translateXValue;
	private double translateYValue;
	
	private CameraEvent(BusEventType<? extends BusEvent> busEventType, double scaleFactor, double translateXValue, double translateYValue) {
		super(busEventType);
		this.scaleFactor = scaleFactor;
		this.translateXValue = translateXValue;
		this.translateYValue = translateYValue;
	}
	
	public CameraEvent(double translateXValue, double translateYValue) {
		this(MOVE, 0, translateXValue, translateYValue);
	}
	
	public CameraEvent(double scaleFactor) {
		this(ZOOM, scaleFactor, 0, 0);
	}
	
	public double getZoomFactor() {
		return scaleFactor;
	}
	
	public double getTranslateXValue() {
		return translateXValue;
	}
	
	public double getTranslateYValue() {
		return translateYValue;
	}
}
