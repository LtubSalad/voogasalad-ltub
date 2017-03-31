package eventbus.examples;

import javafx.event.Event;
import javafx.event.EventType;

public class HelloEvent extends Event {

	private static final long serialVersionUID = 2117330898588563069L;

	public static final EventType<HelloEvent> ANY = new EventType<>(Event.ANY, "HELLO_EVENT");

	private String message;

	public HelloEvent(EventType<? extends Event> eventType, String message) {
		super(eventType);
		this.message = message;
	}

	public HelloEvent(String message) {
		super(HelloEvent.ANY);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}