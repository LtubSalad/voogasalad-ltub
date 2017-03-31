package eventbus;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;

public class EventBus implements IEventBus {
	// used as the source and target of all events
	private Group group = new Group();

	@Override
	public <T extends Event> void on(EventType<T> eventType, EventHandler<? super T> eventHandler) {
		group.addEventHandler(eventType, eventHandler);
	}

	@Override
	public <T extends Event> void off(EventType<T> eventType, EventHandler<? super T> eventHandler) {
		group.removeEventHandler(eventType, eventHandler);
	}

	@Override
	public void emit(Event event) {
		group.fireEvent(event);
	}
}