package player;

import gameauthorgui.tower.TowerAuthor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import newengine.animation.AnimationImage;
import user.UsersModel;

public class MainMenu {
	
	public static final String CSS_LOCATION = "/styleSheets/login.css";

	public static final String STATIC_IMAGE = "/resources/health.jpg";

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
		
//		AnimationImage im = new AnimationImage();
		
		HBox hBox = new HBox();
		hBox.getChildren().add(new ImageView(new Image(STATIC_IMAGE)));//im.getImageView());
		hBox.setLayoutX(300);
		hBox.setLayoutY(200);
		root.getChildren().add(hBox);
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
			primaryStage.setFullScreen(true);
		});
		socialCenter.setOnMouseClicked((event) -> {
			primaryStage.setScene(usersModel.getUserSocialPage());
		});
	}
	
	public Scene getScene() {
		return scene;
	}
	
}
