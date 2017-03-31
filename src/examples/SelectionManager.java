package examples;



import api.EventBus.EventBus;
import api.Sprite.Sprite;
import javafx.event.EventType;

public class SelectionManager {
	private EventBus bus;
	
	public SelectionManager(EventBus eventBus){
		bus = eventBus;
	}
	/*
	// public Sprite getSelected(); // assume only one is selected
	
	private void initHandlers() {
		bus.on(EventType.ROOT, (e) -> {
			Sprite target = e.getTarget();
			if (target.isLandTile()) {
				getSelected().moveTo(e.getLocation());
			};
			if (s.isTargetable()) {
					getSelected().moveAndAttack(target);				
			}
		});
	};
	*/
}
