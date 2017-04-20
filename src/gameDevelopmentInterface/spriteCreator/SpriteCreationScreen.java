package gameDevelopmentInterface.spriteCreator;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import newengine.sprite.component.Component;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import newengine.utils.image.ImageSet;
import utilities.XStreamHandler;

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
		observableComponents.add(Owner.class);
		observableComponents.add(Position.class);
		observableComponents.add(Images.class);
		ComponentSelectorPane selector=new ComponentSelectorPane("Add components with simple parameters", observableComponents,infoPane);
		this.setRight(selector);
		this.setCenter(infoPane);
		this.setTop(new Label("NEW SPRITE"));
		this.setBottom(new BottomPanel());
		
	}
	
	private Class getClassFromFile(String fullClassName) throws Exception {
	    URLClassLoader loader = new URLClassLoader(new URL[] {
	            new URL("file://")
	            
	    });
	    return loader.loadClass(fullClassName);
	}

	private class BottomPanel extends HBox{
		XStreamHandler dataHandler;
		private BottomPanel(){
			Button saveButton= new Button("Save SpriteMakerModel to File");
			saveButton.setOnMouseClicked((click)->{
				dataHandler.saveToFile(infoPane.getSpriteData());
			});
			this.getChildren().add(saveButton);
		}
	}


}
