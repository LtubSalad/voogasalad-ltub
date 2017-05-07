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
 * Handle the background color or image of the player page
 * This class implements the interface {@code player.levelChoice.SettingMenuHandler}
 */
public class BackgroundSettingMenuHandler extends SettingMenuHandler{

	public static final String CSS_LOCATION = "/styleSheets/login.css";
	private ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	private Stage stage;

	public BackgroundSettingMenuHandler(String source) {
		super(source);
		stage = new Stage();
	}

	@Override
	public void handle() {				
		Pane root = new Pane();
		Text splash = createText();
		ColorPicker colorPicker = new ColorPicker();
		Circle circle = new Circle(100,100,30);
		circle.setFill(colorPicker.getValue());
		colorPicker.setOnAction(e -> circle.setFill(colorPicker.getValue()));

		Button okayButton = createButton(root, colorPicker);

		root.getChildren().addAll(circle, colorPicker, okayButton, splash);		

		Scene scene = new Scene(root,400,200);
		scene.getStylesheets().setAll(CSS_LOCATION);
		stage.setScene(scene);		
		stage.show();			
	}

	private Text createText() {
		Text splash = new Text();		
		splash = new Text(10,50, myResources.getString("BackgroundSetting"));
		splash.setFont(Font.font(25));
		splash.setFill(Color.DARKVIOLET);
		return splash;
	}

	private Button createButton(Pane root, ColorPicker colorPicker) {
		Button okayButton = new Button(myResources.getString("OkayButton"));
		okayButton.setLayoutX(200);
		okayButton.setLayoutY(100);
		okayButton.setId("btnLogin");
		okayButton.setOnAction(e -> {
			root.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
			stage.close();
		});
		return okayButton;
	}
}