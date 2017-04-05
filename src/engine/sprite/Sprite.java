package engine.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import commons.MathUtils;
import commons.Point;
import engine.player.Player;

public class Sprite {

	
	// initialize empty image.
	private LtubImage ltubImage = LtubImage.EMPTY_IMAGE;
	private Point imageOffset = null;
//	private boolean locked = false; // TODO
	private Point pos;
	private int z;
	private Movable movable;
	private SelectionBound selectionBound = SelectionBound.IMAGE;
	private List<Point> selectionBoundVertices;
	
	/**
	 * The player that this sprite belongs to.
	 */
	private Player player = Player.DEFAULT;
	private ActionQueue actionQueue = new ActionQueue();
	
	public Sprite() {
		
	}

	public void setImage(LtubImage ltubImage) {
		this.ltubImage = ltubImage;
	}
	
	public LtubImage getImage() {
		return ltubImage;
	}
	
	public void setImageOffset(Point offset) {
		this.imageOffset = offset;
	}
	
	/**
	 * The default offset is half the size of the image.
	 * @return
	 */
	public Point getImageOffset() {
		if (imageOffset != null) {
			return imageOffset;
		} else {
			return new Point(ltubImage.width()/2, ltubImage.height()/2);
		}
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	/**
	 * Get the display position of the Sprite
	 * @return Point
	 */
	public Point getDisplayPos() {
		return new Point(pos.x() - this.getImageOffset().x(), pos.y() - this.getImageOffset().y());
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
	public List<Point> getSelectionBoundVertices() {
		setSelectionBoundVertices();
		return selectionBoundVertices;
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
