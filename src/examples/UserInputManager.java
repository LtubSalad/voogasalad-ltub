package examples;

import api.Sprite.Sprite;
import examples.example3.DisplaySelectionViewEvent;
import javafx.event.Event;
import javafx.event.EventType;

public class UserInputManager {
	private EventBus bus;

	public UserInputManager(EventBus bus){
		this.bus = bus;
	}

	private void initHandlers() {
		bus.on(MouseRightClickEvent.ANY, (e) -> {
			Sprite s = e.getSprite();
			if (s.isSelected) { return; }
			bus.emit(new ClickTargetEvent(ClickTargetEvent.ANY, s));
		});

		bus.on(MouseRightClickEvent.ANY, (e) -> {
			bus.emit(new DisplaySelectionViewEvent(e.getLocation()));
			// different event handler from the previous use case
		});
	}
}