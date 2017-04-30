/**
 * 
 */
package player.levelChoice;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.App;

/**
 * @author Zhiyong
 *
 */
public class BackgroundSettingMenuHandler extends SettingMenuHandler{
	
	private ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);

	public BackgroundSettingMenuHandler(String source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle() {
		Stage stage = new Stage();		
		Pane root = new Pane();
		
		Text splash = new Text();		
		splash = new Text(10,50, myResources.getString("BackgroundSetting"));
		splash.setFont(Font.font(25));
		splash.setFill(Color.DARKVIOLET);
		
		ColorPicker colorPicker = new ColorPicker();
        Circle circle = new Circle(100,100,30);
        circle.setFill(colorPicker.getValue());
        colorPicker.setOnAction(e -> circle.setFill(colorPicker.getValue()));
        
        Button okayButton = new Button(myResources.getString("OkayButton"));
        okayButton.setLayoutX(200);
        okayButton.setLayoutY(100);
        okayButton.setId("btnLogin");
        okayButton.setOnAction(e -> {
        	root.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
        	
        	//TODO: need to change the background of the playpane
        	
        	stage.close();
        });
		
        root.getChildren().addAll(circle, colorPicker, okayButton, splash);		

		Scene scene = new Scene(root,400,200);
		scene.getStylesheets().setAll("/styleSheets/login.css");
		stage.setScene(scene);		
		stage.show();	
		
	}

}
