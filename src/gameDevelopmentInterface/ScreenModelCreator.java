package gameDevelopmentInterface;

import javafx.scene.layout.BorderPane;

/**
 * 
 * The job of the ScreenModelCreator is to provide an interface for the developer to set all the data for a 
 * given screen. It has no public methods aside from it's instantiator. Things it does: binding between the 
 * DummyScreenView and the ScreenModel, allowing the user to place objects on the screen. Adding an object 
 * to the screen should modify the ScreenModel and display something on the DummyScreen if the object implements
 * Sprite.
 * 
 * @author Jake
 *
 */

public class ScreenModelCreator extends BorderPane {
	private ScreenObjectHolder myObjectsToPlace = new ScreenObjectHolder(this);
	private ScreenMap myScreen = new ScreenMap();
	private ButtonsPanel myButtonsPanel = new ButtonsPanel(this);
	
	public ScreenModelCreator() {
		this.setBottom(myObjectsToPlace);
		this.setCenter(myScreen);
		this.setRight(myButtonsPanel);
	}
	/**
	 * 
	 * @return the screen this border pane instantiates for use in ScreenObjectHolder
	 */
	public ScreenMap getScreen() {
		return myScreen;
	}
}