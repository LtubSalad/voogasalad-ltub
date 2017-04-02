package bus;


// test for event extension
public class CoolMsgEvent extends MsgEvent {

	public static final BusEventType<CoolMsgEvent> ANY = new BusEventType<>("COOL_MSG_EVENT");

	
	private String msg;
	
	public CoolMsgEvent(BusEventType<CoolMsgEvent> eventType, String msg) {
		super(eventType, msg);
		this.msg = "Cool"+msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
