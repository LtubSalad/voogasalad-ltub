package newengine.events.camera;

import bus.BusEvent;
import bus.BusEventType;

public class CameraEvent extends BusEvent{

	public static final BusEventType<CameraEvent> ZOOM = new BusEventType<>(
			CameraEvent.class.getName() + "ZOOM");
	public static final BusEventType<CameraEvent> MOVE = new BusEventType<>(
			CameraEvent.class.getName() + "MOVE");
	public static final BusEventType<CameraEvent> RESET = new BusEventType<>(
			CameraEvent.class.getName() + "RESET");
	public static final BusEventType<CameraEvent> FOCUS = new BusEventType<>(
			CameraEvent.class.getName() + "FOCUS");
	public static final BusEventType<CameraEvent> LOCK = new BusEventType<>(
			CameraEvent.class.getName() + "LOCK");
	
	private double scaleFactor;
	private double translateXValue;
	private double translateYValue;
	
	private CameraEvent(BusEventType<? extends BusEvent> busEventType, double scaleFactor, double translateXValue, double translateYValue) {
		super(busEventType);
		this.scaleFactor = scaleFactor;
		this.translateXValue = translateXValue;
		this.translateYValue = translateYValue;
	}
	
	/**
	 * Constructor for {@code CameraEvent.MOVE}
	 * @param busEventType
	 * @param translateXValue
	 * @param translateYValue
	 */
	public CameraEvent(BusEventType<? extends BusEvent> busEventType, double translateXValue, double translateYValue) {
		this(busEventType, 1, translateXValue, translateYValue);
	}
	
	/**
	 * Constructor for {@code CameraEvent.ZOOM}
	 * @param busEventType
	 * @param scaleFactor
	 */
	public CameraEvent(BusEventType<? extends BusEvent> busEventType, double scaleFactor) {
		this(busEventType, scaleFactor, 0, 0);
	}
	
	/**
	 * Constructor for {@code CameraEvent.RESET}
	 * @param busEventType
	 */
	public CameraEvent(BusEventType<? extends BusEvent> busEventType) {
		this(busEventType, 1, 0, 0);
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
