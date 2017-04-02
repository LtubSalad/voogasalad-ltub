package bus;

public class MsgEvent extends BusEvent {

	public static final BusEventType<MsgEvent> ANY = new BusEventType<>("MSG_EVENT_ANY");
	public static final BusEventType<MsgEvent> E_MSG1 = new BusEventType<>("MSG_EVENT_MSG1");
	
	// NOTE: if the constructor if specified correctly, 
	// then trying to create a new event with a wrong type 
	// (not self or child type) will result in compile error.
	public static final BusEventType<BusEvent> E_BUS = new BusEventType<>("MSG_EVENT_BUS"); // WRONG!
	
	private String msg;
	
	public MsgEvent(String msg) {
		super(MsgEvent.ANY);
		this.msg = msg;
	}
	
	// to make type of MsgEvent non-extensible, just use BusEventType<MsgEvent>
	public MsgEvent(BusEventType<? extends MsgEvent> eventType, String msg) {
		super(eventType);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
