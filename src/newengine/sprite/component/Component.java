package newengine.sprite.component;

import newengine.sprite.Sprite;

public abstract class Component {

	protected Sprite sprite;
	
	public final void onAdded(Sprite sprite) {
		this.sprite = sprite;
		initSettings();
		initHandlers();
	}
	
	protected void initSettings() {
		
	}
	
	protected void initHandlers() {
		
	}
	
	public void onUpdated(double dt) {
		
	}
	
	public void onRemoved() {
		
	}

	public abstract ComponentType<? extends Component> getType();
	
}
