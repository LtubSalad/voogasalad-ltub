package engine.input;

import engine.model.Model;
import engine.sprite.Sprite;

public class SelectionChecker {
	

	public SelectionChecker() {
		
	}
	
	public Sprite getSelection(Model model, double x, double y) {
		return model.getSprites().get(0); // TODO
	}

	private void rayCasting() {
		
	}
}
