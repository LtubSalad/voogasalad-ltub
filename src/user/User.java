package user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class User {

	private String name; 
	private String directory; 
	private Image image; 
	private int highScore; 
	private Map<String, Integer> playedGameRatings; 
	private Map<String, Integer> authoredGameRatings; 
	

	public User(String username, String imageFile) {
		this.name = username; 
		this.image = new Image(imageFile);
		// directory
		playedGameRatings = new HashMap<String, Integer>(); 
		authoredGameRatings = new HashMap<String, Integer>(); 
	}
	
		
	public void onGameEnded(String gameFile, int score){
		
	}
	
	
	public void recordGamePlayed(String gameFile){
		if (!playedGameRatings.containsKey(gameFile)){
			playedGameRatings.put(gameFile, null);
		}
	}
	
	public void recordGameRated(String gameFile, Integer rating){
		playedGameRatings.put(gameFile, rating);
	}
	
	public void recordAuthored(String authoredFile){
		if (!authoredGameRatings.containsKey(authoredFile)){
			authoredGameRatings.put(authoredFile, null);
		}
	}

	public void rateAuthored(String authoredFile, Integer rating){
		authoredGameRatings.put(authoredFile, rating);
	}
	
	public Map<String, Integer> getPlayedGamePrefs(){
		return Collections.unmodifiableMap(playedGameRatings);
	}
	
	public Map<String, Integer> getAuthoredGamePrefs(){
		return Collections.unmodifiableMap(authoredGameRatings);
	}
	
	

}
