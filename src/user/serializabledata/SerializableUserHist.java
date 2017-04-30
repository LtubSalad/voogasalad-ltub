package user.serializabledata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.GameHistory;
import user.UserHistory;

public class SerializableUserHist {
	
	Map<String, String> gameToFile; 
	List<String> authored; 
	List<String> played;
	Map<String, SerializableGameHist> gameToHistory;
	Map<String, SerializableGameHist> authToHistory;

	public SerializableUserHist(UserHistory userHistory) {
		this.gameToFile = userHistory.getFileMap(); 
		this.authored = userHistory.getAuthoredGames();
		this.played = userHistory.getPlayedGames();
		
		gameToHistory = makeSerializable(userHistory.getPlayedHistory());
		authToHistory = makeSerializable(userHistory.getAuthoredHistory());
	}

	private Map<String, SerializableGameHist> makeSerializable(Map<String, GameHistory> history) {
		Map<String, SerializableGameHist> myMap = new HashMap<String, SerializableGameHist>();
		for (String gameName: history.keySet()){
			myMap.put(gameName, new SerializableGameHist(history.get(gameName)));
		}	
		return myMap; 
	}

}
