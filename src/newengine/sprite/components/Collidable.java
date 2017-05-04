package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import commons.point.GamePoint;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.image.LtubImage;

public class Collidable extends Component {
	public enum CollisionBoundType {
		POLYGON, IMAGE
	}

	public static final ComponentType<Collidable> TYPE = new ComponentType<>(Collidable.class.getName());
	private final CollisionBoundType boundType;

	@ConstructorForDeveloper
	public Collidable(@VariableName(name = "Bound type") CollisionBoundType boundType) {
		this.boundType = boundType; // TODO different kinds of points
	}

	/**
	 * Get a list of points representing the polygon collision bound.
	 */
	public List<GamePoint> boundPoints() {
		List<GamePoint> bound = new ArrayList<>();
		if (CollisionBoundType.IMAGE == boundType) {
			LtubImage image = sprite.getComponent(Images.TYPE).get().image();
			double w = image.width();
			double h = image.height();
			double heading = sprite.getComponent(Position.TYPE).get().heading();
			GamePoint pivot = new GamePoint(w / 2, h / 2);
			bound.add(rotatedPoint(new GamePoint(0, 0), heading, pivot));
			bound.add(rotatedPoint(new GamePoint(w, 0), heading, pivot));
			bound.add(rotatedPoint(new GamePoint(w, h), heading, pivot));
			bound.add(rotatedPoint(new GamePoint(0, h), heading, pivot));
		} else {
			// TODO
		}
		return bound;
	}

	private GamePoint rotatedPoint(GamePoint p, double heading, GamePoint pivot) {
		p = new GamePoint(p.x() - pivot.x(), p.y() - pivot.y());
		p = new GamePoint(Math.cos(Math.toRadians(heading)) * p.x() + Math.sin(Math.toRadians(heading)) * p.y(),
				-Math.sin(Math.toRadians(heading)) * p.x() + Math.cos(Math.toRadians(heading)) * p.y());
		p = new GamePoint(p.x() + pivot.x(), p.y() - pivot.y());
		return p;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Collidable clone() {
		return new Collidable(boundType);
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		Object[] parameters = new Object[1];
		parameters[0] = boundType;
		return parameters;
	}

}
