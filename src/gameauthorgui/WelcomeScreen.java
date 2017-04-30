package gameauthorgui;

import java.util.ResourceBundle;

import data.DeveloperData;
import gameauthorgui.inputhelpers.ImageParameterInput;
import gameauthorgui.inputhelpers.StringParameterInput;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

public class WelcomeScreen extends VBox {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	public static final int WRAPPING_WIDTH = 750;
	public static final int VERTICAL_GAP = 100;
	private Font titleFont = new Font(50);
	private DeveloperData modelData;
	
	public WelcomeScreen(String genreName, DeveloperData modelData){
		super(VERTICAL_GAP);
		this.modelData = modelData;
		createWelcome(genreName);
		this.setAlignment(Pos.CENTER);
	}
	
	private void createWelcome(String genreName){
		
		Text welcome = new Text(genreName + " " + myResources.getString("CREATION_ENVIRONMENT_SUFFIX"));
		welcome.setFont(titleFont);
		welcome.setWrappingWidth(WRAPPING_WIDTH);
		welcome.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().add(welcome);
		createUserData();
	}
	
	public void createUserData(){
		StringParameterInput gameName = new StringParameterInput(myResources.getString("GAME_NAME"));
		gameName.setAlignment(Pos.CENTER);
		gameName.getTextProperty().addListener(e -> modelData.addData(new Pair<String, String>("GAME_NAME", gameName.getValue())));
		ImageParameterInput icon = new ImageParameterInput(myResources.getString("GAME_ICON"));
		icon.setAlignment(Pos.CENTER);
		icon.getTextProperty().addListener(e -> modelData.addData(new Pair<String, String>("GAME_ICON", icon.getValue())));
		this.getChildren().addAll(gameName, icon);
	}
}
