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
		model.addUser("not me", "4321");
		User me = model.getUserByName("me");
		User notMe = model.getUserByName("not me");
		
		notMe.sendMessage("me", "hi");
		// is not me recieving
		me.recordAuthored("test game auth", "yike.xml");
		me.recordGamePlayed("test play", "yike.xml");
		
		UserSocialPage page = new UserSocialPage(me);
		
		Scene scene = new Scene(page, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main (String[] args){
		launch(args);
	}

}
