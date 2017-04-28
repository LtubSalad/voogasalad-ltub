package gameDevelopmentInterface;

import data.SpritesForScreenUse;
import data.ScreenModelData;
import data.SpriteMakerModel;
import data.SpritesForScreenUse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.BorderPane;
/**
 * 
 * The job of the ScreenModelCreator is to provide an interface for the developer to set all the data for a 
 * given screen. It has no public methods aside from its instantiator. Things it does: binding between the 
 * DummyScreenView and the ScreenModel, allowing the user to place objects on the screen. Adding an object 
 * to the screen should modify the ScreenModel and display something on the DummyScreen if the object implements
 * Sprite.
 * 
 * @author Jake
 *
 */
public class ScreenModelCreator extends BorderPane {
	private ObservableMap<String,String> myGeneralData;
	private ScreenModelData myScreenData;
	private ScreenObjectHolder myObjectsToPlace;
	private ScreenMap myScreen;
	private TowerSetButtonsPanel myButtonsPanel;
	private SpritesForScreenUse mySpriteModelsToDrag;
	private ObservableList<SpriteMakerModel> possibleSprites = FXCollections.observableArrayList();
	
	public ScreenModelCreator(SpritesForScreenUse spriteModelsToDrag, GeneralDataCreator gdc, ScreenModelData screenData) {
		myScreenData = screenData;
		myScreen = new ScreenMap(this);
		myButtonsPanel = new TowerSetButtonsPanel(this);
		mySpriteModelsToDrag = spriteModelsToDrag;
		myObjectsToPlace = new ScreenObjectHolder(this, myScreenData);
		myGeneralData = gdc.getAllData();
		this.setTop(new GeneralGameDataBar(myGeneralData));
		this.setBottom(myObjectsToPlace);
		this.setCenter(new ScrollingEnvironmentSetter(myScreen));
		this.setRight(myButtonsPanel);
	}
	/**
	 * Adds a new sprite to the screen
	 * @param attr
	 */
	public void addPossibleSprite(SpriteMakerModel attr) {
		possibleSprites.add(attr);
	}
	/**
	 * 
	 * @return All sprites currently supposed to be on the screen
	 */
	public ObservableList<SpriteMakerModel> getPossibleSprites() {
		return possibleSprites;
	}
	/**
	 * 
	 * @return the screen this border pane instantiates for use in ScreenObjectHolder
	 */
	public ScreenMap getScreen() {
		return myScreen;
	}
	/**
	 * 
	 * @return all the data currently held on the screen
	 */
	public ScreenModelData getScreenData() {
		return myScreenData;
	}
	
}