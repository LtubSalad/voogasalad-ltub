package gameDevelopmentInterface;

import javafx.scene.control.ScrollPane;

public class ScrollingEnvironmentSetter extends ScrollPane {
	private ScreenMap myScreenMap;
	private static final int SCROLL_SIZE = 450;
	
	public ScrollingEnvironmentSetter(ScreenMap screenMap) {
		myScreenMap = screenMap;
		this.setPrefSize(SCROLL_SIZE, SCROLL_SIZE);
		this.setContent(myScreenMap);
	}
	
	public ScreenMap getScreenMap() {
		return myScreenMap;
	}

}
