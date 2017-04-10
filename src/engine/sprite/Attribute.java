package engine.sprite;

public interface Attribute {
	
	public void switchOn();
	
	public void switchOff();
	
	public Boolean isAttribute();

	public double update(double dt);
	
}
