/**
 * 
 */
package player.levelChoice;

import javafx.scene.control.MenuItem;

/**
 * @author Zhiyong
 * 
 * This interface is used to create different kinds of MenuItems
 * In the game level choice page, the user can choose language,level etc.
 * These items will be created from the interface.
 * The interface will be implemented in {@code player.levelChoice.LevelMenuHandler}.
 */
public interface MenuItemHandler {
	
	/**
	 * This is a method to handle the events after the user
	 * wants to use a MenuItem. For example, the user can choose
	 * a level by click on the corresponding level
	 */
	public void handle();
	
	/**
	 * @return MenuItem
	 * This method is used to create a MenuItem and return the MenuItem
	 * to the class that use to create a MenuItem
	 */
	public MenuItem getMenuItem();

}
