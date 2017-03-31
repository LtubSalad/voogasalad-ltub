package examples;

import api.Sprite.Sprite;

public class UserInputManager {
	private EventBus bus;
	
	public UserInputManager(EventBus bus){
		this.bus = bus;
	}
	
	private void initHandlers() {
		bus.on(MouseRightClickEvent.ANY, (e) -> {
			Sprite s = e.getSprite();
			if (s.isSelected) { return; }
			bus.emit(new ClickTargetEvent(ClickTargetEvent.ANY, s));
});
	}
}