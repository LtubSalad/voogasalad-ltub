package user.serializabledata;

import user.MessagingHistory;
import user.User;
import user.UserHistory;
import user.UsersModel.MessagingHandler;

public class SerializableUser {
	private String name; 
	private String imageFilePath; 
	private SerializableUserHist history; 
	private SerializableMessageHist messages;
	
	private MessagingHandler handler; 
	
	public SerializableUser(User user){
		this.name = user.getName();
		this.imageFilePath = user.getImage().getClass().getName(); //TODO: will this work
		
		
		this.history = new SerializableUserHist(user.getUserHistory());
		this.messages = new SerializableMessageHist(user.getMessagingHistory());
	}
		
}
