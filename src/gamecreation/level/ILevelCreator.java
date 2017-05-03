package gamecreation.level;

import javafx.scene.Node;

/**
 * This defines the expected methods for a LevelCreator class. A LevelCreator is responsible for
 * taking in a new LevelData and setting its basic features. LevelCreator is a GUI component.
 * 
 * Note all LevelCreators are set to be held in a LevelCreatorHolder which allows additional user
 * functionality in terms of adding the actual different types of LevelCreators
 * 
 * @author Matthew Tribby
 */
public interface ILevelCreator {
	
	/**
	 * Creates the content which goes inside the physical GUI component. This will include
	 * all the GUI inputs that need to be taken in to create LevelData.
	 */
	public void createContent();
	
	/**
	 * This method is meant for future extensibility / flexibility. This method will allow
	 * a user to add a JavaFX node to the display inside the GUI LevelCreator
	 * @param node Node to be added to the GUI
	 */
	public void addToContent(Node node);
	
	/**
	 * Returns a JavaFX Node which has the current content of the LevelCreator
	 * @return Current content of LevelCreator GUI component
	 */
	public Node getCurrentContent();
	
	/**
	 * Remove this LevelCreator from its LevelCreatorHolder Object. 
	 */
	public void remove();
}
