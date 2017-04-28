package gameauthorgui;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class WelcomeScreen extends VBox {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	public static final int WRAPPING_WIDTH = 750;
	private Font titleFont = new Font(50);
	
	public WelcomeScreen(String genreName){
		super(100);
		createWelcome(genreName);
		this.setAlignment(Pos.CENTER);
	}
	
	private void createWelcome(String genreName){
		
		Text welcome = new Text(genreName+ " " + myResources.getString("CREATION_ENVIRONMENT_SUFFIX"));
		welcome.setFont(titleFont);
		welcome.setWrappingWidth(WRAPPING_WIDTH);
		welcome.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().add(welcome);
	}
}
