package gameDevelopmentInterface;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ConstructorNameAndEntry extends HBox {
	private Text myName;
	private TextField entry;
	
	public ConstructorNameAndEntry(String name) {
		this.myName = new Text(name);	
		entry = new TextField();
		this.getChildren().addAll(myName, entry);
	}
	
//	public String getName() {
//		return myName.getText();
//	}
	
	public String getEntry() {
		return entry.getText();
	}
	
}
