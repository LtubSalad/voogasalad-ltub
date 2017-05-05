package player.levelChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import newengine.animation.AnimationImage;
import player.App;

/**
 * @author Zhiyong
 * 
 * This is create the level choice for the player
 * In this levelManager, the developer will use
 * the {@code player.levelChoice.LevelMenuHandler}
 *
 */
public class LevelManager {
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final String CSS_LOCATION = "/styleSheets/login.css";
	public static final String TITLE = "Game Level Choice";
	public static final int SEED = 2;
	
	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	private Stage primaryStage;
	private int numberOfLevel;
	private MenuBar menuBar;


	public LevelManager(Stage primaryStage){
		numberOfLevel = 2;
		this.primaryStage = primaryStage;
	}
	
	/**
	 * show the Level MenuItem to the player
	 * The player can choose a level to start a game or choose a random game
	 */
	public void show(){
		//Stage primaryStage = new Stage();
		primaryStage.setTitle(TITLE);
		Group root = new Group();

		Menu level = initLevelMenu();
		Menu setting = initSettingMenu();
		Menu help = initHelpMenu();
		menuBar = new MenuBar(level, setting, help);
		root.getChildren().add(menuBar);
		
		AnimationImage im = new AnimationImage();		
		
		Scene scene = new Scene(root);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("levelChoiceImagePath")));
		scene.setFill(new ImagePattern(image));		
		scene.getStylesheets().setAll(CSS_LOCATION);
		
		HBox hBox = new HBox();
		hBox.getChildren().add(im.getImageView(SEED));
		hBox.setLayoutX(300);
		hBox.setLayoutY(200);
		root.getChildren().add(hBox);

		Button starting = createButton();		
		root.getChildren().add(starting);
		
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(null);
		primaryStage.show();
	}
	
	private Button createButton() {
		Button starting = new Button("Start A Game of Random Level");				
		starting.setLayoutX(500);
		starting.setLayoutY(600);
		starting.setId("starting");
		starting.setMinWidth(100);
		starting.setMinHeight(100);
		return starting;
	}

	private Menu initHelpMenu() {		
		MenuItemHandler h = new HelpMenuHandler(myResources.getString("help"));		
		return new Menu(myResources.getString("help"), null , h.getMenuItem());
	}

	private Menu initSettingMenu() {
		MenuItemHandler s = new BackgroundSettingMenuHandler(myResources.getString("background"));
		Menu languageMenu = new Menu(myResources.getString("language"));
		addlanguageChoice(languageMenu);
		return new Menu(myResources.getString("setting"), null , s.getMenuItem(), languageMenu);
	}

	private void addlanguageChoice(Menu languageMenu) {
		List<MenuItem> list = new ArrayList<>();
		String number = myResources.getString("languageNumber");
		int n = number.charAt(0)-'0';
		for(int i = 0 ; i < n; i++){
			list.add(new LanguageSettingMenuHandler(myResources.getString("language" + (i + 1))).getMenuItem());
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