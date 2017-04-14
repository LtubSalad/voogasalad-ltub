package bus;

/**
 * An event class to be used with the EventBus. Extend BusEvent to define
 * different kinds of events which could hold different kinds of data. Ideally
 * the events only hold data, while all actions are done by the handler.
 * 
 * Note that these {@code BusEvent}s are separate from the JavaFX events,
 * and DO NOT RELY ON JAVAFX Event, EventType, or EventHandler. But the usage 
 * is similar to JavaFX event system (except that all events are shared on a bus).
 * 
 * Here is the sample code for creating a MsgEvent that can store a String message.
 * <pre>
 * <code>
 * public class MsgEvent extends BusEvent {
 *     // different handlers can be defined for different "minor" event types.
 *     public static final {@code BusEvenType<MsgEvent>} ANY = new BusEventType<>("MSG_EVENT_ANY");
 *     public static final {@code BusEvenType<MsgEvent>} OTHER = new BusEventType<>("MSG_EVENT_OTHER");
 *     
 *     private String msg;
 *     
 *     public MsgEvent(String msg) {
 *         this(MsgEvent.ANY, msg);
 *     }
 *     public MsgEvent({@code BusEventType<? extends MsgEvent>} eventType, String msg) {
 *         super(eventType);
 *         this.msg = msg;
 *     }
 *     
 *     public String getMsg() { return msg; }
 * }
 * </code>
 * </pre>
 * 
 * @author keping
 *
 */
public class BusEvent {
	public static final BusEventType<BusEvent> ANY = new BusEventType<>("ROOT");

	private BusEventType<? extends BusEvent> busEventType;

	public BusEvent(BusEventType<? extends BusEvent> busEventType) {
		this.busEventType = busEventType;
	}
	
	public BusEventType<? extends BusEvent> getEventType() {
		return busEventType;
	}
}
