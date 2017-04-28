package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import newengine.sprite.component.Component;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Cooldown;
import newengine.sprite.components.DamageStrength;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.RangeShootingAI;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import utilities.XStreamHandler;

public class SpriteCreationScreen extends BorderPane{
	private SpriteMakerModel spriteData;
	private SpriteInfoPane infoPane;
	private EventHandlerPane scriptPane;
	private DeveloperData model;
	
	public SpriteCreationScreen(DeveloperData model){
		this.model=model;
		instantiate();
	}
	
	public void instantiate(){
		spriteData=new SpriteMakerModel();
		scriptPane=new EventHandlerPane(spriteData);
		infoPane=new SpriteInfoPane(spriteData,model);
		this.setRight(instantiateSelector());
		this.setLeft(scriptPane);
		this.setCenter(infoPane);
		this.setTop(new Label("NEW SPRITE"));
		this.setBottom(new BottomPanel());	
	}
	
	private ComponentSelectorPane instantiateSelector(){
		List<Class<? extends Component>> basicComponents= new ArrayList<>();
		ObservableList<Class<? extends Component>> observableComponents=FXCollections.observableList(basicComponents);
		observableComponents.add(Attacker.class);
		observableComponents.add(Collidable.class);
		observableComponents.add(Cooldown.class);
		observableComponents.add(DamageStrength.class);
		observableComponents.add(EventQueue.class);
		observableComponents.add(Health.class);
		observableComponents.add(Images.class);
		observableComponents.add(Owner.class);
		observableComponents.add(PathFollower.class);
		observableComponents.add(Range.class);
		observableComponents.add(RangeShootingAI.class);
		observableComponents.add(Selectable.class);
		observableComponents.add(SoundEffect.class);
		observableComponents.add(Speed.class);
		observableComponents.add(SkillSet.class);
		
		return new ComponentSelectorPane("Add components and set parameters", observableComponents,infoPane);
	}

	private class BottomPanel extends HBox{
		XStreamHandler dataHandler=new XStreamHandler();
		private BottomPanel(){
			Button saveButton= new Button("Save SpriteMakerModel to File");
			saveButton.setOnMouseClicked((click)->{
				try {
					updateSprite();
					dataHandler.saveToFile(spriteData);
				} catch (Exception e) {
					//FIXME
					e.printStackTrace();
				}
			});
			Button listSaveButton=new Button("Save SpriteMakerModel to THIS GAME's list of SpriteMakerModels");
			listSaveButton.setOnMouseClicked((click)->{
				try {
					updateSprite();
					model.addSprite(spriteData);
				} catch (Exception e) {
					//FIXME
				}				
			});
			this.getChildren().addAll(saveButton,listSaveButton);
		}
		
		private void updateSprite() throws Exception{
			scriptPane.updateSprite();
			infoPane.updateSpriteData();
		}
	}


}
