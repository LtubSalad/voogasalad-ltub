package examples.example3;

import eventbus.EventBus;
import javafx.event.EventType;

public class UserInputManager {
	
	EventBus bus;
	
	private void initHandlers() {
		EventType eventType;
		
		bus.getBus().on(eventType, (e) -> {
			Sprite s = e.getSprite();
			getBus().emit(new DisplaySelectionViewEvent(s, e.getLocation));
			// different event handler from the previous use case
		});
	}

}
