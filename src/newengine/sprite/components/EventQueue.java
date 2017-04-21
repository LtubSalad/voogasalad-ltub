package newengine.sprite.components;

import java.util.LinkedList;

import bus.BusEvent;
import helperAnnotations.ConstructorForDeveloper;
import newengine.events.QueueEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class EventQueue extends Component {
	
	public static final ComponentType<EventQueue> TYPE = new ComponentType<>(EventQueue.class.getName());
	private LinkedList<BusEvent> events = new LinkedList<>();
	private boolean eventFinished = true;
	
	public EventQueue() {
		
	}
	
	@Override
	public void afterAdded() {
		sprite.on(QueueEvent.ADD, (e) -> {
			addEvent(e.getEvent());
		});
		sprite.on(QueueEvent.NEXT, (e) -> {
			eventFinished = true;
		});
	}
	
	@Override
	public void onUpdated(double dt) {
		if (true == eventFinished) {
			emitNextEvent();
		}
	}

	private void addEvent(BusEvent event) {
		events.addLast(event);
	}

	private boolean isEmpty() {
		return events.isEmpty();
	}
	
	// TODO
	private void clearQueue() {
		events.clear();
	}
	
	private void emitNextEvent() {
		if (!isEmpty()) {
			eventFinished = false;
			sprite.getSpriteBus().emit(events.removeFirst());
		}
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	@Override
	public EventQueue clone() {
		return new EventQueue();
	}
}
