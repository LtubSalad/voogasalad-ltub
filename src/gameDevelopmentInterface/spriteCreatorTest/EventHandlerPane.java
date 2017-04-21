package gameDevelopmentInterface.spriteCreatorTest;

import java.util.HashMap;
import java.util.Map;

import bus.BusEvent;
import javafx.scene.layout.VBox;
import newengine.sprite.Sprite;

public class EventHandlerPane extends VBox {
	private Map<BusEvent, String> myAddedEventHandlers;
	private Sprite mySprite;
	
	public EventHandlerPane() {
		myAddedEventHandlers = new HashMap<BusEvent, String>();
		mySprite = new Sprite();
	}
	
	public void add(BusEvent event) {
		String stringVersionOfHandler = "";
		myAddedEventHandlers.put(event, stringVersionOfHandler);
	}
	
	public Map<BusEvent, String> getAddedEventHandlers() {
		return null;
	}

}
