package bus;


public class BusEvent {

	/**
	 * common super type for all busEventTypes.
	 */
	public static final BusEventType<BusEvent> ROOT = new BusEventType<>("ROOT");
	
	protected BusEventType<? extends BusEvent> busEventType;
	
    public BusEvent(BusEventType<? extends BusEvent> busEventType) {
        this.busEventType = busEventType;
    }
	
	public BusEventType<? extends BusEvent> getEventType() {
		return busEventType;
	}
	
}
