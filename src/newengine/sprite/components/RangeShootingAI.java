package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import newengine.events.range.InRangeEvent;
import newengine.events.skill.TriggerSkillEvent;
import newengine.skill.skills.FireProjectileSkill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class RangeShootingAI extends Component {
	
	public static final ComponentType<RangeShootingAI> TYPE = new ComponentType<>(RangeShootingAI.class.getName());

	@ConstructorForDeveloper
	public RangeShootingAI(){
		
	}
	
	@Override
	public void afterAdded() {
		sprite.on(InRangeEvent.ANY, (e) -> {
			Sprite shootTarget = null;
			for (Sprite detectee: e.getDetectees()) {
				if (detectee.getComponent(Owner.TYPE).get().player().isEnemyWith(
						sprite.getComponent(Owner.TYPE).get().player()) && !detectee.getComponent(Weapon.TYPE).isPresent()) {
					shootTarget = detectee;
					break;
				}
			}
			if (shootTarget != null) {
				sprite.emit(new TriggerSkillEvent(FireProjectileSkill.TYPE, new Target(shootTarget)));
			}
		});
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		return new RangeShootingAI();
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return new Object[] {};
	}
	
	

}
