package newengine.sprite;

public abstract class Control {
	
	public abstract void onAdded(Sprite sprite);
	
	public abstract void onUpdated(double dt);
	
	public abstract void onRemoved();

	public abstract ControlType<? extends Control> getType();

}
