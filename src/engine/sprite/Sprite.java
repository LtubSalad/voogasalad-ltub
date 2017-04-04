package engine.sprite;

import java.util.Optional;

import commons.MathUtils;
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
	private ActionQueue actionQueue = new ActionQueue();
	
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
	public void executeAction(Action action) {
		actionQueue.clearQueue();
		action.execute();
	}
	public void queueAction(Action action) {
		actionQueue.addAction(action);
	}
	
	public void update(double dt) {
		double tRemain = dt;
		// TODO: this piece of actions queueing code has to be improved
		if (movable != null) {
			if (!movable.isMoving()) {
				if (!actionQueue.isEmpty()) {
					actionQueue.executeNextAction();
				}
			}
			while (!MathUtils.doubleEquals(tRemain, 0) && movable.isMoving()) {
				tRemain = movable.update(dt);
				if (!MathUtils.doubleEquals(tRemain, 0)) {
					if (!actionQueue.isEmpty()) {
						actionQueue.executeNextAction();
					}
				}
			}
		}
	}
	
}
