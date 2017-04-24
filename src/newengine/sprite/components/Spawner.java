package newengine.sprite.components;

import data.SpriteMakerModel;
import gamedata.AuthDataTranslator;
import newengine.events.SpriteModelEvent;
import newengine.events.spawner.SpawnEvent;
import newengine.events.spawner.SpawnPrefEvent;
import newengine.events.timer.PeriodicEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Spawner extends Component {
	public static final ComponentType<SoundEffect> TYPE = new ComponentType<>(Spawner.class.getName());
	private SpriteMakerModel mySpriteModel;
	private Sprite mySprite;
	private double secondsBetween;
	private int totalNumber;
	
	public Spawner(SpriteMakerModel spriteToSpawn) {
		mySpriteModel = spriteToSpawn;
	}

	@Override
	public void afterAdded() { 
		AuthDataTranslator translator = new AuthDataTranslator(mySpriteModel);
		mySprite = translator.getSprite();
		
		sprite.on(SpawnPrefEvent.SETPREFS, e -> {
			this.totalNumber = e.getTotalNumber();
			this.secondsBetween = e.getSecondBetween();
			sprite.emit(new PeriodicEvent(totalNumber, secondsBetween, () -> sprite.emit(new SpawnEvent(SpawnEvent.SPAWN))));
		});
		sprite.on(SpawnEvent.SPAWN, (e) -> {
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.ADD, mySprite.clone()));
		});
	}
	
	

	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
