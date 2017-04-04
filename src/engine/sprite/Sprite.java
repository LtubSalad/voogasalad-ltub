package engine.sprite;

import java.util.Optional;

public class Sprite {

//	private boolean locked = false; // TODO
	private double x;
	private double y;
	private int z;
	private Movable movable;
	
	public Sprite() {
		
	}
	
	// initialize empty image.
	private Image image = new Image(null);

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}

	public double x() {
		return x;
	}
	public double y() {
		return y;
	}
	public int z() {
		return z;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setMovable(Movable movable) {
		this.movable = movable;
	}
	public Optional<Movable> getMovable() {
		return Optional.ofNullable(movable);
	}
	
	public void update(double dt) {
		if (movable != null) {
			movable.update(dt);	
		}
	}
	
}
