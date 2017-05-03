package newengine.view;

import bus.EventBus;
import javafx.scene.Node;
import javafx.scene.control.Label;
import newengine.events.selection.SelectSpriteEvent;
import newengine.sprite.SpriteID;

public class TowersButton {

	private EventBus bus;
	private Label button = new Label("towers");
	
	public TowersButton(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		button.setOnMouseClicked((e) -> {
			bus.emit(new SelectSpriteEvent(SpriteID.TOWER_BUILDER_ID));
		});
	}
	
	public Node getNode() {
		return button;
	}
	

}
