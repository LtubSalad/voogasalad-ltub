package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagingHistory {
	
	private List<Message> myRecievedPosts; 
	private	List<Message> mySentMessages;
	private Map<String, List<Message>> myPrivateMessages;
	
	public MessagingHistory(){
		myRecievedPosts = new ArrayList<Message>();
		mySentMessages = new ArrayList<Message>();
		myPrivateMessages = new HashMap<String, List<Message>>(); 
	}
	
	public void recieve(String sender, String message){
		Message newMessage = new Message(sender, message);
		myRecievedPosts.add(newMessage);
	}

}
