package gameDevelopmentInterface;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
	private TabPane developerTabs;
	private AttributeHolderCreator myAttributeHolderCreator = new AttributeHolderCreator();
	private ScreenModelCreator myScreenModelCreator = new ScreenModelCreator(myAttributeHolderCreator);

	public MasterDeveloperInterface() {
		instantiateTabs();
		developerScene = new Scene(developerTabs);
		developerScene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
	}

	private void instantiateTabs() {
		developerTabs = new TabPane();
		Tab classCreatorTab = new Tab("Create AttributeHolder", myAttributeHolderCreator);
		Tab GeneralDataTab = new Tab("General Data", new GeneralDataCreator());
		Tab ScreenSettingView = new Tab("Screen Setting", myScreenModelCreator);
		ObservableList<Tab> myTabs = developerTabs.getTabs();
		myTabs.addAll(classCreatorTab, GeneralDataTab, ScreenSettingView);
	}

	public Scene getScene() {
		return developerScene;
	}
}
