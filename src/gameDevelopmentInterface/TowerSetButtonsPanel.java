package gameDevelopmentInterface;

import java.util.ResourceBundle;

import data.SpriteMakerModel;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import utilities.XStreamHandler;

/**
 * This class is intended to hold all of the clickable items that a user might
 * need to use on the game authoring environment where they wish to set on the
 * screen different sprites.
 * 
 * @author Jake
 *
 */
public class TowerSetButtonsPanel extends VBox {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String LOAD_SPRITE_FROM_FILE = "LOAD_SPRITE_FROM_FILE";
	private static final String SAVE_THIS_SETUP = "SAVE_THIS_SETUP";
	private static final String LOAD_SCREEN_MODEL_FROM_FILE = "LOAD_SCREEN_MODEL_FROM_FILE";
	private Button saveSetupButton;
	private Button loadButton;
	private Button loadSpriteButton;
	private XStreamHandler xstreamHandler = new XStreamHandler();
	private ScreenModelCreator mySMC;

	public TowerSetButtonsPanel(ScreenModelCreator smc) {
		mySMC = smc;
		makeButtons();
		this.getChildren().addAll(saveSetupButton, loadButton, loadSpriteButton);
	}

	private void makeButtons() {
		saveSetupButton = new Button(myResources.getString(SAVE_THIS_SETUP));
		loadSpriteButton = new Button(myResources.getString(LOAD_SPRITE_FROM_FILE));
		loadSpriteButton.setOnAction(e -> {
			SpriteMakerModel sprite = xstreamHandler.getAttributeFromFile();
			mySMC.addPossibleSprite(sprite);
		});
		saveSetupButton.setOnAction(e -> {
			xstreamHandler.saveToFile(mySMC.getScreenData().getDataToSave());
		});
		loadButton = new Button(myResources.getString(LOAD_SCREEN_MODEL_FROM_FILE));
		loadButton.setOnAction((click) -> {
			mySMC.getScreenData().setObjectsOnScreen(xstreamHandler.getScreenModelFile());
		});
	}
}