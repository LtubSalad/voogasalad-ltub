package newengine.sprite.components;

import newengine.events.range.InRangeEvent;
import newengine.events.sprite.AttackEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Range extends Component {

	public static final ComponentType<Range> TYPE = new ComponentType<>(Range.class.getName());
	private final double range;
	// TODO: use pairs of (InRangeEvent type, rangeVar) to denote different kinds of range events.
	
	public Range(double range) {
		this.range = range;
	}
	
	public double range() {
		return range;
	}

	public void afterAdded() {
		sprite.on(InRangeEvent.ANY, (e) -> {
			for (Sprite detectee: e.getDetectees()) {
				//System.out.println(sprite.getID() + " fires projectile at " + detectee.getID());
				Target target = new Target(detectee);
//				sprite.emit(new FireProjectileEvent(FireProjectileEvent.SPECIFIC, sprite, target));
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
