package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagingHistory {
	
	private String myUserName; 
	
	private List<Message> myRecievedPosts; 
	private	List<Message> mySentMessages;
	private Map<String, List<Message>> myPrivateMessages;
	
	public MessagingHistory(String username){
		this.myUserName = username; 
		myRecievedPosts = new ArrayList<Message>();
		mySentMessages = new ArrayList<Message>();
		myPrivateMessages = new HashMap<String, List<Message>>();  // map of person being messaged by the user + the string of messages 
	}
	
	public void recieve(String sender, String message){
		Message newMessage = new Message(sender, message);
		myRecievedPosts.add(newMessage);
	}
	
	public void addSent(String recipient, String message){
		mySentMessages.add(new Message(recipient, message));
	}
	
	//TODO: fix this, time permitting 
	public void sendPrivateMessage(String other, String message){
		// other = key in the map 
		if (!myPrivateMessages.containsKey(other)){
			myPrivateMessages.put(other, new ArrayList<Message>());
		}
		myPrivateMessages.get(other).add(new Message(other, message));
	}
	
	public void recievePrivateMessage(String sender, String message){
		if (!myPrivateMessages.containsKey(sender)){
			myPrivateMessages.put(sender, new ArrayList<Message>());
		}
		myPrivateMessages.get(sender).add(new Message(sender, message));
	}

}
