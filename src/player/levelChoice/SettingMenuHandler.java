package player.levelChoice;

import javafx.scene.control.MenuItem;

/**
 * @author Zhiyong
 * This is an abstract class to handle the Setting Menu in the main page
 *
 */
public abstract class SettingMenuHandler implements MenuItemHandler{
	private MenuItem setting;
	
	/**
	 * @param source of the MenuItem
	 * Set the name of the MenuItem as the source
	 */
	public SettingMenuHandler(String source){
		setting = new MenuItem(source);
		setting.setOnAction(e -> handle());
	}

	@Override
	public abstract void handle();

	@Override
	public MenuItem getMenuItem() {
		return setting;
	}
	
}