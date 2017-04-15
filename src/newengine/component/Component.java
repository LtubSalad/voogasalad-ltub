package newengine.component;

import newengine.sprite.Sprite;

public abstract class Component {

	public void onAdded(Sprite sprite) {
		
	}
	
	public void onUpdated(double dt) {
		
	}
	
	public void onRemoved() {
		
	}

	public abstract ComponentType<? extends Component> getType();
}
