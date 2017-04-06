package LevelChoice;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * @author Zhiyong
 *
 */
public class SettingMenuHandler implements MenuItemHandler{
	private MenuItem setting;
	//add optional shortcutcode for the setting
	public SettingMenuHandler(String source, KeyCode  keyCode, KeyCombination.Modifier ... shortcut){
		setting = new MenuItem(source);
		setting.setAccelerator(new KeyCodeCombination(keyCode, shortcut));
		setting.setOnAction(e -> handle());
	}

	@Override
	public void handle() {
		
		
	}

	@Override
	public MenuItem getMenuItem() {

		return setting;
	}
	
}