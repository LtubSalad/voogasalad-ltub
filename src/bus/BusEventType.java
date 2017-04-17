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
	public static final BusEventType<BusEvent> ROOT = new BusEventType<>("ROOT_EVENT", null);

	private final BusEventType<? super T> superType;
	private final String name; // NOTE: name is the only unique identifier for the BusEventType.

//	/**
//	 * By default the super type is BusEventType.ROOT, and the name is null.
//	 */
//	public BusEventType() {
//		this(ROOT, null);
//	}
	
	/**
	 * Pass the event type name for debugging.
	 * 
	 * @param name
	 */
	public BusEventType(String name) {
		this(ROOT, name);
	}

//	public BusEventType(BusEventType<? super T> superType) {
//		this(superType, null);
//	}
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		BusEventType other = (BusEventType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return (name != null) ? name : super.toString();
	}

}