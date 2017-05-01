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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import newengine.animation.AnimationImage;
import player.App;

/**
 * @author Zhiyong
 *
 */
public class LevelManager {
	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	private Stage primaryStage;
	private int numberOfLevel;
	private MenuBar menuBar;

	//TODO : add voice search like google


	public LevelManager(Stage primaryStage){
		numberOfLevel = 2;
		this.primaryStage = primaryStage;


	}
	public void show(){

		Menu level = initLevelMenu();
		Menu setting = initSettingMenu();
		Menu help = initHelpMenu();
		menuBar = new MenuBar(level, setting, help);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("Game Level Choice");
		Group root = new Group();
		
		AnimationImage im = new AnimationImage();
		
		
		root.getChildren().add(menuBar);
		Scene scene = new Scene(root);

		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("levelChoiceImagePath")));
		scene.setFill(new ImagePattern(image));
		
		scene.getStylesheets().setAll("/styleSheets/login.css");
		
		HBox hBox = new HBox();
		hBox.getChildren().add(im.getImageView(2));
		hBox.setLayoutX(300);
		hBox.setLayoutY(200);
		root.getChildren().add(hBox);

		Button starting = new Button("Start A Game of Random Level");
		
		
		starting.setLayoutX(500);
		starting.setLayoutY(600);
		starting.setId("starting");
		starting.setMinWidth(100);
		starting.setMinHeight(100);
		root.getChildren().add(starting);
		
		primaryStage.setScene(scene);
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(null);
		primaryStage.show();
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
