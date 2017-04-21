package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import commons.point.GamePoint;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.image.LtubImage;

public class Collidable extends Component {
	public enum CollisionBoundType {
		POLYGON, IMAGE
	}

	public static final ComponentType<Collidable> TYPE = new ComponentType<>(Collidable.class.getName());
	private final CollisionBoundType boundType;
	private final List<GamePoint> bound = new ArrayList<>();
	
	public Collidable(CollisionBoundType boundType) {
		this.boundType = boundType; // TODO different kinds of points
	}
	
	@Override
	public void afterAdded() { // TODO bound for multiple images
		if (CollisionBoundType.IMAGE == boundType) {
			LtubImage image = sprite.getComponent(Images.TYPE).get().image();
			double w = image.width();
			double h = image.height();
			bound.add(new GamePoint(0, 0));
			bound.add(new GamePoint(w, 0));
			bound.add(new GamePoint(w, h));
			bound.add(new GamePoint(0, h));
		}
		else {
			// TODO
		}
	}
	
	/**
	 * Get a list of points representing the polygon collision bound.
	 */
	public List<GamePoint> boundPoints() {
		return bound;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	@Override
	public Collidable clone() {
		return new Collidable(boundType);		
	}

}
