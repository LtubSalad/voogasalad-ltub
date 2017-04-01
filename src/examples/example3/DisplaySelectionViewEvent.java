package examples.example3;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;

public class DisplaySelectionViewEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2989410279153830499L;
	public static final EventType<DisplaySelectionViewEvent> ANY = new EventType<>(Event.ANY, "DISPLAY_EVENT");
	
	public DisplaySelectionViewEvent(Point2D d) {
		super(DisplaySelectionViewEvent.ANY);
		// TODO Auto-generated constructor stub
	}
	

}
