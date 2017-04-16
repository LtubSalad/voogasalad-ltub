package newengine.sprite.components;

import newengine.events.range.InRangeEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class RangeDetector extends Component {

	public static final ComponentType<RangeDetector> TYPE = new ComponentType<>();
	private final Var<Double> rangeVar = new Var<>();
	// TODO: use pairs of (InRangeEvent type, rangeVar) to denote different kinds of range events.
	
	public RangeDetector(double range) {
		rangeVar.set(range);
	}
	
	public double range() {
		return rangeVar.get();
	}

	protected void initHandlers() {
		sprite.on(InRangeEvent.ANY, (e) -> {
			// TODO
			System.out.println(sprite+" detected "+e.getDetectees());
		});
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	
}
