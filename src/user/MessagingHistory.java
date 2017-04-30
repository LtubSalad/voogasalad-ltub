package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

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
		System.out.println("message being added to send history for user " + myUserName);
		mySentMessages.add(new Message(recipient, message));
	}
	
	//TODO: fix this, time permitting 
	public void sendPrivateMessage(String other, String message){
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
	
	public Map<String, String> getDisplayableMessages(){
		Map<String, String> dispMessages = new HashMap<String, String>(); 
		myRecievedPosts.stream().forEach(message -> {
			Pair<String,String> m = message.getComment();
			dispMessages.put(m.getKey(), m.getValue());
		});
		return dispMessages; 
	}

	public String getUsername() {
		return myUserName; 
	}

	public List<Message> getRecievedPosts() {
		return myRecievedPosts; 
	}

	public List<Message> getSentMessages() {
		return mySentMessages;
	}

	public Map<String, List<Message>> getPrivateMessages() {
		return myPrivateMessages; 
	}

}
