package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tahiaemran
 * 
 * history object for every game authored or played 
 *
 */

public class GameHistory {
	
	private String name; 
	private String filePath; 
	private GameStats stats;  
	private Map<String, List<String>> comments; 
	
	
	public GameHistory(String name, String filepath){
		this.name = name; 
		this.filePath = filepath; 
		stats = new GameStats(); 
		comments = new HashMap<String, List<String>>(); 
	}
	
	
	public Map<String, List<String>>getComments(){
		return comments; 
	}
	
	public Map<String,String> getStats(){
		return stats.getDisplayableStats();
	}
	
	public String getName(){
		return name;
	}
	
	public String getFile(){
		return filePath; 
	}
	
	public void addComment(String user, String message){
		if (!comments.containsKey(user)){
			comments.put(user, new ArrayList<String>());
		}
		comments.get(user).add(message);
		
	}
	
	public void addRecordedStat(String statName, String stat){
		stats.addCustomStat(statName, stat);
	}
	
	public boolean containsRecordedStat(String name){
		return (stats.getCustomStat(name)!=null);
	}
	
	public String getCustomStat(String name){
		return stats.getCustomStat(name);
	}
	
	public void addRating(Integer rating){
		stats.rate(rating);
	}
	
	public void incrementPlays(){
		stats.incrementPlays();
	}
	
}
