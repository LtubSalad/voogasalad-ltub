package engine.sprite;

import java.util.Optional;

import commons.Point;
import engine.player.Player;

public class Sprite {

	
	// initialize empty image.
	private Image image = new Image(null);
//	private boolean locked = false; // TODO
	private Point pos;
	private int z;
	private Movable movable;
	/**
	 * The player that this sprite belongs to.
	 */
	private Player player = Player.DEFAULT;
	
	public Sprite() {
		
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}

	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void setMovable(Movable movable) {
		this.movable = movable;
	}
	public Optional<Movable> getMovable() {
		return Optional.ofNullable(movable);
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	
	public void update(double dt) {
		if (movable != null) {
			movable.update(dt);	
		}
	}
	
}
