package user;

import javafx.util.Pair;

public class Comment {
	private String commenter; 
	private String comment; 
	
	public Comment(String person, String message){
		this.commenter = person; 
		this.comment = message; 
	}
	
	public Pair<String, String>getComment(){
		return new Pair<String, String>(commenter, comment);
	}
}
