package player;

import java.util.ArrayList;
import java.util.Arrays;

import gameauthorgui.tower.TowerAuthor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import newengine.animation.AnimationImage;
import user.UsersModel;

public class MainMenu {

	public static final String CSS_LOCATION = "/styleSheets/login.css";

	private Stage primaryStage;
	private UsersModel usersModel;
	private Scene scene;
	private Button playGame = new Button("Play Game");
	private Button authorGame = new Button("Author Game");
	private Button socialCenter = new Button("Social Center");

	public MainMenu(Stage primaryStage, UsersModel usersModel){	
		this.primaryStage = primaryStage;
		this.usersModel = usersModel;
		BorderPane root = new BorderPane();
		VBox buttons = new VBox();
		buttons.setId("vbox");
		buttons.getChildren().addAll(playGame, authorGame, socialCenter);

		playGame.setMaxWidth(Double.MAX_VALUE);
		authorGame.setMaxWidth(Double.MAX_VALUE);
		socialCenter.setMaxWidth(Double.MAX_VALUE);


		playGame.setId("main-button");
		authorGame.setId("main-button");
		socialCenter.setId("main-button");
		root.setCenter(buttons);		
		HBox hBox1 = getHBox(1, 200,200);
		HBox hBox2 = getHBox(2, 400,200);
		HBox hBox3 = getHBox(3, 200,400);
		HBox hBox4 = getHBox(4, 400,400);
		HBox[] h = {hBox1, hBox2, hBox3, hBox4};


		root.getChildren().addAll(new ArrayList<HBox>(Arrays.asList(h)));

		initHandlers();
		scene = new Scene(root, App.WIDTH, App.HEIGHT);
		scene.getStylesheets().setAll(CSS_LOCATION);
		primaryStage.setScene(scene);
	}

	private void initHandlers() {
		playGame.setOnMouseClicked((event) -> {
			new GameChooser(primaryStage);
		});
		authorGame.setOnMouseClicked((event) -> {
			TowerAuthor developerView = new TowerAuthor();
			primaryStage.setScene(developerView.getScene());
			//primaryStage.setFullScreen(true);
		});
		socialCenter.setOnMouseClicked((event) -> {
			primaryStage.setScene(usersModel.getUserSocialPage());
		});
	}

	public Scene getScene() {
		return scene;
	}

	private HBox getHBox( int seed, int x, int y){
		AnimationImage imageView = new AnimationImage();	
		HBox hBox = new HBox();
		hBox.getChildren().add(imageView.getImageView(seed));
		hBox.setLayoutX(x);
		hBox.setLayoutY(y);

		return hBox;
	}

}
