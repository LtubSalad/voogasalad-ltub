package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHistory {
	
	Map<String, String> gameToFile; 
	List<String> authored; 
	List<String> played;
	Map<String, GameHistory> gameToHistory;
	Map<String, GameHistory> authToHistory;
	
	public UserHistory(){
		gameToFile = new HashMap<String, String>();  
		authored = new ArrayList<String>();
		played = new ArrayList<String>(); 
	}
	
		
	
	public Map<String, GameHistory> getPlayedHistory(){
		return gameToHistory; 
	}
	
	public Map<String, GameHistory> getAuthoredHistory(){
		return authToHistory; 
	}

	public void addPlayedGame(String name, String filepath){
		gameToFile.put(name, filepath);
		played.add(name);
		GameHistory history = configGame(name, filepath);
		gameToHistory.put(name, history);
	}
	
	public void addAuthoredGame(String name, String filepath){
		gameToFile.put(name, filepath);
		played.add(name);
		GameHistory authHistory = configAuth(name, filepath);
		authToHistory.put(name, authHistory);
	}

	//TODO: restructure this to rid the ifs 

	public void rateGame(String name, int rating){
		if(authored.contains(name)){
			authToHistory.get(name).addRating(rating);
		}
		if(played.contains(name)){
			gameToHistory.get(name).addRating(rating);
		}
	}
	
	//TODO: restructure this to rid the ifs 

	public void incrementPlays(String name){
		if(authored.contains(name)){
			authToHistory.get(name).incrementPlays(); 
		}
		if(played.contains(name)){
			gameToHistory.get(name).incrementPlays(); 
		}
	}
	
	//TODO: restructure this to rid the ifs 

	public void addComment(String username, String comment){
		if(authored.contains(username)){
			authToHistory.get(username).addComment(username, comment);
		}
		if(played.contains(username)){
			gameToHistory.get(username).addComment(username, comment);
		}
	}
	
	
	private GameHistory configAuth(String name, String filepath) {
		GameHistory GH = new GameHistory(name, filepath);
		return GH; 
	}

	private GameHistory configGame(String name, String filepath) {
		GameHistory GH = new GameHistory(name, filepath);
		GH.addRecordedStat("High Score", Integer.toString(0));
		return GH;
	}
	
	

	
}
