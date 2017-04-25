package newauthorgui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WelcomeTowerScreen extends VBox {
	
	public WelcomeTowerScreen(){
		super(100);
		createWelcome();
		this.setAlignment(Pos.CENTER);
	}
	
	private void createWelcome(){
		//TODO resource file
		Text welcome = new Text("Tower Defense Game Creation Environment");
		this.getChildren().add(welcome);
	}
}
