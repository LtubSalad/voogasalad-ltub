package examples.example5;

import javafx.event.Event;
import javafx.event.EventType;

public class UserSendMessageEvent extends Event {

	private static final long serialVersionUID = -3982301660838841339L;
	public static final EventType<UserSendMessageEvent> RAW = new EventType<>(Event.ANY, "SendMsgEvent");
	public static final EventType<UserSendMessageEvent> FILTERED = new EventType<>(Event.ANY, "SendMsgEvent");
	
	private String message;
	
	public UserSendMessageEvent(EventType<? extends Event> eventType, String message) {
		super(eventType);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
