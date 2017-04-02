package bus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


final class SubscriberRegistry implements Iterable<Subscriber<? extends BusEvent>> {

	private final BasicEventBus bus;
	private final List<Subscriber<? extends BusEvent>> subscribers; // TODO: ??? a more efficient implementation than List
	
	SubscriberRegistry(BasicEventBus bus) {
		this.subscribers = new ArrayList<Subscriber<? extends BusEvent>>();
		this.bus = bus;
	}
	
	<T extends BusEvent> void register(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		subscribers.add(new Subscriber<T>(bus, eventType, eventHandler));
	}
	
	<T extends BusEvent> void unregister(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		Subscriber<? extends BusEvent> subscriberToRemove = null;
		for (Subscriber<? extends BusEvent> subscriber : subscribers) {
			if (subscriber.getBusEventType() == eventType &&
					subscriber.getBusEventHanlder() == eventHandler) {
				subscriberToRemove = subscriber;
				break;
			}
		}
		if (subscriberToRemove != null) {
			subscribers.remove(subscriberToRemove);
		}
	}

	@Override
	public Iterator<Subscriber<? extends BusEvent>> iterator() {
		return subscribers.iterator();
	}
	
}
