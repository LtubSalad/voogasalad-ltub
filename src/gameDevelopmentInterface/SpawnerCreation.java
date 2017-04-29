package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;

import commons.point.GamePoint;
import data.DeveloperData;
import data.SpriteMakerModel;
import gameDevelopmentInterface.spriteCreator.ComponentSelectorPane;
//import gameDevelopmentInterface.spriteCreator.EventHandlerPane;
import gameDevelopmentInterface.spriteCreator.SpriteCreationScreen;
import gameDevelopmentInterface.spriteCreator.SpriteInfoPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import newengine.sprite.component.Component;
import newengine.sprite.components.Cooldown;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Position;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.Spawner;
import utilities.XStreamHandler;

public class SpawnerCreation extends SpriteCreationScreen {
	private SpriteMakerModel spriteData;
	private SpriteInfoPane infoPane;
	//private EventHandlerPane scriptPane;
	private DeveloperData model;
	
	public SpawnerCreation(DeveloperData model) {
		super(model);
		this.model = model;
		instantiate();
	}
	
	@Override
	public void instantiate() {
		spriteData = new SpriteMakerModel();
		//scriptPane = new EventHandlerPane(spriteData);
		infoPane = new SpriteInfoPane(spriteData, model);
		this.setCenter(infoPane);
		this.setTop(new MonsterAdder(model));
		this.setLeft(instantiateSelector());
		this.setBottom(new BottomPanel());
	}
	
	private ComponentSelectorPane instantiateSelector() {
		List<Class<? extends Component>> basicComponents = new ArrayList<>();
		ObservableList<Class<? extends Component>> observableComponents = FXCollections.observableList(basicComponents);
		observableComponents.add(Cooldown.class);
		observableComponents.add(Images.class);
		observableComponents.add(Owner.class);
		observableComponents.add(PathFollower.class);
		observableComponents.add(Selectable.class);
		observableComponents.add(SkillSet.class);
		observableComponents.add(Spawner.class);
		return new ComponentSelectorPane("Add components and set parameters", observableComponents, infoPane);
	}
	
	public class BottomPanel extends HBox {
		XStreamHandler dataHandler = new XStreamHandler();

		public BottomPanel() {
			Button saveButton = new Button("Save Spawner to File");
			saveButton.setOnMouseClicked((click) -> {
				try {
					updateSprite();
					PathFollower pathComponent = (PathFollower) spriteData.getComponentByType(PathFollower.TYPE);
					GamePoint gp = pathComponent.getPath().getPath().peek();
					spriteData.addComponent(new Position(gp, 0));
					dataHandler.saveToFile(spriteData);
				} catch (Exception e) {
					// FIXME
				}
			});
			Button listSaveButton = new Button("Save Spawner to THIS GAME's list of SpriteMakerModels");
			listSaveButton.setOnMouseClicked((click) -> {
				try {
					updateSprite();
					PathFollower pathComponent = (PathFollower) spriteData.getComponentByType(PathFollower.TYPE);
					GamePoint gp = pathComponent.getPath().getPath().peek();
					spriteData.addComponent(new Position(gp, 0));
					model.addSprite(spriteData);
				} catch (Exception e) {
					// FIXME
				}
			});
			this.getChildren().addAll(saveButton, listSaveButton);
		}

		private void updateSprite() throws Exception {
			infoPane.updateSpriteData();
		}
	}
	
}