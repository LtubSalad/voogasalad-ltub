package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.BorderPane;
import user.serializabledata.SerializableUserModel;
import user.view.UserSocialPage;

public class UsersModel {

	/**
	 * @author tahiaemran
	 * 
	 * nested class, allows encapsulated sending of messages from one user to the other 
	 * 
	 */
	public class MessagingHandler{
		public void sendMessage(String sender, String message, String reciever){
			System.out.println("entering handler");
			sendAMessage(sender, message, reciever); 
		}
	}
	
	private Map<String, String> userToPass; 
	private Map<String, User> usernameToData; 
	private Map<String, String> userToDirectory; //unsure if necessary
	private List<String> passwordVerify; 
	//TODO: finish this 
	private User currentUser; 
	private UserSocialPage page; 
	// when they save a game, add it to their game history 
	// when they play a game, add it to their game played history 

	public UsersModel(){
		userToPass = new HashMap<String, String>(); 
		usernameToData = new HashMap<String, User>(); 
		userToDirectory = new HashMap<String, String>(); 
		passwordVerify = new ArrayList<String>();
	}

	// TODO: handle loading data
	
	private void sendAMessage(String sender, String message, String username){
		System.out.println("handler working");
		User reciever = usernameToData.get(username);
		reciever.recieveMessage(sender, message);
		
	}

	public void addUser(String username, String password){
		if (!userToPass.containsKey(username)){
			userToPass.put(username, password);
			passwordVerify.add(username+password);
			User user = new User(username, "resources/maple.jpg", new MessagingHandler());
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

	public User getUserByName(String string) {
		return usernameToData.get(string);
	}
	
	public SerializableUserModel getSerializable(){
		return new SerializableUserModel(this);
	}

	public Map<String, String> getUserPasswordData() {
		return userToPass; 
	}

	public Map<String, User> getUserData() {
		return usernameToData; 
	}

	public List<String> getVerificationStructure() {
		return passwordVerify; 
	}
	
	public BorderPane getSocialView(){
		return page; 
	}

}

