package newengine.control;

import newengine.sprite.Sprite;

public abstract class Control {
	
	public void onAdded(Sprite sprite) {
		
	}
	
	public void onUpdated(double dt) {
		
	}
	
	public void onRemoved() {
		
	}

	public ControlType<? extends Control> getType() {
		return null;
	}

}
