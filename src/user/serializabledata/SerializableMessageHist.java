package user.serializabledata;

import java.util.List;
import java.util.Map;

import user.Message;
import user.MessagingHistory;

public class SerializableMessageHist {

	private String myUserName; 
	
	private List<Message> myRecievedPosts; 
	private	List<Message> mySentMessages;
	private Map<String, List<Message>> myPrivateMessages;
	
	
	public SerializableMessageHist(MessagingHistory messagingHistory) {
		this.myUserName = messagingHistory.getUsername(); 
		this.myRecievedPosts = messagingHistory.getRecievedPosts(); 
		this.mySentMessages = messagingHistory.getSentMessages();
		this.myPrivateMessages = messagingHistory.getPrivateMessages();
		
	}
	
	//TODO: extraction methods for reloading
	
	public String extractUsername(){
		return myUserName; 
	}
	

}
