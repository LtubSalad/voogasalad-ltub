package eventbus.examples;

import eventbus.Bus;
import javafx.event.EventHandler;

public class Tony {
	private String message = "unreceived";

	private EventHandler<HelloEvent> helloHandler = (e) -> {
		message = e.getMessage();
	};

	public Tony() {
		initEventHandlers();
	}

	public String getMessage() {
		return message;
	}

	private void initEventHandlers() {
		Bus.bus().on(HelloEvent.ANY, helloHandler);
	}

	/**
	 * Called when destroying this object.
	 */
	private void removeEventHandlders() {
		Bus.bus().off(HelloEvent.ANY, helloHandler);
	}

}