package user;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.view.UserSocialPage;

public class UserTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//User testuser = new User("me", "resources/green_house.jpg");
//		testuser.recordAuthored("Test Tower Defense", "yike.xml");
//		testuser.recordGamePlayed("Test Played", "yike.xml");
//		UserSocialPage page = new UserSocialPage(testuser);
//		Scene scene = new Scene(page);
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
		
		UsersModel model = new UsersModel(); 
		model.addUser("me", "1234");
		User me = model.getUserByName("me");
		model.addUser("not me", "4321");
		User notMe = model.getUserByName("not me");
		me.sendMessage("not me", "hi");
		
	}
	
	public static void main (String[] args){
		launch(args);
	}

}
