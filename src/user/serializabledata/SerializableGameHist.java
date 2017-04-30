package user.serializabledata;

import java.util.List;
import java.util.Map;

import user.GameHistory;
import user.GameStats;

public class SerializableGameHist {
	
	private String name; 
	private String filePath; 
	private GameStats stats;  
	private Map<String, List<String>> comments; 

	public SerializableGameHist(GameHistory gameHistory) {
		this.name = gameHistory.getName();
		this.filePath = gameHistory.getFile();
		this.stats = gameHistory.getStatsForSerialization(); 
		this.comments = gameHistory.getComments();
	}
	
	public String extractName(){
		return name;
	}
	
	public String extractImageFile(){
		return filePath;
	}
	
	public GameStats extractStats(){
		return stats; 
	}
	
	public Map<String, List<String>> extractComments(){
		return comments; 
	}

}
