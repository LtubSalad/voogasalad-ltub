package bus;

import java.io.Serializable;

public final class BusEventType<T extends BusEvent> implements Serializable {

	private static final long serialVersionUID = -4978911606283544346L;
	
	// only for debugging.
	private final String name;
	
	public BusEventType() {
		this.name = "UNNAMED_EVENT";
	}
	
	/**
	 * Pass the event type name for debugging.
	 * @param name
	 */
	public BusEventType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return (name != null) ? name : super.toString();
	}
	
}