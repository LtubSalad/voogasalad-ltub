package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import newengine.sprite.component.Component;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Cost;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Range;
import newengine.sprite.components.RangeShootingAI;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import newengine.sprite.components.TowerDefenceTypeInformation;
import newengine.sprite.components.Upgrade;
import utilities.AlertHandler;

/**
 * This is a screen with various panes that allow the user to add components to a sprite, specify 
 * the parameters of those components, write scripts to handle events received by sprites, and save 
 * sprites to a file.
 * Can be used to either load up a preexisting sprite, or instantiate a completely new sprite.
 * 
 * @author Daniel
 * 
 */
public class SpriteCreationScreen extends BorderPane {
	private SpriteDataPane infoPane;
	private EventHandlerPane scriptPane;
	private DeveloperData developerData;
	private SpriteDescriptorPane descriptorPane;
	private SavePanel savePanel;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);

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
		descriptorPane = new SpriteDescriptorPane(sprite);
		savePanel=new SavePanel(this,developerData);
		this.setRight(instantiateSelector());
		//this.setLeft(scriptPane);
		this.setCenter(infoPane);
		this.setTop(descriptorPane);
		this.setBottom(savePanel);
	}

	public SpriteDataPane getInfoPane() {
		return infoPane;
	}

	private ComponentSelectorPane instantiateSelector() {
		List<Class<? extends Component>> basicComponents = new ArrayList<>();
		ObservableList<Class<? extends Component>> observableComponents = FXCollections.observableList(basicComponents);
		observableComponents.add(Cost.class);
		observableComponents.add(Attacker.class);
		observableComponents.add(Collidable.class);
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
		observableComponents.add(TowerDefenceTypeInformation.class);
		observableComponents.add(Upgrade.class);

		return new ComponentSelectorPane(myResources.getString("addComponents"), observableComponents, infoPane);
	}

	public SpriteMakerModel produceNewModel() {
		SpriteMakerModel sprite = new SpriteMakerModel();
		try {
			scriptPane.updateSprite(sprite);
			infoPane.updateSpriteData(sprite);
			descriptorPane.updateSpriteData(sprite);
		} catch (Exception e) {
			AlertHandler.showError(myResources.getString("modelError"));
			return null;

		}

		String name = sprite.getName();
		if (name == null || name.equals("")) {
			AlertHandler.showError(myResources.getString("modelNoName"));
			return null;
		}

		return sprite;
	}
}
