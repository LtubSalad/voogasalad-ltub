package newengine.view;

import bus.EventBus;
import javafx.scene.Node;
import javafx.scene.control.Button;
import newengine.events.selection.SelectSpriteEvent;
import newengine.sprite.SpriteID;

public class TowersButton {

	private EventBus bus;
	private Button button = new Button("towers");
	
	public TowersButton(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		button.setOnMouseClicked((e) -> {
			bus.emit(new SelectSpriteEvent(new SpriteID("__TOWER_BUILDER")));
		});
	}
	
	public Node getNode() {
		return button;
	}
	

}
