package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersModel {
	// hashmap of username to password 
	// hashmap of each user to userdata objects 
	// hashmap of each user to file system location 

	private Map<String, String> userToPass; 
	private Map<String, User> usernameToData; 
	private Map<String, String> userToDirectory; //unsure if necessary
	private List<String> passwordVerify; 

	private User currentUser; 
	// public void add user - add their username and password to the hashmap, create them a file system that they add authored games to
	// when they save a game, add it to their game history 
	// when they play a game, add it to their game played history 
	// allow each game to have a rating 

	public UsersModel(){
		userToPass = new HashMap<String, String>(); 
		usernameToData = new HashMap<String, User>(); 
		userToDirectory = new HashMap<String, String>(); 
		passwordVerify = new ArrayList<String>();
	}

	// TODO: handle loading data

	public void addUser(String username, String password){
		if (!userToPass.containsKey(username)){
			userToPass.put(username, password);
			passwordVerify.add(username+password);
			User user = new User(username, "dummy image file name");
			usernameToData.put(username, user);
		}

		// user directory stuff 
	}


	public void verifyUser(String username, String usernameandpassword){
		if (passwordVerify.contains(usernameandpassword)){
			currentUser = usernameToData.get(username); 
			// TODO: connect here to the gameplayer 
		}
	}






}

