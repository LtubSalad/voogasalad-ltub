package newengine.event.input;

import bus.BusEvent;
import bus.BusEventType;
import javafx.scene.input.KeyCode;

public class KeyEvent extends BusEvent {

	public static final BusEventType<KeyEvent> PRESS = new BusEventType<>("PRESS_KEY");
	public static final BusEventType<KeyEvent> RELEASE = new BusEventType<>("RELEASE_KEY");
	public static final BusEventType<KeyEvent> TYPE = new BusEventType<>("TYPE_KEY"); 
	
	private final KeyCode keyCode;
	
	public KeyEvent(BusEventType<? extends BusEvent> busEventType, KeyCode keyCode) {
		super(busEventType);
		this.keyCode = keyCode;
	}
	
	public KeyCode getCode() {
		return keyCode;
	}

	
	
}
