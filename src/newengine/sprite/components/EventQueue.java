package newengine.sprite.components;
import java.io.Serializable;
import java.util.LinkedList;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import bus.BusEvent;
import bus.BusEventHandler;
import helperAnnotations.ConstructorForDeveloper;
import newengine.events.QueueEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
public class EventQueue extends Component {
	
	public static final ComponentType<EventQueue> TYPE = new ComponentType<>(EventQueue.class.getName());
	private LinkedList<BusEvent> events = new LinkedList<>();
	private boolean eventFinished = true;
	
	@ConstructorForDeveloper
	public EventQueue(){
		this(new LinkedList<BusEvent>());
	}
	
	public EventQueue(LinkedList<BusEvent> events) {
		this.events = events;
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
	public boolean isEmpty() {
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
		return new EventQueue((LinkedList<BusEvent>) events.clone());
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return null;
	}
}