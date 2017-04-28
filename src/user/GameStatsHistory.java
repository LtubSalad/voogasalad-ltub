package user;

import java.util.List;
import java.util.Map;

/**
 * @author tahiaemran
 * 
 *
 */
public interface GameStatsHistory {
	public String getRatings(String gameName); 
	public String getNumPlays(String gameName); 
	public Map<String, String> getSpecificStats(); 
	public void rate(String gameName, int rating);
	public void incrementPlays(String gameName); 
	public void addComment(String gameName, String username, String comment); 
	public void addGameToHistory(String filename);
	public List<String> getGameHistory(); 
}
