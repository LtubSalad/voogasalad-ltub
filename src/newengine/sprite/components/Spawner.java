package newengine.sprite.components;

import bus.BusEvent;
import commons.point.GamePoint;
import newengine.events.QueueEvent;
import newengine.events.debug.SysPrintEvent;
import newengine.events.game.StartLevelEvent;
import newengine.events.skill.TriggerSkillEvent;
import newengine.events.sprite.MoveEvent;
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
	
	//FIXME don't need path actually
	public Spawner(int spritesToSpawn, Path pathSpritesFollow, double spawnBetweenTime) {
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
		//		System.out.println("outside = update called");
		//		//if(needToSpawn){
		//			System.out.println("inside need to spawn");
		//
		//			sprite.getComponent(GameBus.TYPE).get().getGameBus().on(StartLevelEvent.START, e -> {
		//				sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new SysPrintEvent("HIIIIIIIIIIII"));
		//			});
		//			needToSpawn = false;
		//		//}


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
