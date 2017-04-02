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
	
	private void initHandlers() {
		bus.on(MsgEvent.ANY, (e) -> {
			msgStored = e.getMsg();
			numHandlersCalled += 1;
		});
		bus.on(CoolMsgEvent.ANY, (e) -> {
			msgStored = "CoolReceived" + e.getMsg();
			numHandlersCalled += 1;
		});
	}
	
}
