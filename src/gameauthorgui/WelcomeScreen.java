package gameauthorgui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WelcomeScreen extends VBox {
	
	public WelcomeScreen(String genreName){
		super(100);
		createWelcome(genreName);
		this.setAlignment(Pos.CENTER);
	}
	
	private void createWelcome(String genreName){
		//TODO resource file
		Text welcome = new Text(genreName+ " Creation Environment");
		this.getChildren().add(welcome);
	}
}
