package examples;

import examples.example3.DisplaySelectionViewEvent;

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

		bus.on(DisplaySelectionViewEvent.ANY, e -> { 
			// get the location information from the event e
			// do some actions to display a circle at the location
		});
	}


}
