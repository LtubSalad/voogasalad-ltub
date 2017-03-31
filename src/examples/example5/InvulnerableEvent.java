package examples.example5;

import javafx.event.Event;
import javafx.event.EventType;

public class InvulnerableEvent extends Event {

	private static final long serialVersionUID = 4416853848424429831L;
	
	public static final EventType<InvulnerableEvent> ANY = new EventType<>(Event.ANY, "InvulEvent");
	
	public InvulnerableEvent() {
		super(InvulnerableEvent.ANY);
	}

	
}
