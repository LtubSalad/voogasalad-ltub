package gameDevelopmentInterface;

import data.AttributeData;
import data.AttributesForScreenUse;
import gameDevelopmentInterface.attributeCreator.AttributeHolderCreator;
import gameDevelopmentInterface.attributeCreator.GroundUpAttributeCreator;
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
	private static final String SCREEN_SETTING = "Screen Setting";
	private static final String GENERAL_DATA = "General Data";
	private static final String CREATE_ATTRIBUTE_HOLDER = "Create AttributeHolder";
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private Scene developerScene;
	private BorderPane view;
	private TabPane developerTabs;
	private AttributesForScreenUse attributesModel = new AttributesForScreenUse();
	private AttributeHolderCreator myAttributeHolderCreator = new AttributeHolderCreator(attributesModel);
	private GeneralDataCreator myGeneralDataCreator = new GeneralDataCreator();
	private ScreenModelCreator myScreenModelCreator = new ScreenModelCreator(attributesModel, myGeneralDataCreator);

	public MasterDeveloperInterface() {
		instantiate();
		developerScene = new Scene(view);
	//	developerScene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
	}

	private void instantiateTabs() {
		developerTabs = new TabPane();
		Tab classCreatorTab = new Tab(CREATE_ATTRIBUTE_HOLDER, myAttributeHolderCreator);
		Tab GeneralDataTab = new Tab(GENERAL_DATA, myGeneralDataCreator);
		Tab ScreenSettingView = new Tab(SCREEN_SETTING, myScreenModelCreator);
		ObservableList<Tab> myTabs = developerTabs.getTabs();
		myTabs.addAll(classCreatorTab, GeneralDataTab, ScreenSettingView);
	}

	private void instantiate() {
		view = new BorderPane();
		instantiateTabs();
		view.setTop(new TabAdder());
		view.setCenter(developerTabs);

	}

	public Scene getScene() {
		return developerScene;
	}

	class TabAdder extends HBox {
		private static final String CREATE_SPRITE = "Create Sprite";
		private static final String CREATE_NEW_SCREEN = "Create new Screen";
		private static final String CREATE_NEW_SPRITE = "Create new Sprite";
		private static final String CREATE_NEW_ATTRIBUTE= "Create new Attribute";

		private TabAdder() {
			instantiate();
		}


		private void instantiate() {
			Button spriteButton = new Button(CREATE_NEW_SPRITE);
			Button screenButton = new Button(CREATE_NEW_SCREEN);
			Button attributeButton= new Button(CREATE_NEW_ATTRIBUTE);
			spriteButton.setOnAction((clicked) -> {
				Tab spriteTab = new Tab(CREATE_SPRITE, new AttributeHolderCreator(attributesModel));
				developerTabs.getTabs().add(spriteTab);
			});
			screenButton.setOnAction((clicked) -> {
				Tab screenTab = new Tab(CREATE_NEW_SCREEN,
						new ScreenModelCreator(attributesModel, myGeneralDataCreator));
				developerTabs.getTabs().add(screenTab);
			});
			attributeButton.setOnAction((clicked)->{
				Tab attributeTab=new Tab(CREATE_NEW_ATTRIBUTE,
						new GroundUpAttributeCreator());
				developerTabs.getTabs().add(attributeTab);
			});
			this.getChildren().addAll(spriteButton, screenButton,attributeButton);
		}	

	}
}