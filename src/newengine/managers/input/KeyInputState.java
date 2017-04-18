package newengine.managers.input;

import java.util.HashMap;
import java.util.Map;

import bus.EventBus;
import javafx.scene.input.KeyCode;

public class KeyInputState {
	
	private EventBus bus;
	private Map<KeyCode, Boolean> keysOnMap = new HashMap<>();
	
	public KeyInputState(EventBus bus) {
		this.bus = bus;
	}
	
	public void pressKey(KeyCode keyCode) {
		keysOnMap.put(keyCode, true);
	}
	public void releaseKey(KeyCode keyCode) {
		keysOnMap.put(keyCode, false);
	}
	public boolean isKeyPressed(KeyCode keyCode) {
		return keysOnMap.computeIfAbsent(keyCode, (k) -> false);
	}
	
}
