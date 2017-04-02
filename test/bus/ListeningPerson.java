package bus;

public class ListeningPerson {

	private EventBus bus;
	private String msgStored = "unreceived";
	private int numHandlersCalled = 0;
	
	
	public ListeningPerson(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	public String getMsgStored() {
		return msgStored;
	}
	
	public int getNumHandlersCalled() {
		return numHandlersCalled;
	}
	
	private final BusEventHandler<MsgEvent> msgHandler = (e) -> {
		msgStored = e.getMsg();
		numHandlersCalled += 1;
	};
	private final BusEventHandler<CoolMsgEvent> coolMsgHandler = (e) -> {
		msgStored = "CoolReceived" + e.getMsg();
		numHandlersCalled += 1;
	};
	
	private void initHandlers() {
		// NOTE: 
		// In real application, be careful when defining handlers on
		// an event type and its sub event type at the same time.
		// Here if CoolMsgEvent.ANY has superType MsgEvent.ANY, 
		// then both handlers will be called with CoolMsgEvent.ANY,
		// and only the second handler will be called with MsgEvent.ANY.
		bus.on(MsgEvent.ANY, msgHandler);
		bus.on(CoolMsgEvent.ANY, coolMsgHandler);
	}
	
	public BusEventHandler<MsgEvent> getMsgHandler() { return msgHandler; }
	public BusEventHandler<CoolMsgEvent> getCoolMsgHandler() { return coolMsgHandler; }
	
}
