package newengine.sprite.components;

import commons.point.GamePoint;
import data.SpriteMakerModel;
import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.skill.TriggerSkillEvent;
import newengine.events.spawner.SpawnerDoneEvent;
import newengine.events.timer.DelayedEvent;
import newengine.events.timer.PeriodicEvent;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Spawner extends Component {
	

	public static final ComponentType<Spawner> TYPE = new ComponentType<>(Spawner.class.getName());

	private double secondsBetween = 1.5;
	private int totalNumber = 5;
	private boolean needToSpawn = true;
	private GamePoint startingPosition;

	// FIXME don't need path actually
	@ConstructorForDeveloper
	public Spawner(@VariableName(name = "Monsters") int spritesToSpawn,
			@VariableName(name = "Followed path") Path pathSpritesFollow,
			@VariableName(name = "Spawn interval") double spawnBetweenTime, SpriteMakerModel spriteToSpawn) {
		secondsBetween = spawnBetweenTime;
		totalNumber = spritesToSpawn;
		PathFollower pf = (PathFollower) spriteToSpawn.getComponentByType(PathFollower.TYPE);
		startingPosition = pf.getPath().getPath().peek();
	}

	public int getNum() {
		return totalNumber;
	}

	public void onUpdated(double dt) {		
		if (needToSpawn) {
			sprite.getComponent(GameBus.TYPE).get().getGameBus()
			.emit(new PeriodicEvent(totalNumber, secondsBetween, () -> 
			sprite.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(startingPosition)))));
			needToSpawn = false;
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new DelayedEvent(DelayedEvent.ANY, totalNumber * secondsBetween + 10, 
					() -> sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new SpawnerDoneEvent(SpawnerDoneEvent.DONE))));
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
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSpawnTime(double spawnTime){
		this.secondsBetween = spawnTime;
	}

}