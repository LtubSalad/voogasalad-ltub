package user.view;

import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import user.User;

public class MessagingView extends VBox{
	private VBox messagesToDisplay; 
	private VBox messageInputBox; 
	
	private User myUser; 
	
	private final String USERNAME_LABEL = "Username: ";
	private final String MESSAGE_LABEL = "Message: ";
	
	public MessagingView(User user){
		myUser = user;
		messagesToDisplay = makeMessageDisplay(myUser.getDisplayableMessages());
		messageInputBox = makeInputBox();
		this.getChildren().addAll(messagesToDisplay, messageInputBox);
	}

	private VBox makeInputBox() {
		VBox inputBox = new VBox(10);
		Label username = new Label(USERNAME_LABEL);
		Label messageName = new Label(MESSAGE_LABEL);
		TextField userField = new TextField(); 
		TextField inputField = new TextField();
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> {
			System.out.println("workinggg");
			String message = inputField.getText(); 
			String sender = userField.getText(); 
			//FIXME: fix connection stuff
			myUser.recieveMessage(sender, message);
		});
		
		HBox usernameField  = new HBox(); 
		usernameField.getChildren().addAll(username, userField);
		HBox messageField = new HBox(); 
		messageField.getChildren().addAll(messageName, inputField);
		inputBox.getChildren().addAll(usernameField, messageField, sendButton);
		
		return inputBox;
		
	}

	private VBox makeMessageDisplay(Map<String, String> displayableMessages) {
		VBox messagesBox = new VBox(5);
		for (String person: displayableMessages.keySet()){
			Text display = new Text(person + ": " +  displayableMessages.get(person));
			TextFlow wrapper = new TextFlow(display);
			wrapper.setTextAlignment(TextAlignment.LEFT);
			messagesBox.getChildren().add(wrapper);
		}
		return messagesBox; 
	}
	
	
	
	
	
}
