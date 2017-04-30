package player;

import gameauthorgui.tower.TowerAuthor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.UsersModel;

public class MainMenu {

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
		buttons.getChildren().addAll(playGame, authorGame, socialCenter);
		root.setCenter(buttons);

		initHandlers();
		scene = new Scene(root, App.WIDTH, App.HEIGHT);
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
