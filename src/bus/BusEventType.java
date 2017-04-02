package bus;

/**
 * Denote types of BusEvent. Refer to {@link BusEvent} for 
 * sample usage. Different from JavaFX EventType, but similar usage.
 * @author keping
 *
 * @param <T>
 */
public final class BusEventType<T extends BusEvent> {

	/**
	 * common super type for all busEventTypes.
	 */
	public static final BusEventType<BusEvent> ROOT = new BusEventType<>("EVENT", null);

	private final BusEventType<? super T> superType;
	private final String name; // only for debugging.

	/**
	 * By default the super type is BusEventType.ROOT, and the name is null.
	 */
	public BusEventType() {
		this(ROOT, null);
	}
	
	/**
	 * Pass the event type name for debugging.
	 * 
	 * @param name
	 */
	public BusEventType(String name) {
		this(ROOT, name);
	}

	public BusEventType(BusEventType<? super T> superType) {
		this(superType, null);
	}
	
	public BusEventType(BusEventType<? super T> superType, String name) {
		if (superType == null) {
            throw new NullPointerException(
					"Event super type must not be null!");
		}
		
        this.superType = superType;
        this.name = name;
	}
	
	/**
	 * Internal constructor without checking.
	 * @param name
	 * @param superType
	 */
	BusEventType(String name, BusEventType<? super T> superType) {
		this.name = name;
		this.superType = superType;
	}
	

	public final BusEventType<? super T> getSuperType() {
		return superType;
	}

	@Override
	public String toString() {
		return (name != null) ? name : super.toString();
	}

}