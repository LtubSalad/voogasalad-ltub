package user;

import javafx.scene.image.Image;
import user.UsersModel.MessagingHandler;

/**
 * @author tahiaemran
 *
 */
public class User {

	private String name; 
	private String directory; 
	private Image image; 

	private UserHistory history; 
	private MessagingHistory messages;
	
	private MessagingHandler handler; 

	public User(String username, String imageFile, MessagingHandler MH) {
		// directory
		this.name = username; 
		//this.image = new Image(imageFile);
		history = new UserHistory(); 
		messages = new MessagingHistory(username); 
		handler = MH; 
	}

	public void onGameEnded(String gameFile, int score){
		// check if game is new 
		history.checkHighScore(gameFile,score);
		history.incrementPlays(gameFile);
	}


	public void recordGamePlayed(String name, String gameFile){
		history.addPlayedGame(name, gameFile);
	}


	public void recordAuthored(String name, String authoredFile){
		history.addAuthoredGame(name, authoredFile);
	}

	public UserHistory getUserHistory(){
		return history; 
	}

	public String getName(){
		return name; 
	}

	public Image getImage(){
		return image; 
	}


	public void recieveMessage(String sender, String message) {
		messages.recieve(sender, message);
	}
	
	public void sendMessage(String recipient, String message){
		handler.sendMessage(name, message, recipient);
		messages.addSent(recipient, message);
		System.out.println("message being sent to recipient " + recipient);
	}

}
