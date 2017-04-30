package user.view;

import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.TilePane;
import user.User;
import user.UsersModel;

public class SocialCenterView extends TabPane {
	
	private UsersModel usersToVisualize; 
	private User current; 
	
	public SocialCenterView(UsersModel currentUsers){
		this.usersToVisualize = currentUsers; 
		current = usersToVisualize.getCurrentUser();
		renderMainUserPage(); 
		renderOtherUsersPane(); 
	}

	private void renderOtherUsersPane() {
		TilePane buttonsPane = new TilePane(); 
		Map<String, User> allUsers = usersToVisualize.getUserData();
		allUsers.keySet().stream().forEach(username ->{
			User user =  allUsers.get(username);
			Button userLink = makeButton(username, user);
			buttonsPane.getChildren().add(userLink);
			this.getTabs().stream().forEach(tab ->{
				tab.setContent(buttonsPane);
			});
		});
	}

	private Button makeButton(String username, User user) {
		Button b = new Button(username);
		b.setOnMouseClicked(e ->{
			Tab newTab = new Tab(username);
			newTab.setContent(new UserSocialPage(user));
			this.getTabs().add(newTab);
			renderOtherUsersPane();
		});
		return b; 
	}

	private void renderMainUserPage() {
		UserSocialPage myUserPage = new UserSocialPage(current);
		Tab myTab = new Tab(current.getName());
		myTab.setContent(myUserPage);
		myTab.setClosable(true);
		this.getTabs().add(myTab);
	}
	
	// make the pane with all online users (Setbottom)  
	
	
}
