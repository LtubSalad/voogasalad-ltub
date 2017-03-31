package examples;

public class ViewDisplayManager {
	EventBus bus;
	
	public ViewDisplayManager(EventBus bus){
		this.bus = bus;
	}
	
	private void initHandlers() {
		bus.on(SpriteAttackedEvent.ANY, e -> { 
		// get the attacked sprite from the event
		// update the health view of the sprite
	});
}
}
