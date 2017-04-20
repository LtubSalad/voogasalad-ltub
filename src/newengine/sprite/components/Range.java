package newengine.sprite.components;

import gameDevelopmentInterface.spriteCreator.helperAnnotations.ConstructorForDeveloper;
import gameDevelopmentInterface.spriteCreator.helperAnnotations.VariableName;
import newengine.events.range.InRangeEvent;
import newengine.events.sprite.AttackEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;
import newengine.utils.variable.Var;

public class Range extends Component {

	public static final ComponentType<Range> TYPE = new ComponentType<>(Range.class.getName());
	private final Var<Double> rangeVar = new Var<>();
	// TODO: use pairs of (InRangeEvent type, rangeVar) to denote different kinds of range events.
	
	@ConstructorForDeveloper
	public Range(@VariableName(name = "range") double range) {
		rangeVar.set(range);
	}
	
	public double range() {
		return rangeVar.get();
	}

	public void afterAdded() {
		sprite.on(InRangeEvent.ANY, (e) -> {
			for (Sprite detectee: e.getDetectees()) {
				System.out.println(sprite + " fires at sprite " + detectee);
				sprite.emit(new AttackEvent(AttackEvent.FIRE, new Target(detectee)));
			}
		});
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	
}
