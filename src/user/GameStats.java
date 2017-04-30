package user;

import java.util.HashMap;
import java.util.Map;

public class GameStats {
	
	private final String RATING_NAME = "Rating";
	private final String PLAYS_NAME = "Number of Plays";
	private final String LIKES_NAME = "Likes";
	
	private Integer rating; 
	private Integer numPlays; 
	private Integer numLikes; 
	
	private Map<String, String> displayableStats; 

	// default constructor 
	
	public GameStats(){
		rating = 0; 
		numPlays = 0; 
		numLikes = 0; 
		displayableStats = new HashMap<String, String>();
		displayableStats.put(RATING_NAME, Integer.toString(rating));
		displayableStats.put(PLAYS_NAME, Integer.toString(numPlays));
	}
	
	public void rate(int rate){
		rating = rate; 
		displayableStats.put(RATING_NAME, Integer.toString(rate));
	}
	
	public void incrementPlays(){
		numPlays++;
		displayableStats.put(PLAYS_NAME, Integer.toString(numPlays));
	}
	
	public void incrementLikes(){
		numLikes++;
		displayableStats.put(LIKES_NAME, Integer.toString(numLikes));
	}
	
	public Map<String,String> getDisplayableStats(){
		return displayableStats; 
	}
	
	
	// TODO: BUILD A WAY TO MODIFY THESE 
	public void addCustomStat(String name, String value){
		displayableStats.put(name, value);
	}
	
	public String getCustomStat(String name){
		return displayableStats.get(name);
	}
	
}
