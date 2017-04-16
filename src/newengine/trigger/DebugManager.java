package newengine.trigger;

import bus.EventBus;

public class DebugManager {

	private EventBus bus;
	
	public DebugManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() { 
		bus.on(SysPrintEvent.ANY, (e) -> {
			System.out.println(e.message());
		});
	}
	
}
