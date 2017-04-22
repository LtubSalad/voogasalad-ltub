package newengine.events.camera;

import bus.BusEvent;
import bus.BusEventType;

public class CameraEvent extends BusEvent{

	public static final BusEventType<CameraEvent> ZOOM = new BusEventType<>(
			CameraEvent.class.getName() + "ZOOM");
	public static final BusEventType<CameraEvent> MOVE = new BusEventType<>(
			CameraEvent.class.getName() + "MOVE");
	
	private double x;
	private double y;
	private double scaleFactor;
	private double translateXValue;
	private double translateYValue;
	
	private CameraEvent(BusEventType<? extends BusEvent> busEventType, double x, double y, 
			double scaleFactor, double translateXValue, double translateYValue) {
		super(busEventType);
		this.x = x;
		this.y = y;
		this.scaleFactor = scaleFactor;
		this.translateXValue = translateXValue;
		this.translateYValue = translateYValue;
	}

	public static CameraEvent createScaleCameraEvent(double x, double y, double scaleFactor) {
		return new CameraEvent(CameraEvent.ZOOM, x, y, scaleFactor, 0, 0);
	}
	
	public static CameraEvent createTranslateCameraEvent(double x, double y, double translateXValue, double translateYValue) {
		return new CameraEvent(CameraEvent.MOVE, x, y, 1, translateXValue, translateYValue);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
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
