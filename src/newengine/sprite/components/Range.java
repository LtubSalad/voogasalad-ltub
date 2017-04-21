package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.range.InRangeEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Range extends Component {

	public static final ComponentType<Range> TYPE = new ComponentType<>(Range.class.getName());
	private final double range;
	// TODO: use pairs of (InRangeEvent type, rangeVar) to denote different kinds of range events.
	
//	public Range(double range) {
//		this.range = range;
//	}
	
	@ConstructorForDeveloper
	public Range(@VariableName(name = "range") double range) {
		this.range = range;
	}
	
	public double range() {
		return range;
	}

	public void afterAdded() {
		sprite.on(InRangeEvent.ANY, (e) -> {
			for (Sprite detectee: e.getDetectees()) {
				Target target = new Target(detectee);
				// TODO
			}
		});
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	@Override
	public Range clone() {
		return new Range(range);
	}

	
}
