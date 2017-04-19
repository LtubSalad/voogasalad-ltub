package gameDevelopmentInterface.spriteCreator;

import java.net.URL;
import java.net.URLClassLoader;

import javafx.scene.layout.BorderPane;
import newengine.sprite.components.SoundEffect;

public class SpriteCreationScreen extends BorderPane{
	public SpriteCreationScreen(){
		instantiate();
	}
	
	public void instantiate(){
		ComponentSetter<SoundEffect> setter=new ComponentSetter<SoundEffect>(SoundEffect.class);
		this.setCenter(setter);
		
	}
	
	private Class getClassFromFile(String fullClassName) throws Exception {
	    URLClassLoader loader = new URLClassLoader(new URL[] {
	            new URL("file://")
	            
	    });
	    return loader.loadClass(fullClassName);
	}


}
