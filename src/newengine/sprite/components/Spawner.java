package newengine.sprite.components;

import commons.point.GamePoint;
import helperAnnotations.ConstructorForDeveloper;
import newengine.events.skill.TriggerSkillEvent;
import newengine.events.spawner.SpawnPrefEvent;
import newengine.events.timer.PeriodicEvent;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Spawner extends Component {
	public static final ComponentType<SoundEffect> TYPE = new ComponentType<>(Spawner.class.getName());
	private double secondsBetween;
	private int totalNumber;

	@ConstructorForDeveloper
	public Spawner(){
		
	}
	
	@Override
	public void afterAdded() { 
		//sprite.on(SpawnPrefEvent.SETPREFS, e -> {
			System.out.println("Made the event spawner");
			this.totalNumber = 10;//e.getTotalNumber();
			this.secondsBetween = 10;// e.getSecondBetween();
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new PeriodicEvent(totalNumber, secondsBetween, () -> 
			//System.out.println("clock is working");
			sprite.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(new GamePoint(10,20))))));//sprite.getComponent(Position.TYPE).get().pos())))));
		//});
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return new Spawner();
	}

	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
