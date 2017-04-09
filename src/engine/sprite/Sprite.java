package engine.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import commons.MathUtils;
import engine.camera.GamePoint;
import engine.player.Player;
import engine.sprite.collision.Collidable;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;

public class Sprite {

	// initialize empty image.
	private ImageSet imageSet;
	// private boolean locked = false; // TODO
	private GamePoint pos;
	private int z;
	private Movable movable = null;
	private Collidable collidable = null;
	private SelectionBound selectionBound = SelectionBound.IMAGE;
	private List<GamePoint> selectionBoundVertices;
	private double detectionRange;
	/**
	 * The player that this sprite belongs to.
	 */
	private Player player = Player.DEFAULT;
	private ActionQueue actionQueue = new ActionQueue();

	public Sprite() {

	}

	public void setImageSet(ImageSet imageSet) {
		this.imageSet = imageSet;		
	}

	/**
	 * Return an image corresponding to the sprite at the 
	 * current frame. Could change with direction and moving distance.
	 * @return
	 */
	public LtubImage getImage() {
		// TODO: pass in angle and dist
		return imageSet.getImage(0, 0);
	}
	
	public GamePoint getPos() {
		return pos;
	}
	
	public void setPos(GamePoint pos) {
		this.pos = pos;
	}

	public void setSelectionBound(SelectionBound selectionBound) {
		this.selectionBound = selectionBound;
	}
	
	public SelectionBound getSelectionBound() {
		return selectionBound;
	}
	
	private void setSelectionBoundVertices() {
		selectionBoundVertices = new ArrayList<>();
		if (selectionBound == SelectionBound.IMAGE) {
//			if (ltubImage != null) {
//				// Image rectangle nodes are added in a clock-wise order
//				selectionBoundVertices.add(this.getDisplayPos());
//				selectionBoundVertices.add(new Point(this.getDisplayPos().x() + ltubImage.width(), this.getDisplayPos().y()));
//				selectionBoundVertices.add(new Point(this.getDisplayPos().x() + ltubImage.width(), this.getDisplayPos().y() + ltubImage.height()));
//				selectionBoundVertices.add(new Point(this.getDisplayPos().x(), this.getDisplayPos().y() + ltubImage.height()));
//			}
		}
		else if (selectionBound == SelectionBound.POLYGON) {
			// TODO
		}
	}
	
	/**
	 * Get a list of Point indicating the definite display positions of selection bound vertices
	 * @return List<Point>
	 */
	public List<GamePoint> getSelectionBoundVertices() {
		setSelectionBoundVertices();
		return selectionBoundVertices;
	}
	
	public void setDetectionRange(double detectionRange) {
		this.detectionRange = detectionRange;
	}
	
	public double getDetectionRange() {
		return detectionRange;
	}

	public void setMovable(Movable movable) {
		this.movable = movable;
	}
	public Optional<Movable> getMovable() {
		return Optional.ofNullable(movable);
	}
	public void setCollidable(Collidable collidable) {
		this.collidable = collidable;
	}
	public Optional<Collidable> getCollidable() {
		return Optional.ofNullable(collidable);
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
