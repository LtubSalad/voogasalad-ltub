package engine.input;

import bus.EventBus;
import engine.model.Model;
import engine.sprite.Sprite;

public class InputManager {
	
	private EventBus bus;
	private Model model;
	private SelectionChecker selectionChecker;

	public InputManager(EventBus bus, Model model) {
		this.bus = bus;
		this.model = model;
		selectionChecker = new SelectionChecker();
		initHandlers();
	}

	private void initHandlers() {
		bus.on(MouseClickEvent.ANY, e -> {
			Sprite selected = selectionChecker.getSelection(model, e.getX(), e.getY());
			selected.getMovable().ifPresent((movable) -> {
				movable.moveTo(e.getX(), e.getY());
			});
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
