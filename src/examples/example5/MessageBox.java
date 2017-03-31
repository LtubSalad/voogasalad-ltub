package examples.example5;

import eventbus.Bus;
import javafx.scene.control.TextField;

public class MessageBox extends TextField {
	
	public MessageBox() {
		super();
		initHandlers();
	}
	
	private void initHandlers() {
		this.setOnAction((e) -> {
			Bus.bus().emit(new UserSendMessageEvent(UserSendMessageEvent.RAW, "haha message"));
		});
	}
	
}
