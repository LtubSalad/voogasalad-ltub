package bus;


// test for event extension
public class CoolMsgEvent extends MsgEvent {

	// To extends event, must specify super event type.
	public static final BusEventType<CoolMsgEvent> ANY = new BusEventType<>(MsgEvent.ANY, "COOL_MSG_EVENT");

	private String msg;
	
	public CoolMsgEvent(BusEventType<CoolMsgEvent> eventType, String msg) {
		super(eventType, msg);
		this.msg = "Cool"+msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
