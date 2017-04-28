package user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthoredGameHistory implements GameStatsHistory{

	private List<String> myGamesMade; 
	
	private Map<String, String> myGameRatings; 
	private Map<String, Integer> myGamePlays; 
	private Map<String, List<Comment>> myComments; 
	
	public AuthoredGameHistory(){
		// TODO: maybe refactor this 
		myGamesMade = new ArrayList<String>(); 
		myGameRatings = new HashMap<String, String>(); 
		myGamePlays = new HashMap<String, Integer>(); 
		myComments = new HashMap<String, List<Comment>>(); 	
	}

	@Override
	public String getRatings(String gameName) {
		if (myGameRatings.containsKey(gameName)){
			return myGameRatings.get(gameName);
		}
		else{
			return Integer.toString(0);
		}
	}

	@Override
	public String getNumPlays(String gameName) {
		if(myGamePlays.containsKey(gameName)){
			return Integer.toString(myGamePlays.get(gameName));
		}
		return Integer.toString(0);
	}

	@Override
	public Map<String,String> getSpecificStats() {
		//FIXME: tahia fix this
		return myGameRatings;
	}

	@Override
	public void rate(String gameName, int rating) {
		myGameRatings.put(gameName, Integer.toString(rating));
		
	}

	@Override
	public void incrementPlays(String gameName) {
		int newValue = myGamePlays.get(gameName)+1;
		myGamePlays.put(gameName, newValue);
		
	}

	@Override
	public void addComment(String gameName, String username, String comment) {
		if (!myComments.containsKey(gameName)){
			List<Comment> comments = new ArrayList<Comment>(); 
			comments.add(new Comment(username, comment));
			myComments.put(gameName, comments);
		}
		else{
			myComments.get(gameName).add(new Comment(username, comment));
		}
	}

	@Override
	public void addGameToHistory(String filename) {
		myGamesMade.add(filename);
		myGameRatings.put(filename, Integer.toString(0));
		myGamePlays.put(filename, 0);
		myComments.put(filename, new ArrayList<Comment>());
	}

	@Override
	public List<String> getGameHistory() {
		return Collections.unmodifiableList(myGamesMade);
	}

	
}
