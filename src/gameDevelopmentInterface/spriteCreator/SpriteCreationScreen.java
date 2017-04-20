package gameDevelopmentInterface.spriteCreator;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import newengine.sprite.component.Component;
import newengine.sprite.components.Range;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;

public class SpriteCreationScreen extends BorderPane{
	private SpriteMakerModel spriteData;
	private SpriteInfoPane infoPane;
	
	public SpriteCreationScreen(){
		instantiate();
	}
	
	public void instantiate(){
		spriteData=new SpriteMakerModel();
		infoPane=new SpriteInfoPane(spriteData);
		List<Class<? extends Component>> basicComponents= new ArrayList<>();
		ObservableList<Class<? extends Component>> observableComponents=FXCollections.observableList(basicComponents);
		observableComponents.add(Range.class);
		observableComponents.add(SoundEffect.class);
		observableComponents.add(Speed.class);
		ComponentSelectorPane selector=new ComponentSelectorPane("Add components with simple parameters", observableComponents,infoPane);
		this.setRight(selector);
		this.setCenter(infoPane);
		this.setTop(new Label("NEW SPRITE"));
	}
	
	private Class getClassFromFile(String fullClassName) throws Exception {
	    URLClassLoader loader = new URLClassLoader(new URL[] {
	            new URL("file://")
	            
	    });
	    return loader.loadClass(fullClassName);
	}


}
