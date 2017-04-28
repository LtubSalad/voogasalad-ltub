package user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class User {

	private String name; 
	private String directory; 
	private Image image; 

	private UserHistory history; 

	
	
	public User(String username, String imageFile) {
		this.name = username; 
		this.image = new Image(imageFile);
		// directory
	}
	
		
	public void onGamePlayed(String gameFile, int score){
		// increment game plays
		// modify score 
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

}
