package user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PlayingHistory implements GameStatsHistory {
	
	private List<String> playedGames; 
	
	private Map<String, String> playedGameRatings;
	private Map<String, Integer> playedGameNums;
	private Map<String, List<Comment>> myComments; 
	
	@Override	
	public String getRatings(String gameName) {
		if (playedGameRatings.containsKey(gameName)){
			return playedGameRatings.get(gameName);
		}
		else{
			return Integer.toString(0);
		}
	}
	@Override
	public String getNumPlays(String gameName) {
		if (playedGameNums.containsKey(gameName)){
			return Integer.toString(playedGameNums.get(gameName));
		}
		else{
			return Integer.toString(0);
		}
	}
	@Override
	public Map<String, String> getSpecificStats() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void rate(String gameName, int rating) {
		playedGameRatings.put(gameName, Integer.toString(rating));
		
	}
	@Override
	public void incrementPlays(String gameName) {
		if (playedGameNums.containsKey(gameName)){
			int newPlays = playedGameNums.get(gameName)+1;
			playedGameNums.put(gameName, newPlays);
		}
		else{
			playedGameNums.put(gameName, 1);
		}
		
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
		playedGames.add(filename);
		playedGameRatings.put(filename, Integer.toString(0));
		playedGameNums.put(filename, 0);
		myComments.put(filename, new ArrayList<Comment>());
	}
	@Override
	public List<String> getGameHistory() {
		return Collections.unmodifiableList(playedGames);
	} 
	
	

	
	
}
