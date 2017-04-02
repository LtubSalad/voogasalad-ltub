package gameDevelopmentInterface;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author Jake, Daniel
 * Holds three tabs: the ClassCreator user interface, the ScreenModelCreator interface, 
 * and the GeneralDataModel creator interface
 *
 * Has a save button that takes the GeneralModelData, InterfaceData, ScreenModelData and produces XML files
 * from them to be read from.
 * 
 * No public methods because they most of its actions are event based.
 */
public class MasterDeveloperInterface{
	private Scene developerScene;
	private TabPane developerTabs;
	public MasterDeveloperInterface() {
		instantiateTabs();
		developerScene=new Scene(developerTabs);
	}
	
	private void instantiateTabs(){
		developerTabs=new TabPane();
		Tab classCreatorTab = new Tab("Create AttributeHolder", new AttributeHolderCreator());
		ObservableList<Tab> myTabs= developerTabs.getTabs();
		myTabs.add(classCreatorTab);
	}
	
	public Scene getScene(){
		return developerScene;
	}
}
