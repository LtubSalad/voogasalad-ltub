package newengine.sprite.components;

import java.io.File;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SoundEffect extends Component {

	public static final ComponentType<SoundEffect> TYPE = new ComponentType<>(SoundEffect.class.getName());
	
	private String moveSoundFile;

	private final File base =new File(System.getProperty("user.dir")+File.separator+"data");
	
	@ConstructorForDeveloper
	public SoundEffect(@VariableName(name = "Sound file") File soundFile){
		this(soundFile.toURI().toString());
	}
	
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
	
	@Override
	public SoundEffect clone() {
		return new SoundEffect(moveSoundFile);
	}
}
