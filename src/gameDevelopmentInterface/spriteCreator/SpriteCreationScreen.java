package gameDevelopmentInterface.spriteCreator;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import javafx.scene.layout.BorderPane;
import newengine.sprite.component.Component;
import newengine.sprite.components.Range;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;

public class SpriteCreationScreen extends BorderPane{
	private SpriteMakerModel spriteData;
	
	public SpriteCreationScreen(){
		instantiate();
	}
	
	public void instantiate(){
		spriteData=new SpriteMakerModel();
		List<Class<? extends Component>> basicComponents= new ArrayList<>();
		basicComponents.add(Range.class);
		basicComponents.add(SoundEffect.class);
		basicComponents.add(Speed.class);
		ComponentSelectorPane selector=new ComponentSelectorPane("Add components with simple parameters", basicComponents,spriteData);
	
		this.setRight(selector);
		
	}
	
	private Class getClassFromFile(String fullClassName) throws Exception {
	    URLClassLoader loader = new URLClassLoader(new URL[] {
	            new URL("file://")
	            
	    });
	    return loader.loadClass(fullClassName);
	}


}
