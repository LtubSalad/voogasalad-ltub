package newengine.sprite.components;

import commons.point.GamePoint;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;
import newengine.utils.variable.Var;

public class Position extends Component {

	public static final ComponentType<Position> TYPE = new ComponentType<>(Position.class.getName());
	private final Var<GamePoint> posVar = new Var<>();
	
	public Position(GamePoint pos) {
		posVar.set(pos);
	}

	@Override
	public void onUpdated(double dt) {
		double speed = sprite.getComponent(Speed.TYPE).get().speed();
		double heading = sprite.getComponent(Speed.TYPE).get().heading();
		double x = pos().x() + dt * speed * Math.cos(Math.toRadians(heading));
		double y = pos().y() - dt * speed * Math.sin(Math.toRadians(heading));
		posVar.set(new GamePoint(x, y));
//		System.out.println("x: " + x + ", y: " + y);
	}
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	public GamePoint pos() {
		return posVar.get();
	}
	
	@Override
	public void afterAdded() {
		sprite.on(MoveEvent.SPECIFIC, e -> {
			moveTo(e.getTarget());
		});
	}

	private void moveTo(Target target) {
//		System.out.print("Sprite at (" + pos().x() + ", " + pos().y() + ")" );
		// needs speed data
		if (target.getLocation().isPresent()) {
			posVar.set(target.getLocation().get());			
		}
		else {
			
		}
//		System.out.println(" has been moved to (" + pos().x() + ", " + pos().y() + ")");
	}
	
	
}
