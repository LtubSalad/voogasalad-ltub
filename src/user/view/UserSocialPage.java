package user.view;

import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import user.GameHistory;
import user.MessagingHistory;
import user.User;

public class UserSocialPage extends BorderPane{
	
	//BorderPane myPageView; 

	private User myUser; 

	private HBox contents; 
	
	private VBox playHistory; 
	private VBox authoredHistory; 
	private VBox messages; 
	private BorderPane profileView; 

	public UserSocialPage(User user){
		myUser = user; 
		configProfile(); 
		contents = new HBox(100); 
		playHistory = configHistory(user.getUserHistory().getPlayedHistory());
		authoredHistory = configHistory(user.getUserHistory().getAuthoredHistory());
		messages = configMessageBox();
		configure(); 
		
	}

	//TODO CONFIG OBSERVABLEZ
	private void configure() {
		contents.getChildren().addAll(playHistory, authoredHistory, messages);
		contents.fillHeightProperty().setValue(true);
		// TODO: Figure this out
		contents.getStylesheets().add("resources/socialStyle.css");
		contents.getStyleClass().add("hbox");
		this.setTop(profileView);
		this.setCenter(contents);
		this.getStylesheets().add("resources/socialStyle.css");
		this.getStyleClass().add("borderpane");
	}

	private VBox configMessageBox() {
			MessagingView MV = new MessagingView(myUser);
			MessagingHistory MH = myUser.getMessagingHistory();
			MH.addObserver(MV);
			return MV; 
		}

	private VBox configHistory(Map<String, GameHistory> history) {
		VBox hist = new VBox(10); 
		for (String game: history.keySet()){
			GameStatsView gameSpecific = new GameStatsView(game, history.get(game));
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



	private void configProfile() {
		profileView = new BorderPane();
		VBox profileInfo = new VBox(10);
		ImageView img = new ImageView(myUser.getImage());
		System.out.println("did add the image");
		
		Text name = new Text(myUser.getName());
		name.setFont(new Font(18));
		TextFlow textWrapper = new TextFlow(name);
		textWrapper.setTextAlignment(TextAlignment.CENTER);
		
		profileInfo.getChildren().addAll(img, textWrapper);
		profileInfo.setAlignment(Pos.CENTER);
		profileView.setCenter(profileInfo);
	}

}
