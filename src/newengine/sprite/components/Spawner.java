package newengine.sprite.components;

import java.util.Queue;

import commons.point.GamePoint;
import data.SpriteMakerModel;
import gameDevelopmentInterface.Path;
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
	
	public Spawner(Queue<SpriteMakerModel> spritesToSpawn, Path pathSpritesFollow, double spawnBetweenTime) {
		secondsBetween = spawnBetweenTime;
		totalNumber = spritesToSpawn.size();
		startingPosition = pathSpritesFollow.getPath().peek();
	}

	public void afterAdded(){
		//		sprite.emit(new QueueEvent(QueueEvent.ADD, new PeriodicEvent(5, 3.0, () -> sprite.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(new GamePoint(10,20)))))));
		//		sprite.emit(new QueueEvent(QueueEvent.NEXT, new BusEvent(BusEvent.ANY)));
	}

	public void onUpdated(double dt) {
		if (needToSpawn) {
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new PeriodicEvent(totalNumber, secondsBetween, () -> 
			sprite.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(startingPosition)))));
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
