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

	// person is registered to listen to "MsgEvent.ANY" and "CoolMsgEvent.ANY"
	
	@Test
	public void testEmitReceive() {
		ListeningPerson person = new ListeningPerson(bus);
		String msgToSend = "Hello_haha";
		bus.emit(new MsgEvent(MsgEvent.ANY, msgToSend));
		assertEquals(msgToSend, person.getMsgStored());
		assertEquals(1, person.getNumHandlersCalled());
	}
	
	@Test
	public void testEmitReceiveMultiplePersons() {
		ListeningPerson p1 = new ListeningPerson(bus);
		ListeningPerson p2 = new ListeningPerson(bus);
		String msgToSend = "Hello_haha";
		bus.emit(new MsgEvent(MsgEvent.ANY, msgToSend));
		assertEquals(msgToSend, p1.getMsgStored());
		assertEquals(msgToSend, p2.getMsgStored());
		assertEquals(1, p1.getNumHandlersCalled());
		assertEquals(1, p2.getNumHandlersCalled());
	}
	
	@Test
	public void testDifferentEventType1() {
		ListeningPerson person = new ListeningPerson(bus);
		String originStoredMsg = person.getMsgStored();
		String msgToSend = "Hello_haha";
		bus.emit(new MsgEvent(MsgEvent.E_MSG1, msgToSend));
		assertEquals(originStoredMsg, person.getMsgStored());
	}
	
	@Test
	public void testSubEventType1() {
		ListeningPerson person = new ListeningPerson(bus);
		String msgToSend = "Hello_haha";
		// Note: the handler for CoolMsgEvent.ANY is called only when
		// both the constructor and the argument are the right type.
		bus.emit(new MsgEvent(CoolMsgEvent.ANY, msgToSend));
		assertEquals(1, person.getNumHandlersCalled());
		assertEquals(msgToSend, person.getMsgStored());
	}

	@Test
	public void testSubEventType2() {
		ListeningPerson person = new ListeningPerson(bus);
		String msgToSend = "Hello_haha";
		bus.emit(new CoolMsgEvent(CoolMsgEvent.ANY, msgToSend));
		assertEquals(2, person.getNumHandlersCalled());
		assertEquals("CoolReceivedCool"+msgToSend, person.getMsgStored());
	}
	
	@Test
	public void testOff1() {
		ListeningPerson person = new ListeningPerson(bus);
		String originStoredMsg = person.getMsgStored();
		bus.off(MsgEvent.ANY, person.getMsgHandler());
		String msgToSend = "Hello_haha";
		bus.emit(new MsgEvent(MsgEvent.ANY, msgToSend));
		assertEquals(originStoredMsg, person.getMsgStored());
	}
	
	@Test
	public void testOff2() {
		ListeningPerson person = new ListeningPerson(bus);
		bus.off(CoolMsgEvent.ANY, person.getMsgHandler());
		String msgToSend = "Hello_haha";
		bus.emit(new MsgEvent(MsgEvent.ANY, msgToSend));
		assertEquals(msgToSend, person.getMsgStored());
	}
	
}
