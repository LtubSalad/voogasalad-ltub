package newengine.sprite.components;

import data.SpriteMakerModel;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Spawner extends Component {
	public static final ComponentType<SoundEffect> TYPE = new ComponentType<>(Spawner.class.getName());
	private SpriteMakerModel mySpriteToSpawn;
	
	public Spawner(SpriteMakerModel spriteToSpawn) {
		mySpriteToSpawn = spriteToSpawn;
	}

	@Override
	public void afterAdded() { 
		
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
