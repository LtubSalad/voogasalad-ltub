package examples;

import java.util.List;

import api.Sprite.Sprite;
import javafx.event.EventType;

public class SelectionManager {
	private EventBus bus;
	private List<Sprite> spriteList;
	
	public SelectionManager(EventBus eventBus){
		bus = eventBus;
	}
	
	// assume only one is selected for this example
	public Sprite getSelected(){
		return spriteList.get(0);
	} 
	
	private void initHandlers() {
		bus.on(ClickTargetEvent.ANY, (e) -> {
			Sprite target = e.getSprite();
			if (target.isLandTile()) {
				getSelected().move(e.getLocation());
			};
			if (target.isTargetable()) {
					getSelected().attack(target);				
			}
		});
	};
	
}
