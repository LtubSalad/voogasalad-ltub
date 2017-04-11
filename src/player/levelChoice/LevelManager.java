/**
 * 
 */
package player.levelChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * @author Zhiyong
 *
 */
public class LevelManager {
	public static final String RESOURCES_LOCATION = "resources/menu";
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	
	
	private  ResourceBundle myResources = ResourceBundle.getBundle(RESOURCES_LOCATION);
	private int numberOfLevel;
	private MenuBar menuBar;
	
	//TODO : add voice search like google
	
	
	public LevelManager(){
		numberOfLevel = 2;
		
		
		
	}
	public void show(){
		
		Menu level = initLevelMenu();
		Menu setting = initSettingMenu();
		Menu help = initHelpMenu();
		menuBar = new MenuBar(level, setting, help);
		
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Game Level Choice");
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		root.getChildren().add(menuBar);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Menu initHelpMenu() {		
		MenuItemHandler h = new HelpMenuHandler(myResources.getString("help"));		
		return new Menu(myResources.getString("help"), null , h.getMenuItem());
	}

	private Menu initSettingMenu() {
		MenuItemHandler s = new SettingMenuHandler(myResources.getString("background"),KeyCode.S, KeyCombination.SHORTCUT_DOWN);
		Menu languageMenu = new Menu(myResources.getString("language"));
		addlanguageChoice(languageMenu);
		return new Menu(myResources.getString("setting"), null , s.getMenuItem(), languageMenu);
	}

	private void addlanguageChoice(Menu languageMenu) {
		List<MenuItem> list = new ArrayList<>();
		String number = myResources.getString("languageNumber");
		int n = number.charAt(0)-'0';
		for(int i = 0 ; i < n; i++){
			int temp = i + 1;
			String str =myResources.getString("language" + temp);
		list.add(new SettingMenuHandler(str, KeyCode.A, KeyCombination.SHORTCUT_DOWN).getMenuItem());
		}
		languageMenu.getItems().addAll(list);
	}
	private Menu initLevelMenu() {

		MenuItemHandler[] levelList = new MenuItemHandler[numberOfLevel];
		MenuItem[] levelMenuList = new MenuItem[numberOfLevel];
		for(int i = 0; i < numberOfLevel; i++){
			levelList[i] = new LevelMenuHandler(myResources.getString("level") + i, i);	
			levelMenuList[i] = levelList[i].getMenuItem();
		}
		return new Menu(myResources.getString("level"), null, levelMenuList);
	}

	
	public MenuBar getMenuBar(){
		return menuBar;
	}
	
	public int getNumberOfLevel(){
		return numberOfLevel;
	}
	

}
