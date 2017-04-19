package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SoundEffect extends Component {

	public static final ComponentType<SoundEffect> TYPE = new ComponentType<>(SoundEffect.class.getName());
	
	private String moveSoundFile;
	
	public SoundEffect(String moveSoundFile) {
		this.moveSoundFile = moveSoundFile;
	}
	
	public String getMoveSoundFile() {
		return moveSoundFile;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
}
