package player.levelChoice;

import java.util.ResourceBundle;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import player.App;
import utilities.MessageShowing;
import utilities.PopUpMessage;

/**
 * @author Zhiyong
 * This class implements the MenuItemHandler
 * This class will handle the Help information in the MenuItem
 */
public class HelpMenuHandler implements MenuItemHandler{
	private ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	private MenuItem viewHelp;

	public HelpMenuHandler(String source){
		viewHelp = new MenuItem(source);
		viewHelp.setId("menu-item");
		viewHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));
		viewHelp.setOnAction(e -> handle());
	}

	@Override
	public void handle() {
		PopUpMessage help = new MessageShowing();
		help.show(myResources.getString("HelpMessageForStarting"));		
	}

	@Override
	public MenuItem getMenuItem() {

		return viewHelp;
	}	
}