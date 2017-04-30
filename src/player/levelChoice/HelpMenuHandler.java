package player.levelChoice;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * @author Zhiyong
 *
 */
public class HelpMenuHandler implements MenuItemHandler{
	private MenuItem viewHelp;
	public HelpMenuHandler(String source){
		viewHelp = new MenuItem(source);
		viewHelp.setId("menu-item");
		viewHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
		viewHelp.setOnAction(e -> handle());
	}

	@Override
	public void handle() {
		HelpMessage help = new HelpMessage();
		help.show();		
	}

	@Override
	public MenuItem getMenuItem() {

		return viewHelp;
	}
	
}
