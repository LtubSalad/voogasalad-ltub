package engine.input;

import bus.EventBus;
import commons.Point;
import engine.model.Model;
import engine.player.Player;
import engine.sprite.Sprite;

public class InputManager {
	
	private EventBus bus;
	private Model model;
	private SelectionChecker selectionChecker;
	private ActionManager actionManager;

	public InputManager(EventBus bus, Model model) {
		this.bus = bus;
		this.model = model;
		selectionChecker = new SelectionChecker();
		initHandlers();
	}
	
	public void setActionManager(ActionManager actionManager) {
		this.actionManager = actionManager;
	}

	private void initHandlers() {
		bus.on(MouseClickEvent.ANY, e -> {
			Sprite selected = selectionChecker.getSelection(model, e.getX(), e.getY());
			actionManager.moveSpriteTo(Player.DEFAULT, selected, new Point(e.getX(), e.getY()));
		});
	}
	

//	private void select(double x, double y) {
//		if (selectionChecker.checkSelection(model, x, y)) {
//			System.out.println("The click is in one polygon.");
//		}
//		else {
//			System.out.println("The click is not in any polygon.");
//		}
//	}

}
