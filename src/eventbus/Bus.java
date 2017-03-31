package eventbus;

public class Bus {
	private static final EventBus bus = new EventBus();

	private Bus() {
	}

	public static EventBus bus() {
		return bus;
	}

}