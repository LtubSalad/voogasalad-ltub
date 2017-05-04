package newengine.view;

import bus.EventBus;
import javafx.scene.Node;
import javafx.scene.control.Button;
import newengine.events.selection.SelectSpriteEvent;
import newengine.sprite.SpriteID;

public class TowersButton {

	private static final String SHOW_TOWERS = "Show Towers";
	private EventBus bus;
	private Button button = new Button(SHOW_TOWERS);
	
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
