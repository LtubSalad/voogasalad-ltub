package player.levelChoice;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * @author Zhiyong
 *
 */
public abstract class SettingMenuHandler implements MenuItemHandler{
	private MenuItem setting;
	//add optional shortcutcode for the setting
	public SettingMenuHandler(String source){
		setting = new MenuItem(source);
//		setting.setAccelerator(new KeyCodeCombination(keyCode, shortcut));
		setting.setOnAction(e -> handle());
	}

	@Override
	public abstract void handle();

	@Override
	public MenuItem getMenuItem() {

		return setting;
	}
	
}