package newengine.sprite.components;

import commons.point.GamePoint;
import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.skill.TriggerSkillEvent;
import newengine.events.timer.PeriodicEvent;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Spawner extends Component {

	public static final ComponentType<Spawner> TYPE = new ComponentType<>(Spawner.class.getName());
	private double secondsBetween;
	private int totalNumber;
	private boolean needToSpawn = true;
	private GamePoint startingPosition;

	// FIXME don't need path actually
	@ConstructorForDeveloper
	public Spawner(@VariableName(name = "Monsters") int spritesToSpawn,
			@VariableName(name = "Followed path") Path pathSpritesFollow,
			@VariableName(name = "Spawn interval") double spawnBetweenTime) {
		secondsBetween = spawnBetweenTime;
		totalNumber = spritesToSpawn;
		startingPosition = pathSpritesFollow.getPath().peek();
	}

	public int getNum() {
		return totalNumber;
	}

	public void onUpdated(double dt) {
		if (needToSpawn) {
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new PeriodicEvent(5, 3.0, () -> sprite.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(new GamePoint(10,20))))));
			needToSpawn = false;
		}
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}