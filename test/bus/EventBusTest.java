package bus;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class EventBusTest {
	
	private EventBus bus;
	
	@Before
	public void init() {
		bus = new BasicEventBus();
	}

	// person is registered to listen to "MsgEvent.ANY"
	
//	@Test
//	public void testEmitReceive() {
//		ListeningPerson person = new ListeningPerson(bus);
//		String msgToSend = "Hello_haha";
//		bus.emit(new MsgEvent(MsgEvent.ANY, msgToSend));
//		assertEquals(msgToSend, person.getMsgStored());
//		assertEquals(1, person.getNumHandlersCalled());
//	}
//	
//	@Test
//	public void testEmitReceiveMultiplePersons() {
//		ListeningPerson p1 = new ListeningPerson(bus);
//		ListeningPerson p2 = new ListeningPerson(bus);
//		String msgToSend = "Hello_haha";
//		bus.emit(new MsgEvent(MsgEvent.ANY, msgToSend));
//		assertEquals(msgToSend, p1.getMsgStored());
//		assertEquals(msgToSend, p2.getMsgStored());
//		assertEquals(1, p1.getNumHandlersCalled());
//		assertEquals(1, p2.getNumHandlersCalled());
//	}
//	
//	@Test
//	public void testDifferentEventType1() {
//		ListeningPerson person = new ListeningPerson(bus);
//		String msgToSend = "Hello_haha";
//		bus.emit(new MsgEvent(MsgEvent.E_MSG1, msgToSend));
//		assertNotEquals(msgToSend, person.getMsgStored());
//	}
//	
	@Test
	public void testSubEventTypes() {
		ListeningPerson person = new ListeningPerson(bus);
		String msgToSend = "Hello_haha";
		bus.emit(new MsgEvent(CoolMsgEvent.ANY, msgToSend));
		assertEquals(1, person.getNumHandlersCalled());
		assertEquals(msgToSend, person.getMsgStored());
	}

	
	
}
