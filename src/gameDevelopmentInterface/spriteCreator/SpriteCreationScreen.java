package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import newengine.sprite.components.Range;
import newengine.sprite.components.RangeShootingAI;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Spawner;
import newengine.sprite.components.Speed;
import newengine.sprite.components.TowerDefenceTypeInformation;
import newengine.sprite.components.Upgradeable;
import utilities.AlertHandler;
import utilities.XStreamHandler;

public class SpriteCreationScreen extends BorderPane {
	private SpriteDataPane infoPane;
	private EventHandlerPane scriptPane;
	private DeveloperData developerData;
	private SpriteDescriptorPane descriptorPane;

	public SpriteCreationScreen(DeveloperData model, SpriteMakerModel spriteData) {
		this.developerData = model;
		instantiate(spriteData);
	}

	public SpriteCreationScreen(DeveloperData model) {
		this(model, new SpriteMakerModel());
	}

	public void instantiate(SpriteMakerModel sprite) {
		scriptPane = new EventHandlerPane(sprite);
		infoPane = new SpriteDataPane(sprite, developerData);
		descriptorPane=new SpriteDescriptorPane(sprite);
		this.setRight(instantiateSelector());
		this.setLeft(scriptPane);
		this.setCenter(infoPane);
		this.setTop(descriptorPane);
		this.setBottom(new BottomPanel());
	}

	public SpriteDataPane getInfoPane() {
		return infoPane;
	}

	private ComponentSelectorPane instantiateSelector() {
		List<Class<? extends Component>> basicComponents = new ArrayList<>();
		ObservableList<Class<? extends Component>> observableComponents = FXCollections.observableList(basicComponents);
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
		observableComponents.add(Spawner.class);
		observableComponents.add(TowerDefenceTypeInformation.class);
		observableComponents.add(Upgradeable.class);

		return new ComponentSelectorPane("Add components", observableComponents, infoPane);
	}

	public class BottomPanel extends HBox {
		XStreamHandler dataHandler = new XStreamHandler();

		public BottomPanel() {
			AlertHandler alertHandler = new AlertHandler();
			Button saveButton = new Button("Save Sprite to File");
			saveButton.setOnMouseClicked((click) -> {
				Alert alert = alertHandler.confirmationPopUp("Are you sure you wish to save?");
				Optional<ButtonType> result = alert.showAndWait();
				 if (result.isPresent() && result.get() == ButtonType.CANCEL) {
				     return;
				 }
				
				SpriteMakerModel modelToSave = produceNewModel();
				if(modelToSave!=null){
					XStreamHandler handler=new XStreamHandler();
					handler.saveToFile(modelToSave);
					AlertHandler.showMessage("Saved successfully");
				}
			});

			Button listSaveButton = new Button("Save Sprite to this game's Sprites");
			listSaveButton.setOnMouseClicked((click) -> {
				Alert alert = alertHandler.confirmationPopUp("Are you sure you wish to save?");
				Optional<ButtonType> result = alert.showAndWait();
				 if (result.isPresent() && result.get() == ButtonType.CANCEL) {
				     return;
				 }

				SpriteMakerModel modelToSave = produceNewModel();
				if(modelToSave!=null){
					for (SpriteMakerModel spriteModel : developerData.getSprites()) {
						if (spriteModel.getName().equals(modelToSave.getName())) {
							Alert sameNameAlert = alertHandler
									.confirmationPopUp("Another sprite in your game data has this name. Overwrite?");
							sameNameAlert.showAndWait().ifPresent(response -> {
								if (response == ButtonType.NO) {
									return;
								}
								developerData.getSprites().remove(spriteModel);
							});
						}
					}
				
					developerData.getSprites().add(modelToSave);
					AlertHandler.showMessage("Saved successfully");
				}

			});
			this.getChildren().addAll(saveButton, listSaveButton);
		}

		private SpriteMakerModel produceNewModel(){
			SpriteMakerModel sprite = new SpriteMakerModel();
			try {
				scriptPane.updateSprite(sprite);
				infoPane.updateSpriteData(sprite);
				descriptorPane.updateSpriteData(sprite);
			} catch (Exception e) {
				AlertHandler.showError("Model could not be created");
				return null;
			}

			String name = sprite.getName();
			if (name == null || name.equals("")) {
				AlertHandler.showError("Model has no name");
				return null;
			}

			return sprite;
		}
	}

}
