package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import commons.point.GamePoint;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.image.LtubImage;
import newengine.utils.variable.Var;

public class Collidable extends Component {
	public enum CollisionBoundType {
		POLYGON, IMAGE
	}

	public static final ComponentType<Collidable> TYPE = new ComponentType<>();
	private final Var<CollisionBoundType> boundTypeVar = new Var<>();
	private final Var<List<GamePoint>> boundVar = new Var<>();
	
	public Collidable(CollisionBoundType boundType) {
		boundTypeVar.set(boundType); // TODO different kinds of points
		initBoundPoints();
	}
	private void initBoundPoints() {
		List<GamePoint> bound = new ArrayList<>(); // TODO bound for multiple images
		LtubImage image = sprite.getComponent(Images.TYPE).get().image();
		double w = image.width();
		double h = image.height();
		bound.add(new GamePoint(0, 0));
		bound.add(new GamePoint(w, 0));
		bound.add(new GamePoint(w, h));
		bound.add(new GamePoint(0, h));
		boundVar.set(bound);
	}
	
	/**
	 * Get a list of points representing the polygon collision bound.
	 */
	public List<GamePoint> boundPoints() {
		return boundVar.get();
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

}
