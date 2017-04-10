package gameDevelopmentInterface;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Jake, Daniel Holds three tabs: the ClassCreator user interface, the
 *         ScreenModelCreator interface, and the GeneralDataModel creator
 *         interface
 *
 *         Has a save button that takes the GeneralModelData, InterfaceData,
 *         ScreenModelData and produces XML files from them to be read from.
 * 
 *         No public methods because they most of its actions are event based.
 */
public class MasterDeveloperInterface {
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private Scene developerScene;
	private BorderPane view;
	private TabPane developerTabs;

	public MasterDeveloperInterface() {
		instantiate();
		developerScene = new Scene(view);
		developerScene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
	}

	private void instantiateTabs() {
		developerTabs = new TabPane();
		Tab classCreatorTab = new Tab("Create AttributeHolder", new AttributeHolderCreator());
		Tab GeneralDataTab = new Tab("General Data", new GeneralDataCreator());
		Tab ScreenSettingView = new Tab("Screen Setting", new ScreenModelCreator());
		ObservableList<Tab> myTabs = developerTabs.getTabs();
		myTabs.addAll(classCreatorTab, GeneralDataTab, ScreenSettingView);
	}
	
	private void instantiate(){
		view=new BorderPane();
		instantiateTabs();
		view.setTop(new TabAdder());
		view.setCenter(developerTabs);
		
	}

	public Scene getScene() {
		return developerScene;
	}
	
	class TabAdder extends HBox{
		private TabAdder(){
			instantiate();
		}
		
		private void instantiate(){
			Button spriteButton=new Button("Create new Sprite");
			Button screenButton = new Button("Create new Screen");
			spriteButton.setOnAction((clicked)->{
				Tab spriteTab=new Tab("Create Sprite", new AttributeHolderCreator());
				developerTabs.getTabs().add(spriteTab);
			});
			screenButton.setOnAction((clicked)->{
				Tab screenTab=new Tab("Create new Screen", new ScreenModelCreator());
				developerTabs.getTabs().add(screenTab);
			});
			this.getChildren().addAll(spriteButton,screenButton);
		}	
	}
}
