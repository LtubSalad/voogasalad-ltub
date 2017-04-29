package user.view;

import java.util.List;
import java.util.Map;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import user.GameHistory;
import user.User;

public class UserSocialPage extends BorderPane{

	private User myUser; 

	private HBox contents; 
	
	private VBox playHistory; 
	private VBox authoredHistory; 
	private VBox messages; 
	private VBox profileInfo; 

	public UserSocialPage(User user){
		myUser = user; 
		configProfile(); 
		contents = new HBox(); 
		playHistory = configHistory(user.getUserHistory().getPlayedHistory());
		authoredHistory = configHistory(user.getUserHistory().getAuthoredHistory());
		messages = configMessageBox();
		configure(); 
	}

	private void configure() {
		contents.getChildren().addAll(playHistory, authoredHistory, messages);
		this.setCenter(profileInfo);
		this.setBottom(contents);
	}

	private VBox configMessageBox() {
		// TODO finish this 
		VBox messages = new VBox();
		messages.getChildren().add(new Text("write your message here"));
		return messages; 
	}



	private VBox configHistory(Map<String, GameHistory> history) {
		VBox hist = new VBox(); 
		for (String game: history.keySet()){
			VBox gameSpecific = new VBox(); 
			GameHistory GH = history.get(game);
			gameSpecific.getChildren().add(new Text(game));
			VBox stats = makeStatsPane(GH.getStats());
			VBox comments = makeCommentsPane(GH.getComments());
			gameSpecific.getChildren().addAll(stats, comments);
			hist.getChildren().add(gameSpecific);
		}
		return hist;
	}



	private VBox makeCommentsPane(Map<String, List<String>> comments) {
		VBox commentsBox = new VBox();
		for (String user: comments.keySet()){
			Text username = new Text(user);
			VBox userComments = new VBox(); 
			comments.get(user).stream().forEach(comment ->{
				userComments.getChildren().add(new Text(comment));
			});
			commentsBox.getChildren().addAll(username, userComments);
		}
		return commentsBox;
	}



	private VBox makeStatsPane(Map<String, String> stats) {
		VBox statsBox = new VBox(); 
		for(String statName: stats.keySet()){
			HBox statLine = new HBox(); 
			Text name = new Text( statName);
			Text stat = new Text(stats.get(statName));
			statLine.getChildren().addAll(name, stat);
			statsBox.getChildren().add(statLine);
		}
		return statsBox; 
	}



	private void configProfile() {
		profileInfo = new VBox();
		ImageView img = new ImageView(myUser.getImage());
		Text name = new Text(myUser.getName());
		profileInfo.getChildren().addAll(img, name);

	}

}
