package newengine.sprite.components;

import bus.BusEvent;
import bus.BusEventHandler;
import commons.MathUtils;
import commons.point.GamePoint;
import newengine.events.QueueEvent;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;
import newengine.utils.variable.Var;

public class TestPosition extends Component {

	public static final ComponentType<Position> TYPE = new ComponentType<>(TestPosition.class.getName());
	private final Var<GamePoint> posVar = new Var<>();
	private final Var<Double> headingVar = new Var<>();
	private Target target;
	private boolean isMoving = false;
	private BusEventHandler positionEventHandler;
	
	public TestPosition() {
		posVar.set(new GamePoint(0,0));
		double heading  = 0;
		headingVar.set(heading);
	}
	
//	public TestPosition(GamePoint pos, double heading) {
//		posVar.set(pos);
//		headingVar.set(heading);
//	}
	
	@Override
	public void afterAdded() {
		System.out.println("Did it!");
		positionEventHandler = new TestPositionHandler(sprite, this);
		//sprite.on(MoveEvent.SPECIFIC, positionEventHandler);
	}

	@Override
	public void onUpdated(double dt) {
		if (!isMoving()) {return;}
		GamePoint pDest = target.getLocation();
		double xDest = pDest.x();
		double yDest = pDest.y();
		GamePoint pos = posVar.get();
		double x = pos.x();
		double y = pos.y();
		if (MathUtils.doubleEquals(x, xDest) && MathUtils.doubleEquals(y, yDest)) {
			stopMoving();
		}
		double xDiff = xDest - x;
		double yDiff = yDest - y;
		double dist = pos.distFrom(pDest);
		double speed = sprite.getComponent(Speed.TYPE).get().speed();
		if (speed * dt > dist) {
			// arrives at destination at this frame.
			posVar.set(new GamePoint(xDest, yDest));
			stopMoving();
			sprite.emit(new QueueEvent(QueueEvent.NEXT, new BusEvent(BusEvent.ANY)));
		}
		// not arrived at destination, move by time and speed.
		double vx = 0;
		double vy = 0;
		if (!MathUtils.doubleEquals(x, xDest) && MathUtils.doubleEquals(y, yDest)) {
			vx = xDiff > 0 ? speed : -speed;
		} else if (MathUtils.doubleEquals(x, xDest) && !MathUtils.doubleEquals(y, yDest)) {
			vy = yDiff > 0 ? speed : -speed;
		} else {
			vx = speed / dist * xDiff;
			vy = speed / dist * yDiff;
		}
		posVar.set(new GamePoint(x + vx * dt, y + vy * dt));
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	public GamePoint pos() {
		return posVar.get();
	}

	public double heading() {
		return headingVar.get();
	}

	public void moveTo(Target target) {
		this.target = target;
		startMoving();
	}
	
	private void startMoving() {
		isMoving = true;
	}
	
	private void stopMoving() {
		isMoving = false;
	}
	
	public boolean isMoving() {
		return isMoving;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return null;
	}
}
